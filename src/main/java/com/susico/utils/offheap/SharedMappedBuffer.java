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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by sirin_000 on 17/09/2015.
 */
public class SharedMappedBuffer implements Closeable {
    protected final SharedMappedResource sharedMappedResource;

    protected final Long2ObjectHashMap<Long2ObjectHashMap<MappedByteBuffer>> bufferMapping = new Long2ObjectHashMap<>();

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource) {
        this.sharedMappedResource = sharedMappedResource;
    }

    private final AtomicBoolean guard = new AtomicBoolean(false);

    public final MappedByteBuffer map(final long position, final long size) {
        Long2ObjectHashMap<MappedByteBuffer> positionMap = bufferMapping.get(position);

        if (positionMap == null) {
            try {
                while (!guard.compareAndSet(false, true))
                    LockSupport.parkNanos(1);

                positionMap = new Long2ObjectHashMap<>();

                MappedByteBuffer mappedByteBuffer = sharedMappedResource.map(position, size);

                positionMap.put(size, mappedByteBuffer);

                bufferMapping.put(position, positionMap);

                return mappedByteBuffer;
            } finally {
                guard.set(false);
            }
        }

        MappedByteBuffer mappedByteBuffer = positionMap.get(size);

        if (mappedByteBuffer == null) {
            try {
                while (!guard.compareAndSet(false, true))
                    LockSupport.parkNanos(1);

                mappedByteBuffer = sharedMappedResource.map(position, size);

                positionMap.put(size, mappedByteBuffer);

                return mappedByteBuffer;
            } finally {
                guard.set(false);
            }
        }

        return mappedByteBuffer;
    }

    public final boolean unmap(final long position, final long size, final boolean save) {
        Long2ObjectHashMap<MappedByteBuffer> positionMap = bufferMapping.get(position);

        if (positionMap == null)
            return false;

        MappedByteBuffer mappedByteBuffer = positionMap.get(size);

        if (mappedByteBuffer == null)
            return false;

        try {
            if (save)
                Utils.IOUtils.saveAndUnmap(mappedByteBuffer);
            else
                Utils.IOUtils.discardAndUnmap(mappedByteBuffer);
        } catch (Throwable t) {
            return false;
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
