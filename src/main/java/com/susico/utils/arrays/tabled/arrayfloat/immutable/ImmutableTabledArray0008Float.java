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

public abstract class ImmutableTabledArray0008Float extends
    ImmutableTabledArray0004Float {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Float.class, "value0004");

    protected final @NotNull float value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Float.class, "value0005");

    protected final @NotNull float value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Float.class, "value0006");

    protected final @NotNull float value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Float.class, "value0007");

    protected final @NotNull float value0007;

        
    public final @NotNull float getValue0004() {
        return value0004;
    }

    public final @NotNull float getValue0004Unsafe() {
        return (float) UNSAFE.getFloat(this, value0004FieldOffset);
    }

    public final @NotNull float getValue0004Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0004FieldOffset);
    }
    
    public final @NotNull float getValue0005() {
        return value0005;
    }

    public final @NotNull float getValue0005Unsafe() {
        return (float) UNSAFE.getFloat(this, value0005FieldOffset);
    }

    public final @NotNull float getValue0005Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0005FieldOffset);
    }
    
    public final @NotNull float getValue0006() {
        return value0006;
    }

    public final @NotNull float getValue0006Unsafe() {
        return (float) UNSAFE.getFloat(this, value0006FieldOffset);
    }

    public final @NotNull float getValue0006Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0006FieldOffset);
    }
    
    public final @NotNull float getValue0007() {
        return value0007;
    }

    public final @NotNull float getValue0007Unsafe() {
        return (float) UNSAFE.getFloat(this, value0007FieldOffset);
    }

    public final @NotNull float getValue0007Volatile() {
        return (float) UNSAFE.getFloatVolatile(this, value0007FieldOffset);
    }
    
    protected ImmutableTabledArray0008Float(
        final boolean checked, final int length, final @NotNull float ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0008Float(
        final boolean checked, final int definedAsValues, final int length, final @NotNull float ... values) {
        super(checked, definedAsValues + 4, length, values);
        final int len = values.length;

        
        if (len >= 7) {
            this.value0007 = ArrayAccess.UNCHECKED.get(7, values);
        } else {
            this.value0007 = Float.NaN;
        }
            
        if (len >= 6) {
            this.value0006 = ArrayAccess.UNCHECKED.get(6, values);
        } else {
            this.value0006 = Float.NaN;
        }
            
        if (len >= 5) {
            this.value0005 = ArrayAccess.UNCHECKED.get(5, values);
        } else {
            this.value0005 = Float.NaN;
        }
            
        if (len >= 4) {
            this.value0004 = ArrayAccess.UNCHECKED.get(4, values);
        } else {
            this.value0004 = Float.NaN;
        }
            
    }

    public static  ImmutableTabledArray0008Float getInstance(
        final boolean checked, final int length, final @NotNull float ... values) {
        return new ImmutableTabledArray0008Float(checked, length, values) {
    
    
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
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
