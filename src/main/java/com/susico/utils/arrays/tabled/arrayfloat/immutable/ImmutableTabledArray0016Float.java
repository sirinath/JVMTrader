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

package com.susico.utils.arrays.tabled.arrayfloat.immutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0016Float extends
    ImmutableTabledArray0008Float {
    
    protected final static long value0008FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0008");

    protected final @NotNull float value0008;

    protected final static long value0009FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0009");

    protected final @NotNull float value0009;

    protected final static long value0010FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0010");

    protected final @NotNull float value0010;

    protected final static long value0011FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0011");

    protected final @NotNull float value0011;

    protected final static long value0012FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0012");

    protected final @NotNull float value0012;

    protected final static long value0013FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0013");

    protected final @NotNull float value0013;

    protected final static long value0014FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0014");

    protected final @NotNull float value0014;

    protected final static long value0015FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0016Float.class, "value0015");

    protected final @NotNull float value0015;

        
    public final @NotNull float getValue0008() {
        return value0008;
    }

    public final @NotNull float getValue0008Unsafe() {
        return (float) UNSAFE.getFloat(this, value0008FieldOffset);
    }

    public final @NotNull float getValue0008Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0008FieldOffset);
    }
    
    public final @NotNull float getValue0009() {
        return value0009;
    }

    public final @NotNull float getValue0009Unsafe() {
        return (float) UNSAFE.getFloat(this, value0009FieldOffset);
    }

    public final @NotNull float getValue0009Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0009FieldOffset);
    }
    
    public final @NotNull float getValue0010() {
        return value0010;
    }

    public final @NotNull float getValue0010Unsafe() {
        return (float) UNSAFE.getFloat(this, value0010FieldOffset);
    }

    public final @NotNull float getValue0010Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0010FieldOffset);
    }
    
    public final @NotNull float getValue0011() {
        return value0011;
    }

    public final @NotNull float getValue0011Unsafe() {
        return (float) UNSAFE.getFloat(this, value0011FieldOffset);
    }

    public final @NotNull float getValue0011Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0011FieldOffset);
    }
    
    public final @NotNull float getValue0012() {
        return value0012;
    }

    public final @NotNull float getValue0012Unsafe() {
        return (float) UNSAFE.getFloat(this, value0012FieldOffset);
    }

    public final @NotNull float getValue0012Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0012FieldOffset);
    }
    
    public final @NotNull float getValue0013() {
        return value0013;
    }

    public final @NotNull float getValue0013Unsafe() {
        return (float) UNSAFE.getFloat(this, value0013FieldOffset);
    }

    public final @NotNull float getValue0013Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0013FieldOffset);
    }
    
    public final @NotNull float getValue0014() {
        return value0014;
    }

    public final @NotNull float getValue0014Unsafe() {
        return (float) UNSAFE.getFloat(this, value0014FieldOffset);
    }

    public final @NotNull float getValue0014Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0014FieldOffset);
    }
    
    public final @NotNull float getValue0015() {
        return value0015;
    }

    public final @NotNull float getValue0015Unsafe() {
        return (float) UNSAFE.getFloat(this, value0015FieldOffset);
    }

    public final @NotNull float getValue0015Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0015FieldOffset);
    }
    
    protected ImmutableTabledArray0016Float(
        final boolean checked, final int length, final @NotNull float ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0016Float(
        final boolean checked, final int definedAsValues, final int length, final @NotNull float ... values) {
        super(checked, definedAsValues + 8, length, values);
        final int len = values.length;

        
        if (len >= 15) {
            this.value0015 = ArrayAccess.UNCHECKED.get(15, values);
        } else {
            this.value0015 = Float.NaN;
        }
            
        if (len >= 14) {
            this.value0014 = ArrayAccess.UNCHECKED.get(14, values);
        } else {
            this.value0014 = Float.NaN;
        }
            
        if (len >= 13) {
            this.value0013 = ArrayAccess.UNCHECKED.get(13, values);
        } else {
            this.value0013 = Float.NaN;
        }
            
        if (len >= 12) {
            this.value0012 = ArrayAccess.UNCHECKED.get(12, values);
        } else {
            this.value0012 = Float.NaN;
        }
            
        if (len >= 11) {
            this.value0011 = ArrayAccess.UNCHECKED.get(11, values);
        } else {
            this.value0011 = Float.NaN;
        }
            
        if (len >= 10) {
            this.value0010 = ArrayAccess.UNCHECKED.get(10, values);
        } else {
            this.value0010 = Float.NaN;
        }
            
        if (len >= 9) {
            this.value0009 = ArrayAccess.UNCHECKED.get(9, values);
        } else {
            this.value0009 = Float.NaN;
        }
            
        if (len >= 8) {
            this.value0008 = ArrayAccess.UNCHECKED.get(8, values);
        } else {
            this.value0008 = Float.NaN;
        }
            
    }

    public static  ImmutableTabledArray0016Float getInstance(
        final boolean checked, final int length, final @NotNull float ... values) {
        return new ImmutableTabledArray0016Float(checked, length, values) {
    
    
            @Override
            public final @NotNull float get(final int index) {
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
            public final @NotNull float getUnsafe(final int index) {
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
            public final @NotNull float getVolatile(final int index) {
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
