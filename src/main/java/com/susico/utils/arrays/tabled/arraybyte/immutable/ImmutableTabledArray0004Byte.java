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

public abstract class ImmutableTabledArray0004Byte extends ImmutableTabledArray0002Byte {
    
    protected final byte value0002;
    protected final byte value0003;
        
    public final byte getValue0002() {
        return value0002;
    }
    
    public final byte getValue0003() {
        return value0003;
    }
    
    protected ImmutableTabledArray0004Byte(final boolean checked, final byte ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0004Byte(final boolean checked, final int definedAsValues, final byte ... values) {
        super(checked, definedAsValues + 2, values);
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

    public static  ImmutableTabledArray0004Byte getInstance(final boolean checked, final byte ... values) {
        return new ImmutableTabledArray0004Byte(checked, values) {
            

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
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
