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

package com.susico.utils.arrays.tabled.arraychar.immutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0004Char extends
    ImmutableTabledArray0002Char {
    
    protected final static long value0002FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0004Char.class, "value0002");

    protected final @NotNull char value0002;

    protected final static long value0003FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0004Char.class, "value0003");

    protected final @NotNull char value0003;

        
    public final @NotNull char getValue0002() {
        return value0002;
    }

    public final @NotNull char getValue0002Unsafe() {
        return (char) UNSAFE.getChar(this, value0002FieldOffset);
    }

    public final @NotNull char getValue0002Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0002FieldOffset);
    }
    
    public final @NotNull char getValue0003() {
        return value0003;
    }

    public final @NotNull char getValue0003Unsafe() {
        return (char) UNSAFE.getChar(this, value0003FieldOffset);
    }

    public final @NotNull char getValue0003Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0003FieldOffset);
    }
    
    protected ImmutableTabledArray0004Char(
        final boolean checked, final int length, final @NotNull char ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0004Char(
        final boolean checked, final int definedAsValues, final int length, final @NotNull char ... values) {
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

    public static  ImmutableTabledArray0004Char getInstance(
        final boolean checked, final int length, final @NotNull char ... values) {
        return new ImmutableTabledArray0004Char(checked, length, values) {
    
    
            @Override
            public final @NotNull char get(final int index) {
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
            public final @NotNull char getUnsafe(final int index) {
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
            public final @NotNull char getVolatile(final int index) {
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
