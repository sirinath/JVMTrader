
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
public final class MutableInt extends Number
    implements BoxOnce<MutableInt> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableInt.class, "value");

    /**
     * Value
     */
    private  int value;

    /**
     * @param i Parameter
     */
    public MutableInt(final @NotNull int i) {
        value = i;
    }

    @Override
    public final MutableInt clone() throws CloneNotSupportedException {
        return new MutableInt(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull int getValue() {
        return (int) value;
    }

    public final @NotNull int get() {
        return (int) value;
    }

    public final @NotNull int getValueVolatile() {
        return (int) UNSAFE.getIntVolatile(this, valueFieldOffset);
    }
    
    public final void setValue(final @NotNull int value) {
        this.value = value;
    }

    public final void set(final @NotNull int value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull int value) {
        UNSAFE.putIntVolatile(this, valueFieldOffset, value);
    }

    public final void setValueOrdered(
        final @NotNull int value) {
            UNSAFE.putOrderedInt(this, valueFieldOffset, (value));
    }

    public final boolean compareAndSwapValue(final @NotNull int expected,
        final @NotNull int value) {
        return UNSAFE.compareAndSwapInt(this,
            valueFieldOffset,
            (expected), (value));
    }

    public final int getAndSetValue(
        final @NotNull int value) {
        return (int) (UNSAFE.getAndSetInt(this,
            valueFieldOffset,
            ((int) value)));
    }
        
    public final int getAndUpdateValue(final @NotNull BiOpInt op, final @NotNull int value) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) op.apply(current, value))));
        return current;
    }

    public final int updateAndGetValue(final @NotNull BiOpInt op, final @NotNull int value) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) newValue)));

        return newValue;
    }

    public final int getAndUpdateValue(final @NotNull UnaryOpInt op) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) op.apply(current))));
        return current;
    }

    public final int updateAndGetValue(final @NotNull UnaryOpInt op) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, valueFieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) newValue)));

        return newValue;
    }

    public final int getAndUpdateValue(final @NotNull MultiOpInt op, final @NotNull int ... value) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) op.apply(current, value))));
        return current;
    }

    public final int updateAndGetValue(final @NotNull MultiOpInt op, final @NotNull int ... value) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwapInt(this, valueFieldOffset,
            ((int) current), ((int) newValue)));

        return newValue;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableInt)
            return value == ((MutableInt) other).getValue();
        else if (other instanceof ImmutableInt)
            return value == ((ImmutableInt) other).getValue();
        else if (other instanceof Integer)
            return ((Integer) other).intValue() == value;
        else
            return false;
    }
    @Override
    public final int hashCode() {
        return Integer.hashCode(value);
    }
    @Override
    public final int compareTo(final @NotNull MutableInt other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ImmutableInt other) {
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