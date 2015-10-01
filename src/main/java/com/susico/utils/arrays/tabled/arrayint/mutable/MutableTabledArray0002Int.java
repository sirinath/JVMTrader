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

package com.susico.utils.arrays.tabled.arrayint.mutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class MutableTabledArray0002Int extends MutableTabledArray0001Int {
    
    protected int value0001;
        
    public final int getValue0001() {
        return value0001;
    }
    
    public final void setValue0001(final int value0001) {
        this.value0001 = value0001;
    }
    
    protected MutableTabledArray0002Int(final boolean checked, final int ... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArray0002Int(final boolean checked, final int definedAsValues, final int ... values) {
        super(checked, definedAsValues + 1, values);
        final int len = values.length;

        
        if (len >= 2) {
            this.value0001 = ArrayAccess.UNCHECKED.get(values, 1);
        } else {
            this.value0001 = 0;
        }
            
    }

    public static  MutableTabledArray0002Int getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArray0002Int(checked, values) {
            
            @Override
            public final void put(final int index, final int value) {
                switch (index) {
                    
                    case 0:
                        value0000 = value;
                        break;
                    
                    case 1:
                        value0001 = value;
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            

            @Override
            public final int get(final int index) {
                switch (index) {
                
                    case 0:
                        return value0000;
                                
                    case 1:
                        return value0001;
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
