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

package com.susico.utils.arrays.tabled.arraylong.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0008Long extends
    MutableTabledArray0004Long {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Long.class, "value0004");

    protected long value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Long.class, "value0005");

    protected long value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Long.class, "value0006");

    protected long value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Long.class, "value0007");

    protected long value0007;

        
    public final @NotNull long getValue0004() {
        return value0004;
    }

    public final @NotNull long getValue0004Unsafe() {
        return (long) UNSAFE.getLong(this, value0004FieldOffset);
    }

    public final @NotNull long getValue0004Volatile() {
        return (long) UNSAFE.getLongVolatile(this, value0004FieldOffset);
    }
    
    public final @NotNull long getValue0005() {
        return value0005;
    }

    public final @NotNull long getValue0005Unsafe() {
        return (long) UNSAFE.getLong(this, value0005FieldOffset);
    }

    public final @NotNull long getValue0005Volatile() {
        return (long) UNSAFE.getLongVolatile(this, value0005FieldOffset);
    }
    
    public final @NotNull long getValue0006() {
        return value0006;
    }

    public final @NotNull long getValue0006Unsafe() {
        return (long) UNSAFE.getLong(this, value0006FieldOffset);
    }

    public final @NotNull long getValue0006Volatile() {
        return (long) UNSAFE.getLongVolatile(this, value0006FieldOffset);
    }
    
    public final @NotNull long getValue0007() {
        return value0007;
    }

    public final @NotNull long getValue0007Unsafe() {
        return (long) UNSAFE.getLong(this, value0007FieldOffset);
    }

    public final @NotNull long getValue0007Volatile() {
        return (long) UNSAFE.getLongVolatile(this, value0007FieldOffset);
    }
    
    public final void setValue0004(final @NotNull long value0004) {
        this.value0004 = value0004;
    }

    public final void putValue0004Unsafe(final @NotNull long value0004) {
        UNSAFE.putLong(this, value0004FieldOffset, value0004);
    }

    public final void putValue0004Volatile(final @NotNull long value0004) {
        UNSAFE.putLongVolatile(this, value0004FieldOffset, value0004);
    }
    
    public final void putValue0004Ordered(
        final @NotNull long value0004) {
            UNSAFE.putOrderedLong(this, value0004FieldOffset, (value0004));
    }

    public final boolean compareAndSwapValue0004(final @NotNull long expected,
        final @NotNull long value0004) {
        return UNSAFE.compareAndSwapLong(this,
            value0004FieldOffset,
            (expected), (value0004));
    }

    public final @NotNull long getAndSetValue0004(
        final @NotNull long value0004) {
        return (long) (UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            (value0004)));
    }
        
    public final @NotNull long getAndUpdateValue0004(final @NotNull BiOpLong op, final @NotNull long value0004) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (op.apply(current, value0004))));
        return current;
    }

    public final @NotNull long updateAndGetValue0004(final @NotNull BiOpLong op, final @NotNull long value0004) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0004FieldOffset);
            newValue = op.apply(current, value0004);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0004(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull long updateAndGetValue0004(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0004FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0004(final @NotNull MultiOpLong op, final @NotNull long ... value0004) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0004FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (op.apply(current, value0004))));
        return current;
    }

    public final @NotNull long updateAndGetValue0004(final @NotNull MultiOpLong op, final @NotNull long ... value0004) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0004FieldOffset);
            newValue = op.apply(current, value0004);
        } while (!UNSAFE.compareAndSwapLong(this, value0004FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final void setValue0005(final @NotNull long value0005) {
        this.value0005 = value0005;
    }

    public final void putValue0005Unsafe(final @NotNull long value0005) {
        UNSAFE.putLong(this, value0005FieldOffset, value0005);
    }

    public final void putValue0005Volatile(final @NotNull long value0005) {
        UNSAFE.putLongVolatile(this, value0005FieldOffset, value0005);
    }
    
    public final void putValue0005Ordered(
        final @NotNull long value0005) {
            UNSAFE.putOrderedLong(this, value0005FieldOffset, (value0005));
    }

    public final boolean compareAndSwapValue0005(final @NotNull long expected,
        final @NotNull long value0005) {
        return UNSAFE.compareAndSwapLong(this,
            value0005FieldOffset,
            (expected), (value0005));
    }

    public final @NotNull long getAndSetValue0005(
        final @NotNull long value0005) {
        return (long) (UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            (value0005)));
    }
        
    public final @NotNull long getAndUpdateValue0005(final @NotNull BiOpLong op, final @NotNull long value0005) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (op.apply(current, value0005))));
        return current;
    }

    public final @NotNull long updateAndGetValue0005(final @NotNull BiOpLong op, final @NotNull long value0005) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0005FieldOffset);
            newValue = op.apply(current, value0005);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0005(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull long updateAndGetValue0005(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0005FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0005(final @NotNull MultiOpLong op, final @NotNull long ... value0005) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0005FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (op.apply(current, value0005))));
        return current;
    }

    public final @NotNull long updateAndGetValue0005(final @NotNull MultiOpLong op, final @NotNull long ... value0005) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0005FieldOffset);
            newValue = op.apply(current, value0005);
        } while (!UNSAFE.compareAndSwapLong(this, value0005FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final void setValue0006(final @NotNull long value0006) {
        this.value0006 = value0006;
    }

    public final void putValue0006Unsafe(final @NotNull long value0006) {
        UNSAFE.putLong(this, value0006FieldOffset, value0006);
    }

    public final void putValue0006Volatile(final @NotNull long value0006) {
        UNSAFE.putLongVolatile(this, value0006FieldOffset, value0006);
    }
    
    public final void putValue0006Ordered(
        final @NotNull long value0006) {
            UNSAFE.putOrderedLong(this, value0006FieldOffset, (value0006));
    }

    public final boolean compareAndSwapValue0006(final @NotNull long expected,
        final @NotNull long value0006) {
        return UNSAFE.compareAndSwapLong(this,
            value0006FieldOffset,
            (expected), (value0006));
    }

    public final @NotNull long getAndSetValue0006(
        final @NotNull long value0006) {
        return (long) (UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            (value0006)));
    }
        
    public final @NotNull long getAndUpdateValue0006(final @NotNull BiOpLong op, final @NotNull long value0006) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (op.apply(current, value0006))));
        return current;
    }

    public final @NotNull long updateAndGetValue0006(final @NotNull BiOpLong op, final @NotNull long value0006) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0006FieldOffset);
            newValue = op.apply(current, value0006);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0006(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull long updateAndGetValue0006(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0006FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0006(final @NotNull MultiOpLong op, final @NotNull long ... value0006) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0006FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (op.apply(current, value0006))));
        return current;
    }

    public final @NotNull long updateAndGetValue0006(final @NotNull MultiOpLong op, final @NotNull long ... value0006) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0006FieldOffset);
            newValue = op.apply(current, value0006);
        } while (!UNSAFE.compareAndSwapLong(this, value0006FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final void setValue0007(final @NotNull long value0007) {
        this.value0007 = value0007;
    }

    public final void putValue0007Unsafe(final @NotNull long value0007) {
        UNSAFE.putLong(this, value0007FieldOffset, value0007);
    }

    public final void putValue0007Volatile(final @NotNull long value0007) {
        UNSAFE.putLongVolatile(this, value0007FieldOffset, value0007);
    }
    
    public final void putValue0007Ordered(
        final @NotNull long value0007) {
            UNSAFE.putOrderedLong(this, value0007FieldOffset, (value0007));
    }

    public final boolean compareAndSwapValue0007(final @NotNull long expected,
        final @NotNull long value0007) {
        return UNSAFE.compareAndSwapLong(this,
            value0007FieldOffset,
            (expected), (value0007));
    }

    public final @NotNull long getAndSetValue0007(
        final @NotNull long value0007) {
        return (long) (UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            (value0007)));
    }
        
    public final @NotNull long getAndUpdateValue0007(final @NotNull BiOpLong op, final @NotNull long value0007) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (op.apply(current, value0007))));
        return current;
    }

    public final @NotNull long updateAndGetValue0007(final @NotNull BiOpLong op, final @NotNull long value0007) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0007FieldOffset);
            newValue = op.apply(current, value0007);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0007(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull long updateAndGetValue0007(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0007FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0007(final @NotNull MultiOpLong op, final @NotNull long ... value0007) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0007FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (op.apply(current, value0007))));
        return current;
    }

    public final @NotNull long updateAndGetValue0007(final @NotNull MultiOpLong op, final @NotNull long ... value0007) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0007FieldOffset);
            newValue = op.apply(current, value0007);
        } while (!UNSAFE.compareAndSwapLong(this, value0007FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0008Long(
        final boolean checked, final int length, final @NotNull long ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0008Long(
        final boolean checked, final int definedAsValues, final int length, final @NotNull long ... values) {
        super(checked, definedAsValues + 4, length, values);
        final int len = values.length;

        
        if (len >= 7) {
            this.value0007 = ArrayAccess.UNCHECKED.get(7, values);
        } else {
            this.value0007 = 0;
        }
            
        if (len >= 6) {
            this.value0006 = ArrayAccess.UNCHECKED.get(6, values);
        } else {
            this.value0006 = 0;
        }
            
        if (len >= 5) {
            this.value0005 = ArrayAccess.UNCHECKED.get(5, values);
        } else {
            this.value0005 = 0;
        }
            
        if (len >= 4) {
            this.value0004 = ArrayAccess.UNCHECKED.get(4, values);
        } else {
            this.value0004 = 0;
        }
            
    }

    public static  MutableTabledArray0008Long getInstance(
        final boolean checked, final int length, final @NotNull long ... values) {
        return new MutableTabledArray0008Long(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull long value) {
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
            public final void putVolatile(final int index, final @NotNull long value) {
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
            public final void putUnsafe(final int index, final @NotNull long value) {
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
            public final void putOrdered(final int index, final @NotNull long value) {
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
            public final boolean compareAndSwap(final int index, final @NotNull long expected, final @NotNull long value) {
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
            public final @NotNull long getAndSet(final int index, final @NotNull long value) {
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
            public final @NotNull long getAndUpdate(final int index, final @NotNull BiOpLong op, final @NotNull long value) {
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
            public final @NotNull long updateAndGet(final int index, final @NotNull BiOpLong op, final @NotNull long value) {
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
            public final @NotNull long getAndUpdate(final int index, final @NotNull UnaryOpLong op) {
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
            public final @NotNull long updateAndGet(final int index, final @NotNull UnaryOpLong op) {
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
            public final @NotNull long getAndUpdate(final int index, final @NotNull MultiOpLong op, final @NotNull long ... value) {
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
            public final @NotNull long updateAndGet(final int index, final @NotNull MultiOpLong op, final @NotNull long ... value) {
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
            public final @NotNull long get(final int index) {
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
            public final @NotNull long getUnsafe(final int index) {
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
            public final @NotNull long getVolatile(final int index) {
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
