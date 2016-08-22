
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
public final class MutableShort extends Number
    implements BoxOnce<MutableShort> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableShort.class, "value");

    /**
     * Value
     */
    private  short value;

    /**
     * @param i Parameter
     */
    public MutableShort(final @NotNull short i) {
        value = i;
    }

    @Override
    public final MutableShort clone() throws CloneNotSupportedException {
        return new MutableShort(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull short getValue() {
        return (short) value;
    }

    public final @NotNull short get() {
        return (short) value;
    }

    public final @NotNull short getValueVolatile() {
        return (short) UNSAFE.getShortVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull short value) {
        this.value = value;
    }

    public final void set(final @NotNull short value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull short value) {
        UNSAFE.putShortVolatile(this, valueFieldOffset, value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableShort)
            return value == ((MutableShort) other).getValue();
        else if (other instanceof ImmutableShort)
            return value == ((ImmutableShort) other).getValue();
        else if (other instanceof Short)
            return ((Short) other).shortValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Short.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull MutableShort other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableShort other) {
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