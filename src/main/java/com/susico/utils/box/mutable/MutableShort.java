
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