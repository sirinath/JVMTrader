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

package com.susico.utils.arrays.tabled.arrayshort.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0008Short extends
    MutableTabledArray0004Short {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Short.class, "value0004");

    protected short value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Short.class, "value0005");

    protected short value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Short.class, "value0006");

    protected short value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0008Short.class, "value0007");

    protected short value0007;

        
    public final @NotNull short getValue0004() {
        return value0004;
    }

    public final @NotNull short getValue0004Unsafe() {
        return (short) UNSAFE.getShort(this, value0004FieldOffset);
    }

    public final @NotNull short getValue0004Volatile() {
        return (short) UNSAFE.getShortVolatile(this, value0004FieldOffset);
    }
    
    public final @NotNull short getValue0005() {
        return value0005;
    }

    public final @NotNull short getValue0005Unsafe() {
        return (short) UNSAFE.getShort(this, value0005FieldOffset);
    }

    public final @NotNull short getValue0005Volatile() {
        return (short) UNSAFE.getShortVolatile(this, value0005FieldOffset);
    }
    
    public final @NotNull short getValue0006() {
        return value0006;
    }

    public final @NotNull short getValue0006Unsafe() {
        return (short) UNSAFE.getShort(this, value0006FieldOffset);
    }

    public final @NotNull short getValue0006Volatile() {
        return (short) UNSAFE.getShortVolatile(this, value0006FieldOffset);
    }
    
    public final @NotNull short getValue0007() {
        return value0007;
    }

    public final @NotNull short getValue0007Unsafe() {
        return (short) UNSAFE.getShort(this, value0007FieldOffset);
    }

    public final @NotNull short getValue0007Volatile() {
        return (short) UNSAFE.getShortVolatile(this, value0007FieldOffset);
    }
    
    public final void setValue0004(final @NotNull short value0004) {
        this.value0004 = value0004;
    }

    public final void putValue0004Unsafe(final @NotNull short value0004) {
        UNSAFE.putShort(this, value0004FieldOffset, value0004);
    }

    public final void putValue0004Volatile(final @NotNull short value0004) {
        UNSAFE.putShortVolatile(this, value0004FieldOffset, value0004);
    }
    
    public final void setValue0005(final @NotNull short value0005) {
        this.value0005 = value0005;
    }

    public final void putValue0005Unsafe(final @NotNull short value0005) {
        UNSAFE.putShort(this, value0005FieldOffset, value0005);
    }

    public final void putValue0005Volatile(final @NotNull short value0005) {
        UNSAFE.putShortVolatile(this, value0005FieldOffset, value0005);
    }
    
    public final void setValue0006(final @NotNull short value0006) {
        this.value0006 = value0006;
    }

    public final void putValue0006Unsafe(final @NotNull short value0006) {
        UNSAFE.putShort(this, value0006FieldOffset, value0006);
    }

    public final void putValue0006Volatile(final @NotNull short value0006) {
        UNSAFE.putShortVolatile(this, value0006FieldOffset, value0006);
    }
    
    public final void setValue0007(final @NotNull short value0007) {
        this.value0007 = value0007;
    }

    public final void putValue0007Unsafe(final @NotNull short value0007) {
        UNSAFE.putShort(this, value0007FieldOffset, value0007);
    }

    public final void putValue0007Volatile(final @NotNull short value0007) {
        UNSAFE.putShortVolatile(this, value0007FieldOffset, value0007);
    }
    
    protected MutableTabledArray0008Short(
        final boolean checked, final int length, final @NotNull short ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0008Short(
        final boolean checked, final int definedAsValues, final int length, final @NotNull short ... values) {
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

    public static  MutableTabledArray0008Short getInstance(
        final boolean checked, final int length, final @NotNull short ... values) {
        return new MutableTabledArray0008Short(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull short value) {
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
            public final void putVolatile(final int index, final @NotNull short value) {
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
            public final void putUnsafe(final int index, final @NotNull short value) {
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
            public final @NotNull short get(final int index) {
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
            public final @NotNull short getUnsafe(final int index) {
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
            public final @NotNull short getVolatile(final int index) {
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