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

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class MutableTabledArray0008<T> extends MutableTabledArray0004<T> {
    
    protected T value0004;
    protected T value0005;
    protected T value0006;
    protected T value0007;
        
    public final T getValue0004() {
        return value0004;
    }
    
    public final T getValue0005() {
        return value0005;
    }
    
    public final T getValue0006() {
        return value0006;
    }
    
    public final T getValue0007() {
        return value0007;
    }
    
    public final void setValue0004(final T value0004) {
        this.value0004 = value0004;
    }
    
    public final void setValue0005(final T value0005) {
        this.value0005 = value0005;
    }
    
    public final void setValue0006(final T value0006) {
        this.value0006 = value0006;
    }
    
    public final void setValue0007(final T value0007) {
        this.value0007 = value0007;
    }
    
    protected MutableTabledArray0008(final boolean checked, final int length, final T ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0008(final boolean checked, final int definedAsValues, final int length, final T ... values) {
        super(checked, definedAsValues + 4, length, values);
        final int len = values.length;

        
        if (len >= 8) {
            this.value0007 = ArrayAccess.UNCHECKED.get(values, 7);
        } else {
            this.value0007 = null;
        }
            
        if (len >= 7) {
            this.value0006 = ArrayAccess.UNCHECKED.get(values, 6);
        } else {
            this.value0006 = null;
        }
            
        if (len >= 6) {
            this.value0005 = ArrayAccess.UNCHECKED.get(values, 5);
        } else {
            this.value0005 = null;
        }
            
        if (len >= 5) {
            this.value0004 = ArrayAccess.UNCHECKED.get(values, 4);
        } else {
            this.value0004 = null;
        }
            
    }

    public static <T> MutableTabledArray0008<T> getInstance(final boolean checked, final int length, final T ... values) {
        return new MutableTabledArray0008<T>(checked, length, values) {
            
            @Override
            public final void put(final int index, final T value) {
                switch (index) {
                    
                    case 0:
                        value0000 = value;
                        break;
                    
                    case 1:
                        value0001 = value;
                        break;
                    
                    case 2:
                        value0002 = value;
                        break;
                    
                    case 3:
                        value0003 = value;
                        break;
                    
                    case 4:
                        value0004 = value;
                        break;
                    
                    case 5:
                        value0005 = value;
                        break;
                    
                    case 6:
                        value0006 = value;
                        break;
                    
                    case 7:
                        value0007 = value;
                        break;
                    
                    default:
                        putToRest(index, value);
                }
            }
            

            @Override
            public final T get(final int index) {
                switch (index) {
                
                    case 0:
                        return value0000;
                                
                    case 1:
                        return value0001;
                                
                    case 2:
                        return value0002;
                                
                    case 3:
                        return value0003;
                                
                    case 4:
                        return value0004;
                                
                    case 5:
                        return value0005;
                                
                    case 6:
                        return value0006;
                                
                    case 7:
                        return value0007;
                                
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}