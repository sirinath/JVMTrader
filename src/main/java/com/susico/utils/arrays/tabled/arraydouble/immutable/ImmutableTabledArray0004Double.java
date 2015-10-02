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

public abstract class ImmutableTabledArray0004Double extends ImmutableTabledArray0002Double {
    
    protected final double value0002;
    protected final double value0003;
        
    public final double getValue0002() {
        return value0002;
    }
    
    public final double getValue0003() {
        return value0003;
    }
    
    protected ImmutableTabledArray0004Double(final boolean checked, final int length, final double ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0004Double(final boolean checked, final int definedAsValues, final int length, final double ... values) {
        super(checked, definedAsValues + 2, length, values);
        final int len = values.length;

        
        if (len >= 4) {
            this.value0003 = ArrayAccess.UNCHECKED.get(values, 3);
        } else {
            this.value0003 = Double.NaN;
        }
            
        if (len >= 3) {
            this.value0002 = ArrayAccess.UNCHECKED.get(values, 2);
        } else {
            this.value0002 = Double.NaN;
        }
            
    }

    public static  ImmutableTabledArray0004Double getInstance(final boolean checked, final int length, final double ... values) {
        return new ImmutableTabledArray0004Double(checked, length, values) {
            

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
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
