/*
 * Copyright (c) 2015. Suminda Sirinath Salpitikorala Dharmasena
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.susico.utils.memory.offheap;

import com.susico.utils.io.IOUtils;
import uk.co.real_logic.agrona.collections.Long2ObjectHashMap;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by sirin_000 on 17/09/2015.
 */
public  final class SharedMappedBuffer implements SharedBuffer<MappedByteBuffer> {
    private static final class RefCounts { // Not thread safe!!! Do not use outside. Hence private.
        private MappedByteBuffer mappedByteBuffer;
        private int refCount = 0;
        private boolean save;

        public static final int POOL_SIZE = 1000;
        private static final ArrayDeque<RefCounts> pool = new ArrayDeque<>(POOL_SIZE);

        private RefCounts() {
            pool.addLast(this);
        }

        public static RefCounts getInstance() {
            RefCounts obj = pool.removeFirst();
            return obj == null ? new RefCounts() : obj;
        }

        protected static void returnToPool(RefCounts refCounts) {
            pool.addLast(refCounts);
        }

        public final RefCounts initTo(final MappedByteBuffer mappedByteBuffer) {
            this.mappedByteBuffer = mappedByteBuffer;
            refCount = 1;

            return this;
        }

        public final boolean isSave() {
            return save;
        }

        public final RefCounts setSave(final boolean save) {
            this.save = save;

            return this;
        }

        public final MappedByteBuffer release() {
            try {
                return mappedByteBuffer;
            } finally {
                mappedByteBuffer = null;
                refCount = 0;
                save = true;
                returnToPool(this);
            }
        }

        public final MappedByteBuffer reference() {
            ++refCount;

            return mappedByteBuffer;
        }

        public final int dereference() {
            int refs = --refCount;

            if(refs == 0) {
                try {
                    if (save)
                        IOUtils.saveAndUnmap(mappedByteBuffer);
                    else
                        IOUtils.discardAndUnmap(mappedByteBuffer);
                } finally {
                    release();
                }
            }

            return refs;
        }
    }

    protected final SharedMappedResource sharedMappedResource;

    protected final boolean save;

    private final Long2ObjectHashMap<Long2ObjectHashMap<RefCounts>> bufferMapping = new Long2ObjectHashMap<>();

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource, final boolean save) {
        this.sharedMappedResource = sharedMappedResource;
        this.save = save;
    }

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource) {
        this(sharedMappedResource, true);
    }

    private final AtomicBoolean guard = new AtomicBoolean(false);

    @Override
    public final MappedByteBuffer map(final long position, final long size) {
        Long2ObjectHashMap<RefCounts> positionMap = bufferMapping.get(position);

        if (positionMap == null) {
            try {
                while (!guard.compareAndSet(false, true))
                    LockSupport.parkNanos(1);

                positionMap = new Long2ObjectHashMap<>();

                MappedByteBuffer mappedByteBuffer = sharedMappedResource.map(position, size);

                RefCounts refCounts = RefCounts.getInstance().initTo(mappedByteBuffer).setSave(save);

                positionMap.put(size, refCounts);

                bufferMapping.put(position, positionMap);

                return mappedByteBuffer;
            } finally {
                guard.set(false);
            }
        }

        RefCounts refCounts = positionMap.get(size);

        if (refCounts == null) {
            try {
                while (!guard.compareAndSet(false, true))
                    LockSupport.parkNanos(1);

                MappedByteBuffer mappedByteBuffer = sharedMappedResource.map(position, size);

                refCounts = RefCounts.getInstance().initTo(mappedByteBuffer).setSave(save);

                positionMap.put(size, refCounts);

                return mappedByteBuffer;
            } finally {
                guard.set(false);
            }
        }

        return refCounts.reference();
    }

    @Override
    public final boolean unmapIfRCZero(final long position, final long size) {
        Long2ObjectHashMap<RefCounts> positionMap = bufferMapping.get(position);

        if (positionMap == null)
            return false;

        RefCounts refCounts = positionMap.get(size);

        if (refCounts == null)
            return false;

        int refs = -1;
        try {
            while (!guard.compareAndSet(false, true))
                LockSupport.parkNanos(1);

            refs = refCounts.dereference();
        } catch (Throwable t) {
            return false;
        } finally {
            try {
                if (refs == 0)
                    positionMap.remove(size);
            } finally {
                guard.set(false);
            }
        }

        return true;
    }


    @Override
    public final boolean forceUnmap(final long position, final long size) {
        Long2ObjectHashMap<RefCounts> positionMap = bufferMapping.get(position);

        if (positionMap == null)
            return false;

        try {
            while (!guard.compareAndSet(false, true))
                LockSupport.parkNanos(1);

            RefCounts refCounts = positionMap.remove(size);

            if (refCounts == null)
                return false;

            MappedByteBuffer mappedByteBuffer = refCounts.release();

            if (save)
                IOUtils.saveAndUnmap(mappedByteBuffer);
            else
                IOUtils.discardAndUnmap(mappedByteBuffer);
        } catch (Throwable t) {
            return false;
        } finally {
            guard.set(false);
        }

        return true;
    }


    @Override
    public final void close() throws IOException {
        sharedMappedResource.close();
    }
}
