/*
 * Microtrader is available under either the terms of the Apache License, Version 2.0 (ASF 2.0)
 * the Academic Free License Version 3.0, (AFL 3.0) or MIT License (MIT). As a recipient of
 * Microtrader, you may choose which license to receive this code or content under
 * (except as noted in per-module LICENSE files). Some modules may not be the copyright
 * of the Suminda Sirinath Salpitikorala Dharmasena and Project Contributors.
 * These modules contain explicit declarations of copyright in both the LICENSE files
 * in the directories in which they reside and in the code or content itself.
 *
 * No external contributions are allowed under licenses which are fundamentally
 * incompatible with the ASL 2.0, AFL 3.0 and MIT that Microtrader is distributed under.
 * By contributing to this project by means of including but not limited to patches,
 * pull requests, code submissions, issues, bug report, code snippets, discussions,
 * email message, chat messages such content will be licensed under the terms of
 * ASL 2.0, AFL 3.0 and MIT where the recipients are free to choose under which license
 * code or content is received under.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

// Auto generated. Do not edit directly!


package com.susico.utils.arrays.tabled.arraydouble.mutable;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0000Double extends MutableTabledArrayDouble {
    protected MutableTabledArray0000Double(final boolean checked, final int length, final @NotNull double ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000Double(
        final boolean checked, final int definedAsValues, final int length, final @NotNull double ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static  MutableTabledArray0000Double getInstance(
            final boolean checked, final int length, final @NotNull double ... values) {
        return new MutableTabledArray0000Double(checked, length, values) {
            @Override
            public final void put(final int index, final @NotNull double value) {
                putToRest(index, value);
            }

            public final void putUnsafe(final int index, final @NotNull double value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final @NotNull double value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final @NotNull double get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull double getUnsafe(final int index) {
                return getFromRest(index);
            }

            @Override
            public final @NotNull double getVolatile(final int index) {
                return getVolatileFromRest(index);
            }

            public final void putOrdered(
                final int index, final @NotNull double value) {
                    putOrderedToRest(index, value);
            }

            public final boolean compareAndSwap(
                final int index, final @NotNull double expected, final @NotNull double value) {
                return compareAndSwapFromRest(index, expected, value);
            }

            public final @NotNull double getAndSet(
                final int index, final @NotNull double value) {
                return getAndSetFromRest(index, value);
            }
        
            public final @NotNull double getAndUpdate(
                final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull double updateAndGet(
                final int index, final @NotNull BiOpDouble op, final @NotNull double value) {
                return updateAndGetFromRest(index, op, value);
            }

            public final @NotNull double getAndUpdate(
                final int index, final @NotNull UnaryOpDouble op) {
                return getAndUpdateFromRest(index, op);
            }

            public final @NotNull double updateAndGet(
                final int index, final @NotNull UnaryOpDouble op) {
                return updateAndGetFromRest(index, op);
            }

            public final @NotNull double getAndUpdate(
                final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
                return getAndUpdateFromRest(index, op, value);
            }

            public final @NotNull double updateAndGet(
                final int index, final @NotNull MultiOpDouble op, final @NotNull double ... value) {
                return updateAndGetFromRest(index, op, value);
            }

        };
    }
}