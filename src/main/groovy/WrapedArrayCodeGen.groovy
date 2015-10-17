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

String upcase1st(String str) {
    if (str == null || str.length() == 0)
        return ""

    return str.substring(0, 1).toUpperCase() + str.substring(1)
}

String arrayAccess(Class<?> type, boolean mutable) {
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : "Object"
    String typeSuffixCap = typeSuffix.toUpperCase()
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String generic = type.isPrimitive() ? "" : "<T>"
    String mutabilityPrefix = mutable ? "Mutable" : "Immutable"
    String finalField = mutable ? "volatile" : "final"

    StringBuilder buffer = new StringBuilder("""
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

import com.susico.utils.functions.*;

public final class ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final boolean SAFE;
    protected ${finalField} ${typeName}[] buffer;
    protected volatile long index = 0;

    proected static final long indexFieldOffset =
        getFieldOffset(${mutabilityPrefix}WrappedArrayAccess${typeSuffix}.class, "index");

    public ArrayAccess(final boolean SAFE, final ${typeName}[] array) {
        this.SAFE = SAFE;
        this.buffer = array;
    }

    ${
        mutable ? """
    public final ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic wrap(final ${typeName}[] array) {
        this.buffer = array

        return this;
    }""" : ""
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic checked(
        final boolean checked, final ${typeName}[] array) {
        return new ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic(checked, array);
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic checked(
        final ${typeName}[] array) {
        return checked(true, array);
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic unchecked(
        final ${typeName}[] array) {
        return checked(false, array);
    }

    protected static long getFieldOffset(Class<?> cls, String field) {
        try {
            return UNSAFE.objectFieldOffset(cls.getField(field));
        } catch (NoSuchFieldException e) {
            com.susico.utils.UncheckedExceptions.rethrow(e);
        }

        return 0L;
    }

    public final boolean isSafe() {
        return SAFE;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(final long value) {
        index = value;
    }

    public void resetIndex() {
        index = 0L;
    }

    public long incAndGetIndex() {
        return subAndGetIndex(1);
    }

    public long getAndIncIndex() {
        return getAndSubIndex(1);
    }

    public long decAndGetIndex() {
        return addAndGetIndex(1);
    }

    public long getAndDecIndex() {
        return getAndAddIndex(1);
    }

    public long getAndSetIndex(final long value) {
        UNSAFE.getAndSetLong(this, indexFieldOffset, value);
    }

    public final long getAndAddIndex(final long value) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, current + value));
        return current;
    }

    public final long addAndGetIndex(final long value) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = current + value;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long getAndSubIndex(final long value) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, current - value));
        return current;
    }

    public final long subAndGetIndex(final long value) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = current - value;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long getAndHalveIndex() {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, current >>> 1));
        return current;
    }

    public final long halveAndGetIndex() {
        long current;
        long newValue;

        do {
            current = index;
            newValue = current >>> 1;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long getAndDivIndex(final long value) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, current / value));
        return current;
    }

    public final long divAndGetIndex(final long value) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = current / value;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final boolean compareAndSwapIndex(final long expected, final long value) {
        return UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            expected, value);
    }

    public static  boolean inRange(final int index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final long index, final long length) {
        return index < length && index >= 0;
    }

    public final  boolean inRange(final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final int offset, final int length) {
        final int indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final int index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final int index, final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final int offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public final  boolean inRange(final int offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public final  boolean inRange(final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public final  boolean inRange(final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }
""")

    buffer.append("""
    public static final long ARRAY_${typeSuffixCap}_BASE_OFFSET = UNSAFE.ARRAY_${typeSuffixCap}_BASE_OFFSET;
    public static final long ARRAY_${typeSuffixCap}_INDEX_SCALE = UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE :
            ${
        typeSuffix.equalsIgnoreCase("BOOLEAN") ?
                1 :
                typeSuffix.equalsIgnoreCase("INT") ?
                        "Integer.BYTES" :
                        typeSuffix.equalsIgnoreCase("CHAR") ?
                                "Character.BYTES" :
                                typeSuffix.equalsIgnoreCase("OBJECT") ?
                                        "UNSAFE.ADDRESS_SIZE" :
                                        typeSuffix + ".BYTES"
    };

    public static final long ARRAY_${typeSuffixCap}_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_${typeSuffixCap}_INDEX_SCALE) - 1;

    public static $generic long contentByteSize(final $typeName ... buffer) {
        return buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final long contentByteSize() {
        return contentByteSize(buffer);
    }

    public static $generic long totalByteSize(final $typeName ... buffer) {
        return ARRAY_${typeSuffixCap}_BASE_OFFSET + buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final long totalByteSize() {
        return totalByteSize(buffer);
    }

    public static $generic void init(final byte value, final $typeName[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET,
            buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
    }

    public final void init(final byte value) {
        init(value, buffer);
    }

    public final $typeName[] copy(final $typeName[] destination) {
        return copy(destination, buffer);
    }

    public final $generic $typeName[] copy(final $typeName[] destination, final $typeName ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET, destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET,
                source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }
""")

    Class<?>[] indexTypes = [Integer.TYPE, Long.TYPE]

    for (Class<?> indexType : indexTypes) {
        String indexTypeName = indexType.getSimpleName();

        buffer.append("""
    ${
            indexTypes.equals(Long.TYPE) ? """
    public final boolean inRange() {
        return inRange(index);
    } """ : ""
        }

    public final boolean inRange(final ${indexTypeName} index) {
        return inRange(index, buffer);
    }

    public static $generic boolean inRange(final ${indexTypeName} index, final $typeName ... buffer) {
        return inRange(index, buffer.length);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndex() {
        checkIndex(index);
    }""" : ""
        }

    public final void checkIndex(final ${indexTypeName} index) {
        checkIndex(index, buffer);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final $typeName ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndexIfSafeOn() {
        checkIndexIfSafeOn(index);
    }""" : ""
        }

    public final void checkIndexIfSafeOn(final ${indexTypeName} index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static $generic void checkIndexIfSafeOn(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final boolean inRange(final long length) {
        return inRange(index, length);
    } """ : ""
        }

    public final boolean inRange(final ${indexTypeName} index, final long length) {
        return inRange(index, length, buffer);
    }

    public static $generic boolean inRange(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        return inRange(index, length, buffer.length);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndex(final long length) {
        checkIndex(index, length);
    }""" : ""
        }

    public final void checkIndex(final ${indexTypeName} index, final long length) {
        checkIndex(index, length, buffer);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        if (inRange(index, length, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + length, length, buffer.length));
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndexIfSafeOn(final long length) {
        checkIndexIfSafeOn(index, length);
    }""" : ""
        }

    public final void checkIndexIfSafeOn(final ${indexTypeName} index, final long length) {
        checkIndexIfSafeOn(index, length);
    }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static void checkIndexIfSafeOn(
        final boolean SAFE, final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, length, buffer);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $typeName get() {
        return get(index);
    }""" : ""
        }

    public final $typeName get(final ${indexTypeName} index) {
        return get(index, buffer);
    }

    public final $generic $typeName get(final ${indexTypeName} index, final $typeName ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static $generic $typeName get(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $typeName[] put(final $typeName value) {
        return put(index, value);
    }""" : ""
        }

    public final $typeName[] put(final ${indexTypeName} index, final $typeName value) {
        return put(index, buffer, value);
    }

    public final $generic $typeName[] put(final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName[] put(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $typeName getVolatile() {
        return getVolatile(index);
    }""" : ""
        }

    public final $typeName getVolatile(final ${indexTypeName} index) {
        return getVolatile(index, buffer);
    }

    public final $generic $typeName getVolatile(final ${indexTypeName} index, final $typeName ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static $generic $typeName getVolatile(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $typeName[] putVolatile(final $typeName value) {
        return putVolatile(index, value);
    } """ : ""
        }

    public final $typeName[] putVolatile(
        final ${indexTypeName} index, final $typeName value) {
        return putVolatile(index, buffer, value);
    }

    public final $generic $typeName[] putVolatile(
        final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName[] putVolatile(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $typeName[] copy(final $typeName ... source) {
        return copy(index, source);
    }""" : ""
        }

    public final $typeName[] copy(final ${indexTypeName} index, final $typeName ... source) {
        return copy(index, buffer, source);
    }

    public final $generic $typeName[] copy(final ${indexTypeName} index, final $typeName[] destination, final $typeName ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final ${
            indexTypeName
        } index, final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET,
                destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final $typeName[] copy(
        final ${indexTypeName} length,
        final ${indexTypeName} indexSource, final $typeName ... source) {
        return copy(length, indexDestination, indexSource, source);
    }

    public final $typeName[] copy(
        final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final ${indexTypeName} indexSource, final $typeName ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final $generic $typeName[] copy(
        final ${indexTypeName} length, final ${indexTypeName} indexDestination, final $typeName[] destination,
        final ${indexTypeName} indexSource, final $typeName ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static $generic $typeName[] copy(
        final boolean SAFE, final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final $typeName[] destination, final ${indexTypeName} indexSource, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + indexSource << ARRAY_${typeSuffixCap}_INDEX_SCALE,
                destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + indexDestination << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }
""")

        if (type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
                type.equals(Integer.TYPE) || type.equals(Long.TYPE) ||
                type.equals(Object.class)) {
            String valTransform = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                    type.equals(Double.TYPE) ? "Double.doubleToRawLongBits" :
                            type.equals(Float.TYPE) ? "Float.floatToRawIntBits" :
                                    ""
            String transformBack = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                    type.equals(Double.TYPE) ? "Double.longBitsToDouble" :
                            type.equals(Float.TYPE) ? "Float.intBitsToFloat" :
                                    ""
            String sameSizeNum = type.equals(Integer.TYPE) ? "Int" :
                    type.equals(Long.TYPE) ? "Long" :
                            type.equals(Float.TYPE) ? "Int" :
                                    type.equals(Double.TYPE) ? "Long" : "Object"

            buffer.append("""
    ${
                indexTypes.equals(Long.TYPE) ? """
    public final $typeName[] putOrdered(
        final $typeName value) {
        return putOrdered(index, value);
    } """ : ""
            }

    public final $typeName[] putOrdered(
        final ${indexTypeName} index, final $typeName value) {
        return putOrdered(index, buffer, value);
    }

    public final $generic $typeName[] putOrdered(
        final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName[] putOrdered(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrdered${sameSizeNum}(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                ${valTransform}(value));

        return buffer;
    }

    ${
                indexTypes.equals(Long.TYPE) ? """
    public final boolean compareAndSwap(
        final $typeName expected, final $typeName value) {
        return compareAndSwap(index, expected, value);
    }""" : ""
            }

    public final boolean compareAndSwap(
        final ${indexTypeName} index, final $typeName expected, final $typeName value) {
        return compareAndSwap(index, buffer, expected, value);
    }

    public final $generic boolean compareAndSwap(
        final ${indexTypeName} index, final $typeName[] buffer, final $typeName expected, final $typeName value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static $generic boolean compareAndSwap(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName expected,
        final $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwap${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(expected), ${valTransform}(value));
    }

    ${
                indexTypes.equals(Long.TYPE) ? """
    public final $typeName getAndSet(
        final $typeName value) {
        return ($typeName) getAndSet(index, value);
    }""" : ""
            }

    public final $typeName getAndSet(
        final ${indexTypeName} index, final $typeName value) {
        return ($typeName) getAndSet(index, buffer, value);
    }

    public final $generic $typeName getAndSet(
        final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return ($typeName) getAndSet(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName getAndSet(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return ($typeName) ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(value)));
    }
        """)

            String[] opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

            for (String opType : opTypes) {
                String valueParam = opType.startsWith("Multi") ?
                        ", final $typeName ... value" :
                        opType.startsWith("Bi") ?
                                ", final $typeName value" :
                                ""

                String applyOp = opType.startsWith("Multi") ?
                        "op.apply(current, value)" :
                        opType.startsWith("Bi") ?
                                "op.apply(current, value)" :
                                "op.apply(current)"

                String applyOpMulti = opType.startsWith("Multi") ?
                        ", value" :
                        opType.startsWith("Bi") ?
                                ", value" :
                                ""

                buffer.append("""
    ${
                    indexTypes.equals(Long.TYPE) ? """
    public final $typeName getAndUpdate(
        final ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index, op${applyOpMulti});
    }""" : ""
                }

    public final $typeName getAndUpdate(
        final ${indexTypeName} index, final ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index, buffer, op${applyOpMulti});
    }

    public final $generic $typeName getAndUpdate(
        final ${indexTypeName} index, final $typeName[] buffer, final ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static $generic $typeName getAndUpdate(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer,
        final ${opType}$generic op$valueParam) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        do {
            current = ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(${applyOp})));
        return current;
    }

    ${
                    indexTypes.equals(Long.TYPE) ? """
    public final $typeName updateAndGet(
        final ${opType}$generic op$valueParam) {
        return updateAndGet(index, op${applyOpMulti});
    }""" : ""
                }


    public final $typeName updateAndGet(
        final ${indexTypeName} index, final ${opType}$generic op$valueParam) {
        return updateAndGet(index, buffer, op${applyOpMulti});
    }

    public final $generic $typeName updateAndGet(
        final ${indexTypeName} index, final $typeName[] buffer, final ${opType}$generic op$valueParam) {
        return updateAndGet(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static $generic $typeName updateAndGet(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final ${
                    opType
                }$generic op$valueParam) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        $typeName newValue;
        do {
            current = ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
            newValue = ${applyOp};
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
            }
        }
    }

    return buffer.append("}").toString()
}

void arrayAccessGen() {
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]
    boolean[] mutableStates = [false, true]

    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\access\\")
    p.mkdirs()

    for (Class<?> type : types) {
        for (boolean mutable : mutableStates) {
            String mutabilityPrefix = mutable ? "Mutable" : "Immutable"
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : "Object"

            File f = new File(p, "${mutabilityPrefix}WrappedArrayAccess${typeSuffix}.java")
            f.createNewFile()

            PrintWriter pw = f.newPrintWriter()
            pw.print(arrayAccess(type, mutable))
            pw.flush()
            pw.close()
        }
    }
}

arrayAccessGen()