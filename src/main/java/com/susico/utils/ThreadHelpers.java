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

package com.susico.utils;


import sun.misc.Unsafe;

/**
 * Created by sirin_000 on 07/10/2015.
 */
public class ThreadHelpers {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static final boolean isThreadSafe(final Thread safeThread) {
        return Thread.currentThread().equals(safeThread);
    }

    public static void runThreadSafeSynchronized(final Object synchronizationTarget, final Runnable codeToRun, final Thread safeThread) {
        runThreadSafeSynchronized(synchronizationTarget, codeToRun, isThreadSafe(safeThread));
    }

    public static void runThreadSafeSynchronized(final Object synchronizationTarget, final Runnable codeToRun, final boolean singleThreadedOrThreadSafe) {
        if (singleThreadedOrThreadSafe)
            codeToRun.run();
        else
            synchronized (synchronizationTarget) {
                codeToRun.run();
            }
    }

    public static void runThreadSafeSynchronized(final Runnable codeToRun, final boolean singleThreadedOrThreadSafe) {
        runThreadSafeSynchronized(codeToRun, codeToRun, singleThreadedOrThreadSafe);
    }

    public static void runThreadSafeSynchronized(final Runnable codeToRun, final Thread safeThread) {
        runThreadSafeSynchronized(codeToRun, codeToRun, safeThread);
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

    public static void runThreadSafeFenced(final Runnable codeToRun, final boolean singleThreadedOrThreadSafe) {
        if (singleThreadedOrThreadSafe)
            codeToRun.run();
        else {
            UNSAFE.fullFence();

            codeToRun.run();

            UNSAFE.fullFence();
        }
    }

    public static void runThreadSafeFenced(final Runnable codeToRun, final Thread safeThread) {
        runThreadSafeFenced(codeToRun, isThreadSafe(safeThread));
    }
}
