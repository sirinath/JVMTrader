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

package com.susico.utils.arrays.tabled.arrayint.immutable;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

/**
 * Created by sirin_000 on 25/09/2015.
 */
public abstract class ImmutableTabledArrayInt8 extends ImmutableTabledArrayIntBase {
    protected final int value00;
    protected final int value01;
    protected final int value02;
    protected final int value03;
    protected final int value04;
    protected final int value05;
    protected final int value06;
    protected final int value07;

    protected ImmutableTabledArrayInt8(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArrayInt8(final boolean checked, final int definedAsValues, final int... values) {
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

    public static ImmutableTabledArrayInt8 getInstance(final boolean checked, final int ... values) {
        return new ImmutableTabledArrayInt8(checked, values) {
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
}
