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
 */ // Auto generated. Do not edit directly!


package com.susico.utils.arrays.tabled.arraydouble.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0008Double extends
    MutableTabledArray0004Double {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Double.class, "value0004");

    protected double value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Double.class, "value0005");

    protected double value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Double.class, "value0006");

    protected double value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Double.class, "value0007");

    protected double value0007;

        
    public final @NotNull double getValue0004() {
        return value0004;
    }

    public final @NotNull double getValue0004Unsafe() {
        return (double) UNSAFE.getDouble(this, value0004FieldOffset);
    }

    public final @NotNull double getValue0004Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0004FieldOffset);
    }
    
    public final @NotNull double getValue0005() {
        return value0005;
    }

    public final @NotNull double getValue0005Unsafe() {
        return (double) UNSAFE.getDouble(this, value0005FieldOffset);
    }

    public final @NotNull double getValue0005Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0005FieldOffset);
    }
    
    public final @NotNull double getValue0006() {
        return value0006;
    }

    public final @NotNull double getValue0006Unsafe() {
        return (double) UNSAFE.getDouble(this, value0006FieldOffset);
    }

    public final @NotNull double getValue0006Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0006FieldOffset);
    }
    
    public final @NotNull double getValue0007() {
        return value0007;
    }

    public final @NotNull double getValue0007Unsafe() {
        return (double) UNSAFE.getDouble(this, value0007FieldOffset);
    }

    public final @NotNull double getValue0007Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0007FieldOffset);
    }
    
    public final void setValue0004(final @NotNull double value0004) {
        this.value0004 = value0004;
    }

    public final void putValue0004Unsafe(final @NotNull double value0004) {
        UNSAFE.putDouble(this, value0004FieldOffset, value0004);
    }

    public final void putValue0004Volatile(final @NotNull double value0004) {
        UNSAFE.putDoubleVolatile(this, value0004FieldOffset, value0004);
    }
    
    public final void putValue0004Ordered(
        final @NotNull double value0004) {
            UNSAFE.putOrderedLong(this, value0004FieldOffset, Double.doubleToRawLongBits(value0004));
    }

    public final boolean compareAndSwapValue0004(final @NotNull double expected,
        final @NotNull double value0004) {
        return UNSAFE.compareAndSwapLong(this,
            value0004FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0004));
    }

    public final @NotNull double getAndSetValue0004(
        final @NotNull double value0004) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0004)));
    }
        
    public final @NotNull double getAndUpdateValue0004(final @NotNull BiOpDouble op, final @NotNull double value0004) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0004))));
        return current;
    }

    public final @NotNull double updateAndGetValue0004(final @NotNull BiOpDouble op, final @NotNull double value0004) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0004FieldOffset);
            newValue = op.apply(current, value0004);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0004(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0004(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0004FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0004(final @NotNull MultiOpDouble op, final @NotNull double ... value0004) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0004))));
        return current;
    }

    public final @NotNull double updateAndGetValue0004(final @NotNull MultiOpDouble op, final @NotNull double ... value0004) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0004FieldOffset);
            newValue = op.apply(current, value0004);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0005(final @NotNull double value0005) {
        this.value0005 = value0005;
    }

    public final void putValue0005Unsafe(final @NotNull double value0005) {
        UNSAFE.putDouble(this, value0005FieldOffset, value0005);
    }

    public final void putValue0005Volatile(final @NotNull double value0005) {
        UNSAFE.putDoubleVolatile(this, value0005FieldOffset, value0005);
    }
    
    public final void putValue0005Ordered(
        final @NotNull double value0005) {
            UNSAFE.putOrderedLong(this, value0005FieldOffset, Double.doubleToRawLongBits(value0005));
    }

    public final boolean compareAndSwapValue0005(final @NotNull double expected,
        final @NotNull double value0005) {
        return UNSAFE.compareAndSwapLong(this,
            value0005FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0005));
    }

    public final @NotNull double getAndSetValue0005(
        final @NotNull double value0005) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0005)));
    }
        
    public final @NotNull double getAndUpdateValue0005(final @NotNull BiOpDouble op, final @NotNull double value0005) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0005))));
        return current;
    }

    public final @NotNull double updateAndGetValue0005(final @NotNull BiOpDouble op, final @NotNull double value0005) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0005FieldOffset);
            newValue = op.apply(current, value0005);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0005(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0005(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0005FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0005(final @NotNull MultiOpDouble op, final @NotNull double ... value0005) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0005))));
        return current;
    }

    public final @NotNull double updateAndGetValue0005(final @NotNull MultiOpDouble op, final @NotNull double ... value0005) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0005FieldOffset);
            newValue = op.apply(current, value0005);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0006(final @NotNull double value0006) {
        this.value0006 = value0006;
    }

    public final void putValue0006Unsafe(final @NotNull double value0006) {
        UNSAFE.putDouble(this, value0006FieldOffset, value0006);
    }

    public final void putValue0006Volatile(final @NotNull double value0006) {
        UNSAFE.putDoubleVolatile(this, value0006FieldOffset, value0006);
    }
    
    public final void putValue0006Ordered(
        final @NotNull double value0006) {
            UNSAFE.putOrderedLong(this, value0006FieldOffset, Double.doubleToRawLongBits(value0006));
    }

    public final boolean compareAndSwapValue0006(final @NotNull double expected,
        final @NotNull double value0006) {
        return UNSAFE.compareAndSwapLong(this,
            value0006FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0006));
    }

    public final @NotNull double getAndSetValue0006(
        final @NotNull double value0006) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0006)));
    }
        
    public final @NotNull double getAndUpdateValue0006(final @NotNull BiOpDouble op, final @NotNull double value0006) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0006))));
        return current;
    }

    public final @NotNull double updateAndGetValue0006(final @NotNull BiOpDouble op, final @NotNull double value0006) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0006FieldOffset);
            newValue = op.apply(current, value0006);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0006(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0006(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0006FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0006(final @NotNull MultiOpDouble op, final @NotNull double ... value0006) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0006))));
        return current;
    }

    public final @NotNull double updateAndGetValue0006(final @NotNull MultiOpDouble op, final @NotNull double ... value0006) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0006FieldOffset);
            newValue = op.apply(current, value0006);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final void setValue0007(final @NotNull double value0007) {
        this.value0007 = value0007;
    }

    public final void putValue0007Unsafe(final @NotNull double value0007) {
        UNSAFE.putDouble(this, value0007FieldOffset, value0007);
    }

    public final void putValue0007Volatile(final @NotNull double value0007) {
        UNSAFE.putDoubleVolatile(this, value0007FieldOffset, value0007);
    }
    
    public final void putValue0007Ordered(
        final @NotNull double value0007) {
            UNSAFE.putOrderedLong(this, value0007FieldOffset, Double.doubleToRawLongBits(value0007));
    }

    public final boolean compareAndSwapValue0007(final @NotNull double expected,
        final @NotNull double value0007) {
        return UNSAFE.compareAndSwapLong(this,
            value0007FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0007));
    }

    public final @NotNull double getAndSetValue0007(
        final @NotNull double value0007) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0007)));
    }
        
    public final @NotNull double getAndUpdateValue0007(final @NotNull BiOpDouble op, final @NotNull double value0007) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0007))));
        return current;
    }

    public final @NotNull double updateAndGetValue0007(final @NotNull BiOpDouble op, final @NotNull double value0007) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0007FieldOffset);
            newValue = op.apply(current, value0007);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0007(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0007(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0007FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0007(final @NotNull MultiOpDouble op, final @NotNull double ... value0007) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0007))));
        return current;
    }

    public final @NotNull double updateAndGetValue0007(final @NotNull MultiOpDouble op, final @NotNull double ... value0007) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0007FieldOffset);
            newValue = op.apply(current, value0007);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    protected MutableTabledArray0008Double(
        final boolean checked, final int length, final @NotNull double ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0008Double(
        final boolean checked, final int definedAsValues, final int length, final @NotNull double ... values) {
        super(checked, definedAsValues + 4, length, values);
        final int len = values.length;

        
        if (len >= 7) {
            this.value0007 = ArrayAccess.UNCHECKED.get(7, values);
        } else {
            this.value0007 = Double.NaN;
        }
            
        if (len >= 6) {
            this.value0006 = ArrayAccess.UNCHECKED.get(6, values);
        } else {
            this.value0006 = Double.NaN;
        }
            
        if (len >= 5) {
            this.value0005 = ArrayAccess.UNCHECKED.get(5, values);
        } else {
            this.value0005 = Double.NaN;
        }
            
        if (len >= 4) {
            this.value0004 = ArrayAccess.UNCHECKED.get(4, values);
        } else {
            this.value0004 = Double.NaN;
        }
            
    }

    public static  MutableTabledArray0008Double getInstance(
        final boolean checked, final int length, final @NotNull double ... values) {
        return new MutableTabledArray0008Double(checked, length, values) {
    
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
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
