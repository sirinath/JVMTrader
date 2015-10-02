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

package com.susico.utils.arrays.tabled.arrayboolean.immutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0016Boolean extends ImmutableTabledArray0008Boolean {
    
    protected final boolean value0008;
    protected final boolean value0009;
    protected final boolean value0010;
    protected final boolean value0011;
    protected final boolean value0012;
    protected final boolean value0013;
    protected final boolean value0014;
    protected final boolean value0015;
        
    public final boolean getValue0008() {
        return value0008;
    }
    
    public final boolean getValue0009() {
        return value0009;
    }
    
    public final boolean getValue0010() {
        return value0010;
    }
    
    public final boolean getValue0011() {
        return value0011;
    }
    
    public final boolean getValue0012() {
        return value0012;
    }
    
    public final boolean getValue0013() {
        return value0013;
    }
    
    public final boolean getValue0014() {
        return value0014;
    }
    
    public final boolean getValue0015() {
        return value0015;
    }
    
    protected ImmutableTabledArray0016Boolean(final boolean checked, final int length, final boolean ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0016Boolean(final boolean checked, final int definedAsValues, final int length, final boolean ... values) {
        super(checked, definedAsValues + 8, length, values);
        final int len = values.length;

        
        if (len >= 16) {
            this.value0015 = ArrayAccess.UNCHECKED.get(values, 15);
        } else {
            this.value0015 = false;
        }
            
        if (len >= 15) {
            this.value0014 = ArrayAccess.UNCHECKED.get(values, 14);
        } else {
            this.value0014 = false;
        }
            
        if (len >= 14) {
            this.value0013 = ArrayAccess.UNCHECKED.get(values, 13);
        } else {
            this.value0013 = false;
        }
            
        if (len >= 13) {
            this.value0012 = ArrayAccess.UNCHECKED.get(values, 12);
        } else {
            this.value0012 = false;
        }
            
        if (len >= 12) {
            this.value0011 = ArrayAccess.UNCHECKED.get(values, 11);
        } else {
            this.value0011 = false;
        }
            
        if (len >= 11) {
            this.value0010 = ArrayAccess.UNCHECKED.get(values, 10);
        } else {
            this.value0010 = false;
        }
            
        if (len >= 10) {
            this.value0009 = ArrayAccess.UNCHECKED.get(values, 9);
        } else {
            this.value0009 = false;
        }
            
        if (len >= 9) {
            this.value0008 = ArrayAccess.UNCHECKED.get(values, 8);
        } else {
            this.value0008 = false;
        }
            
    }

    public static  ImmutableTabledArray0016Boolean getInstance(final boolean checked, final int length, final boolean ... values) {
        return new ImmutableTabledArray0016Boolean(checked, length, values) {
            

            @Override
            public final boolean get(final int index) {
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
