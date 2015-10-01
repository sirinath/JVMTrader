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

package com.susico.utils.arrays.tabled.arrayshort.immutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0002Short extends ImmutableTabledArray0001Short {
    
    protected final short value0001;
        
    public final short getValue0001() {
        return value0001;
    }
    
    protected ImmutableTabledArray0002Short(final boolean checked, final short ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0002Short(final boolean checked, final int definedAsValues, final short ... values) {
        super(checked, definedAsValues + 1, values);
        final int len = values.length;

        
        if (len >= 2) {
            this.value0001 = ArrayAccess.UNCHECKED.get(values, 1);
        } else {
            this.value0001 = 0;
        }
            
    }

    public static  ImmutableTabledArray0002Short getInstance(final boolean checked, final short ... values) {
        return new ImmutableTabledArray0002Short(checked, values) {
            

            @Override
            public final short get(final int index) {
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
