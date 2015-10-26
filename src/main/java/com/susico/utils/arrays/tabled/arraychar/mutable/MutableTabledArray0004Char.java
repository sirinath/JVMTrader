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

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0004Char extends
    MutableTabledArray0002Char {
    
    protected final static long value0002FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0004Char.class, "value0002");

    protected char value0002;

    protected final static long value0003FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0004Char.class, "value0003");

    protected char value0003;

        
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
    
    public final void setValue0002(final @NotNull char value0002) {
        this.value0002 = value0002;
    }

    public final void putValue0002Unsafe(final @NotNull char value0002) {
        UNSAFE.putChar(this, value0002FieldOffset, value0002);
    }

    public final void putValue0002Volatile(final @NotNull char value0002) {
        UNSAFE.putCharVolatile(this, value0002FieldOffset, value0002);
    }
    
    public final void setValue0003(final @NotNull char value0003) {
        this.value0003 = value0003;
    }

    public final void putValue0003Unsafe(final @NotNull char value0003) {
        UNSAFE.putChar(this, value0003FieldOffset, value0003);
    }

    public final void putValue0003Volatile(final @NotNull char value0003) {
        UNSAFE.putCharVolatile(this, value0003FieldOffset, value0003);
    }
    
    protected MutableTabledArray0004Char(
        final boolean checked, final int length, final @NotNull char ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0004Char(
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

    public static  MutableTabledArray0004Char getInstance(
        final boolean checked, final int length, final @NotNull char ... values) {
        return new MutableTabledArray0004Char(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull char value) {
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
                    
                    case 1:
                        putValue0001Volatile(value);
                        break;
                    
                    case 2:
                        putValue0002Volatile(value);
                        break;
                    
                    case 3:
                        putValue0003Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull char value) {
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
                    
                    default:
                        putToRest(index, value);
                }
            }
            
    
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
