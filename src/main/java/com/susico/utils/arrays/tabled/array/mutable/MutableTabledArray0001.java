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

public abstract class MutableTabledArray0001<T> extends MutableTabledArray0000<T> {
    
    protected T value0000;
    public final T getValue0000() {
        return value0000;
    }
    
    public final void setValue0000(final T value0000) {
        this.value0000 = value0000;
    }
    
    protected MutableTabledArray0001(final boolean checked, final int length, final T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0001(final boolean checked, final int definedAsValues, final int length, final T ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(values, 0);
        } else {
            this.value0000 = null;
        }

    }

    public static <T> MutableTabledArray0001 getInstance(final boolean checked, final int length, final T ... values) {
        return new MutableTabledArray0001<T>(checked, length, values) {
            
            @Override
            public final void put(final int index, final T value) {
                switch (index) {
                
                    case 0:
                        value0000 = value;
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
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
