
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

public final class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true);
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false);

    private final boolean SAFE;

    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    private ArrayAccess(final boolean SAFE) {
        this.SAFE = SAFE;
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

    public static final long ARRAY_BOOLEAN_BASE_OFFSET = UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET;
    public static final long ARRAY_BOOLEAN_INDEX_SCALE = UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE > 0 ? UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE : 1;
    public static final long ARRAY_BOOLEAN_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BOOLEAN_INDEX_SCALE) - 1;

    public final  boolean get(final long index, final boolean ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (boolean) UNSAFE.getBoolean(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final  boolean[] put(final long index, final boolean[] buff, final boolean value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putBoolean(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buff;
    }

    public final  boolean get(final int index, final boolean ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (boolean) UNSAFE.getBoolean(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final  boolean[] put(final int index, final boolean[] buff, final boolean value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putBoolean(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buff;
    }

    public final  boolean getVolatile(final long index, final boolean ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (boolean) UNSAFE.getBooleanVolatile(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final  boolean[] putVolatile(final long index, final boolean[] buff, final boolean value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putBooleanVolatile(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buff;
    }

    public final  boolean getVolatile(final int index, final boolean ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (boolean) UNSAFE.getBooleanVolatile(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final  boolean[] putVolatile(final int index, final boolean[] buff, final boolean value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putBooleanVolatile(buff, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buff;
    }

    public final  boolean[] get(final boolean[] buff, final boolean ... values) {
        return copy(buff, values);
    }

    public final  boolean[] put(final boolean[] buff, final boolean ... values) {
        return copy(buff, values);
    }

    public final  boolean[] copy(final boolean[] destination, final boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BOOLEAN_BASE_OFFSET, destination, ARRAY_BOOLEAN_BASE_OFFSET, source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final  boolean[] get(final long index, final boolean[] buff, final boolean ... values) {
        return copy(index, buff, values);
    }

    public final  boolean[] put(final long index, final boolean[] buff, final boolean ... values) {
        return copy(index, buff, values);
    }

    public final  boolean[] copy(final long index, final boolean[] destination, final boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BOOLEAN_BASE_OFFSET, destination, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final  boolean[] get(final long length, final long indexBuff, final boolean[] buff, final long indexValues, final boolean ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  boolean[] put(final long length, final long indexBuff, final boolean[] buff, final long indexValues, final boolean ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  boolean[] copy(final long length, final long indexDestination, final boolean[] destination, final long indexSource, final boolean ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_BOOLEAN_BASE_OFFSET + indexSource << ARRAY_BOOLEAN_INDEX_SCALE, destination, ARRAY_BOOLEAN_BASE_OFFSET + indexDestination << ARRAY_BOOLEAN_INDEX_SHIFT, length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final  boolean[] get(final int index, final boolean[] buff, final boolean ... values) {
        return copy(index, buff, values);
    }

    public final  boolean[] put(final int index, final boolean[] buff, final boolean ... values) {
        return copy(index, buff, values);
    }

    public final  boolean[] copy(final int index, final boolean[] destination, final boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BOOLEAN_BASE_OFFSET, destination, ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final  boolean[] get(final int length, final int indexBuff, final boolean[] buff, final int indexValues, final boolean ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  boolean[] put(final int length, final int indexBuff, final boolean[] buff, final int indexValues, final boolean ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  boolean[] copy(final int length, final int indexDestination, final boolean[] destination, final int indexSource, final boolean ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_BOOLEAN_BASE_OFFSET + indexSource << ARRAY_BOOLEAN_INDEX_SHIFT, destination, ARRAY_BOOLEAN_BASE_OFFSET + indexDestination << ARRAY_BOOLEAN_INDEX_SHIFT, length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_BYTE_BASE_OFFSET = UNSAFE.ARRAY_BYTE_BASE_OFFSET;
    public static final long ARRAY_BYTE_INDEX_SCALE = UNSAFE.ARRAY_BYTE_INDEX_SCALE > 0 ? UNSAFE.ARRAY_BYTE_INDEX_SCALE : Byte.BYTES;
    public static final long ARRAY_BYTE_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BYTE_INDEX_SCALE) - 1;

    public final  byte get(final long index, final byte ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (byte) UNSAFE.getByte(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final  byte[] put(final long index, final byte[] buff, final byte value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putByte(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buff;
    }

    public final  byte get(final int index, final byte ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (byte) UNSAFE.getByte(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final  byte[] put(final int index, final byte[] buff, final byte value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putByte(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buff;
    }

    public final  byte getVolatile(final long index, final byte ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (byte) UNSAFE.getByteVolatile(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final  byte[] putVolatile(final long index, final byte[] buff, final byte value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putByteVolatile(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buff;
    }

    public final  byte getVolatile(final int index, final byte ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (byte) UNSAFE.getByteVolatile(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final  byte[] putVolatile(final int index, final byte[] buff, final byte value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putByteVolatile(buff, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buff;
    }

    public final  byte[] get(final byte[] buff, final byte ... values) {
        return copy(buff, values);
    }

    public final  byte[] put(final byte[] buff, final byte ... values) {
        return copy(buff, values);
    }

    public final  byte[] copy(final byte[] destination, final byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BYTE_BASE_OFFSET, destination, ARRAY_BYTE_BASE_OFFSET, source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final  byte[] get(final long index, final byte[] buff, final byte ... values) {
        return copy(index, buff, values);
    }

    public final  byte[] put(final long index, final byte[] buff, final byte ... values) {
        return copy(index, buff, values);
    }

    public final  byte[] copy(final long index, final byte[] destination, final byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BYTE_BASE_OFFSET, destination, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final  byte[] get(final long length, final long indexBuff, final byte[] buff, final long indexValues, final byte ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  byte[] put(final long length, final long indexBuff, final byte[] buff, final long indexValues, final byte ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  byte[] copy(final long length, final long indexDestination, final byte[] destination, final long indexSource, final byte ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_BYTE_BASE_OFFSET + indexSource << ARRAY_BYTE_INDEX_SCALE, destination, ARRAY_BYTE_BASE_OFFSET + indexDestination << ARRAY_BYTE_INDEX_SHIFT, length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final  byte[] get(final int index, final byte[] buff, final byte ... values) {
        return copy(index, buff, values);
    }

    public final  byte[] put(final int index, final byte[] buff, final byte ... values) {
        return copy(index, buff, values);
    }

    public final  byte[] copy(final int index, final byte[] destination, final byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_BYTE_BASE_OFFSET, destination, ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final  byte[] get(final int length, final int indexBuff, final byte[] buff, final int indexValues, final byte ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  byte[] put(final int length, final int indexBuff, final byte[] buff, final int indexValues, final byte ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  byte[] copy(final int length, final int indexDestination, final byte[] destination, final int indexSource, final byte ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_BYTE_BASE_OFFSET + indexSource << ARRAY_BYTE_INDEX_SHIFT, destination, ARRAY_BYTE_BASE_OFFSET + indexDestination << ARRAY_BYTE_INDEX_SHIFT, length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_CHAR_BASE_OFFSET = UNSAFE.ARRAY_CHAR_BASE_OFFSET;
    public static final long ARRAY_CHAR_INDEX_SCALE = UNSAFE.ARRAY_CHAR_INDEX_SCALE > 0 ? UNSAFE.ARRAY_CHAR_INDEX_SCALE : Character.BYTES;
    public static final long ARRAY_CHAR_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_CHAR_INDEX_SCALE) - 1;

    public final  char get(final long index, final char ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (char) UNSAFE.getChar(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final  char[] put(final long index, final char[] buff, final char value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putChar(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buff;
    }

    public final  char get(final int index, final char ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (char) UNSAFE.getChar(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final  char[] put(final int index, final char[] buff, final char value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putChar(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buff;
    }

    public final  char getVolatile(final long index, final char ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (char) UNSAFE.getCharVolatile(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final  char[] putVolatile(final long index, final char[] buff, final char value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putCharVolatile(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buff;
    }

    public final  char getVolatile(final int index, final char ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (char) UNSAFE.getCharVolatile(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final  char[] putVolatile(final int index, final char[] buff, final char value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putCharVolatile(buff, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buff;
    }

    public final  char[] get(final char[] buff, final char ... values) {
        return copy(buff, values);
    }

    public final  char[] put(final char[] buff, final char ... values) {
        return copy(buff, values);
    }

    public final  char[] copy(final char[] destination, final char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_CHAR_BASE_OFFSET, destination, ARRAY_CHAR_BASE_OFFSET, source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final  char[] get(final long index, final char[] buff, final char ... values) {
        return copy(index, buff, values);
    }

    public final  char[] put(final long index, final char[] buff, final char ... values) {
        return copy(index, buff, values);
    }

    public final  char[] copy(final long index, final char[] destination, final char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_CHAR_BASE_OFFSET, destination, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final  char[] get(final long length, final long indexBuff, final char[] buff, final long indexValues, final char ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  char[] put(final long length, final long indexBuff, final char[] buff, final long indexValues, final char ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  char[] copy(final long length, final long indexDestination, final char[] destination, final long indexSource, final char ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_CHAR_BASE_OFFSET + indexSource << ARRAY_CHAR_INDEX_SCALE, destination, ARRAY_CHAR_BASE_OFFSET + indexDestination << ARRAY_CHAR_INDEX_SHIFT, length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final  char[] get(final int index, final char[] buff, final char ... values) {
        return copy(index, buff, values);
    }

    public final  char[] put(final int index, final char[] buff, final char ... values) {
        return copy(index, buff, values);
    }

    public final  char[] copy(final int index, final char[] destination, final char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_CHAR_BASE_OFFSET, destination, ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final  char[] get(final int length, final int indexBuff, final char[] buff, final int indexValues, final char ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  char[] put(final int length, final int indexBuff, final char[] buff, final int indexValues, final char ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  char[] copy(final int length, final int indexDestination, final char[] destination, final int indexSource, final char ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_CHAR_BASE_OFFSET + indexSource << ARRAY_CHAR_INDEX_SHIFT, destination, ARRAY_CHAR_BASE_OFFSET + indexDestination << ARRAY_CHAR_INDEX_SHIFT, length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_SHORT_BASE_OFFSET = UNSAFE.ARRAY_SHORT_BASE_OFFSET;
    public static final long ARRAY_SHORT_INDEX_SCALE = UNSAFE.ARRAY_SHORT_INDEX_SCALE > 0 ? UNSAFE.ARRAY_SHORT_INDEX_SCALE : Short.BYTES;
    public static final long ARRAY_SHORT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_SHORT_INDEX_SCALE) - 1;

    public final  short get(final long index, final short ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (short) UNSAFE.getShort(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final  short[] put(final long index, final short[] buff, final short value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putShort(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buff;
    }

    public final  short get(final int index, final short ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (short) UNSAFE.getShort(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final  short[] put(final int index, final short[] buff, final short value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putShort(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buff;
    }

    public final  short getVolatile(final long index, final short ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (short) UNSAFE.getShortVolatile(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final  short[] putVolatile(final long index, final short[] buff, final short value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putShortVolatile(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buff;
    }

    public final  short getVolatile(final int index, final short ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (short) UNSAFE.getShortVolatile(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final  short[] putVolatile(final int index, final short[] buff, final short value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putShortVolatile(buff, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buff;
    }

    public final  short[] get(final short[] buff, final short ... values) {
        return copy(buff, values);
    }

    public final  short[] put(final short[] buff, final short ... values) {
        return copy(buff, values);
    }

    public final  short[] copy(final short[] destination, final short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_SHORT_BASE_OFFSET, destination, ARRAY_SHORT_BASE_OFFSET, source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final  short[] get(final long index, final short[] buff, final short ... values) {
        return copy(index, buff, values);
    }

    public final  short[] put(final long index, final short[] buff, final short ... values) {
        return copy(index, buff, values);
    }

    public final  short[] copy(final long index, final short[] destination, final short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_SHORT_BASE_OFFSET, destination, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final  short[] get(final long length, final long indexBuff, final short[] buff, final long indexValues, final short ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  short[] put(final long length, final long indexBuff, final short[] buff, final long indexValues, final short ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  short[] copy(final long length, final long indexDestination, final short[] destination, final long indexSource, final short ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_SHORT_BASE_OFFSET + indexSource << ARRAY_SHORT_INDEX_SCALE, destination, ARRAY_SHORT_BASE_OFFSET + indexDestination << ARRAY_SHORT_INDEX_SHIFT, length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final  short[] get(final int index, final short[] buff, final short ... values) {
        return copy(index, buff, values);
    }

    public final  short[] put(final int index, final short[] buff, final short ... values) {
        return copy(index, buff, values);
    }

    public final  short[] copy(final int index, final short[] destination, final short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_SHORT_BASE_OFFSET, destination, ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final  short[] get(final int length, final int indexBuff, final short[] buff, final int indexValues, final short ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  short[] put(final int length, final int indexBuff, final short[] buff, final int indexValues, final short ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  short[] copy(final int length, final int indexDestination, final short[] destination, final int indexSource, final short ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_SHORT_BASE_OFFSET + indexSource << ARRAY_SHORT_INDEX_SHIFT, destination, ARRAY_SHORT_BASE_OFFSET + indexDestination << ARRAY_SHORT_INDEX_SHIFT, length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_INT_BASE_OFFSET = UNSAFE.ARRAY_INT_BASE_OFFSET;
    public static final long ARRAY_INT_INDEX_SCALE = UNSAFE.ARRAY_INT_INDEX_SCALE > 0 ? UNSAFE.ARRAY_INT_INDEX_SCALE : Integer.BYTES;
    public static final long ARRAY_INT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_INT_INDEX_SCALE) - 1;

    public final  int get(final long index, final int ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (int) UNSAFE.getInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final  int[] put(final long index, final int[] buff, final int value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buff;
    }

    public final  int get(final int index, final int ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (int) UNSAFE.getInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final  int[] put(final int index, final int[] buff, final int value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buff;
    }

    public final  int getVolatile(final long index, final int ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (int) UNSAFE.getIntVolatile(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final  int[] putVolatile(final long index, final int[] buff, final int value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putIntVolatile(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buff;
    }

    public final  int getVolatile(final int index, final int ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (int) UNSAFE.getIntVolatile(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final  int[] putVolatile(final int index, final int[] buff, final int value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putIntVolatile(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buff;
    }

    public final  int[] get(final int[] buff, final int ... values) {
        return copy(buff, values);
    }

    public final  int[] put(final int[] buff, final int ... values) {
        return copy(buff, values);
    }

    public final  int[] copy(final int[] destination, final int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_INT_BASE_OFFSET, destination, ARRAY_INT_BASE_OFFSET, source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final  int[] get(final long index, final int[] buff, final int ... values) {
        return copy(index, buff, values);
    }

    public final  int[] put(final long index, final int[] buff, final int ... values) {
        return copy(index, buff, values);
    }

    public final  int[] copy(final long index, final int[] destination, final int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_INT_BASE_OFFSET, destination, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final  int[] get(final long length, final long indexBuff, final int[] buff, final long indexValues, final int ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  int[] put(final long length, final long indexBuff, final int[] buff, final long indexValues, final int ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  int[] copy(final long length, final long indexDestination, final int[] destination, final long indexSource, final int ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_INT_BASE_OFFSET + indexSource << ARRAY_INT_INDEX_SCALE, destination, ARRAY_INT_BASE_OFFSET + indexDestination << ARRAY_INT_INDEX_SHIFT, length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final  int[] get(final int index, final int[] buff, final int ... values) {
        return copy(index, buff, values);
    }

    public final  int[] put(final int index, final int[] buff, final int ... values) {
        return copy(index, buff, values);
    }

    public final  int[] copy(final int index, final int[] destination, final int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_INT_BASE_OFFSET, destination, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final  int[] get(final int length, final int indexBuff, final int[] buff, final int indexValues, final int ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  int[] put(final int length, final int indexBuff, final int[] buff, final int indexValues, final int ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  int[] copy(final int length, final int indexDestination, final int[] destination, final int indexSource, final int ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_INT_BASE_OFFSET + indexSource << ARRAY_INT_INDEX_SHIFT, destination, ARRAY_INT_BASE_OFFSET + indexDestination << ARRAY_INT_INDEX_SHIFT, length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

            public final  int[] putOrdered(final long index, final int[] buff, final int value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

                return buff;
            }

            public final  int[] putOrdered(final int index, final int[] buff, final int value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

                return buff;
            }
    
    public static final long ARRAY_LONG_BASE_OFFSET = UNSAFE.ARRAY_LONG_BASE_OFFSET;
    public static final long ARRAY_LONG_INDEX_SCALE = UNSAFE.ARRAY_LONG_INDEX_SCALE > 0 ? UNSAFE.ARRAY_LONG_INDEX_SCALE : Long.BYTES;
    public static final long ARRAY_LONG_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_LONG_INDEX_SCALE) - 1;

    public final  long get(final long index, final long ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (long) UNSAFE.getLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final  long[] put(final long index, final long[] buff, final long value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buff;
    }

    public final  long get(final int index, final long ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (long) UNSAFE.getLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final  long[] put(final int index, final long[] buff, final long value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buff;
    }

    public final  long getVolatile(final long index, final long ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (long) UNSAFE.getLongVolatile(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final  long[] putVolatile(final long index, final long[] buff, final long value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putLongVolatile(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buff;
    }

    public final  long getVolatile(final int index, final long ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (long) UNSAFE.getLongVolatile(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final  long[] putVolatile(final int index, final long[] buff, final long value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putLongVolatile(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buff;
    }

    public final  long[] get(final long[] buff, final long ... values) {
        return copy(buff, values);
    }

    public final  long[] put(final long[] buff, final long ... values) {
        return copy(buff, values);
    }

    public final  long[] copy(final long[] destination, final long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_LONG_BASE_OFFSET, destination, ARRAY_LONG_BASE_OFFSET, source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final  long[] get(final long index, final long[] buff, final long ... values) {
        return copy(index, buff, values);
    }

    public final  long[] put(final long index, final long[] buff, final long ... values) {
        return copy(index, buff, values);
    }

    public final  long[] copy(final long index, final long[] destination, final long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_LONG_BASE_OFFSET, destination, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final  long[] get(final long length, final long indexBuff, final long[] buff, final long indexValues, final long ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  long[] put(final long length, final long indexBuff, final long[] buff, final long indexValues, final long ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  long[] copy(final long length, final long indexDestination, final long[] destination, final long indexSource, final long ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_LONG_BASE_OFFSET + indexSource << ARRAY_LONG_INDEX_SCALE, destination, ARRAY_LONG_BASE_OFFSET + indexDestination << ARRAY_LONG_INDEX_SHIFT, length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final  long[] get(final int index, final long[] buff, final long ... values) {
        return copy(index, buff, values);
    }

    public final  long[] put(final int index, final long[] buff, final long ... values) {
        return copy(index, buff, values);
    }

    public final  long[] copy(final int index, final long[] destination, final long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_LONG_BASE_OFFSET, destination, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final  long[] get(final int length, final int indexBuff, final long[] buff, final int indexValues, final long ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  long[] put(final int length, final int indexBuff, final long[] buff, final int indexValues, final long ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  long[] copy(final int length, final int indexDestination, final long[] destination, final int indexSource, final long ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_LONG_BASE_OFFSET + indexSource << ARRAY_LONG_INDEX_SHIFT, destination, ARRAY_LONG_BASE_OFFSET + indexDestination << ARRAY_LONG_INDEX_SHIFT, length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

            public final  long[] putOrdered(final long index, final long[] buff, final long value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

                return buff;
            }

            public final  long[] putOrdered(final int index, final long[] buff, final long value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

                return buff;
            }
    
    public static final long ARRAY_FLOAT_BASE_OFFSET = UNSAFE.ARRAY_FLOAT_BASE_OFFSET;
    public static final long ARRAY_FLOAT_INDEX_SCALE = UNSAFE.ARRAY_FLOAT_INDEX_SCALE > 0 ? UNSAFE.ARRAY_FLOAT_INDEX_SCALE : Float.BYTES;
    public static final long ARRAY_FLOAT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_FLOAT_INDEX_SCALE) - 1;

    public final  float get(final long index, final float ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (float) UNSAFE.getFloat(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final  float[] put(final long index, final float[] buff, final float value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putFloat(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buff;
    }

    public final  float get(final int index, final float ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (float) UNSAFE.getFloat(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final  float[] put(final int index, final float[] buff, final float value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putFloat(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buff;
    }

    public final  float getVolatile(final long index, final float ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (float) UNSAFE.getFloatVolatile(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final  float[] putVolatile(final long index, final float[] buff, final float value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putFloatVolatile(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buff;
    }

    public final  float getVolatile(final int index, final float ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (float) UNSAFE.getFloatVolatile(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final  float[] putVolatile(final int index, final float[] buff, final float value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putFloatVolatile(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buff;
    }

    public final  float[] get(final float[] buff, final float ... values) {
        return copy(buff, values);
    }

    public final  float[] put(final float[] buff, final float ... values) {
        return copy(buff, values);
    }

    public final  float[] copy(final float[] destination, final float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_FLOAT_BASE_OFFSET, destination, ARRAY_FLOAT_BASE_OFFSET, source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final  float[] get(final long index, final float[] buff, final float ... values) {
        return copy(index, buff, values);
    }

    public final  float[] put(final long index, final float[] buff, final float ... values) {
        return copy(index, buff, values);
    }

    public final  float[] copy(final long index, final float[] destination, final float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_FLOAT_BASE_OFFSET, destination, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final  float[] get(final long length, final long indexBuff, final float[] buff, final long indexValues, final float ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  float[] put(final long length, final long indexBuff, final float[] buff, final long indexValues, final float ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  float[] copy(final long length, final long indexDestination, final float[] destination, final long indexSource, final float ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_FLOAT_BASE_OFFSET + indexSource << ARRAY_FLOAT_INDEX_SCALE, destination, ARRAY_FLOAT_BASE_OFFSET + indexDestination << ARRAY_FLOAT_INDEX_SHIFT, length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final  float[] get(final int index, final float[] buff, final float ... values) {
        return copy(index, buff, values);
    }

    public final  float[] put(final int index, final float[] buff, final float ... values) {
        return copy(index, buff, values);
    }

    public final  float[] copy(final int index, final float[] destination, final float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_FLOAT_BASE_OFFSET, destination, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final  float[] get(final int length, final int indexBuff, final float[] buff, final int indexValues, final float ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  float[] put(final int length, final int indexBuff, final float[] buff, final int indexValues, final float ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  float[] copy(final int length, final int indexDestination, final float[] destination, final int indexSource, final float ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_FLOAT_BASE_OFFSET + indexSource << ARRAY_FLOAT_INDEX_SHIFT, destination, ARRAY_FLOAT_BASE_OFFSET + indexDestination << ARRAY_FLOAT_INDEX_SHIFT, length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

            public final  float[] putOrdered(final long index, final float[] buff, final float value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, Float.floatToRawIntBits(value));

                return buff;
            }

            public final  float[] putOrdered(final int index, final float[] buff, final float value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, Float.floatToRawIntBits(value));

                return buff;
            }
    
    public static final long ARRAY_DOUBLE_BASE_OFFSET = UNSAFE.ARRAY_DOUBLE_BASE_OFFSET;
    public static final long ARRAY_DOUBLE_INDEX_SCALE = UNSAFE.ARRAY_DOUBLE_INDEX_SCALE > 0 ? UNSAFE.ARRAY_DOUBLE_INDEX_SCALE : Double.BYTES;
    public static final long ARRAY_DOUBLE_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_DOUBLE_INDEX_SCALE) - 1;

    public final  double get(final long index, final double ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (double) UNSAFE.getDouble(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final  double[] put(final long index, final double[] buff, final double value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putDouble(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buff;
    }

    public final  double get(final int index, final double ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (double) UNSAFE.getDouble(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final  double[] put(final int index, final double[] buff, final double value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putDouble(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buff;
    }

    public final  double getVolatile(final long index, final double ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (double) UNSAFE.getDoubleVolatile(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final  double[] putVolatile(final long index, final double[] buff, final double value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putDoubleVolatile(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buff;
    }

    public final  double getVolatile(final int index, final double ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (double) UNSAFE.getDoubleVolatile(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final  double[] putVolatile(final int index, final double[] buff, final double value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putDoubleVolatile(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buff;
    }

    public final  double[] get(final double[] buff, final double ... values) {
        return copy(buff, values);
    }

    public final  double[] put(final double[] buff, final double ... values) {
        return copy(buff, values);
    }

    public final  double[] copy(final double[] destination, final double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_DOUBLE_BASE_OFFSET, destination, ARRAY_DOUBLE_BASE_OFFSET, source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final  double[] get(final long index, final double[] buff, final double ... values) {
        return copy(index, buff, values);
    }

    public final  double[] put(final long index, final double[] buff, final double ... values) {
        return copy(index, buff, values);
    }

    public final  double[] copy(final long index, final double[] destination, final double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_DOUBLE_BASE_OFFSET, destination, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final  double[] get(final long length, final long indexBuff, final double[] buff, final long indexValues, final double ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  double[] put(final long length, final long indexBuff, final double[] buff, final long indexValues, final double ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  double[] copy(final long length, final long indexDestination, final double[] destination, final long indexSource, final double ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_DOUBLE_BASE_OFFSET + indexSource << ARRAY_DOUBLE_INDEX_SCALE, destination, ARRAY_DOUBLE_BASE_OFFSET + indexDestination << ARRAY_DOUBLE_INDEX_SHIFT, length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final  double[] get(final int index, final double[] buff, final double ... values) {
        return copy(index, buff, values);
    }

    public final  double[] put(final int index, final double[] buff, final double ... values) {
        return copy(index, buff, values);
    }

    public final  double[] copy(final int index, final double[] destination, final double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_DOUBLE_BASE_OFFSET, destination, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final  double[] get(final int length, final int indexBuff, final double[] buff, final int indexValues, final double ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  double[] put(final int length, final int indexBuff, final double[] buff, final int indexValues, final double ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final  double[] copy(final int length, final int indexDestination, final double[] destination, final int indexSource, final double ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_DOUBLE_BASE_OFFSET + indexSource << ARRAY_DOUBLE_INDEX_SHIFT, destination, ARRAY_DOUBLE_BASE_OFFSET + indexDestination << ARRAY_DOUBLE_INDEX_SHIFT, length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

            public final  double[] putOrdered(final long index, final double[] buff, final double value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, Double.doubleToRawLongBits(value));

                return buff;
            }

            public final  double[] putOrdered(final int index, final double[] buff, final double value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, Double.doubleToRawLongBits(value));

                return buff;
            }
    
    public static final long ARRAY_OBJECT_BASE_OFFSET = UNSAFE.ARRAY_OBJECT_BASE_OFFSET;
    public static final long ARRAY_OBJECT_INDEX_SCALE = UNSAFE.ARRAY_OBJECT_INDEX_SCALE > 0 ? UNSAFE.ARRAY_OBJECT_INDEX_SCALE : UNSAFE.ADDRESS_SIZE;
    public static final long ARRAY_OBJECT_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_OBJECT_INDEX_SCALE) - 1;

    public final <T> T get(final long index, final T ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (T) UNSAFE.getObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final <T> T[] put(final long index, final T[] buff, final T value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buff;
    }

    public final <T> T get(final int index, final T ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (T) UNSAFE.getObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final <T> T[] put(final int index, final T[] buff, final T value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buff;
    }

    public final <T> T getVolatile(final long index, final T ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return (T) UNSAFE.getObjectVolatile(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final <T> T[] putVolatile(final long index, final T[] buff, final T value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.putObjectVolatile(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buff;
    }

    public final <T> T getVolatile(final int index, final T ... buff) {
        if (SAFE)
            return buff[index];
        else
            return (T) UNSAFE.getObjectVolatile(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final <T> T[] putVolatile(final int index, final T[] buff, final T value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.putObjectVolatile(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buff;
    }

    public final <T> T[] get(final T[] buff, final T ... values) {
        return copy(buff, values);
    }

    public final <T> T[] put(final T[] buff, final T ... values) {
        return copy(buff, values);
    }

    public final <T> T[] copy(final T[] destination, final T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_OBJECT_BASE_OFFSET, destination, ARRAY_OBJECT_BASE_OFFSET, source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final <T> T[] get(final long index, final T[] buff, final T ... values) {
        return copy(index, buff, values);
    }

    public final <T> T[] put(final long index, final T[] buff, final T ... values) {
        return copy(index, buff, values);
    }

    public final <T> T[] copy(final long index, final T[] destination, final T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_OBJECT_BASE_OFFSET, destination, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final <T> T[] get(final long length, final long indexBuff, final T[] buff, final long indexValues, final T ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final <T> T[] put(final long length, final long indexBuff, final T[] buff, final long indexValues, final T ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final <T> T[] copy(final long length, final long indexDestination, final T[] destination, final long indexSource, final T ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_OBJECT_BASE_OFFSET + indexSource << ARRAY_OBJECT_INDEX_SCALE, destination, ARRAY_OBJECT_BASE_OFFSET + indexDestination << ARRAY_OBJECT_INDEX_SHIFT, length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final <T> T[] get(final int index, final T[] buff, final T ... values) {
        return copy(index, buff, values);
    }

    public final <T> T[] put(final int index, final T[] buff, final T ... values) {
        return copy(index, buff, values);
    }

    public final <T> T[] copy(final int index, final T[] destination, final T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_OBJECT_BASE_OFFSET, destination, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final <T> T[] get(final int length, final int indexBuff, final T[] buff, final int indexValues, final T ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final <T> T[] put(final int length, final int indexBuff, final T[] buff, final int indexValues, final T ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final <T> T[] copy(final int length, final int indexDestination, final T[] destination, final int indexSource, final T ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_OBJECT_BASE_OFFSET + indexSource << ARRAY_OBJECT_INDEX_SHIFT, destination, ARRAY_OBJECT_BASE_OFFSET + indexDestination << ARRAY_OBJECT_INDEX_SHIFT, length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

            public final <T> T[] putOrdered(final long index, final T[] buff, final T value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

                return buff;
            }

            public final <T> T[] putOrdered(final int index, final T[] buff, final T value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedObject(buff, ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

                return buff;
            }
    }