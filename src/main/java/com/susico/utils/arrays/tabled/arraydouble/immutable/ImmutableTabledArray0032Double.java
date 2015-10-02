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

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0032Double extends ImmutableTabledArray0016Double {
    
    protected final double value0016;
    protected final double value0017;
    protected final double value0018;
    protected final double value0019;
    protected final double value0020;
    protected final double value0021;
    protected final double value0022;
    protected final double value0023;
    protected final double value0024;
    protected final double value0025;
    protected final double value0026;
    protected final double value0027;
    protected final double value0028;
    protected final double value0029;
    protected final double value0030;
    protected final double value0031;
        
    public final double getValue0016() {
        return value0016;
    }
    
    public final double getValue0017() {
        return value0017;
    }
    
    public final double getValue0018() {
        return value0018;
    }
    
    public final double getValue0019() {
        return value0019;
    }
    
    public final double getValue0020() {
        return value0020;
    }
    
    public final double getValue0021() {
        return value0021;
    }
    
    public final double getValue0022() {
        return value0022;
    }
    
    public final double getValue0023() {
        return value0023;
    }
    
    public final double getValue0024() {
        return value0024;
    }
    
    public final double getValue0025() {
        return value0025;
    }
    
    public final double getValue0026() {
        return value0026;
    }
    
    public final double getValue0027() {
        return value0027;
    }
    
    public final double getValue0028() {
        return value0028;
    }
    
    public final double getValue0029() {
        return value0029;
    }
    
    public final double getValue0030() {
        return value0030;
    }
    
    public final double getValue0031() {
        return value0031;
    }
    
    protected ImmutableTabledArray0032Double(final boolean checked, final int length, final double ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0032Double(final boolean checked, final int definedAsValues, final int length, final double ... values) {
        super(checked, definedAsValues + 16, length, values);
        final int len = values.length;

        
        if (len >= 32) {
            this.value0031 = ArrayAccess.UNCHECKED.get(values, 31);
        } else {
            this.value0031 = Double.NaN;
        }
            
        if (len >= 31) {
            this.value0030 = ArrayAccess.UNCHECKED.get(values, 30);
        } else {
            this.value0030 = Double.NaN;
        }
            
        if (len >= 30) {
            this.value0029 = ArrayAccess.UNCHECKED.get(values, 29);
        } else {
            this.value0029 = Double.NaN;
        }
            
        if (len >= 29) {
            this.value0028 = ArrayAccess.UNCHECKED.get(values, 28);
        } else {
            this.value0028 = Double.NaN;
        }
            
        if (len >= 28) {
            this.value0027 = ArrayAccess.UNCHECKED.get(values, 27);
        } else {
            this.value0027 = Double.NaN;
        }
            
        if (len >= 27) {
            this.value0026 = ArrayAccess.UNCHECKED.get(values, 26);
        } else {
            this.value0026 = Double.NaN;
        }
            
        if (len >= 26) {
            this.value0025 = ArrayAccess.UNCHECKED.get(values, 25);
        } else {
            this.value0025 = Double.NaN;
        }
            
        if (len >= 25) {
            this.value0024 = ArrayAccess.UNCHECKED.get(values, 24);
        } else {
            this.value0024 = Double.NaN;
        }
            
        if (len >= 24) {
            this.value0023 = ArrayAccess.UNCHECKED.get(values, 23);
        } else {
            this.value0023 = Double.NaN;
        }
            
        if (len >= 23) {
            this.value0022 = ArrayAccess.UNCHECKED.get(values, 22);
        } else {
            this.value0022 = Double.NaN;
        }
            
        if (len >= 22) {
            this.value0021 = ArrayAccess.UNCHECKED.get(values, 21);
        } else {
            this.value0021 = Double.NaN;
        }
            
        if (len >= 21) {
            this.value0020 = ArrayAccess.UNCHECKED.get(values, 20);
        } else {
            this.value0020 = Double.NaN;
        }
            
        if (len >= 20) {
            this.value0019 = ArrayAccess.UNCHECKED.get(values, 19);
        } else {
            this.value0019 = Double.NaN;
        }
            
        if (len >= 19) {
            this.value0018 = ArrayAccess.UNCHECKED.get(values, 18);
        } else {
            this.value0018 = Double.NaN;
        }
            
        if (len >= 18) {
            this.value0017 = ArrayAccess.UNCHECKED.get(values, 17);
        } else {
            this.value0017 = Double.NaN;
        }
            
        if (len >= 17) {
            this.value0016 = ArrayAccess.UNCHECKED.get(values, 16);
        } else {
            this.value0016 = Double.NaN;
        }
            
    }

    public static  ImmutableTabledArray0032Double getInstance(final boolean checked, final int length, final double ... values) {
        return new ImmutableTabledArray0032Double(checked, length, values) {
            

            @Override
            public final double get(final int index) {
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
                                
                    case 16:
                        return value0016;
                                
                    case 17:
                        return value0017;
                                
                    case 18:
                        return value0018;
                                
                    case 19:
                        return value0019;
                                
                    case 20:
                        return value0020;
                                
                    case 21:
                        return value0021;
                                
                    case 22:
                        return value0022;
                                
                    case 23:
                        return value0023;
                                
                    case 24:
                        return value0024;
                                
                    case 25:
                        return value0025;
                                
                    case 26:
                        return value0026;
                                
                    case 27:
                        return value0027;
                                
                    case 28:
                        return value0028;
                                
                    case 29:
                        return value0029;
                                
                    case 30:
                        return value0030;
                                
                    case 31:
                        return value0031;
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
