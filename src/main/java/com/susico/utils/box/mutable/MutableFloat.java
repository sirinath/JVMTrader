
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
public final class MutableFloat extends Number
    implements BoxOnce<MutableFloat> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableFloat.class, "value");

    /**
     * Value
     */
    @Contended private  float value;

    /**
     * @param i Parameter
     */
    public MutableFloat(final @NotNull float i) {
        value = i;
    }

    @Override
    public final MutableFloat clone() throws CloneNotSupportedException {
        return new MutableFloat(value);
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
    
    public final void setValue(final @NotNull float value) {
        this.value = value;
    }

    public final void set(final @NotNull float value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull float value) {
        UNSAFE.putFloatVolatile(this, valueFieldOffset, value);
    }

    public final void setValueOrdered(
        final @NotNull float value) {
            UNSAFE.putOrderedInt(this, valueFieldOffset, Float.floatToRawIntBits(value));
    }

    public final boolean compareAndSwapValue(final @NotNull float expected,
        final @NotNull float value) {
        return UNSAFE.compareAndSwapInt(this,
            valueFieldOffset,
            Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
    }

    public final float getAndSetValue(
        final @NotNull float value) {
        return (float) Float.intBitsToFloat(UNSAFE.getAndSetInt(this,
            valueFieldOffset,
            Float.floatToRawIntBits((float) value)));
    }
        
    public final float getAndUpdateValue(final @NotNull BiOpFloat op, final @NotNull float value) {
        float current;

        do {
            current = (float) UNSAFE.getFloatVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) op.apply(current, value))));
        return current;
    }

    public final float updateAndGetValue(final @NotNull BiOpFloat op, final @NotNull float value) {
        float current;
        float newValue;

        do {
            current = (float) UNSAFE.getFloatVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) newValue)));

        return newValue;
    }

    public final float getAndUpdateValue(final @NotNull UnaryOpFloat op) {
        float current;

        do {
            current = (float) UNSAFE.getFloatVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) op.apply(current))));
        return current;
    }

    public final float updateAndGetValue(final @NotNull UnaryOpFloat op) {
        float current;
        float newValue;

        do {
            current = (float) UNSAFE.getFloatVolatile(this, valueFieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) newValue)));

        return newValue;
    }

    public final float getAndUpdateValue(final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        float current;

        do {
            current = (float) UNSAFE.getFloatVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) op.apply(current, value))));
        return current;
    }

    public final float updateAndGetValue(final @NotNull MultiOpFloat op, final @NotNull float ... value) {
        float current;
        float newValue;

        do {
            current = (float) UNSAFE.getFloatVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            Float.floatToRawIntBits((float) current), Float.floatToRawIntBits((float) newValue)));

        return newValue;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableFloat)
            return value == ((MutableFloat) other).getValue();
        else if (other instanceof ImmutableFloat)
            return value == ((ImmutableFloat) other).getValue();
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
    public final int compareTo(final @NotNull MutableFloat other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableFloat other) {
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