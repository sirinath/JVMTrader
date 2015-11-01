
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


package com.susico.utils.box.immutable;

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
public final class ImmutableFloat extends Number
    implements BoxOnce<ImmutableFloat> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(ImmutableFloat.class, "value");

    /**
     * Value
     */
    @Contended private final float value;

    /**
     * @param i Parameter
     */
    public ImmutableFloat(final @NotNull float i) {
        value = i;
    }

    @Override
    public final ImmutableFloat clone() throws CloneNotSupportedException {
        return new ImmutableFloat(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull float getValue() {
        return (float) value;
    }

    public final @NotNull float get() {
        return (float) value;
    }

    public final @NotNull float getValueVolatile() {
        return (float) UNSAFE.getFloatVolatile(this, valueFieldOffset);
    }
    
    @Override
    public final boolean equals(Object other) {
        if (other instanceof ImmutableFloat)
            return value == ((ImmutableFloat) other).getValue();
        else if (other instanceof MutableFloat)
            return value == ((MutableFloat) other).getValue();
        else if (other instanceof Float)
            return ((Float) other).floatValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Float.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull ImmutableFloat other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull MutableFloat other) {
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