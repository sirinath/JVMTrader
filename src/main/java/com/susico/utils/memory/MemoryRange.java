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

package com.susico.utils.memory;

import com.susico.utils.memory.pool.PooledObject;
import org.jetbrains.annotations.NotNull;

/**
 * Created by sirin_000 on 06/10/2015.
 */
public class MemoryRange extends PooledObject {
    protected long address;
    protected long size;
    protected long end;

    protected MemoryRange() {}

    public static MemoryRange getInstance(final long address, final long size) {
        MemoryRange mr = getFromPoolOrSupplierIfAbsent(MemoryRange.class, MemoryRange::new);
        mr.set(address, size);

        return mr;
    }

    public final void set(final long address, final long size) {
        this.address = address;
        this.size = size;
        this.end = address + size;
    }

    public final long getAddress() {
        return this.address;
    }

    public final long getEnd() {
        return this.end;
    }

    public final long getSize() {
        return this.size;
    }

    public final boolean inRange(final long address) {
        return address >= this.address && address < this.end;
    }

    public final boolean inRange(final long address, final long offset) {
        return inRange(address + offset);
    }

    @Override
    public final int hashCode() {
        final long tmp = end ^ address;
        return (int)((tmp >>> Integer.SIZE) ^ tmp);
    }

    @Override
    public final boolean equals(final @NotNull Object obj) {
        return obj instanceof MemoryRange && ((MemoryRange) obj).getAddress() == address && ((MemoryRange) obj).getSize() == size;
    }
}
