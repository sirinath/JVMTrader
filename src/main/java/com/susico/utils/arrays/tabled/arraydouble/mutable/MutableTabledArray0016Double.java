 // Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.arraydouble.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0016Double extends
    MutableTabledArray0008Double {
    
    protected final static long value0008FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0008");

    protected double value0008;

    protected final static long value0009FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0009");

    protected double value0009;

    protected final static long value0010FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0010");

    protected double value0010;

    protected final static long value0011FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0011");

    protected double value0011;

    protected final static long value0012FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0012");

    protected double value0012;

    protected final static long value0013FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0013");

    protected double value0013;

    protected final static long value0014FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0014");

    protected double value0014;

    protected final static long value0015FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0016Double.class, "value0015");

    protected double value0015;

        
    public final @NotNull double getValue0008() {
        return value0008;
    }

    public final @NotNull double getValue0008Unsafe() {
        return (double) UNSAFE.getDouble(this, value0008FieldOffset);
    }

    public final @NotNull double getValue0008Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0008FieldOffset);
    }
    
    public final @NotNull double getValue0009() {
        return value0009;
    }

    public final @NotNull double getValue0009Unsafe() {
        return (double) UNSAFE.getDouble(this, value0009FieldOffset);
    }

    public final @NotNull double getValue0009Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0009FieldOffset);
    }
    
    public final @NotNull double getValue0010() {
        return value0010;
    }

    public final @NotNull double getValue0010Unsafe() {
        return (double) UNSAFE.getDouble(this, value0010FieldOffset);
    }

    public final @NotNull double getValue0010Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0010FieldOffset);
    }
    
    public final @NotNull double getValue0011() {
        return value0011;
    }

    public final @NotNull double getValue0011Unsafe() {
        return (double) UNSAFE.getDouble(this, value0011FieldOffset);
    }

    public final @NotNull double getValue0011Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0011FieldOffset);
    }
    
    public final @NotNull double getValue0012() {
        return value0012;
    }

    public final @NotNull double getValue0012Unsafe() {
        return (double) UNSAFE.getDouble(this, value0012FieldOffset);
    }

    public final @NotNull double getValue0012Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0012FieldOffset);
    }
    
    public final @NotNull double getValue0013() {
        return value0013;
    }

    public final @NotNull double getValue0013Unsafe() {
        return (double) UNSAFE.getDouble(this, value0013FieldOffset);
    }

    public final @NotNull double getValue0013Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0013FieldOffset);
    }
    
    public final @NotNull double getValue0014() {
        return value0014;
    }

    public final @NotNull double getValue0014Unsafe() {
        return (double) UNSAFE.getDouble(this, value0014FieldOffset);
    }

    public final @NotNull double getValue0014Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0014FieldOffset);
    }
    
    public final @NotNull double getValue0015() {
        return value0015;
    }

    public final @NotNull double getValue0015Unsafe() {
        return (double) UNSAFE.getDouble(this, value0015FieldOffset);
    }

    public final @NotNull double getValue0015Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0015FieldOffset);
    }
    
    public final void setValue0008(final @NotNull double value0008) {
        this.value0008 = value0008;
    }

    public final void putValue0008Unsafe(final @NotNull double value0008) {
        UNSAFE.putDouble(this, value0008FieldOffset, value0008);
    }

    public final void putValue0008Volatile(final @NotNull double value0008) {
        UNSAFE.putDoubleVolatile(this, value0008FieldOffset, value0008);
    }
    
    public final void putValue0008Ordered(
        final @NotNull double value0008) {
            UNSAFE.putOrderedLong(this, value0008FieldOffset, Double.doubleToRawLongBits(value0008));
    }

    public final boolean compareAndSwapValue0008(final @NotNull double expected,
        final @NotNull double value0008) {
        return UNSAFE.compareAndSwapLong(this,
            value0008FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0008));
    }

    public final @NotNull double getAndSetValue0008(
        final @NotNull double value0008) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0008)));
    }
        
    public final @NotNull double getAndUpdateValue0008(final @NotNull BiOpDouble op, final @NotNull double value0008) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0008FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0008))));
        return current;
    }

    public final @NotNull double updateAndGetValue0008(final @NotNull BiOpDouble op, final @NotNull double value0008) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0008FieldOffset);
            newValue = op.apply(current, value0008);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0008(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0008FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0008(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0008FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0008(final @NotNull MultiOpDouble op, final @NotNull double ... value0008) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0008FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0008))));
        return current;
    }

    public final @NotNull double updateAndGetValue0008(final @NotNull MultiOpDouble op, final @NotNull double ... value0008) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0008FieldOffset);
            newValue = op.apply(current, value0008);
        } while (!UNSAFE.compareAndSwapLong(this, value0008FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0009(final @NotNull double value0009) {
        this.value0009 = value0009;
    }

    public final void putValue0009Unsafe(final @NotNull double value0009) {
        UNSAFE.putDouble(this, value0009FieldOffset, value0009);
    }

    public final void putValue0009Volatile(final @NotNull double value0009) {
        UNSAFE.putDoubleVolatile(this, value0009FieldOffset, value0009);
    }
    
    public final void putValue0009Ordered(
        final @NotNull double value0009) {
            UNSAFE.putOrderedLong(this, value0009FieldOffset, Double.doubleToRawLongBits(value0009));
    }

    public final boolean compareAndSwapValue0009(final @NotNull double expected,
        final @NotNull double value0009) {
        return UNSAFE.compareAndSwapLong(this,
            value0009FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0009));
    }

    public final @NotNull double getAndSetValue0009(
        final @NotNull double value0009) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0009)));
    }
        
    public final @NotNull double getAndUpdateValue0009(final @NotNull BiOpDouble op, final @NotNull double value0009) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0009FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0009))));
        return current;
    }

    public final @NotNull double updateAndGetValue0009(final @NotNull BiOpDouble op, final @NotNull double value0009) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0009FieldOffset);
            newValue = op.apply(current, value0009);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0009(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0009FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0009(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0009FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0009(final @NotNull MultiOpDouble op, final @NotNull double ... value0009) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0009FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0009))));
        return current;
    }

    public final @NotNull double updateAndGetValue0009(final @NotNull MultiOpDouble op, final @NotNull double ... value0009) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0009FieldOffset);
            newValue = op.apply(current, value0009);
        } while (!UNSAFE.compareAndSwapLong(this, value0009FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0010(final @NotNull double value0010) {
        this.value0010 = value0010;
    }

    public final void putValue0010Unsafe(final @NotNull double value0010) {
        UNSAFE.putDouble(this, value0010FieldOffset, value0010);
    }

    public final void putValue0010Volatile(final @NotNull double value0010) {
        UNSAFE.putDoubleVolatile(this, value0010FieldOffset, value0010);
    }
    
    public final void putValue0010Ordered(
        final @NotNull double value0010) {
            UNSAFE.putOrderedLong(this, value0010FieldOffset, Double.doubleToRawLongBits(value0010));
    }

    public final boolean compareAndSwapValue0010(final @NotNull double expected,
        final @NotNull double value0010) {
        return UNSAFE.compareAndSwapLong(this,
            value0010FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0010));
    }

    public final @NotNull double getAndSetValue0010(
        final @NotNull double value0010) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0010)));
    }
        
    public final @NotNull double getAndUpdateValue0010(final @NotNull BiOpDouble op, final @NotNull double value0010) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0010FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0010))));
        return current;
    }

    public final @NotNull double updateAndGetValue0010(final @NotNull BiOpDouble op, final @NotNull double value0010) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0010FieldOffset);
            newValue = op.apply(current, value0010);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0010(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0010FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0010(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0010FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0010(final @NotNull MultiOpDouble op, final @NotNull double ... value0010) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0010FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0010))));
        return current;
    }

    public final @NotNull double updateAndGetValue0010(final @NotNull MultiOpDouble op, final @NotNull double ... value0010) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0010FieldOffset);
            newValue = op.apply(current, value0010);
        } while (!UNSAFE.compareAndSwapLong(this, value0010FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0011(final @NotNull double value0011) {
        this.value0011 = value0011;
    }

    public final void putValue0011Unsafe(final @NotNull double value0011) {
        UNSAFE.putDouble(this, value0011FieldOffset, value0011);
    }

    public final void putValue0011Volatile(final @NotNull double value0011) {
        UNSAFE.putDoubleVolatile(this, value0011FieldOffset, value0011);
    }
    
    public final void putValue0011Ordered(
        final @NotNull double value0011) {
            UNSAFE.putOrderedLong(this, value0011FieldOffset, Double.doubleToRawLongBits(value0011));
    }

    public final boolean compareAndSwapValue0011(final @NotNull double expected,
        final @NotNull double value0011) {
        return UNSAFE.compareAndSwapLong(this,
            value0011FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0011));
    }

    public final @NotNull double getAndSetValue0011(
        final @NotNull double value0011) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0011)));
    }
        
    public final @NotNull double getAndUpdateValue0011(final @NotNull BiOpDouble op, final @NotNull double value0011) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0011FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0011))));
        return current;
    }

    public final @NotNull double updateAndGetValue0011(final @NotNull BiOpDouble op, final @NotNull double value0011) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0011FieldOffset);
            newValue = op.apply(current, value0011);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0011(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0011FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0011(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0011FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0011(final @NotNull MultiOpDouble op, final @NotNull double ... value0011) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0011FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0011))));
        return current;
    }

    public final @NotNull double updateAndGetValue0011(final @NotNull MultiOpDouble op, final @NotNull double ... value0011) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0011FieldOffset);
            newValue = op.apply(current, value0011);
        } while (!UNSAFE.compareAndSwapLong(this, value0011FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0012(final @NotNull double value0012) {
        this.value0012 = value0012;
    }

    public final void putValue0012Unsafe(final @NotNull double value0012) {
        UNSAFE.putDouble(this, value0012FieldOffset, value0012);
    }

    public final void putValue0012Volatile(final @NotNull double value0012) {
        UNSAFE.putDoubleVolatile(this, value0012FieldOffset, value0012);
    }
    
    public final void putValue0012Ordered(
        final @NotNull double value0012) {
            UNSAFE.putOrderedLong(this, value0012FieldOffset, Double.doubleToRawLongBits(value0012));
    }

    public final boolean compareAndSwapValue0012(final @NotNull double expected,
        final @NotNull double value0012) {
        return UNSAFE.compareAndSwapLong(this,
            value0012FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0012));
    }

    public final @NotNull double getAndSetValue0012(
        final @NotNull double value0012) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0012)));
    }
        
    public final @NotNull double getAndUpdateValue0012(final @NotNull BiOpDouble op, final @NotNull double value0012) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0012FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0012))));
        return current;
    }

    public final @NotNull double updateAndGetValue0012(final @NotNull BiOpDouble op, final @NotNull double value0012) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0012FieldOffset);
            newValue = op.apply(current, value0012);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0012(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0012FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0012(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0012FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0012(final @NotNull MultiOpDouble op, final @NotNull double ... value0012) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0012FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0012))));
        return current;
    }

    public final @NotNull double updateAndGetValue0012(final @NotNull MultiOpDouble op, final @NotNull double ... value0012) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0012FieldOffset);
            newValue = op.apply(current, value0012);
        } while (!UNSAFE.compareAndSwapLong(this, value0012FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0013(final @NotNull double value0013) {
        this.value0013 = value0013;
    }

    public final void putValue0013Unsafe(final @NotNull double value0013) {
        UNSAFE.putDouble(this, value0013FieldOffset, value0013);
    }

    public final void putValue0013Volatile(final @NotNull double value0013) {
        UNSAFE.putDoubleVolatile(this, value0013FieldOffset, value0013);
    }
    
    public final void putValue0013Ordered(
        final @NotNull double value0013) {
            UNSAFE.putOrderedLong(this, value0013FieldOffset, Double.doubleToRawLongBits(value0013));
    }

    public final boolean compareAndSwapValue0013(final @NotNull double expected,
        final @NotNull double value0013) {
        return UNSAFE.compareAndSwapLong(this,
            value0013FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0013));
    }

    public final @NotNull double getAndSetValue0013(
        final @NotNull double value0013) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0013)));
    }
        
    public final @NotNull double getAndUpdateValue0013(final @NotNull BiOpDouble op, final @NotNull double value0013) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0013FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0013))));
        return current;
    }

    public final @NotNull double updateAndGetValue0013(final @NotNull BiOpDouble op, final @NotNull double value0013) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0013FieldOffset);
            newValue = op.apply(current, value0013);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0013(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0013FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0013(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0013FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0013(final @NotNull MultiOpDouble op, final @NotNull double ... value0013) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0013FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0013))));
        return current;
    }

    public final @NotNull double updateAndGetValue0013(final @NotNull MultiOpDouble op, final @NotNull double ... value0013) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0013FieldOffset);
            newValue = op.apply(current, value0013);
        } while (!UNSAFE.compareAndSwapLong(this, value0013FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0014(final @NotNull double value0014) {
        this.value0014 = value0014;
    }

    public final void putValue0014Unsafe(final @NotNull double value0014) {
        UNSAFE.putDouble(this, value0014FieldOffset, value0014);
    }

    public final void putValue0014Volatile(final @NotNull double value0014) {
        UNSAFE.putDoubleVolatile(this, value0014FieldOffset, value0014);
    }
    
    public final void putValue0014Ordered(
        final @NotNull double value0014) {
            UNSAFE.putOrderedLong(this, value0014FieldOffset, Double.doubleToRawLongBits(value0014));
    }

    public final boolean compareAndSwapValue0014(final @NotNull double expected,
        final @NotNull double value0014) {
        return UNSAFE.compareAndSwapLong(this,
            value0014FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0014));
    }

    public final @NotNull double getAndSetValue0014(
        final @NotNull double value0014) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0014)));
    }
        
    public final @NotNull double getAndUpdateValue0014(final @NotNull BiOpDouble op, final @NotNull double value0014) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0014FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0014))));
        return current;
    }

    public final @NotNull double updateAndGetValue0014(final @NotNull BiOpDouble op, final @NotNull double value0014) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0014FieldOffset);
            newValue = op.apply(current, value0014);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0014(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0014FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0014(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0014FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0014(final @NotNull MultiOpDouble op, final @NotNull double ... value0014) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0014FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0014))));
        return current;
    }

    public final @NotNull double updateAndGetValue0014(final @NotNull MultiOpDouble op, final @NotNull double ... value0014) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0014FieldOffset);
            newValue = op.apply(current, value0014);
        } while (!UNSAFE.compareAndSwapLong(this, value0014FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0015(final @NotNull double value0015) {
        this.value0015 = value0015;
    }

    public final void putValue0015Unsafe(final @NotNull double value0015) {
        UNSAFE.putDouble(this, value0015FieldOffset, value0015);
    }

    public final void putValue0015Volatile(final @NotNull double value0015) {
        UNSAFE.putDoubleVolatile(this, value0015FieldOffset, value0015);
    }
    
    public final void putValue0015Ordered(
        final @NotNull double value0015) {
            UNSAFE.putOrderedLong(this, value0015FieldOffset, Double.doubleToRawLongBits(value0015));
    }

    public final boolean compareAndSwapValue0015(final @NotNull double expected,
        final @NotNull double value0015) {
        return UNSAFE.compareAndSwapLong(this,
            value0015FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0015));
    }

    public final @NotNull double getAndSetValue0015(
        final @NotNull double value0015) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0015)));
    }
        
    public final @NotNull double getAndUpdateValue0015(final @NotNull BiOpDouble op, final @NotNull double value0015) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0015FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0015))));
        return current;
    }

    public final @NotNull double updateAndGetValue0015(final @NotNull BiOpDouble op, final @NotNull double value0015) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0015FieldOffset);
            newValue = op.apply(current, value0015);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0015(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0015FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0015(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0015FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0015(final @NotNull MultiOpDouble op, final @NotNull double ... value0015) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0015FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0015))));
        return current;
    }

    public final @NotNull double updateAndGetValue0015(final @NotNull MultiOpDouble op, final @NotNull double ... value0015) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0015FieldOffset);
            newValue = op.apply(current, value0015);
        } while (!UNSAFE.compareAndSwapLong(this, value0015FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    protected MutableTabledArray0016Double(
        final boolean checked, final int length, final @NotNull double ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0016Double(
        final boolean checked, final int definedAsValues, final int length, final @NotNull double ... values) {
        super(checked, definedAsValues + 8, length, values);
        final int len = values.length;

        
        if (len >= 15) {
            this.value0015 = ArrayAccess.UNCHECKED.get(15, values);
        } else {
            this.value0015 = Double.NaN;
        }
            
        if (len >= 14) {
            this.value0014 = ArrayAccess.UNCHECKED.get(14, values);
        } else {
            this.value0014 = Double.NaN;
        }
            
        if (len >= 13) {
            this.value0013 = ArrayAccess.UNCHECKED.get(13, values);
        } else {
            this.value0013 = Double.NaN;
        }
            
        if (len >= 12) {
            this.value0012 = ArrayAccess.UNCHECKED.get(12, values);
        } else {
            this.value0012 = Double.NaN;
        }
            
        if (len >= 11) {
            this.value0011 = ArrayAccess.UNCHECKED.get(11, values);
        } else {
            this.value0011 = Double.NaN;
        }
            
        if (len >= 10) {
            this.value0010 = ArrayAccess.UNCHECKED.get(10, values);
        } else {
            this.value0010 = Double.NaN;
        }
            
        if (len >= 9) {
            this.value0009 = ArrayAccess.UNCHECKED.get(9, values);
        } else {
            this.value0009 = Double.NaN;
        }
            
        if (len >= 8) {
            this.value0008 = ArrayAccess.UNCHECKED.get(8, values);
        } else {
            this.value0008 = Double.NaN;
        }
            
    }

    public static  MutableTabledArray0016Double getInstance(
        final boolean checked, final int length, final @NotNull double ... values) {
        return new MutableTabledArray0016Double(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull double value) {
                switch (index) {
                    
                    case 0:
                        setValue0000(value);
                        break;
                    
                    case 1:
                        setValue0001(value);
                        break;
                    
                    case 2:
                        setValue0002(value);
                        break;
                    
                    case 3:
                        setValue0003(value);
                        break;
                    
                    case 4:
                        setValue0004(value);
                        break;
                    
                    case 5:
                        setValue0005(value);
                        break;
                    
                    case 6:
                        setValue0006(value);
                        break;
                    
                    case 7:
                        setValue0007(value);
                        break;
                    
                    case 8:
                        setValue0008(value);
                        break;
                    
                    case 9:
                        setValue0009(value);
                        break;
                    
                    case 10:
                        setValue0010(value);
                        break;
                    
                    case 11:
                        setValue0011(value);
                        break;
                    
                    case 12:
                        setValue0012(value);
                        break;
                    
                    case 13:
                        setValue0013(value);
                        break;
                    
                    case 14:
                        setValue0014(value);
                        break;
                    
                    case 15:
                        setValue0015(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putVolatile(final int index, final @NotNull double value) {
                switch (index) {
                    
                    case 0:
                        putValue0000Volatile(value);
                        break;
                    
                    case 1:
                        putValue0001Volatile(value);
                        break;
                    
                    case 2:
                        putValue0002Volatile(value);
                        break;
                    
                    case 3:
                        putValue0003Volatile(value);
                        break;
                    
                    case 4:
                        putValue0004Volatile(value);
                        break;
                    
                    case 5:
                        putValue0005Volatile(value);
                        break;
                    
                    case 6:
                        putValue0006Volatile(value);
                        break;
                    
                    case 7:
                        putValue0007Volatile(value);
                        break;
                    
                    case 8:
                        putValue0008Volatile(value);
                        break;
                    
                    case 9:
                        putValue0009Volatile(value);
                        break;
                    
                    case 10:
                        putValue0010Volatile(value);
                        break;
                    
                    case 11:
                        putValue0011Volatile(value);
                        break;
                    
                    case 12:
                        putValue0012Volatile(value);
                        break;
                    
                    case 13:
                        putValue0013Volatile(value);
                        break;
                    
                    case 14:
                        putValue0014Volatile(value);
                        break;
                    
                    case 15:
                        putValue0015Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull double value) {
                switch (index) {
                    
                    case 0:
                        putValue0000Unsafe(value);
                        break;
                    
                    case 1:
                        putValue0001Unsafe(value);
                        break;
                    
                    case 2:
                        putValue0002Unsafe(value);
                        break;
                    
                    case 3:
                        putValue0003Unsafe(value);
                        break;
                    
                    case 4:
                        putValue0004Unsafe(value);
                        break;
                    
                    case 5:
                        putValue0005Unsafe(value);
                        break;
                    
                    case 6:
                        putValue0006Unsafe(value);
                        break;
                    
                    case 7:
                        putValue0007Unsafe(value);
                        break;
                    
                    case 8:
                        putValue0008Unsafe(value);
                        break;
                    
                    case 9:
                        putValue0009Unsafe(value);
                        break;
                    
                    case 10:
                        putValue0010Unsafe(value);
                        break;
                    
                    case 11:
                        putValue0011Unsafe(value);
                        break;
                    
                    case 12:
                        putValue0012Unsafe(value);
                        break;
                    
                    case 13:
                        putValue0013Unsafe(value);
                        break;
                    
                    case 14:
                        putValue0014Unsafe(value);
                        break;
                    
                    case 15:
                        putValue0015Unsafe(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putOrdered(final int index, final @NotNull double value) {
                switch (index) {
                
                    case 0:
                        putValue0000Ordered(value);
                        break;
                    
                    case 1:
                        putValue0001Ordered(value);
                        break;
                    
                    case 2:
                        putValue0002Ordered(value);
                        break;
                    
                    case 3:
                        putValue0003Ordered(value);
                        break;
                    
                    case 4:
                        putValue0004Ordered(value);
                        break;
                    
                    case 5:
                        putValue0005Ordered(value);
                        break;
                    
                    case 6:
                        putValue0006Ordered(value);
                        break;
                    
                    case 7:
                        putValue0007Ordered(value);
                        break;
                    
                    case 8:
                        putValue0008Ordered(value);
                        break;
                    
                    case 9:
                        putValue0009Ordered(value);
                        break;
                    
                    case 10:
                        putValue0010Ordered(value);
                        break;
                    
                    case 11:
                        putValue0011Ordered(value);
                        break;
                    
                    case 12:
                        putValue0012Ordered(value);
                        break;
                    
                    case 13:
                        putValue0013Ordered(value);
                        break;
                    
                    case 14:
                        putValue0014Ordered(value);
                        break;
                    
                    case 15:
                        putValue0015Ordered(value);
                        break;
                    
                    default:
                        putOrderedToRest(index, value);
                }
            }
            
            @Override
            public final boolean compareAndSwap(final int index, final @NotNull double expected, final @NotNull double value) {
                switch (index) {
                
                    case 0:
                        return compareAndSwapValue0000(expected, value);
                    
                    case 1:
                        return compareAndSwapValue0001(expected, value);
                    
                    case 2:
                        return compareAndSwapValue0002(expected, value);
                    
                    case 3:
                        return compareAndSwapValue0003(expected, value);
                    
                    case 4:
                        return compareAndSwapValue0004(expected, value);
                    
                    case 5:
                        return compareAndSwapValue0005(expected, value);
                    
                    case 6:
                        return compareAndSwapValue0006(expected, value);
                    
                    case 7:
                        return compareAndSwapValue0007(expected, value);
                    
                    case 8:
                        return compareAndSwapValue0008(expected, value);
                    
                    case 9:
                        return compareAndSwapValue0009(expected, value);
                    
                    case 10:
                        return compareAndSwapValue0010(expected, value);
                    
                    case 11:
                        return compareAndSwapValue0011(expected, value);
                    
                    case 12:
                        return compareAndSwapValue0012(expected, value);
                    
                    case 13:
                        return compareAndSwapValue0013(expected, value);
                    
                    case 14:
                        return compareAndSwapValue0014(expected, value);
                    
                    case 15:
                        return compareAndSwapValue0015(expected, value);
                    
                    default:
                        return compareAndSwapFromRest(index, expected, value);
                }
            }
            
            @Override
            public final @NotNull double getAndSet(final int index, final @NotNull double value) {
                switch (index) {
                
                    case 0:
                        return getAndSetValue0000(value);
                    
                    case 1:
                        return getAndSetValue0001(value);
                    
                    case 2:
                        return getAndSetValue0002(value);
                    
                    case 3:
                        return getAndSetValue0003(value);
                    
                    case 4:
                        return getAndSetValue0004(value);
                    
                    case 5:
                        return getAndSetValue0005(value);
                    
                    case 6:
                        return getAndSetValue0006(value);
                    
                    case 7:
                        return getAndSetValue0007(value);
                    
                    case 8:
                        return getAndSetValue0008(value);
                    
                    case 9:
                        return getAndSetValue0009(value);
                    
                    case 10:
                        return getAndSetValue0010(value);
                    
                    case 11:
                        return getAndSetValue0011(value);
                    
                    case 12:
                        return getAndSetValue0012(value);
                    
                    case 13:
                        return getAndSetValue0013(value);
                    
                    case 14:
                        return getAndSetValue0014(value);
                    
                    case 15:
                        return getAndSetValue0015(value);
                    
                    default:
                        return getAndSetFromRest(index, value);
                }
            }
            
            @Override
            public final @NotNull double getAndUpdate(final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    case 2:
                        return getAndUpdateValue0002(op, value);
                    
                    case 3:
                        return getAndUpdateValue0003(op, value);
                    
                    case 4:
                        return getAndUpdateValue0004(op, value);
                    
                    case 5:
                        return getAndUpdateValue0005(op, value);
                    
                    case 6:
                        return getAndUpdateValue0006(op, value);
                    
                    case 7:
                        return getAndUpdateValue0007(op, value);
                    
                    case 8:
                        return getAndUpdateValue0008(op, value);
                    
                    case 9:
                        return getAndUpdateValue0009(op, value);
                    
                    case 10:
                        return getAndUpdateValue0010(op, value);
                    
                    case 11:
                        return getAndUpdateValue0011(op, value);
                    
                    case 12:
                        return getAndUpdateValue0012(op, value);
                    
                    case 13:
                        return getAndUpdateValue0013(op, value);
                    
                    case 14:
                        return getAndUpdateValue0014(op, value);
                    
                    case 15:
                        return getAndUpdateValue0015(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull double updateAndGet(final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    case 2:
                        return updateAndGetValue0002(op, value);
                    
                    case 3:
                        return updateAndGetValue0003(op, value);
                    
                    case 4:
                        return updateAndGetValue0004(op, value);
                    
                    case 5:
                        return updateAndGetValue0005(op, value);
                    
                    case 6:
                        return updateAndGetValue0006(op, value);
                    
                    case 7:
                        return updateAndGetValue0007(op, value);
                    
                    case 8:
                        return updateAndGetValue0008(op, value);
                    
                    case 9:
                        return updateAndGetValue0009(op, value);
                    
                    case 10:
                        return updateAndGetValue0010(op, value);
                    
                    case 11:
                        return updateAndGetValue0011(op, value);
                    
                    case 12:
                        return updateAndGetValue0012(op, value);
                    
                    case 13:
                        return updateAndGetValue0013(op, value);
                    
                    case 14:
                        return updateAndGetValue0014(op, value);
                    
                    case 15:
                        return updateAndGetValue0015(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull double getAndUpdate(final int index, final @NotNull UnaryOpDouble op) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op);
                    
                    case 1:
                        return getAndUpdateValue0001(op);
                    
                    case 2:
                        return getAndUpdateValue0002(op);
                    
                    case 3:
                        return getAndUpdateValue0003(op);
                    
                    case 4:
                        return getAndUpdateValue0004(op);
                    
                    case 5:
                        return getAndUpdateValue0005(op);
                    
                    case 6:
                        return getAndUpdateValue0006(op);
                    
                    case 7:
                        return getAndUpdateValue0007(op);
                    
                    case 8:
                        return getAndUpdateValue0008(op);
                    
                    case 9:
                        return getAndUpdateValue0009(op);
                    
                    case 10:
                        return getAndUpdateValue0010(op);
                    
                    case 11:
                        return getAndUpdateValue0011(op);
                    
                    case 12:
                        return getAndUpdateValue0012(op);
                    
                    case 13:
                        return getAndUpdateValue0013(op);
                    
                    case 14:
                        return getAndUpdateValue0014(op);
                    
                    case 15:
                        return getAndUpdateValue0015(op);
                    
                    default:
                        return getAndUpdateFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull double updateAndGet(final int index, final @NotNull UnaryOpDouble op) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op);
                    
                    case 1:
                        return updateAndGetValue0001(op);
                    
                    case 2:
                        return updateAndGetValue0002(op);
                    
                    case 3:
                        return updateAndGetValue0003(op);
                    
                    case 4:
                        return updateAndGetValue0004(op);
                    
                    case 5:
                        return updateAndGetValue0005(op);
                    
                    case 6:
                        return updateAndGetValue0006(op);
                    
                    case 7:
                        return updateAndGetValue0007(op);
                    
                    case 8:
                        return updateAndGetValue0008(op);
                    
                    case 9:
                        return updateAndGetValue0009(op);
                    
                    case 10:
                        return updateAndGetValue0010(op);
                    
                    case 11:
                        return updateAndGetValue0011(op);
                    
                    case 12:
                        return updateAndGetValue0012(op);
                    
                    case 13:
                        return updateAndGetValue0013(op);
                    
                    case 14:
                        return updateAndGetValue0014(op);
                    
                    case 15:
                        return updateAndGetValue0015(op);
                    
                    default:
                        return updateAndGetFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull double getAndUpdate(final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    case 2:
                        return getAndUpdateValue0002(op, value);
                    
                    case 3:
                        return getAndUpdateValue0003(op, value);
                    
                    case 4:
                        return getAndUpdateValue0004(op, value);
                    
                    case 5:
                        return getAndUpdateValue0005(op, value);
                    
                    case 6:
                        return getAndUpdateValue0006(op, value);
                    
                    case 7:
                        return getAndUpdateValue0007(op, value);
                    
                    case 8:
                        return getAndUpdateValue0008(op, value);
                    
                    case 9:
                        return getAndUpdateValue0009(op, value);
                    
                    case 10:
                        return getAndUpdateValue0010(op, value);
                    
                    case 11:
                        return getAndUpdateValue0011(op, value);
                    
                    case 12:
                        return getAndUpdateValue0012(op, value);
                    
                    case 13:
                        return getAndUpdateValue0013(op, value);
                    
                    case 14:
                        return getAndUpdateValue0014(op, value);
                    
                    case 15:
                        return getAndUpdateValue0015(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull double updateAndGet(final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    case 2:
                        return updateAndGetValue0002(op, value);
                    
                    case 3:
                        return updateAndGetValue0003(op, value);
                    
                    case 4:
                        return updateAndGetValue0004(op, value);
                    
                    case 5:
                        return updateAndGetValue0005(op, value);
                    
                    case 6:
                        return updateAndGetValue0006(op, value);
                    
                    case 7:
                        return updateAndGetValue0007(op, value);
                    
                    case 8:
                        return updateAndGetValue0008(op, value);
                    
                    case 9:
                        return updateAndGetValue0009(op, value);
                    
                    case 10:
                        return updateAndGetValue0010(op, value);
                    
                    case 11:
                        return updateAndGetValue0011(op, value);
                    
                    case 12:
                        return updateAndGetValue0012(op, value);
                    
                    case 13:
                        return updateAndGetValue0013(op, value);
                    
                    case 14:
                        return updateAndGetValue0014(op, value);
                    
                    case 15:
                        return updateAndGetValue0015(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
    
            @Override
            public final @NotNull double get(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000();
                                
                    case 1:
                        return getValue0001();
                                
                    case 2:
                        return getValue0002();
                                
                    case 3:
                        return getValue0003();
                                
                    case 4:
                        return getValue0004();
                                
                    case 5:
                        return getValue0005();
                                
                    case 6:
                        return getValue0006();
                                
                    case 7:
                        return getValue0007();
                                
                    case 8:
                        return getValue0008();
                                
                    case 9:
                        return getValue0009();
                                
                    case 10:
                        return getValue0010();
                                
                    case 11:
                        return getValue0011();
                                
                    case 12:
                        return getValue0012();
                                
                    case 13:
                        return getValue0013();
                                
                    case 14:
                        return getValue0014();
                                
                    case 15:
                        return getValue0015();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull double getUnsafe(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Unsafe();
                                
                    case 1:
                        return getValue0001Unsafe();
                                
                    case 2:
                        return getValue0002Unsafe();
                                
                    case 3:
                        return getValue0003Unsafe();
                                
                    case 4:
                        return getValue0004Unsafe();
                                
                    case 5:
                        return getValue0005Unsafe();
                                
                    case 6:
                        return getValue0006Unsafe();
                                
                    case 7:
                        return getValue0007Unsafe();
                                
                    case 8:
                        return getValue0008Unsafe();
                                
                    case 9:
                        return getValue0009Unsafe();
                                
                    case 10:
                        return getValue0010Unsafe();
                                
                    case 11:
                        return getValue0011Unsafe();
                                
                    case 12:
                        return getValue0012Unsafe();
                                
                    case 13:
                        return getValue0013Unsafe();
                                
                    case 14:
                        return getValue0014Unsafe();
                                
                    case 15:
                        return getValue0015Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull double getVolatile(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Volatile();
                                
                    case 1:
                        return getValue0001Volatile();
                                
                    case 2:
                        return getValue0002Volatile();
                                
                    case 3:
                        return getValue0003Volatile();
                                
                    case 4:
                        return getValue0004Volatile();
                                
                    case 5:
                        return getValue0005Volatile();
                                
                    case 6:
                        return getValue0006Volatile();
                                
                    case 7:
                        return getValue0007Volatile();
                                
                    case 8:
                        return getValue0008Volatile();
                                
                    case 9:
                        return getValue0009Volatile();
                                
                    case 10:
                        return getValue0010Volatile();
                                
                    case 11:
                        return getValue0011Volatile();
                                
                    case 12:
                        return getValue0012Volatile();
                                
                    case 13:
                        return getValue0013Volatile();
                                
                    case 14:
                        return getValue0014Volatile();
                                
                    case 15:
                        return getValue0015Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
