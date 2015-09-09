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

package com.susico.JVMTrader;

import sun.misc.Unsafe;
import uk.co.real_logic.agrona.UnsafeAccess;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by sirin_000 on 03/09/2015.
 */
public class MapManager implements PartitionedBuffer, SlicedBuffer {
    private final static Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final MappedByteBuffer[] partitionedBuffer;
    protected final MappedByteBuffer[] sliceBuffer;

    protected final boolean checked;
    protected final ArrayUtils.ArrayAccess arrayUtils;

    protected final int segments;

    protected final int slices;

    protected final FileChannel fileChannel;

    public MapManager(final boolean checked, final File file, final long size) throws IOException {
        final long aligned = Utils.roundUpTo(size, Consts.BLOCK_SIZE);

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(aligned);
        fileChannel = randomAccessFile.getChannel();

        this.checked = checked;
        this.arrayUtils = ArrayUtils.ArrayAccess.checked(checked);

        segments = segments(aligned);

        partitionedBuffer = new MappedByteBuffer[segments];

        for (int i = 0; i < segments; i++) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, i * PARTITION_SIZE, (i + 1) * PARTITION_SIZE);

            PARTITION_ACCESS.put(i, mappedByteBuffer);
        }

        slices = slices(aligned);

        sliceBuffer = new MappedByteBuffer[slices];

        for (int i = 0; i < slices; i++) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, i * PARTITION_SIZE, (i + 1) * PARTITION_SIZE);

            PARTITION_ACCESS.put(i, mappedByteBuffer);
        }
    }

    public final int getSegments() {
        return segments;
    }

    @Override
    public final int getSlices() {
        return slices;
    }

    public final LongIndexed<MappedByteBuffer> PARTITION_ACCESS = new LongIndexed<MappedByteBuffer>() {
        public final MappedByteBuffer get(long i) {
            return (MappedByteBuffer) arrayUtils.getFromArray(partitionedBuffer, i);
        }

        public final void put(long i, MappedByteBuffer buff) {
            arrayUtils.putFromArray(partitionedBuffer, i, buff);
        }
    };

    public final LongIndexed<MappedByteBuffer> SLICE_ACCESS = new LongIndexed<MappedByteBuffer>() {
        public final MappedByteBuffer get(long i) {
            return (MappedByteBuffer) arrayUtils.getFromArray(sliceBuffer, i);
        }

        public final void put(long i, MappedByteBuffer buff) {
            arrayUtils.putFromArray(sliceBuffer, i, buff);
        }
    };
}
