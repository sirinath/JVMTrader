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

public abstract class ImmutableTabledArray0001Float extends ImmutableTabledArray0000Float {
    
    protected final float value0000;
    public final float getValue0000() {
        return value0000;
    }
    
    protected ImmutableTabledArray0001Float(final boolean checked, final int length, final float ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0001Float(final boolean checked, final int definedAsValues, final int length, final float ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(values, 0);
        } else {
            this.value0000 = Float.NaN;
        }

    }

    public static  ImmutableTabledArray0001Float getInstance(final boolean checked, final int length, final float ... values) {
        return new ImmutableTabledArray0001Float(checked, length, values) {
            

            @Override
            public final float get(final int index) {
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
