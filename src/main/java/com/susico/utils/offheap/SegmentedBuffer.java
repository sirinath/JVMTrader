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
public final class SegmentedBuffer {
    private final int bitCountOfSegmentSize;
    private final int bitCountOfSegmentStart;

    private final long bitMaskOfSegmentSize;
    private final long bitMaskOfSegmentStart;

    private final boolean overlapped;

    private final SharedMappedBuffer sharedMappedBuffer;

    public final long segmentPositionBySegmentStart(long index) {
        return index * bitMaskOfSegmentStart;
    }

    public final long segmentPositionBySegmentSize(long index) {
        return index * bitMaskOfSegmentSize;
    }

    public final long segmentPositionBySegmentStart(long index, long offset) {
        return segmentPositionBySegmentStart(index) + offset;
    }

    public final long segmentPositionBySegmentSize(long index, long offset) {
        return segmentPositionBySegmentSize(index) + offset;
    }

    public final long segmentIndexBySegmentStart(long index) {
        return index >>> bitCountOfSegmentStart;
    }

    public final long segmentIndexBySegmentSize(long index) {
        return index >>> bitCountOfSegmentSize;
    }

    public final long segmentOffsetBySegmentStart(long index) {
        return index & bitMaskOfSegmentStart;
    }

    public final long segmentOffsetBySegmentSize(long index) {
        return index & bitMaskOfSegmentSize;
    }

    public final long segmentSize() {
        return bitMaskOfSegmentSize;
    }

    public SegmentedBuffer(final SharedMappedBuffer sharedMappedBuffer, final int bitCountOfSegmentSize, final boolean overlapped) {
        if (bitCountOfSegmentSize <= 0 || bitCountOfSegmentSize > Integer.SIZE)
            throw new IllegalArgumentException(String.format("bitCountOfSegmentSize cannot be larger than %d but got %d", Integer.SIZE, bitCountOfSegmentSize));

        this.bitCountOfSegmentSize = bitCountOfSegmentSize;
        this.bitCountOfSegmentStart = overlapped ? bitCountOfSegmentSize - 1 : bitCountOfSegmentSize;

        this.overlapped = overlapped;

        this.bitMaskOfSegmentSize = -1L >>> (Long.SIZE - bitCountOfSegmentSize);
        this.bitMaskOfSegmentStart = -1L >>> (Long.SIZE - bitCountOfSegmentStart);

        this.sharedMappedBuffer = sharedMappedBuffer;
    }

    public final MappedByteBuffer segment(final long index) {
        return sharedMappedBuffer.map(segmentPositionBySegmentStart(index), segmentSize());
    }

    public static long numberToBitCountOfSegmentSize(long i) {
        return Long.SIZE - Long.numberOfLeadingZeros(i);
    }
}

