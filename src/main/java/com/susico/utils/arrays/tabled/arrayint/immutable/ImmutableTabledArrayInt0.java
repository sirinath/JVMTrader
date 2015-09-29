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

package com.susico.utils.arrays.tabled.arrayint.immutable;


/**
 * Created by sirin_000 on 25/09/2015.
 */
public abstract class ImmutableTabledArrayInt0 extends ImmutableTabledArrayInt {
    protected ImmutableTabledArrayInt0(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArrayInt0(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues, values.length);
    }

    public static ImmutableTabledArrayInt8 getInstance(final boolean checked, final int ... values) {
        return new ImmutableTabledArrayInt8(checked, values) {
            @Override
            public final int get(final int index) {
                return getFromRest(index);
            }
        };
    }
}
