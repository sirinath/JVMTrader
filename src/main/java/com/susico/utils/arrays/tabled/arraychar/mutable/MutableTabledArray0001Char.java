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

package com.susico.utils.arrays.tabled.arraychar.mutable;

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0001Char extends
    MutableTabledArray0000Char {
    
    protected final static long value0000FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0001Char.class, "value0000");

    @Contended protected char value0000;

    public final @NotNull char getValue0000() {
        return value0000;
    }

    public final @NotNull char getValue0000Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0000FieldOffset);
    }

    public final @NotNull char getValue0000Unsafe() {
        return (char) UNSAFE.getChar(this, value0000FieldOffset);
    }
    
    public final void setValue0000(final @NotNull char value0000) {
        this.value0000 = value0000;
    }

    public final void putValue0000Volatile(final @NotNull char value0000) {
         UNSAFE.putCharVolatile(this, value0000FieldOffset, value0000);
    }

    public final void putValue0000Unsafe(final @NotNull char value0000) {
        UNSAFE.putChar(this, value0000FieldOffset, value0000);
    }
    
    protected MutableTabledArray0001Char(
        final boolean checked, final int length, final @NotNull char ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0001Char(
        final boolean checked, final int definedAsValues, final int length, final @NotNull char ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = 0;
        }

    }

    public static  MutableTabledArray0001Char getInstance(
        final boolean checked, final int length, final @NotNull char ... values) {
        return new MutableTabledArray0001Char(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull char value) {
                switch (index) {
                
                    case 0:
                        setValue0000(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull char value) {
                switch (index) {
                
                    case 0:
                        putValue0000Unsafe(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putVolatile(final int index, final @NotNull char value) {
                switch (index) {
                
                    case 0:
                        putValue0000Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
    
            @Override
            public final @NotNull char get(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull char getUnsafe(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull char getVolatile(final int index) {
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
