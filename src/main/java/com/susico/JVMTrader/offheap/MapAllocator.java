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

package com.susico.JVMTrader.offheap;

import java.nio.MappedByteBuffer;

/**
 * Created by sirin_000 on 10/09/2015.
 */
public class MapAllocator {
    public final static long INVALID_OFFSET = -1L;

    protected final long LBPosition;
    protected final long increment;
    protected final long size;

    protected final SharedMappedResource sharedMappedResource;

    protected final long total;

    private final MappedByteBuffer[] mappedByteBuffers;

    protected final boolean checked;
    protected final JVMTraderUtils.ArrayUtils.ArrayAccess arrayUtils;

    protected final LongIndexed<MappedByteBuffer> PAGE_ACCESS = new LongIndexed<MappedByteBuffer>() {
        public final MappedByteBuffer get(long i) {
            return (MappedByteBuffer) arrayUtils.getFromArray(mappedByteBuffers, i);
        }

        public final void put(long i, MappedByteBuffer buff) {
            arrayUtils.putFromArray(mappedByteBuffers, i, buff);
        }
    };

    public MapAllocator(final boolean checked, final int slots, final long startPosition, final long increment, final long size, final SharedMappedResource sharedMappedResource) {
        this.LBPosition = startPosition;
        this.increment = increment;
        this.size = size;
        this.sharedMappedResource = sharedMappedResource;

        total = slots * size;

        mappedByteBuffers = new MappedByteBuffer[slots];

        this.checked = checked;
        this.arrayUtils = JVMTraderUtils.ArrayUtils.ArrayAccess.checked(checked);

        for (long i = 0, poss = startPosition; i < slots; i++, poss += increment) {
            MappedByteBuffer mappedByteBuffer = sharedMappedResource.map(poss, size);

            PAGE_ACCESS.put(i, mappedByteBuffer);
        }
    }

    public final MappedByteBuffer getSegment(final long segment) {
        return PAGE_ACCESS.get(segment);
    }

    public final long segment(final long i) {
        return i / increment;
    }

    public final long offset(final long i) {
        final long offset = i % increment;

        if (checked && offset > size) {
            return INVALID_OFFSET;
        }

        return offset;
    }
}
