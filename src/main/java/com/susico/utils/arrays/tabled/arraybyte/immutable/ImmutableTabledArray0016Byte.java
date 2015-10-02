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

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0016Byte extends ImmutableTabledArray0008Byte {
    
    protected final byte value0008;
    protected final byte value0009;
    protected final byte value0010;
    protected final byte value0011;
    protected final byte value0012;
    protected final byte value0013;
    protected final byte value0014;
    protected final byte value0015;
        
    public final byte getValue0008() {
        return value0008;
    }
    
    public final byte getValue0009() {
        return value0009;
    }
    
    public final byte getValue0010() {
        return value0010;
    }
    
    public final byte getValue0011() {
        return value0011;
    }
    
    public final byte getValue0012() {
        return value0012;
    }
    
    public final byte getValue0013() {
        return value0013;
    }
    
    public final byte getValue0014() {
        return value0014;
    }
    
    public final byte getValue0015() {
        return value0015;
    }
    
    protected ImmutableTabledArray0016Byte(final boolean checked, final int length, final byte ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0016Byte(final boolean checked, final int definedAsValues, final int length, final byte ... values) {
        super(checked, definedAsValues + 8, length, values);
        final int len = values.length;

        
        if (len >= 16) {
            this.value0015 = ArrayAccess.UNCHECKED.get(values, 15);
        } else {
            this.value0015 = 0;
        }
            
        if (len >= 15) {
            this.value0014 = ArrayAccess.UNCHECKED.get(values, 14);
        } else {
            this.value0014 = 0;
        }
            
        if (len >= 14) {
            this.value0013 = ArrayAccess.UNCHECKED.get(values, 13);
        } else {
            this.value0013 = 0;
        }
            
        if (len >= 13) {
            this.value0012 = ArrayAccess.UNCHECKED.get(values, 12);
        } else {
            this.value0012 = 0;
        }
            
        if (len >= 12) {
            this.value0011 = ArrayAccess.UNCHECKED.get(values, 11);
        } else {
            this.value0011 = 0;
        }
            
        if (len >= 11) {
            this.value0010 = ArrayAccess.UNCHECKED.get(values, 10);
        } else {
            this.value0010 = 0;
        }
            
        if (len >= 10) {
            this.value0009 = ArrayAccess.UNCHECKED.get(values, 9);
        } else {
            this.value0009 = 0;
        }
            
        if (len >= 9) {
            this.value0008 = ArrayAccess.UNCHECKED.get(values, 8);
        } else {
            this.value0008 = 0;
        }
            
    }

    public static  ImmutableTabledArray0016Byte getInstance(final boolean checked, final int length, final byte ... values) {
        return new ImmutableTabledArray0016Byte(checked, length, values) {
            

            @Override
            public final byte get(final int index) {
                switch (index) {
                
                    case 0:
                        return value0000;
                                
                    case 1:
                        return value0001;
                                
                    case 2:
                        return value0002;
                                
                    case 3:
                        return value0003;
                                
                    case 4:
                        return value0004;
                                
                    case 5:
                        return value0005;
                                
                    case 6:
                        return value0006;
                                
                    case 7:
                        return value0007;
                                
                    case 8:
                        return value0008;
                                
                    case 9:
                        return value0009;
                                
                    case 10:
                        return value0010;
                                
                    case 11:
                        return value0011;
                                
                    case 12:
                        return value0012;
                                
                    case 13:
                        return value0013;
                                
                    case 14:
                        return value0014;
                                
                    case 15:
                        return value0015;
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
