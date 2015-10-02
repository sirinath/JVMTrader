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

package com.susico.utils.arrays.tabled.arrayboolean.mutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class MutableTabledArray0001Boolean extends MutableTabledArray0000Boolean {
    
    protected boolean value0000;
    public final boolean getValue0000() {
        return value0000;
    }
    
    public final void setValue0000(final boolean value0000) {
        this.value0000 = value0000;
    }
    
    protected MutableTabledArray0001Boolean(final boolean checked, final int length, final boolean ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0001Boolean(final boolean checked, final int definedAsValues, final int length, final boolean ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(values, 0);
        } else {
            this.value0000 = false;
        }

    }

    public static  MutableTabledArray0001Boolean getInstance(final boolean checked, final int length, final boolean ... values) {
        return new MutableTabledArray0001Boolean(checked, length, values) {
            
            @Override
            public final void put(final int index, final boolean value) {
                switch (index) {
                
                    case 0:
                        value0000 = value;
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            

            @Override
            public final boolean get(final int index) {
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
