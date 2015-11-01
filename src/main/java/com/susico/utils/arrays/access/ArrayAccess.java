
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

package com.susico.utils.arrays.access;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public final class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true);
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false);

    @Contended protected final boolean SAFE;

    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    private ArrayAccess(final boolean SAFE) {
        this.SAFE = SAFE;
    }

    public final boolean isSafe() {
        return SAFE;
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

    public static  boolean inRange(final int index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }
    
    public static  boolean inRange(final long index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final long index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }
    
    public static final long ARRAY_BOOLEAN_BASE_OFFSET = UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET;
    public static final long ARRAY_BOOLEAN_INDEX_SCALE = UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE :
            1;

    public static final long ARRAY_BOOLEAN_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BOOLEAN_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull boolean ... buffer) {
        return buffer.length << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull boolean ... buffer) {
        return ARRAY_BOOLEAN_BASE_OFFSET + buffer.length << ARRAY_BOOLEAN_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull boolean[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_BOOLEAN_BASE_OFFSET,
            buffer.length << ARRAY_BOOLEAN_INDEX_SHIFT, value);
    }

    public final @NotNull  boolean[] copy(final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  boolean[] copy(final boolean SAFE, final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BOOLEAN_BASE_OFFSET, destination,
                ARRAY_BOOLEAN_BASE_OFFSET,
                source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull boolean ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull boolean ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull boolean ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull boolean ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull boolean ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull boolean ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull boolean ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull boolean ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  boolean get(final int index, final @NotNull boolean ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  boolean get(final boolean SAFE, final int index, final @NotNull boolean ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (boolean) UNSAFE.getBoolean(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final @NotNull  boolean[] put(final int index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  boolean[] put(
        final boolean SAFE, final int index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putBoolean(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  boolean getVolatile(final int index, final @NotNull boolean ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  boolean getVolatile(
        final boolean SAFE, final int index, final @NotNull boolean ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (boolean) UNSAFE.getBooleanVolatile(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final @NotNull  boolean[] putVolatile(
        final int index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  boolean[] putVolatile(
        final boolean SAFE, final int index, final @NotNull boolean[] buffer,
        final @NotNull boolean value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putBooleanVolatile(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  boolean[] copy(
        final int index, final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  boolean[] copy(final boolean SAFE, final int index, final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BOOLEAN_BASE_OFFSET,
                destination,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT,
                source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  boolean[] copy(
        final int length, final int indexDestination, final @NotNull boolean[] destination,
        final int indexSource, final @NotNull boolean ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  boolean[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull boolean[] destination, final int indexSource, final @NotNull boolean ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BOOLEAN_BASE_OFFSET + indexSource << ARRAY_BOOLEAN_INDEX_SCALE,
                destination,
                ARRAY_BOOLEAN_BASE_OFFSET + indexDestination << ARRAY_BOOLEAN_INDEX_SHIFT,
                length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final long index, final @NotNull boolean ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull boolean ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull boolean ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull boolean ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull boolean ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull boolean ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull boolean ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull boolean ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  boolean get(final long index, final @NotNull boolean ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  boolean get(final boolean SAFE, final long index, final @NotNull boolean ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (boolean) UNSAFE.getBoolean(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final @NotNull  boolean[] put(final long index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  boolean[] put(
        final boolean SAFE, final long index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putBoolean(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  boolean getVolatile(final long index, final @NotNull boolean ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  boolean getVolatile(
        final boolean SAFE, final long index, final @NotNull boolean ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (boolean) UNSAFE.getBooleanVolatile(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT);
    }

    public final @NotNull  boolean[] putVolatile(
        final long index, final @NotNull boolean[] buffer, final @NotNull boolean value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  boolean[] putVolatile(
        final boolean SAFE, final long index, final @NotNull boolean[] buffer,
        final @NotNull boolean value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putBooleanVolatile(buffer,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  boolean[] copy(
        final long index, final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  boolean[] copy(final boolean SAFE, final long index, final @NotNull boolean[] destination, final @NotNull boolean ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BOOLEAN_BASE_OFFSET,
                destination,
                ARRAY_BOOLEAN_BASE_OFFSET + index << ARRAY_BOOLEAN_INDEX_SHIFT,
                source.length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  boolean[] copy(
        final long length, final long indexDestination, final @NotNull boolean[] destination,
        final long indexSource, final @NotNull boolean ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  boolean[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull boolean[] destination, final long indexSource, final @NotNull boolean ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BOOLEAN_BASE_OFFSET + indexSource << ARRAY_BOOLEAN_INDEX_SCALE,
                destination,
                ARRAY_BOOLEAN_BASE_OFFSET + indexDestination << ARRAY_BOOLEAN_INDEX_SHIFT,
                length << ARRAY_BOOLEAN_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_BYTE_BASE_OFFSET = UNSAFE.ARRAY_BYTE_BASE_OFFSET;
    public static final long ARRAY_BYTE_INDEX_SCALE = UNSAFE.ARRAY_BYTE_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_BYTE_INDEX_SCALE :
            Byte.BYTES;

    public static final long ARRAY_BYTE_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BYTE_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull byte ... buffer) {
        return buffer.length << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull byte ... buffer) {
        return ARRAY_BYTE_BASE_OFFSET + buffer.length << ARRAY_BYTE_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull byte[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_BYTE_BASE_OFFSET,
            buffer.length << ARRAY_BYTE_INDEX_SHIFT, value);
    }

    public final @NotNull  byte[] copy(final @NotNull byte[] destination, final @NotNull byte ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  byte[] copy(final boolean SAFE, final @NotNull byte[] destination, final @NotNull byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BYTE_BASE_OFFSET, destination,
                ARRAY_BYTE_BASE_OFFSET,
                source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull byte ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull byte ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull byte ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull byte ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull byte ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull byte ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull byte ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull byte ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  byte get(final int index, final @NotNull byte ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  byte get(final boolean SAFE, final int index, final @NotNull byte ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (byte) UNSAFE.getByte(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final @NotNull  byte[] put(final int index, final @NotNull byte[] buffer, final @NotNull byte value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  byte[] put(
        final boolean SAFE, final int index, final @NotNull byte[] buffer, final @NotNull byte value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putByte(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  byte getVolatile(final int index, final @NotNull byte ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  byte getVolatile(
        final boolean SAFE, final int index, final @NotNull byte ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (byte) UNSAFE.getByteVolatile(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final @NotNull  byte[] putVolatile(
        final int index, final @NotNull byte[] buffer, final @NotNull byte value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  byte[] putVolatile(
        final boolean SAFE, final int index, final @NotNull byte[] buffer,
        final @NotNull byte value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putByteVolatile(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  byte[] copy(
        final int index, final @NotNull byte[] destination, final @NotNull byte ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  byte[] copy(final boolean SAFE, final int index, final @NotNull byte[] destination, final @NotNull byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BYTE_BASE_OFFSET,
                destination,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT,
                source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  byte[] copy(
        final int length, final int indexDestination, final @NotNull byte[] destination,
        final int indexSource, final @NotNull byte ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  byte[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull byte[] destination, final int indexSource, final @NotNull byte ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BYTE_BASE_OFFSET + indexSource << ARRAY_BYTE_INDEX_SCALE,
                destination,
                ARRAY_BYTE_BASE_OFFSET + indexDestination << ARRAY_BYTE_INDEX_SHIFT,
                length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final long index, final @NotNull byte ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull byte ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull byte ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull byte ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull byte ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull byte ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull byte ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull byte ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  byte get(final long index, final @NotNull byte ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  byte get(final boolean SAFE, final long index, final @NotNull byte ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (byte) UNSAFE.getByte(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final @NotNull  byte[] put(final long index, final @NotNull byte[] buffer, final @NotNull byte value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  byte[] put(
        final boolean SAFE, final long index, final @NotNull byte[] buffer, final @NotNull byte value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putByte(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  byte getVolatile(final long index, final @NotNull byte ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  byte getVolatile(
        final boolean SAFE, final long index, final @NotNull byte ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (byte) UNSAFE.getByteVolatile(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT);
    }

    public final @NotNull  byte[] putVolatile(
        final long index, final @NotNull byte[] buffer, final @NotNull byte value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  byte[] putVolatile(
        final boolean SAFE, final long index, final @NotNull byte[] buffer,
        final @NotNull byte value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putByteVolatile(buffer,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  byte[] copy(
        final long index, final @NotNull byte[] destination, final @NotNull byte ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  byte[] copy(final boolean SAFE, final long index, final @NotNull byte[] destination, final @NotNull byte ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BYTE_BASE_OFFSET,
                destination,
                ARRAY_BYTE_BASE_OFFSET + index << ARRAY_BYTE_INDEX_SHIFT,
                source.length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  byte[] copy(
        final long length, final long indexDestination, final @NotNull byte[] destination,
        final long indexSource, final @NotNull byte ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  byte[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull byte[] destination, final long indexSource, final @NotNull byte ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_BYTE_BASE_OFFSET + indexSource << ARRAY_BYTE_INDEX_SCALE,
                destination,
                ARRAY_BYTE_BASE_OFFSET + indexDestination << ARRAY_BYTE_INDEX_SHIFT,
                length << ARRAY_BYTE_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_CHAR_BASE_OFFSET = UNSAFE.ARRAY_CHAR_BASE_OFFSET;
    public static final long ARRAY_CHAR_INDEX_SCALE = UNSAFE.ARRAY_CHAR_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_CHAR_INDEX_SCALE :
            Character.BYTES;

    public static final long ARRAY_CHAR_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_CHAR_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull char ... buffer) {
        return buffer.length << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull char ... buffer) {
        return ARRAY_CHAR_BASE_OFFSET + buffer.length << ARRAY_CHAR_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull char[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_CHAR_BASE_OFFSET,
            buffer.length << ARRAY_CHAR_INDEX_SHIFT, value);
    }

    public final @NotNull  char[] copy(final @NotNull char[] destination, final @NotNull char ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  char[] copy(final boolean SAFE, final @NotNull char[] destination, final @NotNull char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_CHAR_BASE_OFFSET, destination,
                ARRAY_CHAR_BASE_OFFSET,
                source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull char ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull char ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull char ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull char ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull char ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull char ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull char ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull char ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  char get(final int index, final @NotNull char ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  char get(final boolean SAFE, final int index, final @NotNull char ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (char) UNSAFE.getChar(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final @NotNull  char[] put(final int index, final @NotNull char[] buffer, final @NotNull char value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  char[] put(
        final boolean SAFE, final int index, final @NotNull char[] buffer, final @NotNull char value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putChar(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  char getVolatile(final int index, final @NotNull char ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  char getVolatile(
        final boolean SAFE, final int index, final @NotNull char ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (char) UNSAFE.getCharVolatile(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final @NotNull  char[] putVolatile(
        final int index, final @NotNull char[] buffer, final @NotNull char value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  char[] putVolatile(
        final boolean SAFE, final int index, final @NotNull char[] buffer,
        final @NotNull char value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putCharVolatile(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  char[] copy(
        final int index, final @NotNull char[] destination, final @NotNull char ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  char[] copy(final boolean SAFE, final int index, final @NotNull char[] destination, final @NotNull char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_CHAR_BASE_OFFSET,
                destination,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT,
                source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  char[] copy(
        final int length, final int indexDestination, final @NotNull char[] destination,
        final int indexSource, final @NotNull char ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  char[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull char[] destination, final int indexSource, final @NotNull char ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_CHAR_BASE_OFFSET + indexSource << ARRAY_CHAR_INDEX_SCALE,
                destination,
                ARRAY_CHAR_BASE_OFFSET + indexDestination << ARRAY_CHAR_INDEX_SHIFT,
                length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final long index, final @NotNull char ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull char ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull char ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull char ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull char ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull char ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull char ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull char ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  char get(final long index, final @NotNull char ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  char get(final boolean SAFE, final long index, final @NotNull char ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (char) UNSAFE.getChar(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final @NotNull  char[] put(final long index, final @NotNull char[] buffer, final @NotNull char value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  char[] put(
        final boolean SAFE, final long index, final @NotNull char[] buffer, final @NotNull char value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putChar(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  char getVolatile(final long index, final @NotNull char ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  char getVolatile(
        final boolean SAFE, final long index, final @NotNull char ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (char) UNSAFE.getCharVolatile(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT);
    }

    public final @NotNull  char[] putVolatile(
        final long index, final @NotNull char[] buffer, final @NotNull char value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  char[] putVolatile(
        final boolean SAFE, final long index, final @NotNull char[] buffer,
        final @NotNull char value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putCharVolatile(buffer,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  char[] copy(
        final long index, final @NotNull char[] destination, final @NotNull char ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  char[] copy(final boolean SAFE, final long index, final @NotNull char[] destination, final @NotNull char ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_CHAR_BASE_OFFSET,
                destination,
                ARRAY_CHAR_BASE_OFFSET + index << ARRAY_CHAR_INDEX_SHIFT,
                source.length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  char[] copy(
        final long length, final long indexDestination, final @NotNull char[] destination,
        final long indexSource, final @NotNull char ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  char[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull char[] destination, final long indexSource, final @NotNull char ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_CHAR_BASE_OFFSET + indexSource << ARRAY_CHAR_INDEX_SCALE,
                destination,
                ARRAY_CHAR_BASE_OFFSET + indexDestination << ARRAY_CHAR_INDEX_SHIFT,
                length << ARRAY_CHAR_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_SHORT_BASE_OFFSET = UNSAFE.ARRAY_SHORT_BASE_OFFSET;
    public static final long ARRAY_SHORT_INDEX_SCALE = UNSAFE.ARRAY_SHORT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_SHORT_INDEX_SCALE :
            Short.BYTES;

    public static final long ARRAY_SHORT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_SHORT_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull short ... buffer) {
        return buffer.length << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull short ... buffer) {
        return ARRAY_SHORT_BASE_OFFSET + buffer.length << ARRAY_SHORT_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull short[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_SHORT_BASE_OFFSET,
            buffer.length << ARRAY_SHORT_INDEX_SHIFT, value);
    }

    public final @NotNull  short[] copy(final @NotNull short[] destination, final @NotNull short ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  short[] copy(final boolean SAFE, final @NotNull short[] destination, final @NotNull short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_SHORT_BASE_OFFSET, destination,
                ARRAY_SHORT_BASE_OFFSET,
                source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull short ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull short ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull short ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull short ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull short ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull short ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull short ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull short ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  short get(final int index, final @NotNull short ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  short get(final boolean SAFE, final int index, final @NotNull short ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (short) UNSAFE.getShort(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final @NotNull  short[] put(final int index, final @NotNull short[] buffer, final @NotNull short value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  short[] put(
        final boolean SAFE, final int index, final @NotNull short[] buffer, final @NotNull short value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putShort(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  short getVolatile(final int index, final @NotNull short ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  short getVolatile(
        final boolean SAFE, final int index, final @NotNull short ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (short) UNSAFE.getShortVolatile(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final @NotNull  short[] putVolatile(
        final int index, final @NotNull short[] buffer, final @NotNull short value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  short[] putVolatile(
        final boolean SAFE, final int index, final @NotNull short[] buffer,
        final @NotNull short value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putShortVolatile(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  short[] copy(
        final int index, final @NotNull short[] destination, final @NotNull short ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  short[] copy(final boolean SAFE, final int index, final @NotNull short[] destination, final @NotNull short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_SHORT_BASE_OFFSET,
                destination,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT,
                source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  short[] copy(
        final int length, final int indexDestination, final @NotNull short[] destination,
        final int indexSource, final @NotNull short ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  short[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull short[] destination, final int indexSource, final @NotNull short ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_SHORT_BASE_OFFSET + indexSource << ARRAY_SHORT_INDEX_SCALE,
                destination,
                ARRAY_SHORT_BASE_OFFSET + indexDestination << ARRAY_SHORT_INDEX_SHIFT,
                length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final long index, final @NotNull short ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull short ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull short ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull short ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull short ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull short ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull short ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull short ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  short get(final long index, final @NotNull short ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  short get(final boolean SAFE, final long index, final @NotNull short ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (short) UNSAFE.getShort(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final @NotNull  short[] put(final long index, final @NotNull short[] buffer, final @NotNull short value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  short[] put(
        final boolean SAFE, final long index, final @NotNull short[] buffer, final @NotNull short value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putShort(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  short getVolatile(final long index, final @NotNull short ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  short getVolatile(
        final boolean SAFE, final long index, final @NotNull short ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (short) UNSAFE.getShortVolatile(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT);
    }

    public final @NotNull  short[] putVolatile(
        final long index, final @NotNull short[] buffer, final @NotNull short value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  short[] putVolatile(
        final boolean SAFE, final long index, final @NotNull short[] buffer,
        final @NotNull short value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putShortVolatile(buffer,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  short[] copy(
        final long index, final @NotNull short[] destination, final @NotNull short ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  short[] copy(final boolean SAFE, final long index, final @NotNull short[] destination, final @NotNull short ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_SHORT_BASE_OFFSET,
                destination,
                ARRAY_SHORT_BASE_OFFSET + index << ARRAY_SHORT_INDEX_SHIFT,
                source.length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  short[] copy(
        final long length, final long indexDestination, final @NotNull short[] destination,
        final long indexSource, final @NotNull short ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  short[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull short[] destination, final long indexSource, final @NotNull short ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_SHORT_BASE_OFFSET + indexSource << ARRAY_SHORT_INDEX_SCALE,
                destination,
                ARRAY_SHORT_BASE_OFFSET + indexDestination << ARRAY_SHORT_INDEX_SHIFT,
                length << ARRAY_SHORT_INDEX_SHIFT);

        return destination;
    }

    public static final long ARRAY_INT_BASE_OFFSET = UNSAFE.ARRAY_INT_BASE_OFFSET;
    public static final long ARRAY_INT_INDEX_SCALE = UNSAFE.ARRAY_INT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_INT_INDEX_SCALE :
            Integer.BYTES;

    public static final long ARRAY_INT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_INT_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull int ... buffer) {
        return buffer.length << ARRAY_INT_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull int ... buffer) {
        return ARRAY_INT_BASE_OFFSET + buffer.length << ARRAY_INT_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull int[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_INT_BASE_OFFSET,
            buffer.length << ARRAY_INT_INDEX_SHIFT, value);
    }

    public final @NotNull  int[] copy(final @NotNull int[] destination, final @NotNull int ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  int[] copy(final boolean SAFE, final @NotNull int[] destination, final @NotNull int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_INT_BASE_OFFSET, destination,
                ARRAY_INT_BASE_OFFSET,
                source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull int ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull int ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull int ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull int ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull int ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull int ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull int ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull int ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  int get(final int index, final @NotNull int ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  int get(final boolean SAFE, final int index, final @NotNull int ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (int) UNSAFE.getInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final @NotNull  int[] put(final int index, final @NotNull int[] buffer, final @NotNull int value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] put(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  int getVolatile(final int index, final @NotNull int ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  int getVolatile(
        final boolean SAFE, final int index, final @NotNull int ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final @NotNull  int[] putVolatile(
        final int index, final @NotNull int[] buffer, final @NotNull int value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] putVolatile(
        final boolean SAFE, final int index, final @NotNull int[] buffer,
        final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  int[] copy(
        final int index, final @NotNull int[] destination, final @NotNull int ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  int[] copy(final boolean SAFE, final int index, final @NotNull int[] destination, final @NotNull int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_INT_BASE_OFFSET,
                destination,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
                source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  int[] copy(
        final int length, final int indexDestination, final @NotNull int[] destination,
        final int indexSource, final @NotNull int ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  int[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull int[] destination, final int indexSource, final @NotNull int ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_INT_BASE_OFFSET + indexSource << ARRAY_INT_INDEX_SCALE,
                destination,
                ARRAY_INT_BASE_OFFSET + indexDestination << ARRAY_INT_INDEX_SHIFT,
                length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  int[] putOrdered(
        final int index, final @NotNull int[] buffer, final @NotNull int value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] putOrdered(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final int index, final @NotNull int[] buffer, final @NotNull int expected, final @NotNull int value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull int expected,
        final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapInt(buffer,
            ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull  int getAndSet(
        final int index, final @NotNull int[] buffer, final @NotNull int value) {
        return (int) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int getAndSet(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (int) (UNSAFE.getAndSetInt(buffer,
            ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull  int getAndUpdate(
        final int index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final int index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  int getAndUpdate(
        final int index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final int index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  int getAndUpdate(
        final int index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final int index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final int index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public static  boolean inRange(final long index, final @NotNull int ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull int ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull int ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull int ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull int ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull int ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull int ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull int ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  int get(final long index, final @NotNull int ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  int get(final boolean SAFE, final long index, final @NotNull int ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (int) UNSAFE.getInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final @NotNull  int[] put(final long index, final @NotNull int[] buffer, final @NotNull int value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] put(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  int getVolatile(final long index, final @NotNull int ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  int getVolatile(
        final boolean SAFE, final long index, final @NotNull int ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
    }

    public final @NotNull  int[] putVolatile(
        final long index, final @NotNull int[] buffer, final @NotNull int value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] putVolatile(
        final boolean SAFE, final long index, final @NotNull int[] buffer,
        final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  int[] copy(
        final long index, final @NotNull int[] destination, final @NotNull int ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  int[] copy(final boolean SAFE, final long index, final @NotNull int[] destination, final @NotNull int ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_INT_BASE_OFFSET,
                destination,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
                source.length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  int[] copy(
        final long length, final long indexDestination, final @NotNull int[] destination,
        final long indexSource, final @NotNull int ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  int[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull int[] destination, final long indexSource, final @NotNull int ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_INT_BASE_OFFSET + indexSource << ARRAY_INT_INDEX_SCALE,
                destination,
                ARRAY_INT_BASE_OFFSET + indexDestination << ARRAY_INT_INDEX_SHIFT,
                length << ARRAY_INT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  int[] putOrdered(
        final long index, final @NotNull int[] buffer, final @NotNull int value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int[] putOrdered(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull int value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedInt(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final long index, final @NotNull int[] buffer, final @NotNull int expected, final @NotNull int value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull int expected,
        final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapInt(buffer,
            ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull  int getAndSet(
        final long index, final @NotNull int[] buffer, final @NotNull int value) {
        return (int) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  int getAndSet(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (int) (UNSAFE.getAndSetInt(buffer,
            ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull  int getAndUpdate(
        final long index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final long index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull BiOpInt op, final @NotNull int value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  int getAndUpdate(
        final long index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final long index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull UnaryOpInt op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  int getAndUpdate(
        final long index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        return (int) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int getAndUpdate(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  int updateAndGet(
        final long index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  int updateAndGet(
        final boolean SAFE, final long index, final @NotNull int[] buffer, final @NotNull MultiOpInt op, final @NotNull int ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        int current;
        int newValue;
        do {
            current = (int) UNSAFE.getIntVolatile(buffer,
                ARRAY_INT_BASE_OFFSET + index << ARRAY_INT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public static final long ARRAY_LONG_BASE_OFFSET = UNSAFE.ARRAY_LONG_BASE_OFFSET;
    public static final long ARRAY_LONG_INDEX_SCALE = UNSAFE.ARRAY_LONG_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_LONG_INDEX_SCALE :
            Long.BYTES;

    public static final long ARRAY_LONG_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_LONG_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull long ... buffer) {
        return buffer.length << ARRAY_LONG_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull long ... buffer) {
        return ARRAY_LONG_BASE_OFFSET + buffer.length << ARRAY_LONG_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull long[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_LONG_BASE_OFFSET,
            buffer.length << ARRAY_LONG_INDEX_SHIFT, value);
    }

    public final @NotNull  long[] copy(final @NotNull long[] destination, final @NotNull long ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  long[] copy(final boolean SAFE, final @NotNull long[] destination, final @NotNull long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_LONG_BASE_OFFSET, destination,
                ARRAY_LONG_BASE_OFFSET,
                source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull long ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull long ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull long ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull long ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull long ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull long ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull long ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull long ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  long get(final int index, final @NotNull long ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  long get(final boolean SAFE, final int index, final @NotNull long ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (long) UNSAFE.getLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final @NotNull  long[] put(final int index, final @NotNull long[] buffer, final @NotNull long value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] put(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  long getVolatile(final int index, final @NotNull long ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  long getVolatile(
        final boolean SAFE, final int index, final @NotNull long ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final @NotNull  long[] putVolatile(
        final int index, final @NotNull long[] buffer, final @NotNull long value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] putVolatile(
        final boolean SAFE, final int index, final @NotNull long[] buffer,
        final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  long[] copy(
        final int index, final @NotNull long[] destination, final @NotNull long ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  long[] copy(final boolean SAFE, final int index, final @NotNull long[] destination, final @NotNull long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_LONG_BASE_OFFSET,
                destination,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
                source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  long[] copy(
        final int length, final int indexDestination, final @NotNull long[] destination,
        final int indexSource, final @NotNull long ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  long[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull long[] destination, final int indexSource, final @NotNull long ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_LONG_BASE_OFFSET + indexSource << ARRAY_LONG_INDEX_SCALE,
                destination,
                ARRAY_LONG_BASE_OFFSET + indexDestination << ARRAY_LONG_INDEX_SHIFT,
                length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  long[] putOrdered(
        final int index, final @NotNull long[] buffer, final @NotNull long value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] putOrdered(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final int index, final @NotNull long[] buffer, final @NotNull long expected, final @NotNull long value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull long expected,
        final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapLong(buffer,
            ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull  long getAndSet(
        final int index, final @NotNull long[] buffer, final @NotNull long value) {
        return (long) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long getAndSet(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (long) (UNSAFE.getAndSetLong(buffer,
            ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull  long getAndUpdate(
        final int index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final int index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  long getAndUpdate(
        final int index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final int index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  long getAndUpdate(
        final int index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final int index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final int index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public static  boolean inRange(final long index, final @NotNull long ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull long ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull long ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull long ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull long ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull long ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull long ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull long ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  long get(final long index, final @NotNull long ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  long get(final boolean SAFE, final long index, final @NotNull long ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (long) UNSAFE.getLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final @NotNull  long[] put(final long index, final @NotNull long[] buffer, final @NotNull long value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] put(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  long getVolatile(final long index, final @NotNull long ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  long getVolatile(
        final boolean SAFE, final long index, final @NotNull long ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
    }

    public final @NotNull  long[] putVolatile(
        final long index, final @NotNull long[] buffer, final @NotNull long value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] putVolatile(
        final boolean SAFE, final long index, final @NotNull long[] buffer,
        final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  long[] copy(
        final long index, final @NotNull long[] destination, final @NotNull long ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  long[] copy(final boolean SAFE, final long index, final @NotNull long[] destination, final @NotNull long ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_LONG_BASE_OFFSET,
                destination,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
                source.length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  long[] copy(
        final long length, final long indexDestination, final @NotNull long[] destination,
        final long indexSource, final @NotNull long ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  long[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull long[] destination, final long indexSource, final @NotNull long ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_LONG_BASE_OFFSET + indexSource << ARRAY_LONG_INDEX_SCALE,
                destination,
                ARRAY_LONG_BASE_OFFSET + indexDestination << ARRAY_LONG_INDEX_SHIFT,
                length << ARRAY_LONG_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  long[] putOrdered(
        final long index, final @NotNull long[] buffer, final @NotNull long value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long[] putOrdered(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull long value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedLong(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final long index, final @NotNull long[] buffer, final @NotNull long expected, final @NotNull long value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull long expected,
        final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapLong(buffer,
            ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull  long getAndSet(
        final long index, final @NotNull long[] buffer, final @NotNull long value) {
        return (long) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  long getAndSet(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (long) (UNSAFE.getAndSetLong(buffer,
            ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull  long getAndUpdate(
        final long index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final long index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull BiOpLong op, final @NotNull long value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  long getAndUpdate(
        final long index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final long index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull UnaryOpLong op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull  long getAndUpdate(
        final long index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        return (long) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long getAndUpdate(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull  long updateAndGet(
        final long index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  long updateAndGet(
        final boolean SAFE, final long index, final @NotNull long[] buffer, final @NotNull MultiOpLong op, final @NotNull long ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        long current;
        long newValue;
        do {
            current = (long) UNSAFE.getLongVolatile(buffer,
                ARRAY_LONG_BASE_OFFSET + index << ARRAY_LONG_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public static final long ARRAY_FLOAT_BASE_OFFSET = UNSAFE.ARRAY_FLOAT_BASE_OFFSET;
    public static final long ARRAY_FLOAT_INDEX_SCALE = UNSAFE.ARRAY_FLOAT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_FLOAT_INDEX_SCALE :
            Float.BYTES;

    public static final long ARRAY_FLOAT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_FLOAT_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull float ... buffer) {
        return buffer.length << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull float ... buffer) {
        return ARRAY_FLOAT_BASE_OFFSET + buffer.length << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull float[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_FLOAT_BASE_OFFSET,
            buffer.length << ARRAY_FLOAT_INDEX_SHIFT, value);
    }

    public final @NotNull  float[] copy(final @NotNull float[] destination, final @NotNull float ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  float[] copy(final boolean SAFE, final @NotNull float[] destination, final @NotNull float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_FLOAT_BASE_OFFSET, destination,
                ARRAY_FLOAT_BASE_OFFSET,
                source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull float ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull float ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull float ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull float ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull float ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull float ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  float get(final int index, final @NotNull float ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  float get(final boolean SAFE, final int index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloat(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final @NotNull  float[] put(final int index, final @NotNull float[] buffer, final @NotNull float value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] put(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloat(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  float getVolatile(final int index, final @NotNull float ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  float getVolatile(
        final boolean SAFE, final int index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final @NotNull  float[] putVolatile(
        final int index, final @NotNull float[] buffer, final @NotNull float value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putVolatile(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  float[] copy(
        final int index, final @NotNull float[] destination, final @NotNull float ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  float[] copy(final boolean SAFE, final int index, final @NotNull float[] destination, final @NotNull float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_FLOAT_BASE_OFFSET,
                destination,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
                source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  float[] copy(
        final int length, final int indexDestination, final @NotNull float[] destination,
        final int indexSource, final @NotNull float ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  float[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull float[] destination, final int indexSource, final @NotNull float ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_FLOAT_BASE_OFFSET + indexSource << ARRAY_FLOAT_INDEX_SCALE,
                destination,
                ARRAY_FLOAT_BASE_OFFSET + indexDestination << ARRAY_FLOAT_INDEX_SHIFT,
                length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  float[] putOrdered(
        final int index, final @NotNull float[] buffer, final @NotNull float value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putOrdered(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedInt(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
                Float.floatToRawIntBits(value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final int index, final @NotNull float[] buffer, final @NotNull float expected, final @NotNull float value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull float expected,
        final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapInt(buffer,
            ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
            Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
    }

    public final @NotNull  float getAndSet(
        final int index, final @NotNull float[] buffer, final @NotNull float value) {
        return (float) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float getAndSet(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (float) Float.intBitsToFloat(UNSAFE.getAndSetInt(buffer,
            ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
            Float.floatToRawIntBits(value)));
    }
        
    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public static  boolean inRange(final long index, final @NotNull float ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull float ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull float ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull float ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull float ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull float ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  float get(final long index, final @NotNull float ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  float get(final boolean SAFE, final long index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloat(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final @NotNull  float[] put(final long index, final @NotNull float[] buffer, final @NotNull float value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] put(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloat(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  float getVolatile(final long index, final @NotNull float ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  float getVolatile(
        final boolean SAFE, final long index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    public final @NotNull  float[] putVolatile(
        final long index, final @NotNull float[] buffer, final @NotNull float value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putVolatile(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  float[] copy(
        final long index, final @NotNull float[] destination, final @NotNull float ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  float[] copy(final boolean SAFE, final long index, final @NotNull float[] destination, final @NotNull float ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_FLOAT_BASE_OFFSET,
                destination,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
                source.length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  float[] copy(
        final long length, final long indexDestination, final @NotNull float[] destination,
        final long indexSource, final @NotNull float ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  float[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull float[] destination, final long indexSource, final @NotNull float ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_FLOAT_BASE_OFFSET + indexSource << ARRAY_FLOAT_INDEX_SCALE,
                destination,
                ARRAY_FLOAT_BASE_OFFSET + indexDestination << ARRAY_FLOAT_INDEX_SHIFT,
                length << ARRAY_FLOAT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  float[] putOrdered(
        final long index, final @NotNull float[] buffer, final @NotNull float value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putOrdered(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedInt(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
                Float.floatToRawIntBits(value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final long index, final @NotNull float[] buffer, final @NotNull float expected, final @NotNull float value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull float expected,
        final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapInt(buffer,
            ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
            Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
    }

    public final @NotNull  float getAndSet(
        final long index, final @NotNull float[] buffer, final @NotNull float value) {
        return (float) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float getAndSet(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (float) Float.intBitsToFloat(UNSAFE.getAndSetInt(buffer,
            ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT,
            Float.floatToRawIntBits(value)));
    }
        
    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        float newValue;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

        return newValue;
    }

    public static final long ARRAY_DOUBLE_BASE_OFFSET = UNSAFE.ARRAY_DOUBLE_BASE_OFFSET;
    public static final long ARRAY_DOUBLE_INDEX_SCALE = UNSAFE.ARRAY_DOUBLE_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_DOUBLE_INDEX_SCALE :
            Double.BYTES;

    public static final long ARRAY_DOUBLE_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_DOUBLE_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull double ... buffer) {
        return buffer.length << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static  long totalByteSize(final @NotNull double ... buffer) {
        return ARRAY_DOUBLE_BASE_OFFSET + buffer.length << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public static  void init(final byte value, final @NotNull double[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_DOUBLE_BASE_OFFSET,
            buffer.length << ARRAY_DOUBLE_INDEX_SHIFT, value);
    }

    public final @NotNull  double[] copy(final @NotNull double[] destination, final @NotNull double ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull  double[] copy(final boolean SAFE, final @NotNull double[] destination, final @NotNull double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_DOUBLE_BASE_OFFSET, destination,
                ARRAY_DOUBLE_BASE_OFFSET,
                source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public static  boolean inRange(final int index, final @NotNull double ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull double ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final int index, final @NotNull double ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull double ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull double ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull double ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  double get(final int index, final @NotNull double ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  double get(final boolean SAFE, final int index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDouble(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final @NotNull  double[] put(final int index, final @NotNull double[] buffer, final @NotNull double value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] put(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDouble(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  double getVolatile(final int index, final @NotNull double ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  double getVolatile(
        final boolean SAFE, final int index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final @NotNull  double[] putVolatile(
        final int index, final @NotNull double[] buffer, final @NotNull double value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putVolatile(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  double[] copy(
        final int index, final @NotNull double[] destination, final @NotNull double ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  double[] copy(final boolean SAFE, final int index, final @NotNull double[] destination, final @NotNull double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_DOUBLE_BASE_OFFSET,
                destination,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
                source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  double[] copy(
        final int length, final int indexDestination, final @NotNull double[] destination,
        final int indexSource, final @NotNull double ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  double[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull double[] destination, final int indexSource, final @NotNull double ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_DOUBLE_BASE_OFFSET + indexSource << ARRAY_DOUBLE_INDEX_SCALE,
                destination,
                ARRAY_DOUBLE_BASE_OFFSET + indexDestination << ARRAY_DOUBLE_INDEX_SHIFT,
                length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  double[] putOrdered(
        final int index, final @NotNull double[] buffer, final @NotNull double value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putOrdered(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedLong(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
                Double.doubleToRawLongBits(value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final int index, final @NotNull double[] buffer, final @NotNull double expected, final @NotNull double value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull double expected,
        final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapLong(buffer,
            ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
    }

    public final @NotNull  double getAndSet(
        final int index, final @NotNull double[] buffer, final @NotNull double value) {
        return (double) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double getAndSet(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(buffer,
            ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
            Double.doubleToRawLongBits(value)));
    }
        
    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public static  boolean inRange(final long index, final @NotNull double ... buffer) {
        return inRange(index, buffer.length);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull double ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static  void checkIndex(final long index, final @NotNull double ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull double ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull double ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final  void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull double ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull  double get(final long index, final @NotNull double ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull  double get(final boolean SAFE, final long index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDouble(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final @NotNull  double[] put(final long index, final @NotNull double[] buffer, final @NotNull double value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] put(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDouble(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  double getVolatile(final long index, final @NotNull double ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  double getVolatile(
        final boolean SAFE, final long index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    public final @NotNull  double[] putVolatile(
        final long index, final @NotNull double[] buffer, final @NotNull double value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putVolatile(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull  double[] copy(
        final long index, final @NotNull double[] destination, final @NotNull double ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  double[] copy(final boolean SAFE, final long index, final @NotNull double[] destination, final @NotNull double ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_DOUBLE_BASE_OFFSET,
                destination,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
                source.length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  double[] copy(
        final long length, final long indexDestination, final @NotNull double[] destination,
        final long indexSource, final @NotNull double ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull  double[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull double[] destination, final long indexSource, final @NotNull double ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_DOUBLE_BASE_OFFSET + indexSource << ARRAY_DOUBLE_INDEX_SCALE,
                destination,
                ARRAY_DOUBLE_BASE_OFFSET + indexDestination << ARRAY_DOUBLE_INDEX_SHIFT,
                length << ARRAY_DOUBLE_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull  double[] putOrdered(
        final long index, final @NotNull double[] buffer, final @NotNull double value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putOrdered(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedLong(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
                Double.doubleToRawLongBits(value));

        return buffer;
    }

    public final  boolean compareAndSwap(
        final long index, final @NotNull double[] buffer, final @NotNull double expected, final @NotNull double value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static  boolean compareAndSwap(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull double expected,
        final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapLong(buffer,
            ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
    }

    public final @NotNull  double getAndSet(
        final long index, final @NotNull double[] buffer, final @NotNull double value) {
        return (double) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double getAndSet(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(buffer,
            ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT,
            Double.doubleToRawLongBits(value)));
    }
        
    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        double newValue;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public static final long ARRAY_OBJECT_BASE_OFFSET = UNSAFE.ARRAY_OBJECT_BASE_OFFSET;
    public static final long ARRAY_OBJECT_INDEX_SCALE = UNSAFE.ARRAY_OBJECT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_OBJECT_INDEX_SCALE :
            UNSAFE.ADDRESS_SIZE;

    public static final long ARRAY_OBJECT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_OBJECT_INDEX_SCALE) - 1;

    public static <T> long contentByteSize(final @NotNull T ... buffer) {
        return buffer.length << ARRAY_OBJECT_INDEX_SHIFT;
    }

    public static <T> long totalByteSize(final @NotNull T ... buffer) {
        return ARRAY_OBJECT_BASE_OFFSET + buffer.length << ARRAY_OBJECT_INDEX_SHIFT;
    }

    public static <T> void init(final byte value, final @NotNull T[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_OBJECT_BASE_OFFSET,
            buffer.length << ARRAY_OBJECT_INDEX_SHIFT, value);
    }

    public final @NotNull <T> T[] copy(final @NotNull T[] destination, final @NotNull T ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull <T> T[] copy(final boolean SAFE, final @NotNull T[] destination, final @NotNull T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_OBJECT_BASE_OFFSET, destination,
                ARRAY_OBJECT_BASE_OFFSET,
                source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public static <T> boolean inRange(final int index, final @NotNull T ... buffer) {
        return inRange(index, buffer.length);
    }

    public static <T> boolean inRangeWithOffset(final int index, final int offset, final @NotNull T ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static <T> void checkIndex(final int index, final @NotNull T ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final <T> void checkIndexIfSafeOn(final int index, final @NotNull T ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static <T> void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull T ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static <T> void checkIndexWithOffset(final int index, final int offset, final @NotNull T ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final <T> void checkIndexIfSafeOnWithOffset(
        final int index, final int offset, final @NotNull T ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static <T> void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull T ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull <T> T get(final int index, final @NotNull T ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull <T> T get(final boolean SAFE, final int index, final @NotNull T ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (T) UNSAFE.getObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final @NotNull <T> T[] put(final int index, final @NotNull T[] buffer, final @NotNull T value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] put(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull <T> T getVolatile(final int index, final @NotNull T ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull <T> T getVolatile(
        final boolean SAFE, final int index, final @NotNull T ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final @NotNull <T> T[] putVolatile(
        final int index, final @NotNull T[] buffer, final @NotNull T value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] putVolatile(
        final boolean SAFE, final int index, final @NotNull T[] buffer,
        final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull <T> T[] copy(
        final int index, final @NotNull T[] destination, final @NotNull T ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull <T> T[] copy(final boolean SAFE, final int index, final @NotNull T[] destination, final @NotNull T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_OBJECT_BASE_OFFSET,
                destination,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
                source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull <T> T[] copy(
        final int length, final int indexDestination, final @NotNull T[] destination,
        final int indexSource, final @NotNull T ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull <T> T[] copy(
        final boolean SAFE, final int length, final int indexDestination,
        final @NotNull T[] destination, final int indexSource, final @NotNull T ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_OBJECT_BASE_OFFSET + indexSource << ARRAY_OBJECT_INDEX_SCALE,
                destination,
                ARRAY_OBJECT_BASE_OFFSET + indexDestination << ARRAY_OBJECT_INDEX_SHIFT,
                length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull <T> T[] putOrdered(
        final int index, final @NotNull T[] buffer, final @NotNull T value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] putOrdered(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final <T> boolean compareAndSwap(
        final int index, final @NotNull T[] buffer, final @NotNull T expected, final @NotNull T value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static <T> boolean compareAndSwap(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull T expected,
        final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapObject(buffer,
            ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull <T> T getAndSet(
        final int index, final @NotNull T[] buffer, final @NotNull T value) {
        return (T) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T getAndSet(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (T) (UNSAFE.getAndSetObject(buffer,
            ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull <T> T getAndUpdate(
        final int index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final int index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull <T> T getAndUpdate(
        final int index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final int index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull <T> T getAndUpdate(
        final int index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final int index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final int index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public static <T> boolean inRange(final long index, final @NotNull T ... buffer) {
        return inRange(index, buffer.length);
    }

    public static <T> boolean inRangeWithOffset(final long index, final long offset, final @NotNull T ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    public static <T> void checkIndex(final long index, final @NotNull T ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final <T> void checkIndexIfSafeOn(final long index, final @NotNull T ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static <T> void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull T ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static <T> void checkIndexWithOffset(final long index, final long offset, final @NotNull T ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final <T> void checkIndexIfSafeOnWithOffset(
        final long index, final long offset, final @NotNull T ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static <T> void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull T ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull <T> T get(final long index, final @NotNull T ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull <T> T get(final boolean SAFE, final long index, final @NotNull T ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (T) UNSAFE.getObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final @NotNull <T> T[] put(final long index, final @NotNull T[] buffer, final @NotNull T value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] put(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull <T> T getVolatile(final long index, final @NotNull T ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull <T> T getVolatile(
        final boolean SAFE, final long index, final @NotNull T ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
    }

    public final @NotNull <T> T[] putVolatile(
        final long index, final @NotNull T[] buffer, final @NotNull T value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] putVolatile(
        final boolean SAFE, final long index, final @NotNull T[] buffer,
        final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull <T> T[] copy(
        final long index, final @NotNull T[] destination, final @NotNull T ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull <T> T[] copy(final boolean SAFE, final long index, final @NotNull T[] destination, final @NotNull T ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_OBJECT_BASE_OFFSET,
                destination,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
                source.length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull <T> T[] copy(
        final long length, final long indexDestination, final @NotNull T[] destination,
        final long indexSource, final @NotNull T ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull <T> T[] copy(
        final boolean SAFE, final long length, final long indexDestination,
        final @NotNull T[] destination, final long indexSource, final @NotNull T ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_OBJECT_BASE_OFFSET + indexSource << ARRAY_OBJECT_INDEX_SCALE,
                destination,
                ARRAY_OBJECT_BASE_OFFSET + indexDestination << ARRAY_OBJECT_INDEX_SHIFT,
                length << ARRAY_OBJECT_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull <T> T[] putOrdered(
        final long index, final @NotNull T[] buffer, final @NotNull T value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T[] putOrdered(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull T value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrderedObject(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
                (value));

        return buffer;
    }

    public final <T> boolean compareAndSwap(
        final long index, final @NotNull T[] buffer, final @NotNull T expected, final @NotNull T value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static <T> boolean compareAndSwap(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull T expected,
        final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwapObject(buffer,
            ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
            (expected), (value));
    }

    public final @NotNull <T> T getAndSet(
        final long index, final @NotNull T[] buffer, final @NotNull T value) {
        return (T) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull <T> T getAndSet(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return (T) (UNSAFE.getAndSetObject(buffer,
            ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT,
            (value)));
    }
        
    public final @NotNull <T> T getAndUpdate(
        final long index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final long index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull BiOpObject<T> op, final @NotNull T value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull <T> T getAndUpdate(
        final long index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final long index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull UnaryOpObject<T> op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull <T> T getAndUpdate(
        final long index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        return (T) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T getAndUpdate(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (op.apply(current, value))));
        return current;
    }

    public final @NotNull <T> T updateAndGet(
        final long index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull <T> T updateAndGet(
        final boolean SAFE, final long index, final @NotNull T[] buffer, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        T current;
        T newValue;
        do {
            current = (T) UNSAFE.getObjectVolatile(buffer,
                ARRAY_OBJECT_BASE_OFFSET + index << ARRAY_OBJECT_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(buffer, index,
            (current), (newValue)));

        return newValue;
    }
}