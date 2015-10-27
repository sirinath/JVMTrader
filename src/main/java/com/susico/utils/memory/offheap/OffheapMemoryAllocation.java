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

/**
 * Created by sirin_000 on 06/10/2015.
 */
public final class OffheapMemoryAllocation extends MemoryRange {
    protected Cleaner cleaner = null;

    protected OffheapMemoryAllocation() {}

    public static OffheapMemoryAllocation getInstance(final long size) {
        OffheapMemoryAllocation ma = getFromPoolOrSupplierIfAbsent(OffheapMemoryAllocation.class, OffheapMemoryAllocation::new);

        final long address = UnsafeAccess.UNSAFE.allocateMemory(size);
        ma.set(address, size);

        ma.setCleaner(Cleaner.create(ma, () -> UnsafeAccess.UNSAFE.freeMemory(address)));

        return ma;
    }

    protected final void setCleaner(Cleaner cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public final void close() {
        try {
            cleaner.clean();

            cleaner = null;
        } finally {
            super.close();
        }
    }
}
