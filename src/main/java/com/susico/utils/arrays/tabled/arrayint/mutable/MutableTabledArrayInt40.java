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
public abstract class MutableTabledArrayInt40 extends MutableTabledArrayInt32 {
    protected int value32;
    protected int value33;
    protected int value34;
    protected int value35;
    protected int value36;
    protected int value37;
    protected int value38;
    protected int value39;

    protected MutableTabledArrayInt40(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArrayInt40(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + 8, values);

        switch (values.length) {
            default:
            case 32:
                this.value39 = ArrayAccess.UNCHECKED.get(values, 39);
                this.value38 = ArrayAccess.UNCHECKED.get(values, 38);
                this.value37 = ArrayAccess.UNCHECKED.get(values, 37);
                this.value36 = ArrayAccess.UNCHECKED.get(values, 36);
                this.value35 = ArrayAccess.UNCHECKED.get(values, 35);
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 31:
                this.value39 = 0;
                this.value38 = ArrayAccess.UNCHECKED.get(values, 38);
                this.value37 = ArrayAccess.UNCHECKED.get(values, 37);
                this.value36 = ArrayAccess.UNCHECKED.get(values, 36);
                this.value35 = ArrayAccess.UNCHECKED.get(values, 35);
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 30:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = ArrayAccess.UNCHECKED.get(values, 37);
                this.value36 = ArrayAccess.UNCHECKED.get(values, 36);
                this.value35 = ArrayAccess.UNCHECKED.get(values, 35);
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 29:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = ArrayAccess.UNCHECKED.get(values, 36);
                this.value35 = ArrayAccess.UNCHECKED.get(values, 35);
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 28:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = 0;
                this.value35 = ArrayAccess.UNCHECKED.get(values, 35);
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 27:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = 0;
                this.value35 = 0;
                this.value34 = ArrayAccess.UNCHECKED.get(values, 34);
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 26:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = 0;
                this.value35 = 0;
                this.value34 = 0;
                this.value33 = ArrayAccess.UNCHECKED.get(values, 33);
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 25:
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = 0;
                this.value35 = 0;
                this.value34 = 0;
                this.value33 = 0;
                this.value32 = ArrayAccess.UNCHECKED.get(values, 32);
                break;

            case 24:
            case 23:
            case 22:
            case 21:
            case 20:
            case 19:
            case 18:
            case 17:
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
                this.value39 = 0;
                this.value38 = 0;
                this.value37 = 0;
                this.value36 = 0;
                this.value35 = 0;
                this.value34 = 0;
                this.value33 = 0;
                this.value32 = 0;
                break;
        }
    }

    public static MutableTabledArrayInt40 getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArrayInt40(checked, values) {
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
                    case 24:
                        value32 = value;
                    case 25:
                        value33 = value;
                    case 26:
                        value34 = value;
                    case 27:
                        value35 = value;
                    case 28:
                        value36 = value;
                    case 29:
                        value37 = value;
                    case 30:
                        value38 = value;
                    case 31:
                        value39 = value;
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
                    case 24:
                        return value32;
                    case 25:
                        return value33;
                    case 26:
                        return value34;
                    case 27:
                        return value35;
                    case 28:
                        return value36;
                    case 29:
                        return value37;
                    case 30:
                        return value38;
                    case 31:
                        return value39;
                    default:
                        return getFromRest(index);
                }
            }
        };
    }

    public final int getValue32() {
        return value32;
    }

    public final int getValue33() {
        return value33;
    }

    public final int getValue34() {
        return value34;
    }

    public final int getValue35() {
        return value35;
    }

    public final int getValue36() {
        return value36;
    }

    public final int getValue37() {
        return value37;
    }

    public final int getValue38() {
        return value38;
    }

    public final int getValue39() {
        return value39;
    }

    public final void setValue32(final int value32) {
        this.value32 = value32;
    }

    public final void setValue33(final int value33) {
        this.value33 = value33;
    }

    public final void setValue34(final int value34) {
        this.value34 = value34;
    }

    public final void setValue35(final int value35) {
        this.value35 = value35;
    }

    public final void setValue36(final int value36) {
        this.value36 = value36;
    }

    public final void setValue37(final int value37) {
        this.value37 = value37;
    }

    public final void setValue38(final int value38) {
        this.value38 = value38;
    }

    public final void setValue39(final int value39) {
        this.value39 = value39;
    }
}
