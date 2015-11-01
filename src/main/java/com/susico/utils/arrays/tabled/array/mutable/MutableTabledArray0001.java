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

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0001<T> extends
    MutableTabledArray0000<T> {
    
    protected final static long value0000FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0001.class, "value0000");

    @Contended protected T value0000;

    public final @NotNull T getValue0000() {
        return value0000;
    }

    public final @NotNull T getValue0000Volatile() {
        return (T) UNSAFE.getObjectVolatile(this, value0000FieldOffset);
    }

    public final @NotNull T getValue0000Unsafe() {
        return (T) UNSAFE.getObject(this, value0000FieldOffset);
    }
    
    public final void setValue0000(final @NotNull T value0000) {
        this.value0000 = value0000;
    }

    public final void putValue0000Volatile(final @NotNull T value0000) {
         UNSAFE.putObjectVolatile(this, value0000FieldOffset, value0000);
    }

    public final void putValue0000Unsafe(final @NotNull T value0000) {
        UNSAFE.putObject(this, value0000FieldOffset, value0000);
    }
    
    public final void putValue0000Ordered(
        final @NotNull T value0000) {
            UNSAFE.putOrderedObject(this, value0000FieldOffset, (value0000));
    }

    public final boolean compareAndSwapValue0000(final @NotNull T expected,
        final @NotNull T value0000) {
        return UNSAFE.compareAndSwapObject(this,
            value0000FieldOffset,
            (expected), (value0000));
    }

    public final @NotNull T getAndSetValue0000(
        final @NotNull T value0000) {
        return (T) (UNSAFE.getAndSetObject(this,
            value0000FieldOffset,
            (value0000)));
    }
        
    public final @NotNull T getAndUpdateValue0000(final @NotNull BiOpObject<T> op, final @NotNull T value0000) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (op.apply(current, value0000))));
        return current;
    }

    public final @NotNull T updateAndGetValue0000(final @NotNull BiOpObject<T> op, final @NotNull T value0000) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0000FieldOffset);
            newValue = op.apply(current, value0000);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull T getAndUpdateValue0000(final @NotNull UnaryOpObject<T> op) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull T updateAndGetValue0000(final @NotNull UnaryOpObject<T> op) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0000FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull T getAndUpdateValue0000(final @NotNull MultiOpObject<T> op, final @NotNull T ... value0000) {
        T current;

        do {
            current = (T) UNSAFE.getObjectVolatile(this,
                value0000FieldOffset);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (op.apply(current, value0000))));
        return current;
    }

    public final @NotNull T updateAndGetValue0000(final @NotNull MultiOpObject<T> op, final @NotNull T ... value0000) {
        T current;
        T newValue;

        do {
            current = (T) UNSAFE.getObjectVolatile(this, value0000FieldOffset);
            newValue = op.apply(current, value0000);
        } while (!UNSAFE.compareAndSwapObject(this, value0000FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0001(
        final boolean checked, final int length, final @NotNull T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0001(
        final boolean checked, final int definedAsValues, final int length, final @NotNull T ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = null;
        }

    }

    public static <T> MutableTabledArray0001 getInstance(
        final boolean checked, final int length, final @NotNull T ... values) {
        return new MutableTabledArray0001<T>(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        setValue0000(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        putValue0000Unsafe(value);
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
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putOrdered(final int index, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        putValue0000Ordered(value);
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
                    
                    default:
                        return compareAndSwapFromRest(index, expected, value);
                }
            }
            
            @Override
            public final @NotNull T getAndSet(final int index, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        return getAndSetValue0000(value);
                    
                    default:
                        return getAndSetFromRest(index, value);
                }
            }
            
            @Override
            public final @NotNull T getAndUpdate(final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull T updateAndGet(final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull T getAndUpdate(final int index, final @NotNull UnaryOpObject<T> op) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op);
                    
                    default:
                        return getAndUpdateFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull T updateAndGet(final int index, final @NotNull UnaryOpObject<T> op) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op);
                    
                    default:
                        return updateAndGetFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull T getAndUpdate(final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull T updateAndGet(final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
    
            @Override
            public final @NotNull T get(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull T getUnsafe(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull T getVolatile(final int index) {
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
