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

String upcase1st(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1)
}

String tabledArray(boolean mutable, Class<?> type, int start, int inc) {
    String mutability = mutable ? "Mutable" : "Immutable"
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
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

public abstract class ${mutability}TabledArray$end$typeSuffix$generic extends ${mutability}TabledArray$start$typeSuffix$generic {
    ${
        def tmp = ""
        def str = ""

        for (int i = start; i < start + inc; i++) {
            if (mutable)
                str = """
    protected $typeName value%04d;"""
            else
                str = """
    protected final $typeName value%04d;"""

            tmp += String.format(str, i)
        }

        tmp += """
        """

        for (int i = start; i < start + inc; i++) {
                str = """
    public final $typeName getValue%04d() {
        return value%04d;
    }
    """
            tmp += String.format(str, i, i)
        }


        if (mutable) {
            for (int i = start; i < start + inc; i++) {
                str = """
    public final void setValue%04d(final $typeName value%04d) {
        this.value%04d = value%04d;
    }
    """
                tmp += String.format(str, i, i, i, i)
            }
        }

        return tmp
    }
    protected ${mutability}TabledArray$end$typeSuffix(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected ${mutability}TabledArray$end$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues + $inc, values);

        switch (values.length) {
            default:
            ${
                def tmp = ""
                for (int i = start + inc; i > start; i--) {
                    tmp += String.format("""
            case %d:""", i)

                    for (int j = start + inc; j > i; j--)
                        tmp += String.format("""
                this.value%04d = 0;""", j - 1)

                    for (int j = i; j > start; j--)
                        tmp += String.format("""
                this.value%04d = ArrayAccess.UNCHECKED.get(values, %d);""", j - 1, j - 1)

                    tmp += """
                break;
                    """
                }

                for (int i = start; i >= 0; i--)
                    tmp += String.format("""
                case %d:""", i)

                for (int i = start + inc; i > start; i--)
                    tmp += String.format("""
                this.value%04d = 0;""", i - 1)

                return tmp
            }
        }
    }

    public static ${mutability}TabledArray$end$typeSuffix getInstance(final boolean checked, final $typeName ... values) {
        return new ${mutability}TabledArray$end$typeSuffix(checked, values) {
            ${
                def put = ""
                if (mutable) {
                    put += """
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                    ${
                        def tmp = ""
                        for (int i = 0; i < start + inc; i++)
                            tmp += String.format("""
                    case %d:
                        value%04d = value;
                        break;
                    """, i, i)

                        return tmp
                    }
                    default:
                        putToRest(index, value);
                }
            }
            """
                }

                return put
            }

            @Override
            public final $typeName get(final int index) {
                switch (index) {
                ${
                    def tmp = ""
                    for (int i = 0; i < start + inc; i++)
                        tmp += String.format("""
                    case %d:
                        return value%04d;
                                """, i, i)

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


void tabledArrayGenAll() {
    boolean[] mutable = [true, false]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]
    int limit = 1024
    int inc = 8

    for(boolean m : mutable)
        for(Class<?> type : types)
            for (int i = 0; i < limit; i++) {
                File f = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\array${type}\\${m ? "mutable" : "immutable"}")
                PrintWriter pw = f.newPrintWriter()
                pw.print(tabledArray(m, type, i * inc, inc))
                pw.flush()
                pw.close()
            }
}

String tableArrayFactories(int limit) {
}

