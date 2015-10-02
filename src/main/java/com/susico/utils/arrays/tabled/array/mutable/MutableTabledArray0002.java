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

package com.susico.utils.arrays.tabled.array.mutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class MutableTabledArray0002<T> extends MutableTabledArray0001<T> {
    
    protected T value0001;
        
    public final T getValue0001() {
        return value0001;
    }
    
    public final void setValue0001(final T value0001) {
        this.value0001 = value0001;
    }
    
    protected MutableTabledArray0002(final boolean checked, final int length, final T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0002(final boolean checked, final int definedAsValues, final int length, final T ... values) {
        super(checked, definedAsValues + 1, length, values);
        final int len = values.length;

        
        if (len >= 2) {
            this.value0001 = ArrayAccess.UNCHECKED.get(values, 1);
        } else {
            this.value0001 = null;
        }
            
    }

    public static <T> MutableTabledArray0002<T> getInstance(final boolean checked, final int length, final T ... values) {
        return new MutableTabledArray0002<T>(checked, length, values) {
            
            @Override
            public final void put(final int index, final T value) {
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
            public final T get(final int index) {
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
