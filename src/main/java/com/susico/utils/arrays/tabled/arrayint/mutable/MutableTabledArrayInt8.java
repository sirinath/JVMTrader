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
public abstract class MutableTabledArrayInt8 extends MutableTabledArrayIntBase {
    protected int value00;
    protected int value01;
    protected int value02;
    protected int value03;
    protected int value04;
    protected int value05;
    protected int value06;
    protected int value07;

    protected MutableTabledArrayInt8(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArrayInt8(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + 8, values);

        switch (values.length) {
            default:
            case 8:
                this.value07 = ArrayAccess.UNCHECKED.get(values, 7);
                this.value06 = ArrayAccess.UNCHECKED.get(values, 6);
                this.value05 = ArrayAccess.UNCHECKED.get(values, 5);
                this.value04 = ArrayAccess.UNCHECKED.get(values, 4);
                this.value03 = ArrayAccess.UNCHECKED.get(values, 3);
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 7:
                this.value07 = 0;
                this.value06 = ArrayAccess.UNCHECKED.get(values, 6);
                this.value05 = ArrayAccess.UNCHECKED.get(values, 5);
                this.value04 = ArrayAccess.UNCHECKED.get(values, 4);
                this.value03 = ArrayAccess.UNCHECKED.get(values, 3);
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 6:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = ArrayAccess.UNCHECKED.get(values, 5);
                this.value04 = ArrayAccess.UNCHECKED.get(values, 4);
                this.value03 = ArrayAccess.UNCHECKED.get(values, 3);
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 5:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = ArrayAccess.UNCHECKED.get(values, 4);
                this.value03 = ArrayAccess.UNCHECKED.get(values, 3);
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 4:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = 0;
                this.value03 = ArrayAccess.UNCHECKED.get(values, 3);
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 3:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = 0;
                this.value03 = 0;
                this.value02 = ArrayAccess.UNCHECKED.get(values, 2);
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 2:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = 0;
                this.value03 = 0;
                this.value02 = 0;
                this.value01 = ArrayAccess.UNCHECKED.get(values, 1);
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 1:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = 0;
                this.value03 = 0;
                this.value02 = 0;
                this.value01 = 0;
                this.value00 = ArrayAccess.UNCHECKED.get(values, 0);
                break;

            case 0:
                this.value07 = 0;
                this.value06 = 0;
                this.value05 = 0;
                this.value04 = 0;
                this.value03 = 0;
                this.value02 = 0;
                this.value01 = 0;
                this.value00 = 0;
                break;
        }
    }

    public static MutableTabledArrayInt8 getInstance(final boolean checked, final int ... values) {
        return new MutableTabledArrayInt8(checked, values) {
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
                    default:
                        return getFromRest(index);
                }
            }
        };
    }

    public final int getValue00() {
        return value00;
    }

    public final int getValue01() {
        return value01;
    }

    public final int getValue02() {
        return value02;
    }

    public final int getValue03() {
        return value03;
    }

    public final int getValue04() {
        return value04;
    }

    public final int getValue05() {
        return value05;
    }

    public final int getValue06() {
        return value06;
    }

    public final int getValue07() {
        return value07;
    }

    public final void setValue00(final int value00) {
        this.value00 = value00;
    }

    public final void setValue01(final int value01) {
        this.value01 = value01;
    }

    public final void setValue02(final int value02) {
        this.value02 = value02;
    }

    public final void setValue03(final int value03) {
        this.value03 = value03;
    }

    public final void setValue04(final int value04) {
        this.value04 = value04;
    }

    public final void setValue05(final int value05) {
        this.value05 = value05;
    }

    public final void setValue06(final int value06) {
        this.value06 = value06;
    }

    public final void setValue07(final int value07) {
        this.value07 = value07;
    }
}
