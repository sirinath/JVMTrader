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
public abstract class MutableTabledArrayInt16 extends MutableTabledArrayInt8 {
    protected int value08;
    protected int value09;
    protected int value10;
    protected int value11;
    protected int value12;
    protected int value13;
    protected int value14;
    protected int value15;

    protected MutableTabledArrayInt16(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArrayInt16(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + 8, values);

        switch (values.length) {
            default:
            case 16:
                this.value15 = ArrayAccess.UNCHECKED.get(values, 15);
                this.value14 = ArrayAccess.UNCHECKED.get(values, 14);
                this.value13 = ArrayAccess.UNCHECKED.get(values, 13);
                this.value12 = ArrayAccess.UNCHECKED.get(values, 12);
                this.value11 = ArrayAccess.UNCHECKED.get(values, 11);
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 15:
                this.value15 = 0;
                this.value14 = ArrayAccess.UNCHECKED.get(values, 14);
                this.value13 = ArrayAccess.UNCHECKED.get(values, 13);
                this.value12 = ArrayAccess.UNCHECKED.get(values, 12);
                this.value11 = ArrayAccess.UNCHECKED.get(values, 11);
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 14:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = ArrayAccess.UNCHECKED.get(values, 13);
                this.value12 = ArrayAccess.UNCHECKED.get(values, 12);
                this.value11 = ArrayAccess.UNCHECKED.get(values, 11);
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 13:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = ArrayAccess.UNCHECKED.get(values, 12);
                this.value11 = ArrayAccess.UNCHECKED.get(values, 11);
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 12:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = 0;
                this.value11 = ArrayAccess.UNCHECKED.get(values, 11);
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 11:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = 0;
                this.value11 = 0;
                this.value10 = ArrayAccess.UNCHECKED.get(values, 10);
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 10:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = 0;
                this.value11 = 0;
                this.value10 = 0;
                this.value09 = ArrayAccess.UNCHECKED.get(values, 9);
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 9:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = 0;
                this.value11 = 0;
                this.value10 = 0;
                this.value09 = 0;
                this.value08 = ArrayAccess.UNCHECKED.get(values, 8);
                break;

            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
            case 0:
                this.value15 = 0;
                this.value14 = 0;
                this.value13 = 0;
                this.value12 = 0;
                this.value11 = 0;
                this.value10 = 0;
                this.value09 = 0;
                this.value08 = 0;
                break;
        }
    }

    public static MutableTabledArrayInt16 getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArrayInt16(checked, values) {
            @Override
            public final void put(final int index, final int value) {
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
                    default:
                        return getFromRest(index);
                }
            }
        };
    }

    public final int getValue08() {
        return value08;
    }

    public final int getValue09() {
        return value09;
    }

    public final int getValue10() {
        return value10;
    }

    public final int getValue11() {
        return value11;
    }

    public final int getValue12() {
        return value12;
    }

    public final int getValue13() {
        return value13;
    }

    public final int getValue14() {
        return value14;
    }

    public final int getValue15() {
        return value15;
    }

    public final void setValue08(final int value08) {
        this.value08 = value08;
    }

    public final void setValue09(final int value09) {
        this.value09 = value09;
    }

    public final void setValue10(final int value10) {
        this.value10 = value10;
    }

    public final void setValue11(final int value11) {
        this.value11 = value11;
    }

    public final void setValue12(final int value12) {
        this.value12 = value12;
    }

    public final void setValue13(final int value13) {
        this.value13 = value13;
    }

    public final void setValue14(final int value14) {
        this.value14 = value14;
    }

    public final void setValue15(final int value15) {
        this.value15 = value15;
    }
}
