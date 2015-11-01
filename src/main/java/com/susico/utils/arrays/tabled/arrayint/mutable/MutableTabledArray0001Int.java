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

package com.susico.utils.arrays.tabled.arrayint.mutable;

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0001Int extends
    MutableTabledArray0000Int {
    
    protected final static long value0000FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0001Int.class, "value0000");

    @Contended protected int value0000;

    public final @NotNull int getValue0000() {
        return value0000;
    }

    public final @NotNull int getValue0000Volatile() {
        return (int) UNSAFE.getIntVolatile(this, value0000FieldOffset);
    }

    public final @NotNull int getValue0000Unsafe() {
        return (int) UNSAFE.getInt(this, value0000FieldOffset);
    }
    
    public final void setValue0000(final @NotNull int value0000) {
        this.value0000 = value0000;
    }

    public final void putValue0000Volatile(final @NotNull int value0000) {
         UNSAFE.putIntVolatile(this, value0000FieldOffset, value0000);
    }

    public final void putValue0000Unsafe(final @NotNull int value0000) {
        UNSAFE.putInt(this, value0000FieldOffset, value0000);
    }
    
    public final void putValue0000Ordered(
        final @NotNull int value0000) {
            UNSAFE.putOrderedInt(this, value0000FieldOffset, (value0000));
    }

    public final boolean compareAndSwapValue0000(final @NotNull int expected,
        final @NotNull int value0000) {
        return UNSAFE.compareAndSwapInt(this,
            value0000FieldOffset,
            (expected), (value0000));
    }

    public final @NotNull int getAndSetValue0000(
        final @NotNull int value0000) {
        return (int) (UNSAFE.getAndSetInt(this,
            value0000FieldOffset,
            (value0000)));
    }
        
    public final @NotNull int getAndUpdateValue0000(final @NotNull BiOpInt op, final @NotNull int value0000) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (op.apply(current, value0000))));
        return current;
    }

    public final @NotNull int updateAndGetValue0000(final @NotNull BiOpInt op, final @NotNull int value0000) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0000FieldOffset);
            newValue = op.apply(current, value0000);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0000(final @NotNull UnaryOpInt op) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull int updateAndGetValue0000(final @NotNull UnaryOpInt op) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0000FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0000(final @NotNull MultiOpInt op, final @NotNull int ... value0000) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (op.apply(current, value0000))));
        return current;
    }

    public final @NotNull int updateAndGetValue0000(final @NotNull MultiOpInt op, final @NotNull int ... value0000) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0000FieldOffset);
            newValue = op.apply(current, value0000);
        } while (!UNSAFE.compareAndSwapInt(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0001Int(
        final boolean checked, final int length, final @NotNull int ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0001Int(
        final boolean checked, final int definedAsValues, final int length, final @NotNull int ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = 0;
        }

    }

    public static  MutableTabledArray0001Int getInstance(
        final boolean checked, final int length, final @NotNull int ... values) {
        return new MutableTabledArray0001Int(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        setValue0000(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        putValue0000Unsafe(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putVolatile(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        putValue0000Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putOrdered(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        putValue0000Ordered(value);
                        break;
                    
                    default:
                        putOrderedToRest(index, value);
                }
            }
            
            @Override
            public final boolean compareAndSwap(final int index, final @NotNull int expected, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return compareAndSwapValue0000(expected, value);
                    
                    default:
                        return compareAndSwapFromRest(index, expected, value);
                }
            }
            
            @Override
            public final @NotNull int getAndSet(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return getAndSetValue0000(value);
                    
                    default:
                        return getAndSetFromRest(index, value);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull BiOpInt op, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull BiOpInt op, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull UnaryOpInt op) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op);
                    
                    default:
                        return getAndUpdateFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull UnaryOpInt op) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op);
                    
                    default:
                        return updateAndGetFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull MultiOpInt op, final @NotNull int ... value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull MultiOpInt op, final @NotNull int ... value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
    
            @Override
            public final @NotNull int get(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull int getUnsafe(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull int getVolatile(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
            
        };
    }
}
