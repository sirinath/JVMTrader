
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
public final class MutableDouble extends Number
    implements BoxOnce<MutableDouble> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableDouble.class, "value");

    /**
     * Value
     */
    private  double value;

    /**
     * @param i Parameter
     */
    public MutableDouble(final @NotNull double i) {
        value = i;
    }

    @Override
    public final MutableDouble clone() throws CloneNotSupportedException {
        return new MutableDouble(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull double getValue() {
        return (double) value;
    }

    public final @NotNull double get() {
        return (double) value;
    }

    public final @NotNull double getValueVolatile() {
        return (double) UNSAFE.getDoubleVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull double value) {
        this.value = value;
    }

    public final void set(final @NotNull double value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull double value) {
        UNSAFE.putDoubleVolatile(this, valueFieldOffset, value);
    }

    public final void setValueOrdered(
        final @NotNull double value) {
            UNSAFE.putOrderedLong(this, valueFieldOffset, Double.doubleToRawLongBits(value));
    }

    public final boolean compareAndSwapValue(final @NotNull double expected,
        final @NotNull double value) {
        return UNSAFE.compareAndSwapLong(this,
            valueFieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
    }

    public final double getAndSetValue(
        final @NotNull double value) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            valueFieldOffset,
            Double.doubleToRawLongBits((double) value)));
    }
        
    public final double getAndUpdateValue(final @NotNull BiOpDouble op, final @NotNull double value) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) op.apply(current, value))));
        return current;
    }

    public final double updateAndGetValue(final @NotNull BiOpDouble op, final @NotNull double value) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) newValue)));

        return newValue;
    }

    public final double getAndUpdateValue(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) op.apply(current))));
        return current;
    }

    public final double updateAndGetValue(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, valueFieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) newValue)));

        return newValue;
    }

    public final double getAndUpdateValue(final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) op.apply(current, value))));
        return current;
    }

    public final double updateAndGetValue(final @NotNull MultiOpDouble op, final @NotNull double ... value) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapLong(this, valueFieldOffset,
            Double.doubleToRawLongBits((double) current), Double.doubleToRawLongBits((double) newValue)));

        return newValue;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableDouble)
            return value == ((MutableDouble) other).getValue();
        else if (other instanceof ImmutableDouble)
            return value == ((ImmutableDouble) other).getValue();
        else if (other instanceof Double)
            return ((Double) other).doubleValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Double.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull MutableDouble other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableDouble other) {
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