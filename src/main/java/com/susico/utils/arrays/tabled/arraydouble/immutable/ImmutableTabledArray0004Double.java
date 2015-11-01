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

package com.susico.utils.arrays.tabled.arraydouble.immutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0004Double extends
    ImmutableTabledArray0002Double {
    
    protected final static long value0002FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0004Double.class, "value0002");

    protected final @NotNull double value0002;

    protected final static long value0003FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0004Double.class, "value0003");

    protected final @NotNull double value0003;

        
    public final @NotNull double getValue0002() {
        return value0002;
    }

    public final @NotNull double getValue0002Unsafe() {
        return (double) UNSAFE.getDouble(this, value0002FieldOffset);
    }

    public final @NotNull double getValue0002Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0002FieldOffset);
    }
    
    public final @NotNull double getValue0003() {
        return value0003;
    }

    public final @NotNull double getValue0003Unsafe() {
        return (double) UNSAFE.getDouble(this, value0003FieldOffset);
    }

    public final @NotNull double getValue0003Volatile() {
        return (double) UNSAFE.getDoubleVolatile(this, value0003FieldOffset);
    }
    
    protected ImmutableTabledArray0004Double(
        final boolean checked, final int length, final @NotNull double ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0004Double(
        final boolean checked, final int definedAsValues, final int length, final @NotNull double ... values) {
        super(checked, definedAsValues + 2, length, values);
        final int len = values.length;

        
        if (len >= 3) {
            this.value0003 = ArrayAccess.UNCHECKED.get(3, values);
        } else {
            this.value0003 = Double.NaN;
        }
            
        if (len >= 2) {
            this.value0002 = ArrayAccess.UNCHECKED.get(2, values);
        } else {
            this.value0002 = Double.NaN;
        }
            
    }

    public static  ImmutableTabledArray0004Double getInstance(
        final boolean checked, final int length, final @NotNull double ... values) {
        return new ImmutableTabledArray0004Double(checked, length, values) {
    
    
            @Override
            public final @NotNull double get(final int index) {
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
            public final @NotNull double getUnsafe(final int index) {
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
            public final @NotNull double getVolatile(final int index) {
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
