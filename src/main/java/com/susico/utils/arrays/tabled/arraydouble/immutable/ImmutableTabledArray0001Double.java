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

public abstract class ImmutableTabledArray0001Double extends ImmutableTabledArray0000Double {
    
    protected final double value0000;
    public final double getValue0000() {
        return value0000;
    }
    
    protected ImmutableTabledArray0001Double(final boolean checked, final double ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0001Double(final boolean checked, final int definedAsValues, final double ... values) {
        super(checked, definedAsValues + 1, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(values, 0);
        } else {
            this.value0000 = Double.NaN;
        }

    }

    public static  ImmutableTabledArray0001Double getInstance(final boolean checked, final double ... values) {
        return new ImmutableTabledArray0001Double(checked, values) {
            

            @Override
            public final double get(final int index) {
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
