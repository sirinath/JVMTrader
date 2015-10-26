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

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0000<T> extends MutableTabledArray<T> {
    protected MutableTabledArray0000(final boolean checked, final int length, final @NotNull T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000(
        final boolean checked, final int definedAsValues, final int length, final @NotNull T ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static <T> MutableTabledArray0000<T> getInstance(
            final boolean checked, final int length, final @NotNull T ... values) {
        return new MutableTabledArray0000<T>(checked, length, values) {
            @Override
            public final void put(final int index, final @NotNull T value) {
                putToRest(index, value);
            }

            public final void putUnsafe(final int index, final @NotNull T value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final @NotNull T value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final @NotNull T get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull T getUnsafe(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull T getVolatile(final int index) {
                return getVolatileFromRest(index);
            }

            public final void putOrdered(
                final int index, final @NotNull T value) {
                    putOrderedToRest(index, value);
            }

            public final boolean compareAndSwap(
                final int index, final @NotNull T expected, final @NotNull T value) {
                return compareAndSwapFromRest(index, expected, value);
            }

            public final @NotNull T getAndSet(
                final int index, final @NotNull T value) {
                return getAndSetFromRest(index, value);
            }
        
            public final @NotNull T getAndUpdate(
                final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull T updateAndGet(
                final int index, final @NotNull BiOpObject<T> op, final @NotNull T value) {
                return updateAndGetFromRest(index, op, value);
            }

            public final @NotNull T getAndUpdate(
                final int index, final @NotNull UnaryOpObject<T> op) {
                return getAndUpdateFromRest(index, op);
            }

            public final @NotNull T updateAndGet(
                final int index, final @NotNull UnaryOpObject<T> op) {
                return updateAndGetFromRest(index, op);
            }

            public final @NotNull T getAndUpdate(
                final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull T updateAndGet(
                final int index, final @NotNull MultiOpObject<T> op, final @NotNull T ... value) {
                return updateAndGetFromRest(index, op, value);
            }

        };
    }
}