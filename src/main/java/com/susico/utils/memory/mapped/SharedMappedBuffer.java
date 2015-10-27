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

package com.susico.utils.memory.mapped;

import com.susico.utils.io.IOUtils;
import com.susico.utils.memory.MemoryRange;
import com.susico.utils.memory.SharedBuffer;
import com.susico.utils.memory.heap.rc.RCObject;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sirin_000 on 17/09/2015.
 */
public final class SharedMappedBuffer implements SharedBuffer<MappedByteBuffer> {
    protected final SharedMappedResource sharedMappedResource;
    protected final boolean save;
    private final ConcurrentHashMap<MemoryRange, RCObject> bufferMapping = new ConcurrentHashMap<>();

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource) {
        this(sharedMappedResource, true);
    }

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource, final boolean save) {
        this.sharedMappedResource = sharedMappedResource;
        this.save = save;
    }

    @Override
    public final MappedByteBuffer map(final MemoryRange memoryRange) {
        RCObject rcObject = bufferMapping.get(memoryRange);

        if (rcObject == null) {
            synchronized (this) {
                final MappedByteBuffer mapping = sharedMappedResource.map(memoryRange);
                rcObject = RCObject.getInstance(mapping, () -> {
                    if (save)
                        IOUtils.saveAndUnmap(mapping);
                    else
                        IOUtils.discardAndUnmap(mapping);
                });
                bufferMapping.put(memoryRange, rcObject);
            }
        }

        return (MappedByteBuffer) rcObject.get();
    }

    @Override
    public final boolean unmap(final MemoryRange memoryRange) {
        RCObject rcObject = bufferMapping.get(memoryRange);

        if (rcObject != null) {
            rcObject.release();

            return true;
        }

        return false;
    }


    @Override
    public final void close() throws IOException {
        sharedMappedResource.close();
    }
}
