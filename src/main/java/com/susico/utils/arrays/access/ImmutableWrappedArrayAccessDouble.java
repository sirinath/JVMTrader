
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

import java.util.concurrent.locks.LockSupport;

public final class ImmutableWrappedArrayAccessDouble {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    @Contended protected final boolean SAFE;
    @Contended protected final double[] buffer;
    @Contended protected volatile long index = 0;
    @Contended protected volatile long offset = 0;
    @Contended protected volatile long inc = 1;

    protected static final long indexFieldOffset =
        UnsafeAccess.getFieldOffset(ImmutableWrappedArrayAccessDouble.class, "index");
    protected static final long offsetFieldOffset =
        UnsafeAccess.getFieldOffset(ImmutableWrappedArrayAccessDouble.class, "offset");
    protected static final long incFieldOffset =
        UnsafeAccess.getFieldOffset(ImmutableWrappedArrayAccessDouble.class, "inc");

    public ImmutableWrappedArrayAccessDouble(final boolean SAFE, final double[] array) {
        this.SAFE = SAFE;
        this.buffer = array;
    }

    

    public static  ImmutableWrappedArrayAccessDouble checked(
        final boolean checked, final @NotNull double[] array) {
        return new ImmutableWrappedArrayAccessDouble(checked, array);
    }

    public static  ImmutableWrappedArrayAccessDouble checked(
        final @NotNull double[] array) {
        return checked(true, array);
    }

