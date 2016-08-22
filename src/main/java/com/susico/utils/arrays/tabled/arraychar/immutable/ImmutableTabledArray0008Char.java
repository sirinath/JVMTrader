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
 */ // Auto generated. Do not edit directly!


package com.susico.utils.arrays.tabled.arraychar.immutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class ImmutableTabledArray0008Char extends
    ImmutableTabledArray0004Char {
    
    protected final static long value0004FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Char.class, "value0004");

    protected final @NotNull char value0004;

    protected final static long value0005FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Char.class, "value0005");

    protected final @NotNull char value0005;

    protected final static long value0006FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Char.class, "value0006");

    protected final @NotNull char value0006;

    protected final static long value0007FieldOffset = UnsafeAccess.getFieldOffset(
        ImmutableTabledArray0008Char.class, "value0007");

    protected final @NotNull char value0007;

        
    public final @NotNull char getValue0004() {
        return value0004;
    }

    public final @NotNull char getValue0004Unsafe() {
        return (char) UNSAFE.getChar(this, value0004FieldOffset);
    }

    public final @NotNull char getValue0004Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0004FieldOffset);
    }
    
    public final @NotNull char getValue0005() {
        return value0005;
    }

    public final @NotNull char getValue0005Unsafe() {
        return (char) UNSAFE.getChar(this, value0005FieldOffset);
    }

    public final @NotNull char getValue0005Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0005FieldOffset);
    }
    
    public final @NotNull char getValue0006() {
        return value0006;
    }

    public final @NotNull char getValue0006Unsafe() {
        return (char) UNSAFE.getChar(this, value0006FieldOffset);
    }

    public final @NotNull char getValue0006Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0006FieldOffset);
    }
    
    public final @NotNull char getValue0007() {
        return value0007;
    }

    public final @NotNull char getValue0007Unsafe() {
        return (char) UNSAFE.getChar(this, value0007FieldOffset);
    }

    public final @NotNull char getValue0007Volatile() {
        return (char) UNSAFE.getCharVolatile(this, value0007FieldOffset);
    }
    
    protected ImmutableTabledArray0008Char(
        final boolean checked, final int length, final @NotNull char ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0008Char(
        final boolean checked, final int definedAsValues, final int length, final @NotNull char ... values) {
        super(checked, definedAsValues + 4, length, values);
        final int len = values.length;

        
        if (len >= 7) {
            this.value0007 = ArrayAccess.UNCHECKED.get(7, values);
        } else {
            this.value0007 = 0;
        }
            
        if (len >= 6) {
            this.value0006 = ArrayAccess.UNCHECKED.get(6, values);
        } else {
            this.value0006 = 0;
        }
            
        if (len >= 5) {
            this.value0005 = ArrayAccess.UNCHECKED.get(5, values);
        } else {
            this.value0005 = 0;
        }
            
        if (len >= 4) {
            this.value0004 = ArrayAccess.UNCHECKED.get(4, values);
        } else {
            this.value0004 = 0;
        }
            
    }

    public static  ImmutableTabledArray0008Char getInstance(
        final boolean checked, final int length, final @NotNull char ... values) {
        return new ImmutableTabledArray0008Char(checked, length, values) {
    
    
            @Override
            public final @NotNull char get(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000();
                                
                    case 1:
                        return getValue0001();
                                
                    case 2:
                        return getValue0002();
                                
                    case 3:
                        return getValue0003();
                                
                    case 4:
                        return getValue0004();
                                
                    case 5:
                        return getValue0005();
                                
                    case 6:
                        return getValue0006();
                                
                    case 7:
                        return getValue0007();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull char getUnsafe(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Unsafe();
                                
                    case 1:
                        return getValue0001Unsafe();
                                
                    case 2:
                        return getValue0002Unsafe();
                                
                    case 3:
                        return getValue0003Unsafe();
                                
                    case 4:
                        return getValue0004Unsafe();
                                
                    case 5:
                        return getValue0005Unsafe();
                                
                    case 6:
                        return getValue0006Unsafe();
                                
                    case 7:
                        return getValue0007Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull char getVolatile(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Volatile();
                                
                    case 1:
                        return getValue0001Volatile();
                                
                    case 2:
                        return getValue0002Volatile();
                                
                    case 3:
                        return getValue0003Volatile();
                                
                    case 4:
                        return getValue0004Volatile();
                                
                    case 5:
                        return getValue0005Volatile();
                                
                    case 6:
                        return getValue0006Volatile();
                                
                    case 7:
                        return getValue0007Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
