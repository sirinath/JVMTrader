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

package com.susico.utils.bits;

import com.susico.utils.UnsafeAccess;

/**
 * Created by sirin_000 on 25/09/2015.
 */
public final class BitUtils {
    public static final long PAGE_SIZE = UnsafeAccess.UNSAFE.pageSize();

    public static long roundUpToPageSize(long i) {
        return roundUpTo(i, PAGE_SIZE);
    }

    public static long roundUpTo(long i, long multiple) {
        return (i + multiple - 1L) & ~(multiple - 1L);
    }

    public static int roundUpToPageSize(int i) {
        return roundUpTo(i, (int) PAGE_SIZE);
    }

    public static int roundUpTo(int i, int multiple) {
        return (i + multiple - 1) & ~(multiple - 1);
    }

}
