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

package com.susico.JVMTrader;

import sun.misc.Unsafe;
import uk.co.real_logic.agrona.UnsafeAccess;

/**
 * Created by sirin_000 on 02/09/2015.
 */

public final class  JVMTraderUtils {
    public final static class ArrayUtils {
        public final static class ArrayAccess {
            public final static ArrayAccess CHECKED = new ArrayAccess(true);
            public final static ArrayAccess UNCHECKED = new ArrayAccess(false);
            private final static Unsafe UNSAFE = UnsafeAccess.UNSAFE;
            private final static long ARRAY_OBJECT_BASE_OFFSET = Unsafe.ARRAY_OBJECT_BASE_OFFSET;
            private final static long ARRAY_OBJECT_INDEX_SCALE = Unsafe.ARRAY_OBJECT_INDEX_SCALE;
            private final boolean SHOULD_BOUNDS_CHECK;

            private ArrayAccess(final boolean SHOULD_BOUNDS_CHECK) {
                this.SHOULD_BOUNDS_CHECK = SHOULD_BOUNDS_CHECK;
            }

            public static ArrayAccess checked(final boolean checked) {
                return checked ? CHECKED : UNCHECKED;
            }

            public static ArrayAccess checked() {
                return CHECKED;
            }

            public static ArrayAccess unchecked() {
                return UNCHECKED;
            }

            public final <T> T getFromArray(final T[] buff, final long i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[(int) i];
                else
                    return (T) UNSAFE.getObject(buff, ARRAY_OBJECT_BASE_OFFSET + i * ARRAY_OBJECT_INDEX_SCALE);
            }

            public final <T> T getFromArray(final T[] buff, final int i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[i];
                else
                    return (T) UNSAFE.getObject(buff, ARRAY_OBJECT_BASE_OFFSET + i * ARRAY_OBJECT_INDEX_SCALE);
            }

            public final <T, U extends T> void putFromArray(final T[] buff, final long i, final U obj) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[(int) i] = obj;
                else
                    UNSAFE.putObject(buff, ARRAY_OBJECT_BASE_OFFSET + i * ARRAY_OBJECT_INDEX_SCALE, obj);
            }

            public final <T, U extends T> void putFromArray(final T[] buff, final int i, final U obj) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[i] = obj;
                else
                    UNSAFE.putObject(buff, ARRAY_OBJECT_BASE_OFFSET + i * ARRAY_OBJECT_INDEX_SCALE, obj);
            }
        }
    }

    public final static class BitUtils {
        public final static long PAGE_SIZE = UnsafeAccess.UNSAFE.pageSize();

        public static long roundUpTo(long i, long multiple) {
            return (i + multiple - 1L) & ~(multiple - 1L);
        }

        public static int roundUpTo(int i, int multiple) {
            return (i + multiple - 1) & ~(multiple - 1);
        }

        public static long roundUpToPageSize(long i) {
            return roundUpTo(i, PAGE_SIZE);
        }

        public static int roundUpToPageSize(int i) {
            return roundUpTo(i, (int)PAGE_SIZE);
        }
    }
}
