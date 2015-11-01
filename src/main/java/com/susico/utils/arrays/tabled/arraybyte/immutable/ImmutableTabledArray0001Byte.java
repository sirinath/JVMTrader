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

package com.susico.utils.arrays.tabled.arraybyte.immutable;

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0001Byte extends
    ImmutableTabledArray0000Byte {
    
    protected final static long value0000FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0001Byte.class, "value0000");

    protected final @NotNull byte value0000;

    public final @NotNull byte getValue0000() {
        return value0000;
    }

    public final @NotNull byte getValue0000Volatile() {
        return (byte) UNSAFE.getByteVolatile(this, value0000FieldOffset);
    }

    public final @NotNull byte getValue0000Unsafe() {
        return (byte) UNSAFE.getByte(this, value0000FieldOffset);
    }
    
    protected ImmutableTabledArray0001Byte(
        final boolean checked, final int length, final @NotNull byte ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0001Byte(
        final boolean checked, final int definedAsValues, final int length, final @NotNull byte ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = 0;
        }

    }

    public static  ImmutableTabledArray0001Byte getInstance(
        final boolean checked, final int length, final @NotNull byte ... values) {
        return new ImmutableTabledArray0001Byte(checked, length, values) {
    
    
            @Override
            public final @NotNull byte get(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull byte getUnsafe(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull byte getVolatile(final int index) {
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
