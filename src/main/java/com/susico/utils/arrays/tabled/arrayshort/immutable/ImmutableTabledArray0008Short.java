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

package com.susico.utils.arrays.tabled.arrayshort.immutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0008Short extends
    ImmutableTabledArray0004Short {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Short.class, "value0004");

    protected final @NotNull short value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Short.class, "value0005");

    protected final @NotNull short value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Short.class, "value0006");

    protected final @NotNull short value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Short.class, "value0007");

    protected final @NotNull short value0007;

        
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
    
    protected ImmutableTabledArray0008Short(
        final boolean checked, final int length, final @NotNull short ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0008Short(
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

    public static  ImmutableTabledArray0008Short getInstance(
        final boolean checked, final int length, final @NotNull short ... values) {
        return new ImmutableTabledArray0008Short(checked, length, values) {
    
    
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