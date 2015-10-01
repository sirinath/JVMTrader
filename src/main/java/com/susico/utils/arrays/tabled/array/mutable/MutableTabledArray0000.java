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

public abstract class MutableTabledArray0000<T> extends MutableTabledArray<T> {
    protected MutableTabledArray0000(final boolean checked, final T ... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArray0000(final boolean checked, final int definedAsValues, final T ... values) {
        super(checked, definedAsValues, values);
    }

    public static <T> MutableTabledArray0000<T> getInstance(final boolean checked, final T ... values) {
        return new MutableTabledArray0000<T>(checked, values) {
            @Override
            public final void put(final int index, final T value) {
                putToRest(index, value);
            }

            @Override
            public final T get(final int index) {
                return getFromRest(index);
            }
        };
    }
}
