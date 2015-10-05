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

package com.susico.utils.arrays;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

/**
 * Created by sirin_000 on 03/10/2015.
 */
public final class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true);
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false);

    private final boolean SHOULD_BOUNDS_CHECK;

    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

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

    public final <T> T get(final long index, final T[] buff) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return (T) UNSAFE.getObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + index * UNSAFE.ARRAY_OBJECT_INDEX_SCALE);
    }

    public final <T> void put(final long index, final T[] buff, final T obj) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = obj;
        else
            UNSAFE.putObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + index * UNSAFE.ARRAY_OBJECT_INDEX_SCALE, obj);
    }

    public final boolean get(final boolean[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + index * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE);
    }

    public final void put(final boolean[] buff, final long index, final boolean value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + index * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE, value);
    }

    public final byte get(final byte[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + index * UNSAFE.ARRAY_BYTE_INDEX_SCALE);
    }

    public final void put(final byte[] buff, final long index, final byte value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + index * UNSAFE.ARRAY_BYTE_INDEX_SCALE, value);
    }

    public final char get(final char[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getChar(buff, UNSAFE.ARRAY_CHAR_BASE_OFFSET + index * UNSAFE.ARRAY_CHAR_INDEX_SCALE);
    }

    public final void put(final char[] buff, final long index, final char value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putChar(buff, UNSAFE.ARRAY_CHAR_BASE_OFFSET + index * UNSAFE.ARRAY_CHAR_INDEX_SCALE, value);
    }

    public final double get(final double[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getDouble(buff, UNSAFE.ARRAY_DOUBLE_BASE_OFFSET + index * UNSAFE.ARRAY_DOUBLE_INDEX_SCALE);
    }

    public final void put(final double[] buff, final long index, final double value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putDouble(buff, UNSAFE.ARRAY_DOUBLE_BASE_OFFSET + index * UNSAFE.ARRAY_DOUBLE_INDEX_SCALE, value);
    }

    public final float get(final float[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getFloat(buff, UNSAFE.ARRAY_FLOAT_BASE_OFFSET + index * UNSAFE.ARRAY_FLOAT_INDEX_SCALE);
    }

    public final void put(final float[] buff, final long index, final float value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putFloat(buff, UNSAFE.ARRAY_FLOAT_BASE_OFFSET + index * UNSAFE.ARRAY_FLOAT_INDEX_SCALE, value);
    }

    public final int get(final int[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getInt(buff, UNSAFE.ARRAY_INT_BASE_OFFSET + index * UNSAFE.ARRAY_INT_INDEX_SCALE);
    }

    public final void put(final int[] buff, final long index, final int value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putFloat(buff, UNSAFE.ARRAY_INT_BASE_OFFSET + index * UNSAFE.ARRAY_INT_INDEX_SCALE, value);
    }

    public final long get(final long[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getLong(buff, UNSAFE.ARRAY_LONG_BASE_OFFSET + index * UNSAFE.ARRAY_LONG_INDEX_SCALE);
    }

    public final void put(final long[] buff, final long index, final long value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putLong(buff, UNSAFE.ARRAY_LONG_BASE_OFFSET + index * UNSAFE.ARRAY_LONG_INDEX_SCALE, value);
    }

    public final short get(final short[] buff, final long index) {
        if (SHOULD_BOUNDS_CHECK)
            return buff[(int) index];
        else
            return UNSAFE.getShort(buff, UNSAFE.ARRAY_SHORT_BASE_OFFSET + index * UNSAFE.ARRAY_SHORT_INDEX_SCALE);
    }

    public final void put(final short[] buff, final long index, final short value) {
        if (SHOULD_BOUNDS_CHECK)
            buff[(int) index] = value;
        else
            UNSAFE.putShort(buff, UNSAFE.ARRAY_SHORT_BASE_OFFSET + index * UNSAFE.ARRAY_SHORT_INDEX_SCALE, value);
    }
}
