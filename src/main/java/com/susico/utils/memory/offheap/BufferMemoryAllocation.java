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

import com.susico.utils.UnsafeAccess;
import com.susico.utils.memory.MemoryRange;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by sirin_000 on 06/10/2015.
 */
public final class BufferMemoryAllocation extends MemoryRange {
    protected ByteBuffer buffer = null;

    protected BufferMemoryAllocation() {}

    public static BufferMemoryAllocation getInstance(final int size) {
        BufferMemoryAllocation ma = getFromPoolOrSupplierIfAbsent(BufferMemoryAllocation.class, BufferMemoryAllocation::new);

        ByteBuffer buffer = ByteBuffer.allocateDirect(size);

        final long address = ((DirectBuffer) buffer).address();

        ma.set(address, size);
        ma.setBuffer(buffer);

        return ma;
    }

    protected void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public final void close() {
        try {
            ((DirectBuffer) buffer).cleaner().clean();

            buffer = null;
        } finally {
            super.close();
        }
    }
}
