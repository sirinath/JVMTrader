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
import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

public final class Packer extends ThreadLocal<Packer> {
    public static final Packer PACKER = new Packer();

    protected long aCharFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "aChar");

    protected char aChar = 0;

    protected long aShortFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "aShort");

    protected short aShort = 0;

    protected long anIntFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "anInt");

    protected int anInt = 0;

    protected long aLongFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "aLong");

    protected long aLong = 0;

    protected Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static final int BYTE_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Byte.BYTES) - 1;
    public static final int SHORT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Short.BYTES) - 1;
    public static final int CHAR_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Character.BYTES) - 1;
    public static final int INT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Integer.BYTES) - 1;
    public static final int FLOAT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Float.BYTES) - 1;


    protected Packer() {
    }

    @Override
    protected final Packer initialValue() {
        return new Packer();
    }

    public char packChar(@NotNull final byte... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        aChar = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, this, aCharFieldOffset, len);

        return aChar;
    }

    public void unpackChar(final char value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        aChar = value;

        UNSAFE.copyMemory(this, aCharFieldOffset, values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, len);
    }

    public short packShort(@NotNull final byte... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        aShort = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, this, aShortFieldOffset, len);

        return aShort;
    }

    public void unpackShort(final short value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        aShort = value;

        UNSAFE.copyMemory(this, aShortFieldOffset, values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, len);
    }

    public char packChar(@NotNull final boolean... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        aChar = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, this, aCharFieldOffset, len);

        return aChar;
    }

    public void unpackChar(final char value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        aChar = value;

        UNSAFE.copyMemory(this, aCharFieldOffset, values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, len);
    }

    public short packShort(@NotNull final boolean... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        aShort = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, this, aShortFieldOffset, len);

        return aShort;
    }

    public void unpackShort(final short value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        aShort = value;

        UNSAFE.copyMemory(this, aShortFieldOffset, values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final boolean... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public int packInt(@NotNull final boolean... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        anInt = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, this, anIntFieldOffset, len);

        return anInt;
    }

    public void unpackLong(final long value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, len);
    }

    public void unpackInt(final int value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        anInt = value;

        UNSAFE.copyMemory(this, anIntFieldOffset, values, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final byte... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public int packInt(@NotNull final byte... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        anInt = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, this, anIntFieldOffset, len);

        return anInt;
    }

    public void unpackLong(final long value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, len);
    }

    public void unpackInt(final int value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        anInt = value;

        UNSAFE.copyMemory(this, anIntFieldOffset, values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final short... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_SHORT_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public int packInt(@NotNull final short... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Integer.BYTES);

        anInt = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_SHORT_BASE_OFFSET, this, anIntFieldOffset, len);

        return anInt;
    }

    public void unpackLong(final long value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_SHORT_BASE_OFFSET, len);
    }

    public void unpackInt(final int value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Integer.BYTES);

        anInt = value;

        UNSAFE.copyMemory(this, anIntFieldOffset, values, UNSAFE.ARRAY_SHORT_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final char... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_CHAR_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public int packInt(@NotNull final char... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Integer.BYTES);

        anInt = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_CHAR_BASE_OFFSET, this, anIntFieldOffset, len);

        return anInt;
    }

    public void unpackLong(final long value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_CHAR_BASE_OFFSET, len);
    }

    public void unpackInt(final int value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Integer.BYTES);

        anInt = value;

        UNSAFE.copyMemory(this, anIntFieldOffset, values, UNSAFE.ARRAY_CHAR_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final int... values) {
        final long len = Math.min(values.length << INT_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_INT_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public void unpackLong(final long value, @NotNull final int[] values) {
        final long len = Math.min(values.length << INT_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_INT_BASE_OFFSET, len);
    }

    public long packLong(@NotNull final float... values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Long.BYTES);

        aLong = 0L;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_FLOAT_BASE_OFFSET, this, aLongFieldOffset, len);

        return aLong;
    }

    public void unpackLong(final long value, @NotNull final float[] values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Long.BYTES);

        aLong = value;

        UNSAFE.copyMemory(this, aLongFieldOffset, values, UNSAFE.ARRAY_FLOAT_BASE_OFFSET, len);
    }
}
