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

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0016Float extends ImmutableTabledArray0008Float {
    
    protected final float value0008;
    protected final float value0009;
    protected final float value0010;
    protected final float value0011;
    protected final float value0012;
    protected final float value0013;
    protected final float value0014;
    protected final float value0015;
        
    public final float getValue0008() {
        return value0008;
    }
    
    public final float getValue0009() {
        return value0009;
    }
    
    public final float getValue0010() {
        return value0010;
    }
    
    public final float getValue0011() {
        return value0011;
    }
    
    public final float getValue0012() {
        return value0012;
    }
    
    public final float getValue0013() {
        return value0013;
    }
    
    public final float getValue0014() {
        return value0014;
    }
    
    public final float getValue0015() {
        return value0015;
    }
    
    protected ImmutableTabledArray0016Float(final boolean checked, final int length, final float ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0016Float(final boolean checked, final int definedAsValues, final int length, final float ... values) {
        super(checked, definedAsValues + 8, length, values);
        final int len = values.length;

        
        if (len >= 16) {
            this.value0015 = ArrayAccess.UNCHECKED.get(values, 15);
        } else {
            this.value0015 = Float.NaN;
        }
            
        if (len >= 15) {
            this.value0014 = ArrayAccess.UNCHECKED.get(values, 14);
        } else {
            this.value0014 = Float.NaN;
        }
            
        if (len >= 14) {
            this.value0013 = ArrayAccess.UNCHECKED.get(values, 13);
        } else {
            this.value0013 = Float.NaN;
        }
            
        if (len >= 13) {
            this.value0012 = ArrayAccess.UNCHECKED.get(values, 12);
        } else {
            this.value0012 = Float.NaN;
        }
            
        if (len >= 12) {
            this.value0011 = ArrayAccess.UNCHECKED.get(values, 11);
        } else {
            this.value0011 = Float.NaN;
        }
            
        if (len >= 11) {
            this.value0010 = ArrayAccess.UNCHECKED.get(values, 10);
        } else {
            this.value0010 = Float.NaN;
        }
            
        if (len >= 10) {
            this.value0009 = ArrayAccess.UNCHECKED.get(values, 9);
        } else {
            this.value0009 = Float.NaN;
        }
            
        if (len >= 9) {
            this.value0008 = ArrayAccess.UNCHECKED.get(values, 8);
        } else {
            this.value0008 = Float.NaN;
        }
            
    }

    public static  ImmutableTabledArray0016Float getInstance(final boolean checked, final int length, final float ... values) {
        return new ImmutableTabledArray0016Float(checked, length, values) {
            

            @Override
            public final float get(final int index) {
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
