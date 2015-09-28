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

/**
 * Created by sirin_000 on 28/09/2015.
 */

private static String upcase1st(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1)
}

public static String tabledArray(boolean mutable, Class<?> type, int start, int inc) {
    String mutability = mutable ? "Mutable" : "Immutable"
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : "<T>"
    int end = start + inc

    return """\\
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

package com.susico.utils.arrays.tabled.array${type}.${mutability.toLowerCase()};

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ${mutability}TabledArray$end$typeSuffix extends ${mutability}TabledArray$start$typeSuffix {
    ${
        def tmp = ""
        def finalMod = mutable ? " " : "final"
        for (int i; i < inc; i++) {
            tmp += String.format("""\\

    protected finalMod $typeName value%04d;

    public final int getValue%04d() {
        return value%0d;
    }
    """, i)

            tmp += mutable ? String.format("""\\
    public final void setValue%04d(final int value%04d) {
        this.value%04d = value%04d;
    }
    """, i) : ""
        }

        return tmp
    }
    protected ${mutability}TabledArray$end$typeSuffix(final boolean checked, final int... values) {
        this(checked, 0, values);
    }

    protected ${mutability}TabledArray$end$typeSuffix(final boolean checked, final int definedAsValues, final int... values) {
        super(checked, definedAsValues + $inc, values);

        switch (values.length) {
            default:
            ${
        def tmp = ""
        for (int i = start + length; i > start; i--) {
            tmp += String.format("""\\
            case %d:
                """, i)

            for (int j = start + length; j > i; j--)
                tmp += String.format("""\\
                this.value%04d = 0;
                        """, j - 1)

            for (int j = i; j > start; j--)
                tmp += String.format("""\\
                this.value%04d = ArrayAccess.UNCHECKED.get(values, %d);
                        """, j - 1)
        }

        for (int i = start; i > 0; i--)
            tmp += String.format("""\\
            case %d:
                """, i)

        for (int i = start + length; i > start; i--)
            tmp += String.format("""\\
                this.value%04d = 0;
                """, i - 1)

        return tmp
    }
        }
    }

    public static ${mutability}TabledArray$end$typeSuffix getInstance(final boolean checked, final int ... values) {
        return new ${mutability}TabledArray$end$typeSuffix(checked, values) {
            ${
        return mutable ?
                """\\
            @Override
            public final void put(final int index, final int value) {
                switch (index) {
                ${
                    def tmp = ""
                    for (int i = 0; i < start + length; i++)
                        tmp += """\\
                    case %d:
                        value%04d = value;
                    """

                    return tmp
                }
                    default:
                        putToRest(index, value);
                }
            }
            """ : ""
    }

            @Override
            public final int get(final int index) {
                switch (index) {
                ${
        def tmp = ""
        for (int i = 0; i < start + length; i++)
            tmp += """\\\\
                                case %d:
                                    return value%04d;
                                """

        return tmp
    }
                    default:
                        return getFromRest(index);
                }
            }
        };
    }
}
"""
}

tabledArray(true, Integer.TYPE, 0, 8)