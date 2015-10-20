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

import org.jetbrains.annotations.*;

import java.util.concurrent.locks.LockSupport;

public final class ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final boolean SAFE;
    protected ${finalField} ${typeName}[] buffer;
    protected volatile long index = 0;
    protected volatile long offset = 0;
    protected volatile long inc = 1;

    protected static final long indexFieldOffset =
        UnsafeAccess.getFieldOffset(${mutabilityPrefix}WrappedArrayAccess${typeSuffix}.class, "index");
    protected static final long offsetFieldOffset =
        UnsafeAccess.getFieldOffset(${mutabilityPrefix}WrappedArrayAccess${typeSuffix}.class, "offset");
    protected static final long incFieldOffset =
        UnsafeAccess.getFieldOffset(${mutabilityPrefix}WrappedArrayAccess${typeSuffix}.class, "inc");

    public ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}(final boolean SAFE, final ${typeName}[] array) {
        this.SAFE = SAFE;
        this.buffer = array;
    }

    ${
        mutable ? """
    private volatile boolean wrapGuard = false;

    public final ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic wrap(final @NotNull ${typeName}[] array) {
        try {
            while (wrapGuard)
                LockSupport.parkNanos(1);

            this.wrapGuard = true;

            this.buffer = array
            this.index = 0L;
            this.offset = 0L;
            this.inc = 1L;

            return this;
        } finally {
            this.wrapGuard = false;
        }
    }""" : ""
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic checked(
        final boolean checked, final @NotNull ${typeName}[] array) {
        return new ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic(checked, array);
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic checked(
        final @NotNull ${typeName}[] array) {
        return checked(true, array);
    }

    public static $generic ${mutabilityPrefix}WrappedArrayAccess${typeSuffix}$generic unchecked(
        final @NotNull ${typeName}[] array) {
        return checked(false, array);
    }

    public static long rollIndex(final long index, final int length) {
        return (index % length) & Long.MAX_VALUE;
    }

    public final long rollIndex(final long index) {
        return rollIndex(index, buffer.length);
    }

    public static long rollOffset(final long index, final long offset, final int length) {
        return (((index + offset) % length) & Long.MAX_VALUE) - index;
    }

    public final long rollOffset(final long index, final long offset) {
        return rollOffset(index, offset, buffer.length);
    }

    public final boolean isSafe() {
        return SAFE;
    }

    public final long getIncrementCounter() {
        return index;
    }

    public final long getIndex() {
        return index;
    }

    public final long getOffset() {
        return offset;
    }

    public final long getIncrementCounterWeak() {
        return UNSAFE.getLong(this, incFieldOffset);
    }

    public final long getIndexWeak() {
        return UNSAFE.getLong(this, indexFieldOffset);
    }

    public final long getOffseWeak() {
        return UNSAFE.getLong(this, offsetFieldOffset);
    }

    public final void setIncrementCounter(final long value) {
        inc = value;
    }

    public final void setIndex(final long value) {
        index = rollIndex(value);
    }

    public final void setOffset(final long value) {
        offset = rollOffset(index, value);
    }

    public final void putIncrementCounterWeak(final long value) {
        UNSAFE.putLong(this, incFieldOffset, value);
    }

    public final void putOrderedIncrementCounter(final long value) {
        UNSAFE.putOrderedLong(this, incFieldOffset, value);
    }

    public final void putOffsetWeak(final long value) {
        UNSAFE.putLong(this, offsetFieldOffset, rollOffset(index, value));
    }

    public final void putOrderedOffset(final long value) {
        UNSAFE.putOrderedLong(this, offsetFieldOffset, rollOffset(index, value));
    }

    public final void putIndexWeak(final long value) {
        UNSAFE.putLong(this, indexFieldOffset, rollIndex(value));
    }

    public final void putOrderedIndex(final long value) {
        UNSAFE.putOrderedLong(this, indexFieldOffset, rollIndex(value));
    }

    public final void resetIndex() {
        index = 0L;
    }

    public final void resetOffset() {
        offset = 0L;
    }

    public final void resetIncrementCounter() {
        inc = 0L;
    }

    public final void resetIndexToLast() {
        index = buffer.length - 1;
    }

    public final long incAndGetIndexWeak() {
        index = rollIndex(index + inc);

        return index;
    }

    public final long incAndGetOffsetWeak() {
        offset = rollOffset(index, offset + inc);

        return offset;
    }

    public final long incAndGetIndex() {
        return subAndGetIndex(inc);
    }

    public final long getAndIncIndex() {
        return getAndSubIndex(inc);
    }

    public final long decAndGetIndexWeak() {
        index = rollIndex(index - inc);

        return index;
    }

    public final long decAndGetOffsetWeak() {
        offset = rollOffset(index, offset - inc);

        return offset;
    }

    public final long decAndGetIndex() {
        return addAndGetIndex(inc);
    }

    public final long getAndDecIndex() {
        return getAndAddIndex(inc);
    }

    public final long incBy1AndGetIndex() {
        return addAndGetIndex(1);
    }

    public final long getAndIncBy1Index() {
        return getAndSubIndex(1);
    }

    public final long decBy1AndGetIndex() {
        return addAndGetIndex(1);
    }

    public final long getAndDecBy1Index() {
        return getAndAddIndex(1);
    }

    public final long incBy1AndGetIndexWeak() {
        index = rollIndex(index + 1);

        return index;
    }

    public final long decBy1AndGetIndexWeak() {
        index = rollIndex(index - 1);

        return index;
    }

    public final long incBy1AndGetOffsetWeak() {
        offset = rollOffset(index, offset + 1);

        return offset;
    }

    public final long decBy1AndGetOffsetWeak() {
        offset = rollOffset(index, offset - 1);

        return offset;
    }

    public final long incAndGetOffset() {
        return addAndGetOffset(inc);
    }

    public final long getAndIncOffset() {
        return getAndSubOffset(inc);
    }

    public final long decAndGetOffset() {
        return addAndGetOffset(inc);
    }

    public final long getAndDecOffset() {
        return getAndAddOffset(inc);
    }

    public final long incBy1AndGetOffset() {
        return subAndGetOffset(1);
    }

    public final long getAndIncBy1Offset() {
        return getAndSubOffset(1);
    }

    public final long decBy1AndGetOffset() {
        return addAndGetOffset(1);
    }

    public final long getAndDecBy1Offset() {
        return getAndAddOffset(1);
    }

    public final long getAndSetOffset(final long value) {
        return UNSAFE.getAndSetLong(this, offsetFieldOffset, value);
    }

    public final long getAndSetIndex(final long value) {
        return UNSAFE.getAndSetLong(this, indexFieldOffset, rollIndex(value));
    }

    public final long getAndSetIncrementCounter(final long value) {
        return UNSAFE.getAndSetLong(this, incFieldOffset, value);
    }

    public final long getAndAddOffset(final long value) {
        long current;

        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, current + value)));
        return current;
    }

    public final long getAndAddIndex(final long value) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(current + value)));
        return current;
    }

    public final long getAndAddIncrementCounter(final long value) {
        long current;

        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, current + value));
        return current;
    }

    public final long addAndGetOffset(final long value) {
        long current;
        long newValue;

        do {
            current = offset;
            newValue = rollOffset(index, current + value);
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long addAndGetIndex(final long value) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = rollIndex(current + value);
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long addAndGetIndexWeak(final long value) {
        index = rollIndex(index + value);

        return index;
    }

    public final long addAndGetOffsetWeak(final long value) {
        offset = rollOffset(index, offset + value);

        return offset;
    }

    public final long addAndGetIncrementCounter(final long value) {
        long current;
        long newValue;

        do {
            current = inc;
            newValue = current + value;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long getAndSubOffset(final long value) {
        long current;

        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, current - value)));
        return current;
    }

    public final long getAndSubIndex(final long value) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(current - value)));
        return current;
    }

    public final long subAndGetIncrementCounter(final long value) {
        long current;
        long newValue;

        do {
            current = inc;
            newValue = current - value;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long subAndGetOffset(final long value) {
        long current;
        long newValue;

        do {
            current = offset;
            newValue = rollOffset(index, current - value);
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long subAndGetIndexWeak(final long value) {
        index = rollIndex(index - value);

        return index;
    }

    public final long subAndGetOffsetWeak(final long value) {
        offset = rollOffset(index, offset - value);

        return offset;
    }

    public final long subAndGetIndex(final long value) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = rollIndex(current - value);
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long getAndBiDivIncrementCounter(final int level) {
        long current;

        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, buffer.length >>> level));
        return current;
    }

    public final long getAndBiDivIndex(final int level, final int slice) {
        long current;

        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, (buffer.length >>> level) * slice));
        return current;
    }

    public final long biDivAndGetIncrementCounter(final int level) {
        long current;
        long newValue;

        do {
            current = inc;
            newValue = buffer.length >>> level;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

    public final long biDivAndGetIndex(final int level, final int slice) {
        long current;
        long newValue;

        do {
            current = index;
            newValue = (buffer.length >>> level) * slice;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }
    """)

    String[] opTypes = ["BiOpLong", "UnaryOpLong", "MultiOpLong"]

    for (String opType : opTypes) {
        String valueParam = opType.startsWith("Multi") ?
                ", final @NotNull long ... value" :
                opType.startsWith("Bi") ?
                        ", final long value" :
                        ""

        String applyOp = opType.startsWith("Multi") ?
                "op.apply(current, value)" :
                opType.startsWith("Bi") ?
                        "op.apply(current, value)" :
                        "op.apply(current)"

        buffer.append("""
    public  final long getAndUpdateIndex(
        final @NotNull ${opType} op$valueParam) {

        long current;
        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(${applyOp})));
        return current;
    }

    public  final long updateAndGetIndex(
        final @NotNull ${opType} op$valueParam) {

        long current;
        long newValue;
        do {
            current = index;
            newValue = rollIndex(${applyOp});
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long updateAndGetIndexWeak(
        final @NotNull ${opType} op$valueParam) {
        final long current = index;
        index = rollIndex(${applyOp});

        return index;
    }

    public  final long updateAndGetOffsetWeak(
        final @NotNull ${opType} op$valueParam) {
        final long current = offset;
        offset = rollOffset(index, ${applyOp});

        return offset;
    }

    public  final long getAndUpdateOffset(
        final @NotNull ${opType} op$valueParam) {

        long current;
        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, ${applyOp})));
        return current;
    }

    public  final long updateAndGetOffset(
        final @NotNull ${opType} op$valueParam) {

        long current;
        long newValue;
        do {
            current = offset;
            newValue = rollOffset(index, ${applyOp});
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIncrementCounter(
        final @NotNull ${opType} op$valueParam) {

        long current;
        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, ${applyOp}));
        return current;
    }

    public  final long updateAndGetIncrementCounter(
        final @NotNull ${opType} op$valueParam) {

        long current;
        long newValue;
        do {
            current = inc;
            newValue = ${applyOp};
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }
""")
    }

    buffer.append("""
    public final boolean compareAndSwapIndex(final long expected, final long value) {
        return UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            expected, value);
    }

    public final boolean compareAndSwapOffset(final long expected, final long value) {
        return UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            expected, value);
    }

    public final boolean compareAndSwapIncrementCounter(final long expected, final long value) {
        return UNSAFE.compareAndSwapLong(this, incFieldOffset,
            expected, value);
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

    public static $generic long contentByteSize(final @NotNull $typeName ... buffer) {
        return buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final long contentByteSize() {
        return contentByteSize(buffer);
    }

    public static $generic long totalByteSize(final @NotNull $typeName ... buffer) {
        return ARRAY_${typeSuffixCap}_BASE_OFFSET + buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final long totalByteSize() {
        return totalByteSize(buffer);
    }

    public static $generic void init(final byte value, final @NotNull $typeName[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET,
            buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
    }

    public final void init(final byte value) {
        init(value, buffer);
    }

    public final @NotNull $typeName[] copy(final @NotNull $typeName[] destination) {
        return copy(destination, buffer);
    }

    public final $generic @NotNull $typeName[] copy(final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
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
    public static  boolean inRange(final ${indexTypeName} index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final ${indexTypeName} index, final long length) {
        return index < length && index >= 0;
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final boolean inRangeAtIndex() {
        return inRange(index, buffer);
    }
    """ : ""
        }

    public final boolean inRange(final ${indexTypeName} index) {
        return inRange(index, buffer);
    }

    public static $generic boolean inRange(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return inRange(index, buffer.length);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public static $generic void checkIndexAtIndex() {
        checkIndex(index);
    }
    """ : ""
        }

    public static $generic void checkIndex(final ${indexTypeName} index) {
        checkIndex(index);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final $generic void checkIndexIfSafeOnAtIndex() {
        checkIndexIfSafeOn(index, buffer);
    }
    """ : ""
        }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static $generic void checkIndexIfSafeOn(final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final boolean inRangeWithOffset() {
        return inRangeWithOffset(index, offset);
    }

    public final boolean inRangeWithOffsetAtIndex() {
        return inRange(index);
    }

    public final boolean inRangeWithOffsetAtIndex(final long offset) {
        return inRangeWithOffset(index, offset);
    }
    """ : ""
        }

    public static  boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public final boolean inRangeWithOffset(final ${indexTypeName} index, final long offset) {
        return inRangeWithOffset(index, offset, buffer);
    }

    public static $generic boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndexWithOffset() {
        checkIndexWithOffset(index, offset);
    }

    public final void checkIndexWithOffsetAtIndex() {
        checkIndex(index);
    }

    public final void checkIndexWithOffsetAtIndex(final long offset) {
        checkIndexWithOffset(index, offset);
    }
    """ : ""
        }

    public final void checkIndexWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset) {
        checkIndexWithOffset(index, offset, buffer);
    }

    public static $generic void checkIndexWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + offset, offset, buffer.length));
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final void checkIndexIfSafeOnWithOffset() {
        checkIndexIfSafeOnWithOffset(index, offset);
    }

    public final void checkIndexIfSafeOnWithOffsetAtIndex() {
        checkIndexIfSafeOn(index);
    }

    public final void checkIndexIfSafeOnWithOffsetAtIndex(final long offset) {
        checkIndexIfSafeOnWithOffset(index, offset);
    }
    """ : ""
        }

    public final void checkIndexIfSafeOnWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset) {
        checkIndexIfSafeOnWithOffset(index, offset, buffer);
    }

    public final $generic void checkIndexIfSafeOnWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName get() {
        return get(index + offset);
    }

    public final @NotNull $typeName getAtIndex(final long offset) {
        return get(index + offset);
    }

    public final @NotNull $typeName getAtIndex() {
        return get(index);
    }
    """ : ""
        }

    public final @NotNull $typeName get(final ${indexTypeName} index) {
        return get(index, buffer);
    }

    public final $generic @NotNull $typeName get(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static $generic $typeName get(final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName[] put(final @NotNull $typeName value) {
        return put(index + offset, value);
    }

    public final @NotNull $typeName[] putAtIndex(final long offset, final @NotNull $typeName value) {
        return put(index + offset, value);
    }

    public final @NotNull $typeName[] putAtIndex(final @NotNull $typeName value) {
        return put(index, value);
    }
    """ : ""
        }

    public final @NotNull $typeName[] put(final ${indexTypeName} index, final @NotNull $typeName value) {
        return put(index, buffer, value);
    }

    public final $generic @NotNull $typeName[] put(final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static $generic @NotNull $typeName[] put(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName getVolatile() {
        return getVolatile(index + offset);
    }

    public final @NotNull $typeName getVolatileAtIndex(final long offset) {
        return getVolatile(index + offset);
    }

    public final @NotNull $typeName getVolatileAtIndex() {
        return getVolatile(index);
    }
    """ : ""
        }

    public final @NotNull $typeName getVolatile(final ${indexTypeName} index) {
        return getVolatile(index, buffer);
    }

    public final $generic @NotNull $typeName getVolatile(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static $generic @NotNull $typeName getVolatile(final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName[] putVolatile(final @NotNull $typeName value) {
        return putVolatile(index + offset, value);
    }

    public final @NotNull $typeName[] putVolatileAtIndex(final long offset, final @NotNull $typeName value) {
        return putVolatile(index + offset, value);
    }

    public final @NotNull $typeName[] putVolatileAtIndex(final @NotNull $typeName value) {
        return putVolatile(index, value);
    }
    """ : ""
        }

    public final @NotNull $typeName[] putVolatile(
        final ${indexTypeName} index, final @NotNull $typeName value) {
        return putVolatile(index, buffer, value);
    }

    public final $generic @NotNull $typeName[] putVolatile(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static $generic @NotNull $typeName[] putVolatile(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    ${
            indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName[] copy(final @NotNull $typeName ... source) {
        return copy(index + offset, source);
    }

    public final @NotNull $typeName[] copyAtIndex(final long offset, final @NotNull $typeName ... source) {
        return copy(index + offset, source);
    }

    public final @NotNull $typeName[] copyAtIndex(final @NotNull $typeName ... source) {
        return copy(index, source);
    }
    """ : ""
        }

    public final @NotNull $typeName[] copy(
        final ${indexTypeName} index, final @NotNull $typeName ... source) {
        return copy(index, buffer, source);
    }

    public final $generic @NotNull $typeName[] copy(
        final ${indexTypeName} index, final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static $generic @NotNull $typeName[] copy(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] destination,
        final @NotNull $typeName ... source) {
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

    public final @NotNull $typeName[] copy(
        final ${indexTypeName} length,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull $typeName[] copyAtIndex(
        final long offset, final ${indexTypeName} length,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull $typeName[] copyAtIndex(
        final ${indexTypeName} length,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(length, index, buffer, indexSource, source);
    }

    public final @NotNull $typeName[] copy(
        final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final $generic @NotNull $typeName[] copy(
        final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final @NotNull $typeName[] destination,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static $generic @NotNull $typeName[] copy(
        final boolean SAFE, final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final @NotNull $typeName[] destination, final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
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
    public final @NotNull $typeName[] putOrdered(
        final @NotNull $typeName value) {
        return putOrdered(index + offset, value);
    }

    public final @NotNull $typeName[] putOrderedAtIndex(
        final long offset, final @NotNull $typeName value) {
        return putOrdered(index + offset, value);
    }

    public final @NotNull $typeName[] putOrderedAtIndex(
        final @NotNull $typeName value) {
        return putOrdered(index, value);
    }
    """ : ""
            }

    public final @NotNull $typeName[] putOrdered(
        final ${indexTypeName} index, final @NotNull $typeName value) {
        return putOrdered(index, buffer, value);
    }

    public final $generic @NotNull $typeName[] putOrdered(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static $generic @NotNull $typeName[] putOrdered(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
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
        final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(index + offset, expected, value);
    }

    public final boolean compareAndSwapAtIndex(
        final long offset, final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(index + offset, expected, value);
    }

    public final boolean compareAndSwapAtIndex(
        final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(index, expected, value);
    }
    """ : ""
            }

    public final boolean compareAndSwap(
        final ${indexTypeName} index, final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(index, buffer, expected, value);
    }

    public final $generic boolean compareAndSwap(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static $generic boolean compareAndSwap(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName expected,
        final @NotNull $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwap${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(expected), ${valTransform}(value));
    }

    ${
                indexTypes.equals(Long.TYPE) ? """
    public final @NotNull $typeName getAndSet(
        final @NotNull $typeName value) {
        return ($typeName) getAndSet(index + offset, value);
    }

    public final @NotNull $typeName getAndSetAtIndex(
        final long offset, final @NotNull $typeName value) {
        return ($typeName) getAndSet(index + offset, value);
    }

    public final @NotNull $typeName getAndSetAtIndex(
        final @NotNull $typeName value) {
        return ($typeName) getAndSet(index, value);
    }
    """ : ""
            }

    public final @NotNull $typeName getAndSet(
        final ${indexTypeName} index, final @NotNull $typeName value) {
        return ($typeName) getAndSet(index, buffer, value);
    }

    public final $generic @NotNull $typeName getAndSet(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return ($typeName) getAndSet(this.SAFE, index, buffer, value);
    }

    public static $generic @NotNull $typeName getAndSet(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return ($typeName) ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(value)));
    }
        """)

            opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

            for (String opType : opTypes) {
                String valueParam = opType.startsWith("Multi") ?
                        ", final @NotNull $typeName ... value" :
                        opType.startsWith("Bi") ?
                                ", final @NotNull $typeName value" :
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
    public final @NotNull $typeName getAndUpdate(
        final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index + offset, op${applyOpMulti});
    }

    public final @NotNull $typeName getAndUpdateAtIndex(
        final long offset, final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index + offset, op${applyOpMulti});
    }

    public final @NotNull $typeName getAndUpdateAtIndex(
        final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index, op${applyOpMulti});
    }
    """ : ""
                }

    public final @NotNull $typeName getAndUpdate(
        final ${indexTypeName} index, final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(index, buffer, op${applyOpMulti});
    }

    public final $generic @NotNull $typeName getAndUpdate(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static $generic @NotNull $typeName getAndUpdate(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer,
        final @NotNull ${opType}$generic op$valueParam) {
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
    public final @NotNull $typeName updateAndGet(
        final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(index + offset, op${applyOpMulti});
    }

    public final @NotNull $typeName updateAndGetAtIndex(
        final long offset, final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(index + offset, op${applyOpMulti});
    }

    public final @NotNull $typeName updateAndGetAtIndex(
        final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(index, op${applyOpMulti});
    }
    """ : ""
                }


    public final @NotNull $typeName updateAndGet(
        final ${indexTypeName} index, final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(index, buffer, op${applyOpMulti});
    }

    public final $generic @NotNull $typeName updateAndGet(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer,
        final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static $generic @NotNull $typeName updateAndGet(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer,
        final ${opType}$generic op$valueParam) {
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