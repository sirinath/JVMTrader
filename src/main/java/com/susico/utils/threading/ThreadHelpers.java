/*
 * Microtrader is available under either the terms of the Apache License, Version 2.0 (ASF 2.0)
 * the Academic Free License Version 3.0, (AFL 3.0) or MIT License (MIT). As a recipient of
 * Microtrader, you may choose which license to receive this code or content under
 * (except as noted in per-module LICENSE files). Some modules may not be the copyright
 * of the Suminda Sirinath Salpitikorala Dharmasena and Project Contributors.
 * These modules contain explicit declarations of copyright in both the LICENSE files
 * in the directories in which they reside and in the code or content itself.
 *
 * No external contributions are allowed under licenses which are fundamentally
 * incompatible with the ASL 2.0, AFL 3.0 and MIT that Microtrader is distributed under.
 * By contributing to this project by means of including but not limited to patches,
 * pull requests, code submissions, issues, bug report, code snippets, discussions,
 * email message, chat messages such content will be licensed under the terms of
 * ASL 2.0, AFL 3.0 and MIT where the recipients are free to choose under which license
 * code or content is received under.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
