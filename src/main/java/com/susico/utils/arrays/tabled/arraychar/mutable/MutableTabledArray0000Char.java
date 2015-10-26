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

package com.susico.utils.arrays.tabled.arraychar.mutable;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0000Char extends MutableTabledArrayChar {
    protected MutableTabledArray0000Char(final boolean checked, final int length, final @NotNull char ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000Char(
        final boolean checked, final int definedAsValues, final int length, final @NotNull char ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static  MutableTabledArray0000Char getInstance(
            final boolean checked, final int length, final @NotNull char ... values) {
        return new MutableTabledArray0000Char(checked, length, values) {
            @Override
            public final void put(final int index, final @NotNull char value) {
                putToRest(index, value);
            }

            public final void putUnsafe(final int index, final @NotNull char value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final @NotNull char value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final @NotNull char get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull char getUnsafe(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull char getVolatile(final int index) {
                return getVolatileFromRest(index);
            }

        };
    }
}