
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


package com.susico.utils.box.mutable;

import java.util.Objects;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;
import com.susico.utils.box.*;
import com.susico.utils.box.immutable.*;
import com.susico.utils.box.mutable.*;

import org.jetbrains.annotations.*;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class MutableLong extends Number
    implements BoxOnce<MutableLong> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableLong.class, "value");

    /**
     * Value
     */
    private  long value;

    /**
     * @param i Parameter
     */
    public MutableLong(final @NotNull long i) {
        value = i;
    }

    @Override
    public final MutableLong clone() throws CloneNotSupportedException {
        return new MutableLong(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull long getValue() {
        return (long) value;
    }

    public final @NotNull long get() {
        return (long) value;
    }

    public final @NotNull long getValueVolatile() {
        return (long) UNSAFE.getLongVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull long value) {
        this.value = value;
    }

    public final void set(final @NotNull long value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull long value) {
        UNSAFE.putLongVolatile(this, valueFieldOffset, value);
    }

    public final void setValueOrdered(
        final @NotNull long value) {
            UNSAFE.putOrderedLong(this, valueFieldOffset, (value));
    }

    public final boolean compareAndSwapValue(final @NotNull long expected,
        final @NotNull long value) {
        return UNSAFE.compareAndSwapLong(this,
            valueFieldOffset,
            (expected), (value));
    }

    public final long getAndSetValue(
        final @NotNull long value) {
        return (long) (UNSAFE.getAndSetLong(this,
            valueFieldOffset,
            ((long) value)));
    }
        
    public final long getAndUpdateValue(final @NotNull BiOpLong op, final @NotNull long value) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) op.apply(current, value))));
        return current;
    }

    public final long updateAndGetValue(final @NotNull BiOpLong op, final @NotNull long value) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) newValue)));

        return newValue;
    }

    public final long getAndUpdateValue(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) op.apply(current))));
        return current;
    }

    public final long updateAndGetValue(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, valueFieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) newValue)));

        return newValue;
    }

    public final long getAndUpdateValue(final @NotNull MultiOpLong op, final @NotNull long ... value) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) op.apply(current, value))));
        return current;
    }

    public final long updateAndGetValue(final @NotNull MultiOpLong op, final @NotNull long ... value) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            ((long) current), ((long) newValue)));

        return newValue;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableLong)
            return value == ((MutableLong) other).getValue();
        else if (other instanceof ImmutableLong)
            return value == ((ImmutableLong) other).getValue();
        else if (other instanceof Long)
            return ((Long) other).longValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Long.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull MutableLong other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableLong other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    // Others

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return (long) value;
    }

    @Override
    public final float floatValue() {
        return (float) value;
    }

    @Override
    public final double doubleValue() {
        return (double) value;
    }
}