    public static  ImmutableWrappedArrayAccessDouble unchecked(
        final @NotNull double[] array) {
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
    
    public  final long getAndUpdateIndex(
        final @NotNull BiOpLong op, final long value) {

        long current;
        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(op.apply(current, value))));
        return current;
    }

    public  final long updateAndGetIndex(
        final @NotNull BiOpLong op, final long value) {

        long current;
        long newValue;
        do {
            current = index;
            newValue = rollIndex(op.apply(current, value));
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long updateAndGetIndexWeak(
        final @NotNull BiOpLong op, final long value) {
        final long current = index;
        index = rollIndex(op.apply(current, value));

        return index;
    }

    public  final long updateAndGetOffsetWeak(
        final @NotNull BiOpLong op, final long value) {
        final long current = offset;
        offset = rollOffset(index, op.apply(current, value));

        return offset;
    }

    public  final long getAndUpdateOffset(
        final @NotNull BiOpLong op, final long value) {

        long current;
        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, op.apply(current, value))));
        return current;
    }

    public  final long updateAndGetOffset(
        final @NotNull BiOpLong op, final long value) {

        long current;
        long newValue;
        do {
            current = offset;
            newValue = rollOffset(index, op.apply(current, value));
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIncrementCounter(
        final @NotNull BiOpLong op, final long value) {

        long current;
        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, op.apply(current, value)));
        return current;
    }

    public  final long updateAndGetIncrementCounter(
        final @NotNull BiOpLong op, final long value) {

        long current;
        long newValue;
        do {
            current = inc;
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIndex(
        final @NotNull UnaryOpLong op) {

        long current;
        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(op.apply(current))));
        return current;
    }

    public  final long updateAndGetIndex(
        final @NotNull UnaryOpLong op) {

        long current;
        long newValue;
        do {
            current = index;
            newValue = rollIndex(op.apply(current));
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long updateAndGetIndexWeak(
        final @NotNull UnaryOpLong op) {
        final long current = index;
        index = rollIndex(op.apply(current));

        return index;
    }

    public  final long updateAndGetOffsetWeak(
        final @NotNull UnaryOpLong op) {
        final long current = offset;
        offset = rollOffset(index, op.apply(current));

        return offset;
    }

    public  final long getAndUpdateOffset(
        final @NotNull UnaryOpLong op) {

        long current;
        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, op.apply(current))));
        return current;
    }

    public  final long updateAndGetOffset(
        final @NotNull UnaryOpLong op) {

        long current;
        long newValue;
        do {
            current = offset;
            newValue = rollOffset(index, op.apply(current));
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIncrementCounter(
        final @NotNull UnaryOpLong op) {

        long current;
        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, op.apply(current)));
        return current;
    }

    public  final long updateAndGetIncrementCounter(
        final @NotNull UnaryOpLong op) {

        long current;
        long newValue;
        do {
            current = inc;
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIndex(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        do {
            current = index;
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, rollIndex(op.apply(current, value))));
        return current;
    }

    public  final long updateAndGetIndex(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        long newValue;
        do {
            current = index;
            newValue = rollIndex(op.apply(current, value));
        } while (!UNSAFE.compareAndSwapLong(this, indexFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long updateAndGetIndexWeak(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {
        final long current = index;
        index = rollIndex(op.apply(current, value));

        return index;
    }

    public  final long updateAndGetOffsetWeak(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {
        final long current = offset;
        offset = rollOffset(index, op.apply(current, value));

        return offset;
    }

    public  final long getAndUpdateOffset(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        do {
            current = offset;
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, rollOffset(index, op.apply(current, value))));
        return current;
    }

    public  final long updateAndGetOffset(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        long newValue;
        do {
            current = offset;
            newValue = rollOffset(index, op.apply(current, value));
        } while (!UNSAFE.compareAndSwapLong(this, offsetFieldOffset,
            current, newValue));

        return newValue;
    }

    public  final long getAndUpdateIncrementCounter(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        do {
            current = inc;
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, op.apply(current, value)));
        return current;
    }

    public  final long updateAndGetIncrementCounter(
        final @NotNull MultiOpLong op, final @NotNull long ... value) {

        long current;
        long newValue;
        do {
            current = inc;
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, incFieldOffset,
            current, newValue));

        return newValue;
    }

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

    public static final long ARRAY_DOUBLE_BASE_OFFSET = UNSAFE.ARRAY_DOUBLE_BASE_OFFSET;
    public static final long ARRAY_DOUBLE_INDEX_SCALE = UNSAFE.ARRAY_DOUBLE_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_DOUBLE_INDEX_SCALE :
            Double.BYTES;

    public static final long ARRAY_DOUBLE_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_DOUBLE_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull double ... buffer) {
        return buffer.length << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public final long contentByteSize() {
        return contentByteSize(buffer);
    }

    public static  long totalByteSize(final @NotNull double ... buffer) {
        return ARRAY_DOUBLE_BASE_OFFSET + buffer.length << ARRAY_DOUBLE_INDEX_SHIFT;
    }

    public final long totalByteSize() {
        return totalByteSize(buffer);
    }

    public static  void init(final byte value, final @NotNull double[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_DOUBLE_BASE_OFFSET,
            buffer.length << ARRAY_DOUBLE_INDEX_SHIFT, value);
    }

    public final void init(final byte value) {
        init(value, buffer);
    }

    public final @NotNull double[] copy(final @NotNull double[] destination) {
        return copy(destination, buffer);
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

    public static  boolean inRange(final int index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final long length) {
        return index < length && index >= 0;
    }

    

    public final boolean inRange(final int index) {
        return inRange(index, buffer);
    }

    public static  boolean inRange(final int index, final @NotNull double ... buffer) {
        return inRange(index, buffer.length);
    }

    

    public static  void checkIndex(final int index) {
        checkIndex(index);
    }

    public static  void checkIndex(final int index, final @NotNull double ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    

    public final  void checkIndexIfSafeOn(final int index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull double ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    

    public static  boolean inRangeWithOffset(final int index, final int offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public final boolean inRangeWithOffset(final int index, final long offset) {
        return inRangeWithOffset(index, offset, buffer);
    }

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull double ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    

    public final void checkIndexWithOffset(final int index, final int offset) {
        checkIndexWithOffset(index, offset, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull double ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + offset, offset, buffer.length));
    }

    

    public final void checkIndexIfSafeOnWithOffset(final int index, final int offset) {
        checkIndexIfSafeOnWithOffset(index, offset, buffer);
    }

    public final  void checkIndexIfSafeOnWithOffset(final int index, final int offset, final @NotNull double ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    

    public final @NotNull double get(final int index) {
        return get(index, buffer);
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

    

    public final @NotNull double[] put(final int index, final @NotNull double value) {
        return put(index, buffer, value);
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

    

    public final @NotNull double getVolatile(final int index) {
        return getVolatile(index, buffer);
    }

    public final @NotNull  double getVolatile(final int index, final @NotNull double ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  double getVolatile(final boolean SAFE, final int index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    

    public final @NotNull double[] putVolatile(
        final int index, final @NotNull double value) {
        return putVolatile(index, buffer, value);
    }

    public final @NotNull  double[] putVolatile(
        final int index, final @NotNull double[] buffer, final @NotNull double value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putVolatile(
        final boolean SAFE, final int index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    

    public final @NotNull double[] copy(
        final int index, final @NotNull double ... source) {
        return copy(index, buffer, source);
    }

    public final @NotNull  double[] copy(
        final int index, final @NotNull double[] destination, final @NotNull double ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  double[] copy(
        final boolean SAFE, final int index, final @NotNull double[] destination,
        final @NotNull double ... source) {
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

    public final @NotNull double[] copy(
        final int length,
        final int indexSource, final @NotNull double ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull double[] copyAtIndex(
        final long offset, final int length,
        final int indexSource, final @NotNull double ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull double[] copyAtIndex(
        final int length,
        final int indexSource, final @NotNull double ... source) {
        return copy(length, index, buffer, indexSource, source);
    }

    public final @NotNull double[] copy(
        final int length, final int indexDestination,
        final int indexSource, final @NotNull double ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final @NotNull  double[] copy(
        final int length, final int indexDestination,
        final @NotNull double[] destination,
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

    

    public final @NotNull double[] putOrdered(
        final int index, final @NotNull double value) {
        return putOrdered(index, buffer, value);
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

    

    public final boolean compareAndSwap(
        final int index, final @NotNull double expected, final @NotNull double value) {
        return compareAndSwap(index, buffer, expected, value);
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

    

    public final @NotNull double getAndSet(
        final int index, final @NotNull double value) {
        return (double) getAndSet(index, buffer, value);
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
        
    

    public final @NotNull double getAndUpdate(
        final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer,
        final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final BiOpDouble op, final @NotNull double value) {
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

    

    public final @NotNull double getAndUpdate(
        final int index, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(index, buffer, op);
    }

    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final int index, final @NotNull UnaryOpDouble op) {
        return updateAndGet(index, buffer, op);
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer,
        final @NotNull UnaryOpDouble op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final UnaryOpDouble op) {
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

    

    public final @NotNull double getAndUpdate(
        final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  double getAndUpdate(
        final int index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  double updateAndGet(
        final int index, final @NotNull double[] buffer,
        final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final int index, final @NotNull double[] buffer,
        final MultiOpDouble op, final @NotNull double ... value) {
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

    public static  boolean inRange(final long index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final long index, final long length) {
        return index < length && index >= 0;
    }

    

    public final boolean inRange(final long index) {
        return inRange(index, buffer);
    }

    public static  boolean inRange(final long index, final @NotNull double ... buffer) {
        return inRange(index, buffer.length);
    }

    

    public static  void checkIndex(final long index) {
        checkIndex(index);
    }

    public static  void checkIndex(final long index, final @NotNull double ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    

    public final  void checkIndexIfSafeOn(final long index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull double ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    

    public static  boolean inRangeWithOffset(final long index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public final boolean inRangeWithOffset(final long index, final long offset) {
        return inRangeWithOffset(index, offset, buffer);
    }

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull double ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    

    public final void checkIndexWithOffset(final long index, final long offset) {
        checkIndexWithOffset(index, offset, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull double ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + offset, offset, buffer.length));
    }

    

    public final void checkIndexIfSafeOnWithOffset(final long index, final long offset) {
        checkIndexIfSafeOnWithOffset(index, offset, buffer);
    }

    public final  void checkIndexIfSafeOnWithOffset(final long index, final long offset, final @NotNull double ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull double ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    

    public final @NotNull double get(final long index) {
        return get(index, buffer);
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

    

    public final @NotNull double[] put(final long index, final @NotNull double value) {
        return put(index, buffer, value);
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

    

    public final @NotNull double getVolatile(final long index) {
        return getVolatile(index, buffer);
    }

    public final @NotNull  double getVolatile(final long index, final @NotNull double ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  double getVolatile(final boolean SAFE, final long index, final @NotNull double ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
    }

    

    public final @NotNull double[] putVolatile(
        final long index, final @NotNull double value) {
        return putVolatile(index, buffer, value);
    }

    public final @NotNull  double[] putVolatile(
        final long index, final @NotNull double[] buffer, final @NotNull double value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  double[] putVolatile(
        final boolean SAFE, final long index, final @NotNull double[] buffer, final @NotNull double value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT, value);

        return buffer;
    }

    

    public final @NotNull double[] copy(
        final long index, final @NotNull double ... source) {
        return copy(index, buffer, source);
    }

    public final @NotNull  double[] copy(
        final long index, final @NotNull double[] destination, final @NotNull double ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  double[] copy(
        final boolean SAFE, final long index, final @NotNull double[] destination,
        final @NotNull double ... source) {
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

    public final @NotNull double[] copy(
        final long length,
        final long indexSource, final @NotNull double ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull double[] copyAtIndex(
        final long offset, final long length,
        final long indexSource, final @NotNull double ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull double[] copyAtIndex(
        final long length,
        final long indexSource, final @NotNull double ... source) {
        return copy(length, index, buffer, indexSource, source);
    }

    public final @NotNull double[] copy(
        final long length, final long indexDestination,
        final long indexSource, final @NotNull double ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final @NotNull  double[] copy(
        final long length, final long indexDestination,
        final @NotNull double[] destination,
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

    

    public final @NotNull double[] putOrdered(
        final long index, final @NotNull double value) {
        return putOrdered(index, buffer, value);
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

    

    public final boolean compareAndSwap(
        final long index, final @NotNull double expected, final @NotNull double value) {
        return compareAndSwap(index, buffer, expected, value);
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

    

    public final @NotNull double getAndSet(
        final long index, final @NotNull double value) {
        return (double) getAndSet(index, buffer, value);
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
        
    

    public final @NotNull double getAndUpdate(
        final long index, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull BiOpDouble op, final @NotNull double value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final @NotNull BiOpDouble op, final @NotNull double value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final long index, final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer,
        final @NotNull BiOpDouble op, final @NotNull double value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final BiOpDouble op, final @NotNull double value) {
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

    

    public final @NotNull double getAndUpdate(
        final long index, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(index, buffer, op);
    }

    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull UnaryOpDouble op) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final @NotNull UnaryOpDouble op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final long index, final @NotNull UnaryOpDouble op) {
        return updateAndGet(index, buffer, op);
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer,
        final @NotNull UnaryOpDouble op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final UnaryOpDouble op) {
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

    

    public final @NotNull double getAndUpdate(
        final long index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  double getAndUpdate(
        final long index, final @NotNull double[] buffer, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return (double) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double getAndUpdate(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        double current;
        do {
            current = (double) UNSAFE.getDoubleVolatile(buffer,
                ARRAY_DOUBLE_BASE_OFFSET + index << ARRAY_DOUBLE_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapLong(buffer, index,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull double updateAndGet(
        final long index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  double updateAndGet(
        final long index, final @NotNull double[] buffer,
        final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  double updateAndGet(
        final boolean SAFE, final long index, final @NotNull double[] buffer,
        final MultiOpDouble op, final @NotNull double ... value) {
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
}