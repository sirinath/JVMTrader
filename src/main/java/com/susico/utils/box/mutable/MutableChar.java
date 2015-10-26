
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
public final class MutableChar extends Number
    implements BoxOnce<MutableChar> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableChar.class, "value");

    /**
     * Value
     */
    private  char value;

    /**
     * @param i Parameter
     */
    public MutableChar(final @NotNull char i) {
        value = i;
    }

    @Override
    public final MutableChar clone() throws CloneNotSupportedException {
        return new MutableChar(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull char getValue() {
        return (char) value;
    }

    public final @NotNull char get() {
        return (char) value;
    }

    public final @NotNull char getValueVolatile() {
        return (char) UNSAFE.getCharVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull char value) {
        this.value = value;
    }

    public final void set(final @NotNull char value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull char value) {
        UNSAFE.putCharVolatile(this, valueFieldOffset, value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableChar)
            return value == ((MutableChar) other).getValue();
        else if (other instanceof ImmutableChar)
            return value == ((ImmutableChar) other).getValue();
        else if (other instanceof Character)
            return ((Character) other).charValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Character.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull MutableChar other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableChar other) {
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