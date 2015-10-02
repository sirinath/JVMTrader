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

public abstract class ImmutableTabledArray0000Double extends ImmutableTabledArrayDouble {
    protected ImmutableTabledArray0000Double(final boolean checked, final int length, final double ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0000Double(final boolean checked, final int definedAsValues, final int length, final double ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static  ImmutableTabledArray0000Double getInstance(final boolean checked, final int length, final double ... values) {
        return new ImmutableTabledArray0000Double(checked, length, values) {
            @Override
            public final double get(final int index) {
                return getFromRest(index);
            }
        };
    }
}

