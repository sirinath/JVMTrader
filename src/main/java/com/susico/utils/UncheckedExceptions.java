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
 * Created by sirin_000 on 03/10/2015.
 */
public class UncheckedExceptions {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static void rethrow(final Throwable throwable) {
        UncheckedExceptions.<RuntimeException>rethrowUnchecked(throwable);
    }

    private static <T extends Throwable> void rethrowUnchecked(final Throwable throwable) throws T {
        throw (T) throwable;
    }

    public static void rethrowUnsafe(final Throwable throwable) {
        UNSAFE.throwException(throwable);
    }
}
