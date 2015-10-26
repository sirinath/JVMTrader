
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