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

package com.susico.utils.arrays.tabled.arrayfloat.mutable;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0000Float extends MutableTabledArrayFloat {
    protected MutableTabledArray0000Float(final boolean checked, final int length, final @NotNull float ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000Float(
        final boolean checked, final int definedAsValues, final int length, final @NotNull float ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static  MutableTabledArray0000Float getInstance(
            final boolean checked, final int length, final @NotNull float ... values) {
        return new MutableTabledArray0000Float(checked, length, values) {
            @Override
            public final void put(final int index, final @NotNull float value) {
                putToRest(index, value);
            }

            public final void putUnsafe(final int index, final @NotNull float value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final @NotNull float value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final @NotNull float get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull float getUnsafe(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull float getVolatile(final int index) {
                return getVolatileFromRest(index);
            }

            public final void putOrdered(
                final int index, final @NotNull float value) {
                    putOrderedToRest(index, value);
            }

            public final boolean compareAndSwap(
                final int index, final @NotNull float expected, final @NotNull float value) {
                return compareAndSwapFromRest(index, expected, value);
            }

            public final @NotNull float getAndSet(
                final int index, final @NotNull float value) {
                return getAndSetFromRest(index, value);
            }
        
            public final @NotNull float getAndUpdate(
                final int index, final @NotNull BiOpFloat op, final @NotNull float value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull float updateAndGet(
                final int index, final @NotNull BiOpFloat op, final @NotNull float value) {
                return updateAndGetFromRest(index, op, value);
            }

            public final @NotNull float getAndUpdate(
                final int index, final @NotNull UnaryOpFloat op) {
                return getAndUpdateFromRest(index, op);
            }

            public final @NotNull float updateAndGet(
                final int index, final @NotNull UnaryOpFloat op) {
                return updateAndGetFromRest(index, op);
            }

            public final @NotNull float getAndUpdate(
                final int index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull float updateAndGet(
                final int index, final @NotNull MultiOpFloat op, final @NotNull float ... value) {
                return updateAndGetFromRest(index, op, value);
            }

        };
    }
}