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

package com.susico.utils.arrays.tabled.array.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0002<T> extends
    MutableTabledArray0001<T> {
    
    protected final static long value0001FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0002.class, "value0001");

    protected T value0001;

        
    public final @NotNull T getValue0001() {
        return value0001;
    }

    public final @NotNull T getValue0001Unsafe() {
        return (T) UNSAFE.getObject(this, value0001FieldOffset);
    }

    public final @NotNull T getValue0001Volatile() {
        return (T) UNSAFE.getObjectVolatile(this, value0001FieldOffset);
    }
    
    public final void setValue0001(final @NotNull T value0001) {
        this.value0001 = value0001;
    }

    public final void putValue0001Unsafe(final @NotNull T value0001) {
        UNSAFE.putObject(this, value0001FieldOffset, value0001);
    }

    public final void putValue0001Volatile(final @NotNull T value0001) {
        UNSAFE.putObjectVolatile(this, value0001FieldOffset, value0001);
    }
    
    public final void putValue0001Ordered(
        final @NotNull T value0001) {
            UNSAFE.putOrderedObject(this, value0001FieldOffset, (value0001));
    }

    public final boolean compareAndSwapValue0001(final @NotNull T expected,
        final @NotNull T value0001) {
        return UNSAFE.compareAndSwapObject(this,
            value0001FieldOffset,
            (expected), (value0001));
    }

    public final @NotNull T getAndSetValue0001(
        final @NotNull T value0001) {
        return (T) (UNSAFE.getAndSetObject(this,
            value0000FieldOffset,
            (value0001)));
    }
        
    public final @NotNull T getAndUpdateValue0001(final @NotNull BiOpObject<T> op, final @NotNull T value0001) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (op.apply(current, value0001))));
        return current;
    }

    public final @NotNull T updateAndGetValue0001(final @NotNull BiOpObject<T> op, final @NotNull T value0001) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull T getAndUpdateValue0001(final @NotNull UnaryOpObject<T> op) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull T updateAndGetValue0001(final @NotNull UnaryOpObject<T> op) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0001FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull T getAndUpdateValue0001(final @NotNull MultiOpObject<T> op, final @NotNull T ... value0001) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (op.apply(current, value0001))));
        return current;
    }

    public final @NotNull T updateAndGetValue0001(final @NotNull MultiOpObject<T> op, final @NotNull T ... value0001) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapObject(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0002(
        final boolean checked, final int length, final @NotNull T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0002(
        final boolean checked, final int definedAsValues, final int length, final @NotNull T ... values) {
        super(checked, definedAsValues + 1, length, values);
        final int len = values.length;

        
        if (len >= 1) {
            this.value0001 = ArrayAccess.UNCHECKED.get(1, values);
        } else {
            this.value0001 = null;
        }
            
    }

    public static <T> MutableTabledArray0002<T> getInstance(
        final boolean checked, final int length, final @NotNull T ... values) {
        return new MutableTabledArray0002<T>(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull T value) {
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
            public final void putVolatile(final int index, final @NotNull T value) {
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
            public final void putUnsafe(final int index, final @NotNull T value) {
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
            public final void putOrdered(final int index, final @NotNull T value) {
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
            public final boolean compareAndSwap(final int index, final @NotNull T expected, final @NotNull T value) {
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
            public final @NotNull T getAndSet(final int index, final @NotNull T value) {
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
            public final @NotNull T getAndUpdate(final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
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
            public final @NotNull T updateAndGet(final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
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
            public final @NotNull T getAndUpdate(final int index, final @NotNull UnaryOpObject<T> op) {
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
            public final @NotNull T updateAndGet(final int index, final @NotNull UnaryOpObject<T> op) {
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
            public final @NotNull T getAndUpdate(final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
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
            public final @NotNull T updateAndGet(final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
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
            public final @NotNull T get(final int index) {
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
            public final @NotNull T getUnsafe(final int index) {
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
            public final @NotNull T getVolatile(final int index) {
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
