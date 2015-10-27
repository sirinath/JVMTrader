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

package com.susico.utils.memory.heap.rc;

import com.susico.utils.memory.pool.PooledObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.misc.Cleaner;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.function.Supplier;

/**
 * Created by sirin_000 on 05/10/2015.
 */
public final class RCObject extends PooledObject {
    private final ReferenceQueue<Object> refQ = new ReferenceQueue<>();
    private volatile int rc = 0;
    private volatile SoftReference<Object> value = null;
    private Cleaner cleaner;

    private RCObject() {
    }

    public static <T> RCObject getInstance(final T value, @NotNull final Runnable releaseAction) {
        RCObject obj = getFromPoolOrSupplierIfAbsent(RCObject.class, RCObject::new);

        obj.set(value, releaseAction);

        return obj;
    }

    public static <T> RCObject getInstance(final Supplier<T> supplier, @NotNull final Runnable releaseAction) {
        return getInstance(supplier.get(), releaseAction);
    }

    public static <T extends AutoCloseable> RCObject getInstance(@NotNull final T value) {
        return getInstance(value, () -> {
            try {
                value.close();
            } catch (Exception e) {
            }
        });
    }

    public static <T extends AutoCloseable> RCObject getInstance(@NotNull final Supplier<T> supplier) {
        return getInstance(supplier.get());
    }

    private final void set(final Object value, @NotNull final Runnable releaseAction) {
        this.rc = 0;
        this.value = new SoftReference<Object>(value, refQ);

        this.cleaner = Cleaner.create(value, releaseAction);
    }

    public final void clean() {
        runThreadSafe(() -> {
            rc = 0;

            cleaner.clean();

            value = null;
        });

        returnToPool();
    }

    public final Object get() {
        final Object value = this.value.get();

        if (value != null) {
            ++rc;
        } else {
            clean();
        }

        return value;
    }

    public final void release() {
        close();
    }

    @Override
    public final void close() {
        if (--rc == 0) {
            runThreadSafe(() -> {
                cleaner.clean();

                value = null;

                returnToPool();
            });
        }
    }
}
