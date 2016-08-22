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


package com.susico.utils.arrays.tabled.arraylong.mutable;

import com.susico.utils.UnsafeAccess;
import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public abstract class MutableTabledArray0002Long extends
    MutableTabledArray0001Long {
    
    protected final static long value0001FieldOffset = UnsafeAccess.getFieldOffset(
        MutableTabledArray0002Long.class, "value0001");

    protected long value0001;

        
    public final @NotNull long getValue0001() {
        return value0001;
    }

    public final @NotNull long getValue0001Unsafe() {
        return (long) UNSAFE.getLong(this, value0001FieldOffset);
    }

    public final @NotNull long getValue0001Volatile() {
        return (long) UNSAFE.getLongVolatile(this, value0001FieldOffset);
    }
    
    public final void setValue0001(final @NotNull long value0001) {
        this.value0001 = value0001;
    }

    public final void putValue0001Unsafe(final @NotNull long value0001) {
        UNSAFE.putLong(this, value0001FieldOffset, value0001);
    }

    public final void putValue0001Volatile(final @NotNull long value0001) {
        UNSAFE.putLongVolatile(this, value0001FieldOffset, value0001);
    }
    
    public final void putValue0001Ordered(
        final @NotNull long value0001) {
            UNSAFE.putOrderedLong(this, value0001FieldOffset, (value0001));
    }

    public final boolean compareAndSwapValue0001(final @NotNull long expected,
        final @NotNull long value0001) {
        return UNSAFE.compareAndSwapLong(this,
            value0001FieldOffset,
            (expected), (value0001));
    }

    public final @NotNull long getAndSetValue0001(
        final @NotNull long value0001) {
        return (long) (UNSAFE.getAndSetLong(this,
            value0000FieldOffset,
            (value0001)));
    }
        
    public final @NotNull long getAndUpdateValue0001(final @NotNull BiOpLong op, final @NotNull long value0001) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (op.apply(current, value0001))));
        return current;
    }

    public final @NotNull long updateAndGetValue0001(final @NotNull BiOpLong op, final @NotNull long value0001) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0001(final @NotNull UnaryOpLong op) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (op.apply(current))));
        return current;
    }

    public final @NotNull long updateAndGetValue0001(final @NotNull UnaryOpLong op) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0001FieldOffset);
            newValue = op.apply(current);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    public final @NotNull long getAndUpdateValue0001(final @NotNull MultiOpLong op, final @NotNull long ... value0001) {
        long current;

        do {
            current = (long) UNSAFE.getLongVolatile(this,
                value0001FieldOffset);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (op.apply(current, value0001))));
        return current;
    }

    public final @NotNull long updateAndGetValue0001(final @NotNull MultiOpLong op, final @NotNull long ... value0001) {
        long current;
        long newValue;

        do {
            current = (long) UNSAFE.getLongVolatile(this, value0001FieldOffset);
            newValue = op.apply(current, value0001);
        } while (!UNSAFE.compareAndSwapLong(this, value0001FieldOffset,
            (current), (newValue)));

        return newValue;
    }

    protected MutableTabledArray0002Long(
        final boolean checked, final int length, final @NotNull long ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0002Long(
        final boolean checked, final int definedAsValues, final int length, final @NotNull long ... values) {
        super(checked, definedAsValues + 1, length, values);
        final int len = values.length;

        
        if (len >= 1) {
            this.value0001 = ArrayAccess.UNCHECKED.get(1, values);
        } else {
            this.value0001 = 0;
        }
            
    }

    public static  MutableTabledArray0002Long getInstance(
        final boolean checked, final int length, final @NotNull long ... values) {
        return new MutableTabledArray0002Long(checked, length, values) {
    
            @Override
            public final void put(final int index, final @NotNull long value) {
                switch (index) {
                    
                    case 0:
                        setValue0000(value);
                        break;
                    
                    case 1:
                        setValue0001(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putVolatile(final int index, final @NotNull long value) {
                switch (index) {
                    
                    case 0:
                        putValue0000Volatile(value);
                        break;
                    
                    case 1:
                        putValue0001Volatile(value);
                        break;
                    
                    default:
                        putVolatileToRest(index, value);
                }
            }
            
            @Override
            public final void putUnsafe(final int index, final @NotNull long value) {
                switch (index) {
                    
                    case 0:
                        putValue0000Unsafe(value);
                        break;
                    
                    case 1:
                        putValue0001Unsafe(value);
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            
            @Override
            public final void putOrdered(final int index, final @NotNull long value) {
                switch (index) {
                
                    case 0:
                        putValue0000Ordered(value);
                        break;
                    
                    case 1:
                        putValue0001Ordered(value);
                        break;
                    
                    default:
                        putOrderedToRest(index, value);
                }
            }
            
            @Override
            public final boolean compareAndSwap(final int index, final @NotNull long expected, final @NotNull long value) {
                switch (index) {
                
                    case 0:
                        return compareAndSwapValue0000(expected, value);
                    
                    case 1:
                        return compareAndSwapValue0001(expected, value);
                    
                    default:
                        return compareAndSwapFromRest(index, expected, value);
                }
            }
            
            @Override
            public final @NotNull long getAndSet(final int index, final @NotNull long value) {
                switch (index) {
                
                    case 0:
                        return getAndSetValue0000(value);
                    
                    case 1:
                        return getAndSetValue0001(value);
                    
                    default:
                        return getAndSetFromRest(index, value);
                }
            }
            
            @Override
            public final @NotNull long getAndUpdate(final int index, final @NotNull BiOpLong op, final @NotNull long value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull long updateAndGet(final int index, final @NotNull BiOpLong op, final @NotNull long value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull long getAndUpdate(final int index, final @NotNull UnaryOpLong op) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op);
                    
                    case 1:
                        return getAndUpdateValue0001(op);
                    
                    default:
                        return getAndUpdateFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull long updateAndGet(final int index, final @NotNull UnaryOpLong op) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op);
                    
                    case 1:
                        return updateAndGetValue0001(op);
                    
                    default:
                        return updateAndGetFromRest(index, op);
                }
            }
            
            @Override
            public final @NotNull long getAndUpdate(final int index, final @NotNull MultiOpLong op, final @NotNull long ... value) {
                switch (index) {
                
                    case 0:
                        return getAndUpdateValue0000(op, value);
                    
                    case 1:
                        return getAndUpdateValue0001(op, value);
                    
                    default:
                        return getAndUpdateFromRest(index, op, value);
                }
            }
            
            @Override
            public final @NotNull long updateAndGet(final int index, final @NotNull MultiOpLong op, final @NotNull long ... value) {
                switch (index) {
                
                    case 0:
                        return updateAndGetValue0000(op, value);
                    
                    case 1:
                        return updateAndGetValue0001(op, value);
                    
                    default:
                        return updateAndGetFromRest(index, op, value);
                }
            }
            
    
            @Override
            public final @NotNull long get(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000();
                                
                    case 1:
                        return getValue0001();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull long getUnsafe(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Unsafe();
                                
                    case 1:
                        return getValue0001Unsafe();
                                
                    default:
                        return getFromRest(index);
                }
            }
            @Override
            public final @NotNull long getVolatile(final int index) {
                switch (index) {
                
                    case 0:
                        return getValue0000Volatile();
                                
                    case 1:
                        return getValue0001Volatile();
                                
                    default:
                        return getVolatileFromRest(index);
                }
            }
        };
    }
}
