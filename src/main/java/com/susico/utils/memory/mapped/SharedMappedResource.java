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

import com.susico.utils.memory.MemoryRange;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sirin_000 on 09/09/2015.
 */
public final class SharedMappedResource implements Closeable {
    private static final ConcurrentHashMap<File, SharedMappedResource> items = new ConcurrentHashMap<>();

    protected final File theFile;
    protected final FileChannel fileChannel;
    protected final MapMode mapMode;

    private SharedMappedResource(final File file) {
        try {
            theFile = file;

            boolean rw = file.canWrite();
            mapMode = rw ? MapMode.READ_WRITE : MapMode.READ_ONLY;

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, rw ? "rw" : "r");

            fileChannel = randomAccessFile.getChannel();
        } catch (IOException e) {
            throw new IllegalStateException("Error during IO operation", e);
        }
    }

    public static SharedMappedResource getInstance(final File file) {
        try {
            File theFile = file.getCanonicalFile();

            SharedMappedResource item = items.get(theFile);

            if (item == null) {
                item = new SharedMappedResource(file);
                items.put(file, item);
            }

            return item;
        } catch (IOException e) {
            throw new IllegalStateException("Error during IO operation", e);
        }
    }

    public final MappedByteBuffer map(final long position, final long size) {
        try {
            return fileChannel.map(mapMode, position, size);
        } catch (IOException e) {
            throw new IllegalStateException("Error during IO operation", e);
        }
    }

    public final MappedByteBuffer map(final MemoryRange memoryRange) {
        return map(memoryRange.getAddress(), memoryRange.getSize());
    }

    @Override
    public final void close() throws IOException {
        fileChannel.close();
    }
}
