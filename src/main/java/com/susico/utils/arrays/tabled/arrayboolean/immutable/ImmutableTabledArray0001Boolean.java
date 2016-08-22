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


package com.susico.utils.arrays.tabled.arrayboolean.immutable;

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0001Boolean extends
    ImmutableTabledArray0000Boolean {
    
    protected final static long value0000FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0001Boolean.class, "value0000");

    protected final @NotNull boolean value0000;

    public final @NotNull boolean getValue0000() {
        return value0000;
    }

    public final @NotNull boolean getValue0000Volatile() {
        return (boolean) UNSAFE.getBooleanVolatile(this, value0000FieldOffset);
    }

    public final @NotNull boolean getValue0000Unsafe() {
        return (boolean) UNSAFE.getBoolean(this, value0000FieldOffset);
    }
    
    protected ImmutableTabledArray0001Boolean(
        final boolean checked, final int length, final @NotNull boolean ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0001Boolean(
        final boolean checked, final int definedAsValues, final int length, final @NotNull boolean ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = false;
        }

    }

    public static  ImmutableTabledArray0001Boolean getInstance(
        final boolean checked, final int length, final @NotNull boolean ... values) {
        return new ImmutableTabledArray0001Boolean(checked, length, values) {
    
    
            @Override
            public final @NotNull boolean get(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull boolean getUnsafe(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            
            @Override
            public final @NotNull boolean getVolatile(final int index) {
                switch (index) {
        
                    case 0:
                        return getValue0000Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
            
        };
    }
}
