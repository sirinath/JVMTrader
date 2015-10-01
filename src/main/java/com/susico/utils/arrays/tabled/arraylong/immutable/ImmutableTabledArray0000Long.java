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

package com.susico.utils.arrays.tabled.arraylong.immutable;

public abstract class ImmutableTabledArray0000Long extends ImmutableTabledArrayLong {
    protected ImmutableTabledArray0000Long(final boolean checked, final long ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0000Long(final boolean checked, final int definedAsValues, final long ... values) {
        super(checked, definedAsValues, values);
    }

    public static  ImmutableTabledArray0000Long getInstance(final boolean checked, final long ... values) {
        return new ImmutableTabledArray0000Long(checked, values) {
            @Override
            public final long get(final int index) {
                return getFromRest(index);
            }
        };
    }
}

