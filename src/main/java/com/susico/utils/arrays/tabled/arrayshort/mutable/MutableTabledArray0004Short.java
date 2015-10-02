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

package com.susico.utils.arrays.tabled.arrayshort.mutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class MutableTabledArray0004Short extends MutableTabledArray0002Short {
    
    protected short value0002;
    protected short value0003;
        
    public final short getValue0002() {
        return value0002;
    }
    
    public final short getValue0003() {
        return value0003;
    }
    
    public final void setValue0002(final short value0002) {
        this.value0002 = value0002;
    }
    
    public final void setValue0003(final short value0003) {
        this.value0003 = value0003;
    }
    
    protected MutableTabledArray0004Short(final boolean checked, final int length, final short ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0004Short(final boolean checked, final int definedAsValues, final int length, final short ... values) {
        super(checked, definedAsValues + 2, length, values);
        final int len = values.length;

        
        if (len >= 4) {
            this.value0003 = ArrayAccess.UNCHECKED.get(values, 3);
        } else {
            this.value0003 = 0;
        }
            
        if (len >= 3) {
            this.value0002 = ArrayAccess.UNCHECKED.get(values, 2);
        } else {
            this.value0002 = 0;
        }
            
    }

    public static  MutableTabledArray0004Short getInstance(final boolean checked, final int length, final short ... values) {
        return new MutableTabledArray0004Short(checked, length, values) {
            
            @Override
            public final void put(final int index, final short value) {
                switch (index) {
                    
                    case 0:
                        value0000 = value;
                        break;
                    
                    case 1:
                        value0001 = value;
                        break;
                    
                    case 2:
                        value0002 = value;
                        break;
                    
                    case 3:
                        value0003 = value;
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            

            @Override
            public final short get(final int index) {
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