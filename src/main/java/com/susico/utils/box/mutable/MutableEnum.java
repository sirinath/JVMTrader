
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
import sun.misc.Contended;

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
public final class MutableEnum<T extends Enum<T>> extends Number
    implements BoxOnce<MutableEnum<T>> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableEnum.class, "value");

    /**
     * Value
     */
    @Contended private  Enum value;

    /**
     * @param i Parameter
     */
    public MutableEnum(final @NotNull Enum i) {
        value = i;
    }

    @Override
    public final MutableEnum<T> clone() throws CloneNotSupportedException {
        return new MutableEnum<T>(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull Enum getValue() {
        return (Enum) value;
    }

    public final @NotNull Enum get() {
        return (Enum) value;
    }

    public final @NotNull Enum getValueVolatile() {
        return (Enum) UNSAFE.getObjectVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull Enum value) {
        this.value = value;
    }

    public final void set(final @NotNull Enum value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull Enum value) {
        UNSAFE.putObjectVolatile(this, valueFieldOffset, value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableEnum)
            return value == ((MutableEnum<T>) other).getValue();
        else if (other instanceof ImmutableEnum)
            return value == ((ImmutableEnum) other).getValue();
        else if (other instanceof Enum)
            return other.equals(value);
        else
            return false;
    }
    @Override
    public final int compareTo(final @NotNull MutableEnum<T> other) {
        return value.compareTo(other.getValue());
    }

    public final int compareTo(final @NotNull ImmutableEnum<T> other) {
        return value.compareTo(other.getValue());
    }

    // Enum

    @Override
    public final int intValue() {
        return (int) value.ordinal();
    }

    @Override
    public final long longValue() {
        return (long) value.ordinal();
    }

    @Override
    public final float floatValue() {
        return (float) value.ordinal();
    }

    @Override
    public final double doubleValue() {
        return (double) value.ordinal();
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }
}