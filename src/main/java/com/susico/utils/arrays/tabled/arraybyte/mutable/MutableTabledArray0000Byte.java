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

package com.susico.utils.arrays.tabled.arraybyte.mutable;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0000Byte extends MutableTabledArrayByte {
    protected MutableTabledArray0000Byte(final boolean checked, final int length, final @NotNull byte ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000Byte(
        final boolean checked, final int definedAsValues, final int length, final @NotNull byte ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static  MutableTabledArray0000Byte getInstance(
            final boolean checked, final int length, final @NotNull byte ... values) {
        return new MutableTabledArray0000Byte(checked, length, values) {
            @Override
            public final void put(final int index, final @NotNull byte value) {
                putToRest(index, value);
            }

            public final void putUnsafe(final int index, final @NotNull byte value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final @NotNull byte value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final @NotNull byte get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull byte getUnsafe(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull byte getVolatile(final int index) {
                return getVolatileFromRest(index);
            }

        };
    }
}