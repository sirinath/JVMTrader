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

package com.susico.utils.arrays.tabled.arraybyte.immutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ImmutableTabledArray0008Byte extends ImmutableTabledArray0004Byte {
    
    protected final byte value0004;
    protected final byte value0005;
    protected final byte value0006;
    protected final byte value0007;
        
    public final byte getValue0004() {
        return value0004;
    }
    
    public final byte getValue0005() {
        return value0005;
    }
    
    public final byte getValue0006() {
        return value0006;
    }
    
    public final byte getValue0007() {
        return value0007;
    }
    
    protected ImmutableTabledArray0008Byte(final boolean checked, final byte ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0008Byte(final boolean checked, final int definedAsValues, final byte ... values) {
        super(checked, definedAsValues + 4, values);
        final int len = values.length;

        
        if (len >= 8) {
            this.value0007 = ArrayAccess.UNCHECKED.get(values, 7);
        } else {
            this.value0007 = 0;
        }
            
        if (len >= 7) {
            this.value0006 = ArrayAccess.UNCHECKED.get(values, 6);
        } else {
            this.value0006 = 0;
        }
            
        if (len >= 6) {
            this.value0005 = ArrayAccess.UNCHECKED.get(values, 5);
        } else {
            this.value0005 = 0;
        }
            
        if (len >= 5) {
            this.value0004 = ArrayAccess.UNCHECKED.get(values, 4);
        } else {
            this.value0004 = 0;
        }
            
    }

    public static  ImmutableTabledArray0008Byte getInstance(final boolean checked, final byte ... values) {
        return new ImmutableTabledArray0008Byte(checked, values) {
            

            @Override
            public final byte get(final int index) {
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
