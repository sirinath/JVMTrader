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

package com.susico.utils.offheap;

import com.susico.utils.Utils;
import uk.co.real_logic.agrona.collections.Long2ObjectHashMap;

import java.io.Closeable;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.sql.Ref;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by sirin_000 on 17/09/2015.
 */
public class SharedMappedBuffer implements Closeable {
    private static final class RefCounts { // Not thread safe!!!
        private MappedByteBuffer mappedByteBuffer;
        private int refCount = 0;
        private boolean save = true;

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

        public final int increment() {
            return ++refCount;
        }

        public final int decrement() {
            int refs = --refCount;

            if(refs == 0) {
                try {
                    if (save)
                        Utils.IOUtils.saveAndUnmap(mappedByteBuffer);
                    else
                        Utils.IOUtils.discardAndUnmap(mappedByteBuffer);
                } finally {
                    mappedByteBuffer = null;
                    refs = 0;
                    save = true;
                    returnToPool(this);
                }
            }

            return refs;
        }

        public MappedByteBuffer getMappedByteBuffer() {
            return mappedByteBuffer;
        }

        public RefCounts setMappedByteBuffer(final MappedByteBuffer mappedByteBuffer) {
            this.mappedByteBuffer = mappedByteBuffer;
            refCount = 1;

            return this;
        }

        public boolean isSave() {
            return save;
        }

        public RefCounts setSave(final boolean save) {
            this.save = save;

            return this;
        }
    }



    protected final SharedMappedResource sharedMappedResource;

    private final Long2ObjectHashMap<Long2ObjectHashMap<RefCounts>> bufferMapping = new Long2ObjectHashMap<>();

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource) {
        this.sharedMappedResource = sharedMappedResource;
    }

    private final AtomicBoolean guard = new AtomicBoolean(false);

    public final MappedByteBuffer map(final long position, final long size) {
        Long2ObjectHashMap<RefCounts> positionMap = bufferMapping.get(position);

        if (positionMap == null) {
            try {
                while (!guard.compareAndSet(false, true))
                    LockSupport.parkNanos(1);

                positionMap = new Long2ObjectHashMap<>();

                MappedByteBuffer mappedByteBuffer = sharedMappedResource.map(position, size);

                RefCounts refCounts = RefCounts.getInstance().setMappedByteBuffer(mappedByteBuffer);

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

                refCounts = RefCounts.getInstance().setMappedByteBuffer(mappedByteBuffer);

                positionMap.put(size, refCounts);

                return mappedByteBuffer;
            } finally {
                guard.set(false);
            }
        }

        return refCounts.getMappedByteBuffer();
    }

    public final boolean release(final long position, final long size, final boolean save) {
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

            refCounts.setSave(save);

            refs = refCounts.decrement();
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

    public final boolean release(final long position, final long size) {
        return release(position, size, true);
    }

    public final boolean unmap(final long position, final long size, final boolean save) {
        Long2ObjectHashMap<RefCounts> positionMap = bufferMapping.get(position);

        if (positionMap == null)
            return false;

        try {
            while (!guard.compareAndSet(false, true))
                LockSupport.parkNanos(1);

            RefCounts refCounts = positionMap.remove(size);

            if (refCounts == null)
                return false;

            MappedByteBuffer mappedByteBuffer = refCounts.getMappedByteBuffer();

            if (save)
                Utils.IOUtils.saveAndUnmap(mappedByteBuffer);
            else
                Utils.IOUtils.discardAndUnmap(mappedByteBuffer);
        } catch (Throwable t) {
            return false;
        } finally {
            guard.set(false);
        }

        return true;
    }

    public final boolean unmap(final long position, final long size) {
        return unmap(position, size, true);
    }


    @Override
    public final void close() throws IOException {
        sharedMappedResource.close();
    }
}
