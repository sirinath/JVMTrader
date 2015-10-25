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

package com.susico.utils.threading;


import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by sirin_000 on 07/10/2015.
 */
public class ThreadHelpers {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static final boolean isInSafeThread(final Thread safeThread) {
        return Thread.currentThread().equals(safeThread);
    }

    public static final class ThreadLocks extends WeakHashMap<Object, AtomicBoolean> {
        @Override
        public final AtomicBoolean get(final Object key) {
            AtomicBoolean value = super.get(key);

            if (value == null) {
                value = new AtomicBoolean(false);
                put(Thread.currentThread(), value);
            }

            return value;
        }
    }

    protected static final ThreadLocks threadLocks = new ThreadLocks();

    public static void runSynchronizedWithGuard(final long guardFieldOffset, Object obj, final Runnable codeToRun) {
        try {
            while (UNSAFE.getBooleanVolatile(obj, guardFieldOffset))
                LockSupport.parkNanos(1);

            UNSAFE.putBooleanVolatile(obj, guardFieldOffset, true);

            codeToRun.run();
        } finally {
            UNSAFE.putBooleanVolatile(obj, guardFieldOffset, false);
        }
    }

    public static void runSynchronizedWithGuard(final long guardFieldOffset, Object obj, final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        if (singleThreadedOrThreadSafe)
            codeToRun.run();
        else
            runSynchronizedWithGuard(guardFieldOffset, obj, codeToRun);
    }

    public static void runSynchronizedWithGuard(final long guardFieldOffset, Object obj, final Thread safeThread, final Runnable codeToRun) {
        runSynchronizedWithGuard(guardFieldOffset, obj, isInSafeThread(safeThread), codeToRun);
    }

    public static void runSynchronizedWithGuard(final AtomicBoolean guard, final Runnable codeToRun) {
        try {
            while (guard.get())
                LockSupport.parkNanos(1);

            guard.set(true);

            codeToRun.run();
        } finally {
            guard.set(false);
        }
    }

    public static void runSynchronizedWithGuard(final AtomicBoolean guard, final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        if (singleThreadedOrThreadSafe)
            codeToRun.run();
        else
            runSynchronizedWithGuard(guard, codeToRun);
    }

    public static void runSynchronizedWithGuard(final AtomicBoolean guard, final Thread safeThread, final Runnable codeToRun) {
        runSynchronizedWithGuard(guard, isInSafeThread(safeThread), codeToRun);
    }

    public static void runSynchronizedWithGuard(final Object synchronizationTarget, final Runnable codeToRun) {
        runSynchronizedWithGuard(threadLocks.get(synchronizationTarget), codeToRun);
    }

    public static void runSynchronizedWithGuard(final Object synchronizationTarget, final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        runSynchronizedWithGuard(threadLocks.get(synchronizationTarget), singleThreadedOrThreadSafe, codeToRun);
    }

    public static void runSynchronizedWithGuard(final Object synchronizationTarget, final Thread safeThread, final Runnable codeToRun) {
        runSynchronizedWithGuard(threadLocks.get(synchronizationTarget), isInSafeThread(safeThread), codeToRun);
    }

    @CallerSensitive
    public static void runSynchronizedWithGuard(final Runnable codeToRun) {
        runSynchronizedWithGuard(Reflection.getCallerClass(), codeToRun);
    }

    @CallerSensitive
    public static void runSynchronizedWithGuard(final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        runSynchronizedWithGuard(Reflection.getCallerClass(), singleThreadedOrThreadSafe, codeToRun);
    }

    @CallerSensitive
    public static void runSynchronizedWithGuard(final Thread safeThread, final Runnable codeToRun) {
        runSynchronizedWithGuard(Reflection.getCallerClass(), isInSafeThread(safeThread), codeToRun);
    }


    public static void runThreadSafeSynchronized(final Object synchronizationTarget, final Runnable codeToRun) {
        synchronized (synchronizationTarget) {
            codeToRun.run();
        }
    }

    public static void runThreadSafeSynchronized(final Runnable codeToRun) {
        runThreadSafeSynchronized(codeToRun, codeToRun);
    }

    public static void runThreadSafeSynchronized(final Object synchronizationTarget, final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        if (singleThreadedOrThreadSafe) {
            codeToRun.run();
        } else {
            runThreadSafeSynchronized(synchronizationTarget, codeToRun);
        }
    }

    public static void runThreadSafeSynchronized(final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        runThreadSafeSynchronized(codeToRun, singleThreadedOrThreadSafe, codeToRun);
    }

    public static void runThreadSafeSynchronized(final Object synchronizationTarget, final Thread safeThread, final Runnable codeToRun) {
        runThreadSafeSynchronized(synchronizationTarget, isInSafeThread(safeThread), codeToRun);
    }

    public static void runThreadSafeSynchronized(final Thread safeThread, final Runnable codeToRun) {
        runThreadSafeSynchronized(codeToRun, safeThread, codeToRun);
    }

    public static void fullFence() {
        UNSAFE.fullFence();
    }

    public static void loadFence() {
        UNSAFE.loadFence();
    }

    public static void storeFence() {
        UNSAFE.storeFence();
    }

    public static void runThreadSafeFenced(final Runnable codeToRun) {
        UNSAFE.fullFence();

        codeToRun.run();

        UNSAFE.fullFence();
    }

    public static void runThreadSafeFenced(final boolean singleThreadedOrThreadSafe, final Runnable codeToRun) {
        if (singleThreadedOrThreadSafe)
            codeToRun.run();
        else {
            runThreadSafeFenced(codeToRun);
        }
    }

    public static void runThreadSafeFenced(final Thread safeThread, final Runnable codeToRun) {
        runThreadSafeFenced(isInSafeThread(safeThread), codeToRun);
    }
}
