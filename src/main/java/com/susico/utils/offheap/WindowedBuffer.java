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

import java.nio.MappedByteBuffer;

/**
 * Created by sirin_000 on 03/09/2015.
 */
public final class WindowedBuffer {
    private final SharedMappedBuffer sharedMappedBuffer;
    private final long start;
    private final long stride;
    private final long size;

    private final boolean pow2;
    private final int shift;

    public WindowedBuffer(final SharedMappedBuffer sharedMappedBuffer, final long start, final int stride, final int size) {
        if (stride <= 0) {
            if (size <= 0)
                throw new IllegalArgumentException("size and stride should be greater than 0");

            throw new IllegalArgumentException("stride should be greater than 0");
        } else if (size <= 0)
            throw new IllegalArgumentException("size should be greater than 0");


        this.sharedMappedBuffer = sharedMappedBuffer;
        this.start = start;
        this.stride = stride;
        this.size = size;

        this.pow2 = (stride & (stride - 1)) == 0;
        this.shift = Long.SIZE - Long.numberOfLeadingZeros(stride) - 1;
    }

    public final MappedByteBuffer window(final long index) {
        if (pow2)
            return sharedMappedBuffer.map(index << shift, size);
        else
            return sharedMappedBuffer.map(index * stride, size);
    }
}

