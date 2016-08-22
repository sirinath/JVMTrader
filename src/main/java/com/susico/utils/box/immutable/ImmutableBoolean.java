
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


package com.susico.utils.box.immutable;

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
public final class ImmutableBoolean extends Number
    implements BoxOnce<ImmutableBoolean> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(ImmutableBoolean.class, "value");

    /**
     * Value
     */
    private final boolean value;

    /**
     * @param i Parameter
     */
    public ImmutableBoolean(final @NotNull boolean i) {
        value = i;
    }

    @Override
    public final ImmutableBoolean clone() throws CloneNotSupportedException {
        return new ImmutableBoolean(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull boolean getValue() {
        return (boolean) value;
    }

    public final @NotNull boolean get() {
        return (boolean) value;
    }

    public final @NotNull boolean getValueVolatile() {
        return (boolean) UNSAFE.getBooleanVolatile(this, valueFieldOffset);
    }
    
    @Override
    public final boolean equals(Object other) {
        if (other instanceof ImmutableBoolean)
            return value == ((ImmutableBoolean) other).getValue();
        else if (other instanceof MutableBoolean)
            return value == ((MutableBoolean) other).getValue();
        else if (other instanceof Boolean)
            return ((Boolean) other).booleanValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Boolean.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull ImmutableBoolean other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull MutableBoolean other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }

    // Boolean

    @Override
    public final int intValue() {
        return value ? 1 : 0;
    }

    @Override
    public final long longValue() {
        return value ? 1L : 0L;
    }

    @Override
    public final float floatValue() {
        return value ? 1.0F : 0.0F;
    }

    @Override
    public final double doubleValue() {
        return value ? 1.0D : 0.0D;
    }
}