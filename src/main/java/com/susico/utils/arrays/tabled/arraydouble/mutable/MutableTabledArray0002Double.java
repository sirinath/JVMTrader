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

public abstract class MutableTabledArray0002Double extends
    MutableTabledArray0001Double {
    
    protected final static long value0001FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0002Double.class, "value0001");

    protected double value0001;

        
    public final @NotNull double getValue0001() {
        return value0001;
    }

    public final @NotNull double getValue0001Unsafe() {
        return (double) UNSAFE.getDouble(this, value0001FieldOffset);
    }

    public final @NotNull double getValue0001Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0001FieldOffset);
    }
    
    public final void setValue0001(final @NotNull double value0001) {
        this.value0001 = value0001;
    }

    public final void putValue0001Unsafe(final @NotNull double value0001) {
        UNSAFE.putDouble(this, value0001FieldOffset, value0001);
    }

    public final void putValue0001Volatile(final @NotNull double value0001) {
        UNSAFE.putDoubleVolatile(this, value0001FieldOffset, value0001);
    }
    
    public final void putValue0001Ordered(
        final @NotNull double value0001) {
            UNSAFE.putOrderedLong(this, value0001FieldOffset, Double.doubleToRawLongBits(value0001));
    }

    public final boolean compareAndSwapValue0001(final @NotNull double expected,
        final @NotNull double value0001) {
        return UNSAFE.compareAndSwapLong(this,
            value0001FieldOffset,
            Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value0001));
    }

    public final @NotNull double getAndSetValue0001(
        final @NotNull double value0001) {
        return (double) Double.longBitsToDouble(UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            Double.doubleToRawLongBits(value0001)));
    }
        
    public final @NotNull double getAndUpdateValue0001(final @NotNull BiOpDouble op, final @NotNull double value0001) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0001))));
        return current;
    }

    public final @NotNull double updateAndGetValue0001(final @NotNull BiOpDouble op, final @NotNull double value0001) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0001(final @NotNull UnaryOpDouble op) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current))));
        return current;
    }

    public final @NotNull double updateAndGetValue0001(final @NotNull UnaryOpDouble op) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0001FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    public final @NotNull double getAndUpdateValue0001(final @NotNull MultiOpDouble op, final @NotNull double ... value0001) {
        double current;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(op.apply(current, value0001))));
        return current;
    }

    public final @NotNull double updateAndGetValue0001(final @NotNull MultiOpDouble op, final @NotNull double ... value0001) {
        double current;
        double newValue;

        do {
            current = (double) UNSAFE.getDoubleVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

        return newValue;
    }

    protected MutableTabledArray0002Double(
        final boolean checked, final int length, final @NotNull double ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0002Double(
        final boolean checked, final int definedAsValues, final int length, final @NotNull double ... values) {
        super(checked, definedAsValues + 1, length, values);
        final int len = values.length;

        
        if (len >= 1) {
            this.value0001 = ArrayAccess.UNCHECKED.get(1, values);
        } else {
            this.value0001 = Double.NaN;
        }
            
    }

    public static  MutableTabledArray0002Double getInstance(
        final boolean checked, final int length, final @NotNull double ... values) {
        return new MutableTabledArray0002Double(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull double value) {
                switch (index) {
                    
                    case 0:
                        setValue0000(value);
                        break;
                    
                    case 1:
                        setValue0001(value);
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
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
