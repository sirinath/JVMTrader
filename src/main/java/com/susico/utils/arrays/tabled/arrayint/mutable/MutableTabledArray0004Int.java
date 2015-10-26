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

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0004Int extends
    MutableTabledArray0002Int {
    
    protected final static long value0002FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0004Int.class, "value0002");

    protected int value0002;

    protected final static long value0003FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0004Int.class, "value0003");

    protected int value0003;

        
    public final @NotNull int getValue0002() {
        return value0002;
    }

    public final @NotNull int getValue0002Unsafe() {
        return (int) UNSAFE.getInt(this, value0002FieldOffset);
    }

    public final @NotNull int getValue0002Volatile() {
        return (int) UNSAFE.getIntVolatile(this, value0002FieldOffset);
    }
    
    public final @NotNull int getValue0003() {
        return value0003;
    }

    public final @NotNull int getValue0003Unsafe() {
        return (int) UNSAFE.getInt(this, value0003FieldOffset);
    }

    public final @NotNull int getValue0003Volatile() {
        return (int) UNSAFE.getIntVolatile(this, value0003FieldOffset);
    }
    
    public final void setValue0002(final @NotNull int value0002) {
        this.value0002 = value0002;
    }

    public final void putValue0002Unsafe(final @NotNull int value0002) {
        UNSAFE.putInt(this, value0002FieldOffset, value0002);
    }

    public final void putValue0002Volatile(final @NotNull int value0002) {
        UNSAFE.putIntVolatile(this, value0002FieldOffset, value0002);
    }
    
    public final void putValue0002Ordered(
        final @NotNull int value0002) {
            UNSAFE.putOrderedInt(this, value0002FieldOffset, (value0002));
    }

    public final boolean compareAndSwapValue0002(final @NotNull int expected,
        final @NotNull int value0002) {
        return UNSAFE.compareAndSwapInt(this,
            value0002FieldOffset,
            (expected), (value0002));
    }

    public final @NotNull int getAndSetValue0002(
        final @NotNull int value0002) {
        return (int) (UNSAFE.getAndSetInt(this,
            value0000FieldOffset,
            (value0002)));
    }
        
    public final @NotNull int getAndUpdateValue0002(final @NotNull BiOpInt op, final @NotNull int value0002) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0002FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (op.apply(current, value0002))));
        return current;
    }

    public final @NotNull int updateAndGetValue0002(final @NotNull BiOpInt op, final @NotNull int value0002) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0002FieldOffset);
            newValue = op.apply(current, value0002);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0002(final @NotNull UnaryOpInt op) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0002FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull int updateAndGetValue0002(final @NotNull UnaryOpInt op) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0002FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0002(final @NotNull MultiOpInt op, final @NotNull int ... value0002) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0002FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (op.apply(current, value0002))));
        return current;
    }

    public final @NotNull int updateAndGetValue0002(final @NotNull MultiOpInt op, final @NotNull int ... value0002) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0002FieldOffset);
            newValue = op.apply(current, value0002);
        } while (!UNSAFE.compareAndSwapInt(this, value0002FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final void setValue0003(final @NotNull int value0003) {
        this.value0003 = value0003;
    }

    public final void putValue0003Unsafe(final @NotNull int value0003) {
        UNSAFE.putInt(this, value0003FieldOffset, value0003);
    }

    public final void putValue0003Volatile(final @NotNull int value0003) {
        UNSAFE.putIntVolatile(this, value0003FieldOffset, value0003);
    }
    
    public final void putValue0003Ordered(
        final @NotNull int value0003) {
            UNSAFE.putOrderedInt(this, value0003FieldOffset, (value0003));
    }

    public final boolean compareAndSwapValue0003(final @NotNull int expected,
        final @NotNull int value0003) {
        return UNSAFE.compareAndSwapInt(this,
            value0003FieldOffset,
            (expected), (value0003));
    }

    public final @NotNull int getAndSetValue0003(
        final @NotNull int value0003) {
        return (int) (UNSAFE.getAndSetInt(this,
            value0000FieldOffset,
            (value0003)));
    }
        
    public final @NotNull int getAndUpdateValue0003(final @NotNull BiOpInt op, final @NotNull int value0003) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0003FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (op.apply(current, value0003))));
        return current;
    }

    public final @NotNull int updateAndGetValue0003(final @NotNull BiOpInt op, final @NotNull int value0003) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0003FieldOffset);
            newValue = op.apply(current, value0003);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0003(final @NotNull UnaryOpInt op) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0003FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull int updateAndGetValue0003(final @NotNull UnaryOpInt op) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0003FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull int getAndUpdateValue0003(final @NotNull MultiOpInt op, final @NotNull int ... value0003) {
        int current;

        do {
            current = (int) UNSAFE.getIntVolatile(this,
                value0003FieldOffset);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (op.apply(current, value0003))));
        return current;
    }

    public final @NotNull int updateAndGetValue0003(final @NotNull MultiOpInt op, final @NotNull int ... value0003) {
        int current;
        int newValue;

        do {
            current = (int) UNSAFE.getIntVolatile(this, value0003FieldOffset);
            newValue = op.apply(current, value0003);
        } while (!UNSAFE.compareAndSwapInt(this, value0003FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0004Int(
        final boolean checked, final int length, final @NotNull int ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0004Int(
        final boolean checked, final int definedAsValues, final int length, final @NotNull int ... values) {
        super(checked, definedAsValues + 2, length, values);
        final int len = values.length;

        
        if (len >= 3) {
            this.value0003 = ArrayAccess.UNCHECKED.get(3, values);
        } else {
            this.value0003 = 0;
        }
            
        if (len >= 2) {
            this.value0002 = ArrayAccess.UNCHECKED.get(2, values);
        } else {
            this.value0002 = 0;
        }
            
    }

    public static  MutableTabledArray0004Int getInstance(
        final boolean checked, final int length, final @NotNull int ... values) {
        return new MutableTabledArray0004Int(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull int value) {
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
                    
                    case 1:
                        putValue0001Volatile(value);
                        break;
                    
                    case 2:
                        putValue0002Volatile(value);
                        break;
                    
                    case 3:
                        putValue0003Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull int value) {
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
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putOrdered(final int index, final @NotNull int value) {
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
                    
                    default:
                        putOrderedToRest(index, value);
                }
            }
            
            @Override
            public final boolean compareAndSwap(final int index, final @NotNull int expected, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return compareAndSwapValue0000(expected, value);
                    
                    case 1:
                        return compareAndSwapValue0001(expected, value);
                    
                    case 2:
                        return compareAndSwapValue0002(expected, value);
                    
                    case 3:
                        return compareAndSwapValue0003(expected, value);
                    
                    default:
                        return compareAndSwapFromRest(index, expected, value);
                }
            }
            
            @Override
            public final @NotNull int getAndSet(final int index, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return getAndSetValue0000(value);
                    
                    case 1:
                        return getAndSetValue0001(value);
                    
                    case 2:
                        return getAndSetValue0002(value);
                    
                    case 3:
                        return getAndSetValue0003(value);
                    
                    default:
                        return getAndSetFromRest(index, value);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull BiOpInt op, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    case 2:
                        return getAndUpdateValue0002(op, value);
                    
                    case 3:
                        return getAndUpdateValue0003(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull BiOpInt op, final @NotNull int value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    case 2:
                        return updateAndGetValue0002(op, value);
                    
                    case 3:
                        return updateAndGetValue0003(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull UnaryOpInt op) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op);
                    
                    case 1:
                        return getAndUpdateValue0001(op);
                    
                    case 2:
                        return getAndUpdateValue0002(op);
                    
                    case 3:
                        return getAndUpdateValue0003(op);
                    
                    default:
                        return getAndUpdateFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull UnaryOpInt op) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op);
                    
                    case 1:
                        return updateAndGetValue0001(op);
                    
                    case 2:
                        return updateAndGetValue0002(op);
                    
                    case 3:
                        return updateAndGetValue0003(op);
                    
                    default:
                        return updateAndGetFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull int getAndUpdate(final int index, final @NotNull MultiOpInt op, final @NotNull int ... value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    case 2:
                        return getAndUpdateValue0002(op, value);
                    
                    case 3:
                        return getAndUpdateValue0003(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull int updateAndGet(final int index, final @NotNull MultiOpInt op, final @NotNull int ... value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    case 2:
                        return updateAndGetValue0002(op, value);
                    
                    case 3:
                        return updateAndGetValue0003(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
    
            @Override
            public final @NotNull int get(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000();
                                
                    case 1:
                        return getValue0001();
                                
                    case 2:
                        return getValue0002();
                                
                    case 3:
                        return getValue0003();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull int getUnsafe(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Unsafe();
                                
                    case 1:
                        return getValue0001Unsafe();
                                
                    case 2:
                        return getValue0002Unsafe();
                                
                    case 3:
                        return getValue0003Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull int getVolatile(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Volatile();
                                
                    case 1:
                        return getValue0001Volatile();
                                
                    case 2:
                        return getValue0002Volatile();
                                
                    case 3:
                        return getValue0003Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
