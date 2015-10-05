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

package com.susico.utils.heap.rc;

import sun.misc.Cleaner;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.function.Supplier;

/**
 * Created by sirin_000 on 05/10/2015.
 */
public final class RCObject implements AutoCloseable {
    public static final int INIT_RC_POOL_SIZE = 100;

    private volatile int rc = 0;
    private volatile SoftReference<Object> value = null;

    private RCObject(final ArrayDeque<RCObject> queue, final Runnable releaseAction) {
        this.queue = queue;
        this.releaseAction = releaseAction;
    }

    // RCObject is not thread local through the pool is
    private static final ThreadLocal<ArrayDeque<RCObject>> pool = new ThreadLocal<ArrayDeque<RCObject>>() {
        @Override
        protected final ArrayDeque<RCObject> initialValue() {
            return new ArrayDeque<RCObject>(INIT_RC_POOL_SIZE);
        }
    };

    private final ArrayDeque<RCObject> queue;

    private volatile Runnable releaseAction;

    private Cleaner cleaner;

    public static <T> RCObject getInstance(final Supplier<T> supplier, final Runnable releaseAction) {
        ArrayDeque<RCObject> queue = pool.get();
        RCObject obj = queue.pollFirst();

        if (obj == null) {
            obj = new RCObject(queue, releaseAction);
        }

        Object value = supplier.get();

        obj.set(value, releaseAction);

        return obj;
    }

    private final void set(final Object value, final Runnable releaseAction) {
        this.rc = 0;
        this.value = new SoftReference<Object>(value);
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

                queue.addLast(this);
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

        queue.addLast(this);
    }
}
