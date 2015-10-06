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
import sun.misc.Cleaner;
import sun.reflect.Reflection;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.function.Supplier;

/**
 * Created by sirin_000 on 05/10/2015.
 */
public final class RCObject extends PooledObject {
    private volatile int rc = 0;

    private final ReferenceQueue<Object> refQ = new ReferenceQueue<>();
    private volatile SoftReference<Object> value = null;

    private RCObject() {}

    private volatile Runnable releaseAction;

    private Cleaner cleaner;

    public static <T> RCObject getInstance(final Supplier<T> supplier, final Runnable releaseAction) {
        ArrayDeque<PooledObject> pool = getQueueFor(RCObject.class);

        PooledObject obj = pool.pollFirst();

        if (obj == null) {
            obj = new RCObject();
        }

        Object value = supplier.get();

        ((RCObject) obj).set(value, releaseAction);

        return (RCObject) obj;
    }

    private final void set(final Object value, final Runnable releaseAction) {
        this.rc = 0;
        this.value = new SoftReference<Object>(value, refQ);
        this.releaseAction = releaseAction;

        this.cleaner = Cleaner.create(value, () -> clean());
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

    @Override
    public final void close() {
        if (--rc == 0) {
            synchronized (this) {
                if (releaseAction != null) {
                    try {
                        releaseAction.run();
                    } catch (Throwable t) {
                    } finally {
                        releaseAction = null;
                    }
                }

                value = null;

                returnToPool();
            }
        }
    }

    public final void release() {
        close();
    }

    public synchronized final void clean() {
        rc = 0;

        if (releaseAction != null) {
            try {
                releaseAction.run();
            } catch (Throwable t) {
            } finally {
                releaseAction = null;
            }
        }

        value = null;

        returnToPool();
    }
}
