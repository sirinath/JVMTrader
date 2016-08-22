
/*
 * Microtrader is available under either the terms of the Apache License, Version 2.0 (ASF 2.0)
 * the Academic Free License Version 3.0, (AFL 3.0) or MIT License (MIT). As a recipient of
 * Microtrader, you may choose which license to receive this code or content under
 * (except as noted in per-module LICENSE files). Some modules may not be the copyright
 * of the Suminda Sirinath Salpitikorala Dharmasena and Project Contributors.
 * These modules contain explicit declarations of copyright in both the LICENSE files
 * in the directories in which they reside and in the code or content itself.
 *
 * No external contributions are allowed under licenses which are fundamentally
 * incompatible with the ASL 2.0, AFL 3.0 and MIT that Microtrader is distributed under.
 * By contributing to this project by means of including but not limited to patches,
 * pull requests, code submissions, issues, bug report, code snippets, discussions,
 * email message, chat messages such content will be licensed under the terms of
 * ASL 2.0, AFL 3.0 and MIT where the recipients are free to choose under which license
 * code or content is received under.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.susico.utils.arrays.access;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

import java.util.concurrent.locks.LockSupport;

public final class MutableWrappedArrayAccessFloat {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final boolean SAFE;
    protected volatile float[] buffer;
    protected volatile long index = 0;
    protected volatile long offset = 0;
    protected volatile long inc = 1;

    protected static final long indexFieldOffset =
        UnsafeAccess.getFieldOffset(MutableWrappedArrayAccessFloat.class, "index");
    protected static final long offsetFieldOffset =
        UnsafeAccess.getFieldOffset(MutableWrappedArrayAccessFloat.class, "offset");
    protected static final long incFieldOffset =
        UnsafeAccess.getFieldOffset(MutableWrappedArrayAccessFloat.class, "inc");

    public MutableWrappedArrayAccessFloat(final boolean SAFE, final float[] array) {
        this.SAFE = SAFE;
        this.buffer = array;
    }

    
    private volatile boolean wrapGuard = false;

    public final MutableWrappedArrayAccessFloat wrap(final @NotNull float[] array) {
        try {
            while (wrapGuard)
                LockSupport.parkNanos(1);

            this.wrapGuard = true;

            this.buffer = array;
            this.index = 0L;
            this.offset = 0L;
            this.inc = 1L;

            return this;
        } finally {
            this.wrapGuard = false;
        }
    }

    public static  MutableWrappedArrayAccessFloat checked(
        final boolean checked, final @NotNull float[] array) {
        return new MutableWrappedArrayAccessFloat(checked, array);
    }

    public static  MutableWrappedArrayAccessFloat checked(
        final @NotNull float[] array) {
        return checked(true, array);
    }

    public static  MutableWrappedArrayAccessFloat unchecked(
        final @NotNull float[] array) {
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

    public static final long ARRAY_FLOAT_BASE_OFFSET = UNSAFE.ARRAY_FLOAT_BASE_OFFSET;
    public static final long ARRAY_FLOAT_INDEX_SCALE = UNSAFE.ARRAY_FLOAT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_FLOAT_INDEX_SCALE :
            Float.BYTES;

    public static final long ARRAY_FLOAT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_FLOAT_INDEX_SCALE) - 1;

    public static  long contentByteSize(final @NotNull float ... buffer) {
        return buffer.length << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public final long contentByteSize() {
        return contentByteSize(buffer);
    }

    public static  long totalByteSize(final @NotNull float ... buffer) {
        return ARRAY_FLOAT_BASE_OFFSET + buffer.length << ARRAY_FLOAT_INDEX_SHIFT;
    }

    public final long totalByteSize() {
        return totalByteSize(buffer);
    }

    public static  void init(final byte value, final @NotNull float[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_FLOAT_BASE_OFFSET,
            buffer.length << ARRAY_FLOAT_INDEX_SHIFT, value);
    }

    public final void init(final byte value) {
        init(value, buffer);
    }

    public final @NotNull float[] copy(final @NotNull float[] destination) {
        return copy(destination, buffer);
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

    public static  boolean inRange(final int index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final long length) {
        return index < length && index >= 0;
    }

    

    public final boolean inRange(final int index) {
        return inRange(index, buffer);
    }

    public static  boolean inRange(final int index, final @NotNull float ... buffer) {
        return inRange(index, buffer.length);
    }

    

    public static  void checkIndex(final int index) {
        checkIndex(index);
    }

    public static  void checkIndex(final int index, final @NotNull float ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    

    public final  void checkIndexIfSafeOn(final int index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final  void checkIndexIfSafeOn(final int index, final @NotNull float ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final int index, final @NotNull float ... buffer) {
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

    public static  boolean inRangeWithOffset(final int index, final int offset, final @NotNull float ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    

    public final void checkIndexWithOffset(final int index, final int offset) {
        checkIndexWithOffset(index, offset, buffer);
    }

    public static  void checkIndexWithOffset(final int index, final int offset, final @NotNull float ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + offset, offset, buffer.length));
    }

    

    public final void checkIndexIfSafeOnWithOffset(final int index, final int offset) {
        checkIndexIfSafeOnWithOffset(index, offset, buffer);
    }

    public final  void checkIndexIfSafeOnWithOffset(final int index, final int offset, final @NotNull float ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final int index, final int offset, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    

    public final @NotNull float get(final int index) {
        return get(index, buffer);
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

    

    public final @NotNull float[] put(final int index, final @NotNull float value) {
        return put(index, buffer, value);
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

    

    public final @NotNull float getVolatile(final int index) {
        return getVolatile(index, buffer);
    }

    public final @NotNull  float getVolatile(final int index, final @NotNull float ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  float getVolatile(final boolean SAFE, final int index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    

    public final @NotNull float[] putVolatile(
        final int index, final @NotNull float value) {
        return putVolatile(index, buffer, value);
    }

    public final @NotNull  float[] putVolatile(
        final int index, final @NotNull float[] buffer, final @NotNull float value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putVolatile(
        final boolean SAFE, final int index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    

    public final @NotNull float[] copy(
        final int index, final @NotNull float ... source) {
        return copy(index, buffer, source);
    }

    public final @NotNull  float[] copy(
        final int index, final @NotNull float[] destination, final @NotNull float ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  float[] copy(
        final boolean SAFE, final int index, final @NotNull float[] destination,
        final @NotNull float ... source) {
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

    public final @NotNull float[] copy(
        final int length,
        final int indexSource, final @NotNull float ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull float[] copyAtIndex(
        final long offset, final int length,
        final int indexSource, final @NotNull float ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull float[] copyAtIndex(
        final int length,
        final int indexSource, final @NotNull float ... source) {
        return copy(length, index, buffer, indexSource, source);
    }

    public final @NotNull float[] copy(
        final int length, final int indexDestination,
        final int indexSource, final @NotNull float ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final @NotNull  float[] copy(
        final int length, final int indexDestination,
        final @NotNull float[] destination,
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

    

    public final @NotNull float[] putOrdered(
        final int index, final @NotNull float value) {
        return putOrdered(index, buffer, value);
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

    

    public final boolean compareAndSwap(
        final int index, final @NotNull float expected, final @NotNull float value) {
        return compareAndSwap(index, buffer, expected, value);
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

    

    public final @NotNull float getAndSet(
        final int index, final @NotNull float value) {
        return (float) getAndSet(index, buffer, value);
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
        
    

    public final @NotNull float getAndUpdate(
        final int index, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final int index, final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer,
        final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final BiOpFloat op, final @NotNull float value) {
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

    

    public final @NotNull float getAndUpdate(
        final int index, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(index, buffer, op);
    }

    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final int index, final @NotNull UnaryOpFloat op) {
        return updateAndGet(index, buffer, op);
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer,
        final @NotNull UnaryOpFloat op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final UnaryOpFloat op) {
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

    

    public final @NotNull float getAndUpdate(
        final int index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  float getAndUpdate(
        final int index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final int index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  float updateAndGet(
        final int index, final @NotNull float[] buffer,
        final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final int index, final @NotNull float[] buffer,
        final MultiOpFloat op, final @NotNull float ... value) {
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

    public static  boolean inRange(final long index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final long index, final long length) {
        return index < length && index >= 0;
    }

    

    public final boolean inRange(final long index) {
        return inRange(index, buffer);
    }

    public static  boolean inRange(final long index, final @NotNull float ... buffer) {
        return inRange(index, buffer.length);
    }

    

    public static  void checkIndex(final long index) {
        checkIndex(index);
    }

    public static  void checkIndex(final long index, final @NotNull float ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    

    public final  void checkIndexIfSafeOn(final long index) {
        checkIndexIfSafeOn(index, buffer);
    }

    public final  void checkIndexIfSafeOn(final long index, final @NotNull float ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static  void checkIndexIfSafeOn(final boolean SAFE, final long index, final @NotNull float ... buffer) {
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

    public static  boolean inRangeWithOffset(final long index, final long offset, final @NotNull float ... buffer) {
        return inRangeWithOffset(index, offset, buffer.length);
    }

    

    public final void checkIndexWithOffset(final long index, final long offset) {
        checkIndexWithOffset(index, offset, buffer);
    }

    public static  void checkIndexWithOffset(final long index, final long offset, final @NotNull float ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + offset, offset, buffer.length));
    }

    

    public final void checkIndexIfSafeOnWithOffset(final long index, final long offset) {
        checkIndexIfSafeOnWithOffset(index, offset, buffer);
    }

    public final  void checkIndexIfSafeOnWithOffset(final long index, final long offset, final @NotNull float ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static  void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final long index, final long offset, final @NotNull float ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    

    public final @NotNull float get(final long index) {
        return get(index, buffer);
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

    

    public final @NotNull float[] put(final long index, final @NotNull float value) {
        return put(index, buffer, value);
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

    

    public final @NotNull float getVolatile(final long index) {
        return getVolatile(index, buffer);
    }

    public final @NotNull  float getVolatile(final long index, final @NotNull float ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull  float getVolatile(final boolean SAFE, final long index, final @NotNull float ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
    }

    

    public final @NotNull float[] putVolatile(
        final long index, final @NotNull float value) {
        return putVolatile(index, buffer, value);
    }

    public final @NotNull  float[] putVolatile(
        final long index, final @NotNull float[] buffer, final @NotNull float value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull  float[] putVolatile(
        final boolean SAFE, final long index, final @NotNull float[] buffer, final @NotNull float value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT, value);

        return buffer;
    }

    

    public final @NotNull float[] copy(
        final long index, final @NotNull float ... source) {
        return copy(index, buffer, source);
    }

    public final @NotNull  float[] copy(
        final long index, final @NotNull float[] destination, final @NotNull float ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull  float[] copy(
        final boolean SAFE, final long index, final @NotNull float[] destination,
        final @NotNull float ... source) {
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

    public final @NotNull float[] copy(
        final long length,
        final long indexSource, final @NotNull float ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull float[] copyAtIndex(
        final long offset, final long length,
        final long indexSource, final @NotNull float ... source) {
        return copy(length, index + offset, buffer, indexSource, source);
    }

    public final @NotNull float[] copyAtIndex(
        final long length,
        final long indexSource, final @NotNull float ... source) {
        return copy(length, index, buffer, indexSource, source);
    }

    public final @NotNull float[] copy(
        final long length, final long indexDestination,
        final long indexSource, final @NotNull float ... source) {
        return copy(length, indexDestination, buffer, indexSource, source);
    }

    public final @NotNull  float[] copy(
        final long length, final long indexDestination,
        final @NotNull float[] destination,
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

    

    public final @NotNull float[] putOrdered(
        final long index, final @NotNull float value) {
        return putOrdered(index, buffer, value);
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

    

    public final boolean compareAndSwap(
        final long index, final @NotNull float expected, final @NotNull float value) {
        return compareAndSwap(index, buffer, expected, value);
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

    

    public final @NotNull float getAndSet(
        final long index, final @NotNull float value) {
        return (float) getAndSet(index, buffer, value);
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
        
    

    public final @NotNull float getAndUpdate(
        final long index, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull BiOpFloat op, final @NotNull float value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final @NotNull BiOpFloat op, final @NotNull float value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final long index, final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer,
        final @NotNull BiOpFloat op, final @NotNull float value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final BiOpFloat op, final @NotNull float value) {
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

    

    public final @NotNull float getAndUpdate(
        final long index, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(index, buffer, op);
    }

    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull UnaryOpFloat op) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final @NotNull UnaryOpFloat op) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final long index, final @NotNull UnaryOpFloat op) {
        return updateAndGet(index, buffer, op);
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer,
        final @NotNull UnaryOpFloat op) {
        return updateAndGet(this.SAFE, index, buffer, op);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final UnaryOpFloat op) {
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

    

    public final @NotNull float getAndUpdate(
        final long index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(index, buffer, op, value);
    }

    public final @NotNull  float getAndUpdate(
        final long index, final @NotNull float[] buffer, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return (float) getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float getAndUpdate(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        float current;
        do {
            current = (float) UNSAFE.getFloatVolatile(buffer,
                ARRAY_FLOAT_BASE_OFFSET + index << ARRAY_FLOAT_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwapInt(buffer, index,
            Float.floatToRawIntBits(current), Float.floatToRawIntBits(op.apply(current, value))));
        return current;
    }

    


    public final @NotNull float updateAndGet(
        final long index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(index, buffer, op, value);
    }

    public final @NotNull  float updateAndGet(
        final long index, final @NotNull float[] buffer,
        final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static @NotNull  float updateAndGet(
        final boolean SAFE, final long index, final @NotNull float[] buffer,
        final MultiOpFloat op, final @NotNull float ... value) {
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
}