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

package com.susico.utils.memory.pool;

import com.susico.utils.ThreadHelpers;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Created by sirin_000 on 06/10/2015.
 */
public abstract class PooledObject implements AutoCloseable {
    protected static final ConcurrentHashMap<Class<? extends PooledObject>, ThreadLocal<ArrayDeque<PooledObject>>> poolList = new ConcurrentHashMap<>();

    protected final ThreadLocal<ArrayDeque<PooledObject>> pool;

    protected final Thread createdIn;

    protected final ArrayDeque<PooledObject> queue;

    protected PooledObject() {
        this.createdIn = Thread.currentThread();

        ThreadLocal<ArrayDeque<PooledObject>> thePool = poolList.get(this.getClass());

        if (thePool == null) {
            thePool = new ThreadLocal<ArrayDeque<PooledObject>>() {
                @Override
                protected final ArrayDeque<PooledObject> initialValue() {
                    return new ArrayDeque<PooledObject>();
                }
            };

            poolList.put(this.getClass(), thePool);
        }

        pool = thePool;
        queue = pool.get();
    }

    public static <T extends PooledObject> T getFromPoolOrSupplierIfAbsent(Class<T> cls, Supplier<T> supplyNewIfNotInPool) {
        ArrayDeque<PooledObject> pool = getQueueFor(cls);

        PooledObject obj = pool.pollFirst();

        if (obj == null) {
            obj = supplyNewIfNotInPool.get();
        }

        return (T) obj;
    }

    protected static ArrayDeque<PooledObject> getQueueFor(Class<?> cls) {
        return poolList.get(cls).get();
    }

    @Override
    public void close() {
        returnToPool();
    }

    protected final void returnToPool() {
        runThreadSafe(() -> queue.addLast(this));
    }

    public final boolean isThreadSafe() {
        return ThreadHelpers.isThreadSafe(createdIn);
    }

    protected final void runThreadSafe(Runnable code) {
        ThreadHelpers.runThreadSafeSynchronized(this, code, createdIn);
    }
}
