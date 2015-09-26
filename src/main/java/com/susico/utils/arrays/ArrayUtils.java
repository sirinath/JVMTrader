package com.susico.utils.arrays;

import com.susico.utils.Utils;
import com.susico.utils.Utils.ArrayUtils.ArrayAccess;
import sun.misc.Unsafe;
import uk.co.real_logic.agrona.UnsafeAccess;

/**
 * Created by sirin_000 on 25/09/2015.
 */
public final class ArrayUtils {
    public static final class ArrayAccess {
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

        public final <T> T get(final T[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return (T) UNSAFE.getObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE);
        }

        public final <T, U extends T> void put(final T[] buff, final long i, final U obj) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = obj;
            else
                UNSAFE.putObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE, obj);
        }

        public final boolean get(final boolean[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE);
        }

        public final void put(final boolean[] buff, final long i, final boolean value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE, value);
        }

        public final byte get(final byte[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE);
        }

        public final void put(final byte[] buff, final long i, final byte value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE, value);
        }

        public final char get(final char[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getChar(buff, UNSAFE.ARRAY_CHAR_BASE_OFFSET + i * UNSAFE.ARRAY_CHAR_INDEX_SCALE);
        }

        public final void put(final char[] buff, final long i, final char value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putChar(buff, UNSAFE.ARRAY_CHAR_BASE_OFFSET + i * UNSAFE.ARRAY_CHAR_INDEX_SCALE, value);
        }

        public final double get(final double[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getDouble(buff, UNSAFE.ARRAY_DOUBLE_BASE_OFFSET + i * UNSAFE.ARRAY_DOUBLE_INDEX_SCALE);
        }

        public final void put(final double[] buff, final long i, final double value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putDouble(buff, UNSAFE.ARRAY_DOUBLE_BASE_OFFSET + i * UNSAFE.ARRAY_DOUBLE_INDEX_SCALE, value);
        }

        public final float get(final float[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getFloat(buff, UNSAFE.ARRAY_FLOAT_BASE_OFFSET + i * UNSAFE.ARRAY_FLOAT_INDEX_SCALE);
        }

        public final void put(final float[] buff, final long i, final float value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putFloat(buff, UNSAFE.ARRAY_FLOAT_BASE_OFFSET + i * UNSAFE.ARRAY_FLOAT_INDEX_SCALE, value);
        }

        public final int get(final int[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getInt(buff, UNSAFE.ARRAY_INT_BASE_OFFSET + i * UNSAFE.ARRAY_INT_INDEX_SCALE);
        }

        public final void put(final int[] buff, final long i, final int value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putFloat(buff, UNSAFE.ARRAY_INT_BASE_OFFSET + i * UNSAFE.ARRAY_INT_INDEX_SCALE, value);
        }

        public final long get(final long[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getLong(buff, UNSAFE.ARRAY_LONG_BASE_OFFSET + i * UNSAFE.ARRAY_LONG_INDEX_SCALE);
        }

        public final void put(final long[] buff, final long i, final long value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putLong(buff, UNSAFE.ARRAY_LONG_BASE_OFFSET + i * UNSAFE.ARRAY_LONG_INDEX_SCALE, value);
        }

        public final short get(final short[] buff, final long i) {
            if (SHOULD_BOUNDS_CHECK)
                return buff[(int) i];
            else
                return UNSAFE.getShort(buff, UNSAFE.ARRAY_SHORT_BASE_OFFSET + i * UNSAFE.ARRAY_SHORT_INDEX_SCALE);
        }

        public final void put(final short[] buff, final long i, final short value) {
            if (SHOULD_BOUNDS_CHECK)
                buff[(int) i] = value;
            else
                UNSAFE.putShort(buff, UNSAFE.ARRAY_SHORT_BASE_OFFSET + i * UNSAFE.ARRAY_SHORT_INDEX_SCALE, value);
        }
    }
}
