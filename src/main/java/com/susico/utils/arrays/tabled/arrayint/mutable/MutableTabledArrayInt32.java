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
public abstract class MutableTabledArrayInt32 extends MutableTabledArrayInt24 {
    protected int value24;
    protected int value25;
    protected int value26;
    protected int value27;
    protected int value28;
    protected int value29;
    protected int value30;
    protected int value31;

    protected MutableTabledArrayInt32(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArrayInt32(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + 8, values);

        switch (values.length) {
            default:
            case 32:
                this.value31 = ArrayAccess.UNCHECKED.get(values, 31);
                this.value30 = ArrayAccess.UNCHECKED.get(values, 30);
                this.value29 = ArrayAccess.UNCHECKED.get(values, 29);
                this.value28 = ArrayAccess.UNCHECKED.get(values, 28);
                this.value27 = ArrayAccess.UNCHECKED.get(values, 27);
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 31:
                this.value31 = 0;
                this.value30 = ArrayAccess.UNCHECKED.get(values, 30);
                this.value29 = ArrayAccess.UNCHECKED.get(values, 29);
                this.value28 = ArrayAccess.UNCHECKED.get(values, 28);
                this.value27 = ArrayAccess.UNCHECKED.get(values, 27);
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 30:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = ArrayAccess.UNCHECKED.get(values, 29);
                this.value28 = ArrayAccess.UNCHECKED.get(values, 28);
                this.value27 = ArrayAccess.UNCHECKED.get(values, 27);
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 29:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = ArrayAccess.UNCHECKED.get(values, 28);
                this.value27 = ArrayAccess.UNCHECKED.get(values, 27);
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 28:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = 0;
                this.value27 = ArrayAccess.UNCHECKED.get(values, 27);
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 27:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = 0;
                this.value27 = 0;
                this.value26 = ArrayAccess.UNCHECKED.get(values, 26);
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 26:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = 0;
                this.value27 = 0;
                this.value26 = 0;
                this.value25 = ArrayAccess.UNCHECKED.get(values, 25);
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
                break;

            case 25:
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = 0;
                this.value27 = 0;
                this.value26 = 0;
                this.value25 = 0;
                this.value24 = ArrayAccess.UNCHECKED.get(values, 24);
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
                this.value31 = 0;
                this.value30 = 0;
                this.value29 = 0;
                this.value28 = 0;
                this.value27 = 0;
                this.value26 = 0;
                this.value25 = 0;
                this.value24 = 0;
                break;
        }
    }

    public static MutableTabledArrayInt32 getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArrayInt32(checked, values) {
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
                        value24 = value;
                    case 25:
                        value25 = value;
                    case 26:
                        value26 = value;
                    case 27:
                        value27 = value;
                    case 28:
                        value28 = value;
                    case 29:
                        value29 = value;
                    case 30:
                        value30 = value;
                    case 31:
                        value31 = value;
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
                        return value24;
                    case 25:
                        return value25;
                    case 26:
                        return value26;
                    case 27:
                        return value27;
                    case 28:
                        return value28;
                    case 29:
                        return value29;
                    case 30:
                        return value30;
                    case 31:
                        return value31;
                    default:
                        return getFromRest(index);
                }
            }
        };
    }

    public final int getValue24() {
        return value24;
    }

    public final int getValue25() {
        return value25;
    }

    public final int getValue26() {
        return value26;
    }

    public final int getValue27() {
        return value27;
    }

    public final int getValue28() {
        return value28;
    }

    public final int getValue29() {
        return value29;
    }

    public final int getValue30() {
        return value30;
    }

    public final int getValue31() {
        return value31;
    }

    public final void setValue24(final int value24) {
        this.value24 = value24;
    }

    public final void setValue25(final int value25) {
        this.value25 = value25;
    }

    public final void setValue26(final int value26) {
        this.value26 = value26;
    }

    public final void setValue27(final int value27) {
        this.value27 = value27;
    }

    public final void setValue28(final int value28) {
        this.value28 = value28;
    }

    public final void setValue29(final int value29) {
        this.value29 = value29;
    }

    public final void setValue30(final int value30) {
        this.value30 = value30;
    }

    public final void setValue31(final int value31) {
        this.value31 = value31;
    }
}
