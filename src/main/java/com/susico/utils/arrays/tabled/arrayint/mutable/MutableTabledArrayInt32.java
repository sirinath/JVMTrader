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

package com.susico.utils.arrays.tabled.arrayint.mutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

/**
 * Created by sirin_000 on 25/09/2015.
 */
public abstract class MutableTabledArrayInt32 extends MutableTabledArrayInt16 {
    protected int value16;
    protected int value17;
    protected int value18;
    protected int value19;
    protected int value20;
    protected int value21;
    protected int value22;
    protected int value23;

    protected MutableTabledArrayInt32(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArrayInt32(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + 8, values);

        switch (values.length) {
            default:
            case 24:
                this.value23 = ArrayAccess.UNCHECKED.get(values, 23);
                this.value22 = ArrayAccess.UNCHECKED.get(values, 22);
                this.value21 = ArrayAccess.UNCHECKED.get(values, 21);
                this.value20 = ArrayAccess.UNCHECKED.get(values, 20);
                this.value19 = ArrayAccess.UNCHECKED.get(values, 19);
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 23:
                this.value23 = 0;
                this.value22 = ArrayAccess.UNCHECKED.get(values, 22);
                this.value21 = ArrayAccess.UNCHECKED.get(values, 21);
                this.value20 = ArrayAccess.UNCHECKED.get(values, 20);
                this.value19 = ArrayAccess.UNCHECKED.get(values, 19);
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 22:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = ArrayAccess.UNCHECKED.get(values, 21);
                this.value20 = ArrayAccess.UNCHECKED.get(values, 20);
                this.value19 = ArrayAccess.UNCHECKED.get(values, 19);
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 21:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = ArrayAccess.UNCHECKED.get(values, 20);
                this.value19 = ArrayAccess.UNCHECKED.get(values, 19);
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 20:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = 0;
                this.value19 = ArrayAccess.UNCHECKED.get(values, 19);
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 19:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = 0;
                this.value19 = 0;
                this.value18 = ArrayAccess.UNCHECKED.get(values, 18);
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 18:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = 0;
                this.value19 = 0;
                this.value18 = 0;
                this.value17 = ArrayAccess.UNCHECKED.get(values, 17);
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 17:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = 0;
                this.value19 = 0;
                this.value18 = 0;
                this.value17 = 0;
                this.value16 = ArrayAccess.UNCHECKED.get(values, 16);
                break;

            case 16:
            case 15:
            case 14:
            case 13:
            case 12:
            case 11:
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
            case 0:
                this.value23 = 0;
                this.value22 = 0;
                this.value21 = 0;
                this.value20 = 0;
                this.value19 = 0;
                this.value18 = 0;
                this.value17 = 0;
                this.value16 = 0;
                break;
        }
    }

    public static MutableTabledArrayInt32 getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArrayInt32(checked, values) {
            @Override
            public void put(final int index, final int value) {
                switch (index) {
                    case 0:
                        value00 = value;
                    case 1:
                        value01 = value;
                    case 2:
                        value02 = value;
                    case 3:
                        value03 = value;
                    case 4:
                        value04 = value;
                    case 5:
                        value05 = value;
                    case 6:
                        value06 = value;
                    case 7:
                        value07 = value;
                    case 8:
                        value08 = value;
                    case 9:
                        value09 = value;
                    case 10:
                        value10 = value;
                    case 11:
                        value11 = value;
                    case 12:
                        value12 = value;
                    case 13:
                        value13 = value;
                    case 14:
                        value14 = value;
                    case 15:
                        value15 = value;
                    case 16:
                        value16 = value;
                    case 17:
                        value17 = value;
                    case 18:
                        value18 = value;
                    case 19:
                        value19 = value;
                    case 20:
                        value20 = value;
                    case 21:
                        value21 = value;
                    case 22:
                        value22 = value;
                    case 23:
                        value23 = value;
                    default:
                        putToRest(index, value);
                }
            }

            @Override
            public final int get(final int index) {
                switch (index) {
                    case 0:
                        return value00;
                    case 1:
                        return value01;
                    case 2:
                        return value02;
                    case 3:
                        return value03;
                    case 4:
                        return value04;
                    case 5:
                        return value05;
                    case 6:
                        return value06;
                    case 7:
                        return value07;
                    case 8:
                        return value08;
                    case 9:
                        return value09;
                    case 10:
                        return value10;
                    case 11:
                        return value11;
                    case 12:
                        return value12;
                    case 13:
                        return value13;
                    case 14:
                        return value14;
                    case 15:
                        return value15;
                    case 16:
                        return value16;
                    case 17:
                        return value17;
                    case 18:
                        return value18;
                    case 19:
                        return value19;
                    case 20:
                        return value20;
                    case 21:
                        return value21;
                    case 22:
                        return value22;
                    case 23:
                        return value23;
                    default:
                        return getFromRest(index);
                }
            }
        };
    }

    public final int getValue16() {
        return value16;
    }

    public final int getValue17() {
        return value17;
    }

    public final int getValue18() {
        return value18;
    }

    public final int getValue19() {
        return value19;
    }

    public final int getValue20() {
        return value20;
    }

    public final int getValue21() {
        return value21;
    }

    public final int getValue22() {
        return value22;
    }

    public final int getValue23() {
        return value23;
    }

    public final void setValue16(final int value16) {
        this.value16 = value16;
    }

    public final void setValue17(final int value17) {
        this.value17 = value17;
    }

    public final void setValue18(final int value18) {
        this.value18 = value18;
    }

    public final void setValue19(final int value19) {
        this.value19 = value19;
    }

    public final void setValue20(final int value20) {
        this.value20 = value20;
    }

    public final void setValue21(final int value21) {
        this.value21 = value21;
    }

    public final void setValue22(final int value22) {
        this.value22 = value22;
    }

    public final void setValue23(final int value23) {
        this.value23 = value23;
    }
}
