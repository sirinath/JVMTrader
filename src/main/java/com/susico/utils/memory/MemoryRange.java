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
public final class MemoryRange extends PooledObject {
    protected long address;
    protected long size;
    protected long end;

    protected MemoryRange() {}

    public static MemoryRange getInstance(final long address, final long size) {
        return getFromPoolOrSupplierIfAbsent(MemoryRange.class, MemoryRange::new);
    }

    public void set(final long address, final long size) {
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

    @Override
    public int hashCode() {
        final long tmp = end ^ address;
        return (int)((tmp >>> Integer.SIZE) ^ tmp);
    }

    @Override
    public boolean equals(final @NotNull Object obj) {
        return obj instanceof MemoryRange && ((MemoryRange) obj).getAddress() == address && ((MemoryRange) obj).getSize() == size;
    }
}
