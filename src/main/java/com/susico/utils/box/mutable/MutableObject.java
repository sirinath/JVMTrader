
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
public final class MutableObject<T> 
    implements BoxOnce<MutableObject<T>> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableObject.class, "value");

    /**
     * Value
     */
    private  T value;

    /**
     * @param i Parameter
     */
    public MutableObject(final @NotNull T i) {
        value = i;
    }

    @Override
    public final MutableObject<T> clone() throws CloneNotSupportedException {
        return new MutableObject<T>(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull T getValue() {
        return (T) value;
    }

    public final @NotNull T get() {
        return (T) value;
    }

    public final @NotNull T getValueVolatile() {
        return (T) UNSAFE.getObjectVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull T value) {
        this.value = value;
    }

    public final void set(final @NotNull T value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull T value) {
        UNSAFE.putObjectVolatile(this, valueFieldOffset, value);
    }

    public final void setValueOrdered(
        final @NotNull T value) {
            UNSAFE.putOrderedObject(this, valueFieldOffset, (value));
    }

    public final boolean compareAndSwapValue(final @NotNull T expected,
        final @NotNull T value) {
        return UNSAFE.compareAndSwapObject(this,
            valueFieldOffset,
            (expected), (value));
    }

    public final T getAndSetValue(
        final @NotNull T value) {
        return (T) (UNSAFE.getAndSetObject(this,
            valueFieldOffset,
            ((Object) value)));
    }
        
    public final T getAndUpdateValue(final @NotNull BiOpObject<T> op, final @NotNull T value) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) op.apply(current, value))));
        return current;
    }

    public final T updateAndGetValue(final @NotNull BiOpObject<T> op, final @NotNull T value) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) newValue)));

        return newValue;
    }

    public final T getAndUpdateValue(final @NotNull UnaryOpObject<T> op) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) op.apply(current))));
        return current;
    }

    public final T updateAndGetValue(final @NotNull UnaryOpObject<T> op) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, valueFieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) newValue)));

        return newValue;
    }

    public final T getAndUpdateValue(final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) op.apply(current, value))));
        return current;
    }

    public final T updateAndGetValue(final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapObject(this, valueFieldOffset,
            ((Object) current), ((Object) newValue)));

        return newValue;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableObject)
            return value.equals(((MutableObject<T>) other).getValue());
        else if (other instanceof ImmutableObject)
            return value.equals(((ImmutableObject) other).getValue());
        else if (other instanceof Object)
            return other.equals(value);
        else
            return false;
    }
    @Override
    public final int compareTo(final @NotNull MutableObject<T> other) {
        final @NotNull T otherValue = other.getValue();
        if (value instanceof Comparable)
            return ((Comparable) value).compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - ((Comparable) otherValue).compareTo(value);
        else
            throw new IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }

    public final int compareTo(final @NotNull ImmutableObject<T> other) {
        final @NotNull T otherValue = other.getValue();
        if (value instanceof Comparable)
            return ((Comparable) value).compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - ((Comparable) otherValue).compareTo(value);
        else
            throw new IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }
}