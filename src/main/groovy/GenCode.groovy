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

String upcase1st(String str) {
    if (str == null || str.length() == 0)
        return ""

    return str.substring(0, 1).toUpperCase() + str.substring(1)
}

String tableArrayBase() {
    StringBuffer tmp = new StringBuffer("""
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

package com.susico.utils.arrays.tabled;

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class TabledArray {
    protected final ArrayAccess ARRAY_ACCESS;
    protected final int length;
    protected final int definedAsValues;

    protected TabledArray(final boolean checked, final int definedAsValues, final int length) {
        this.ARRAY_ACCESS = ArrayAccess.checked(checked);
        this.length = length;
        this.definedAsValues = definedAsValues;
    }

    public final int getDefinedAsValues() {
        return definedAsValues;
    }

    public final int getLength() {
        return length;
    }
""");

    boolean[] mutable = [true, false]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]

    for(boolean m : mutable) {
        String mutability = m ? "Mutable" : "Immutable"

        for (Class<?> type : types) {
            String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
            String generic = type.isPrimitive() ? "" : "<T>"

            tmp.append("""
    public static $generic ${mutability}TabledArray${typeSuffix}$generic get${mutability}${typeSuffix}Array(final boolean checked, final int length, final $typeName ... values) {
        return ${mutability}TabledArray${typeSuffix}.${generic}getInstance(checked, length, values);
    }
"""
            )
        }
    }

    return tmp.append("}").toString()
}

String tabledArrayBase(boolean mutable, Class<?> type) {
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    StringBuffer str = new StringBuffer()
    int limit = 13

    if (mutable)
        str.append("""
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.mutable;

import com.susico.utils.arrays.tabled.array${type.getSimpleName()}.immutable.ImmutableTabledArray$typeSuffix;

public abstract class MutableTabledArray$typeSuffix$generic extends ImmutableTabledArray$typeSuffix$generic {
    protected MutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values);
    }


    public abstract void put(int index, $typeName value);

    protected final void putToRest(int index, $typeName value) {
        ARRAY_ACCESS.put(rest, index - definedAsValues, value);
    }
""")
    else
        str.append("""
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.immutable;

import com.susico.utils.arrays.tabled.TabledArray;

public abstract class ImmutableTabledArray$typeSuffix$generic extends TabledArray {
    protected final $typeName[] rest;

    protected ImmutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values.length);
        this.rest = new $typeName[length > definedAsValues ? length - definedAsValues : 0];

        if (rest.length > 0)
            System.arraycopy(values, definedAsValues, rest, 0, rest.length);
    }

    public abstract $typeName get(final int index);

    protected final $typeName getFromRest(int index) {
        return ARRAY_ACCESS.get(rest, index - definedAsValues);
    }
""")

    String mutability = mutable ? "Mutable" : "Immutable"

    str.append("""
    public static $generic ${mutability}TabledArray${typeSuffix}$generic getInstance(final boolean checked, final int length, final $typeName ... values) {
        switch (index) {
            case 0:
                return ${mutability}TabledArray0000${typeSuffix}.getInstance(checked, values);
"""
    )

    for (int i = 0; i < limit; i++) {
        int start = 2 ** i
        int end = 2 * start

        str.append("                ")

        for (int j = start; j <= end; j++) {
            if (j % 16 == 0) {
                str.append("\n                ").append("case $j: ")
            } else {
                str.append("case $j: ")
            }
        }

        str.append("""
                return ${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}.getInstance(checked, values);

"""
        )
    }

    str.append("""
            default:
                return ${mutability}TabledArray${String.format("%04d", 2 ** limit)}${typeSuffix}.getInstance(checked, values);
        }
    }
""")

    return str.append("}").toString()
}

String tabledArray0(boolean mutable, Class<?> type) {
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"

    if (mutable)
        return """
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.mutable;

public abstract class MutableTabledArray0000${typeSuffix}${generic} extends MutableTabledArray${typeSuffix}${generic} {
    protected MutableTabledArray0000${typeSuffix}(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArray0000${typeSuffix}(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values);
    }

    public static ${generic} MutableTabledArray0000${typeSuffix}${generic} getInstance(final boolean checked, final $typeName ... values) {
        return new MutableTabledArray0000${typeSuffix}${generic}(checked, values) {
            @Override
            public final void put(final int index, final $typeName value) {
                putToRest(index, value);
            }

            @Override
            public final $typeName get(final int index) {
                return getFromRest(index);
            }
        };
    }
}
"""
    else
        return """
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.immutable;

public abstract class ImmutableTabledArray0000${typeSuffix}${generic} extends ImmutableTabledArray${typeSuffix}${generic} {
    protected ImmutableTabledArray0000${typeSuffix}(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray0000${typeSuffix}(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values.length);
    }

    public static ${generic} ImmutableTabledArray0000${typeSuffix}${generic} getInstance(final boolean checked, final $typeName ... values) {
        return new ImmutableTabledArray0000${typeSuffix}${generic}(checked, values) {
            @Override
            public final $typeName get(final int index) {
                return getFromRest(index);
            }
        };
    }
}

"""
}

String tabledArray1(boolean mutable, Class<?> type) {
    String mutability = mutable ? "Mutable" : "Immutable"
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    String defaultValue = type.equals(Boolean.TYPE) ? "false" : (type.isPrimitive() ? (type.equals(Float.TYPE) ? "Float.NaN" : (type.equals(Double.TYPE) ? "Double.NaN" : "0")) : "null")

    return """
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.${mutability.toLowerCase()};

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ${mutability}TabledArray0001$typeSuffix$generic extends ${mutability}TabledArray0000$typeSuffix$generic {
    ${
        StringBuilder tmp = new StringBuilder()
        StringBuilder str

        if (mutable) {
            str = new StringBuilder("""
    protected $typeName value0000;""")
        } else {
            str = new StringBuilder("""
    protected final $typeName value0000;""")
        }


        str = new StringBuilder("""
    public final $typeName getValue0000() {
        return value0000;
    }
    """)


        if (mutable) {
            str = new StringBuilder("""
    public final void setValue0000(final $typeName value0000) {
        this.value0000 = value0000;
    }
    """)
        }

        return tmp.toString()
    }
    protected ${mutability}TabledArray0001$typeSuffix(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues + 1, values);
        final int len = values.length;

        switch (len) {
            default:
                if (len <= 1)
                    break;
    ${
        StringBuilder tmp = new StringBuilder()

        tmp.append("""
            case 1:
                this.value0000 = ArrayAccess.UNCHECKED.get(values, 0);
            """)

        return tmp.toString()
    }
        }

        switch (len) {
            default:
                if (len > 1)
                    break;
    ${
        StringBuilder tmp = new StringBuilder()

        tmp.append("""
                case 0:
                    this.value0000 = ${defaultValue};
                    """)

        return tmp.toString()
    }
        }
    }

    public static ${generic} ${mutability}TabledArray0001$typeSuffix getInstance(final boolean checked, final $typeName ... values) {
        return new ${mutability}TabledArray0001$typeSuffix$generic(checked, values) {
            ${
                StringBuilder put = new StringBuilder()
                if (mutable) {
                   put.append("""
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                ${
                    StringBuilder tmp = new StringBuilder()
                    tmp.append("""
                    case 0:
                        value0000 = value;
                        break;
                    """
                    )

                    return tmp.toString()
                }
                    default:
                        putToRest(index, value);
                }
            }
            """)
                }

                return put.toString()
            }

            @Override
            public final $typeName get(final int index) {
                switch (index) {
                ${
                    StringBuilder tmp = new StringBuilder()
                    tmp.append("""
                    case 0:
                        return value0000;
                                """)

                    return tmp.toString()
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

String tabledArray(boolean mutable, Class<?> type, int start) {
    String mutability = mutable ? "Mutable" : "Immutable"
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    int end = 2 * start
    String defaultValue = type.equals(Boolean.TYPE) ? "false" : (type.isPrimitive() ? (type.equals(Float.TYPE) ? "Float.NaN" : (type.equals(Double.TYPE) ? "Double.NaN" : "0")) : "null")

    return """
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

package com.susico.utils.arrays.tabled.array${type.getSimpleName()}.${mutability.toLowerCase()};

import com.susico.utils.arrays.ArrayUtils.ArrayAccess;

public abstract class ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix$generic extends ${mutability}TabledArray${String.format("%04d", start)}$typeSuffix$generic {
    ${
        StringBuilder tmp = new StringBuilder()
        StringBuilder str

        for (int i = start; i < end; i++) {
            if (mutable) {
                str = new StringBuilder("""
    protected $typeName value%04d;""")
            } else {
                str = new StringBuilder("""
    protected final $typeName value%04d;""")
            }

            tmp.append(String.format(str.toString(), i))
        }

        tmp.append("""
        """)

        for (int i = start; i < end; i++) {
            str = new StringBuilder("""
    public final $typeName getValue%04d() {
        return value%04d;
    }
    """)
            tmp.append(String.format(str.toString(), i, i))
        }


        if (mutable) {
            for (int i = start; i < end; i++) {
                str = new StringBuilder("""
    public final void setValue%04d(final $typeName value%04d) {
        this.value%04d = value%04d;
    }
    """)
                tmp.append(String.format(str.toString(), i, i, i, i))
            }
        }

        return tmp.toString()
    }
    protected ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues + ${end - start}, values);
        final int len = values.length;

        switch (len) {
            default:
                if (len <= $start)
                    break;
            ${
                StringBuilder tmp = new StringBuilder()

                for (int i = end; i > start; i--) {
                    tmp.append(String.format("""
            case %d:
                this.value%04d = ArrayAccess.UNCHECKED.get(values, %d);
            """, i, i - 1, i - 1))
                }

                return tmp.toString()
            }
        }

        switch (len) {
            default:
                if (len > $end)
                    break;
            ${
                StringBuilder tmp = new StringBuilder()

                for (int i = start; i <= end; i++) {
                    tmp.append(String.format("""
                case %d:
                    this.value%04d = ${defaultValue};
                    """, i, i - 1))
                }

                return tmp.toString()
            }
        }
    }

    public static ${generic} ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix getInstance(final boolean checked, final $typeName ... values) {
        return new ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix$generic(checked, values) {
            ${
                StringBuilder put = new StringBuilder()
                if (mutable) {
                    put.append("""
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                    ${
                        StringBuilder tmp = new StringBuilder()
                        for (int i = 0; i < end; i++)
                            tmp.append(String.format("""
                    case %d:
                        value%04d = value;
                        break;
                    """, i, i)
                            )

                        return tmp.toString()
                    }
                    default:
                        putToRest(index, value);
                }
            }
            """
                    )
                }

                return put.toString()
            }

            @Override
            public final $typeName get(final int index) {
                switch (index) {
                ${
                    StringBuilder tmp = new StringBuilder()
                    for (int i = 0; i < end; i++)
                        tmp.append(String.format("""
                    case %d:
                        return value%04d;
                                """, i, i)
                        )

                    return tmp.toString()
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
    int limit = 13

    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\")
    p.mkdirs()

    File f = new File(p, "TabledArray.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(tableArrayBase())
    pw.flush()
    pw.close()

    for(boolean m : mutable) {
        String mutability = mutable ? "Mutable" : "Immutable"

        for (Class<?> type : types) {
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""

            p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\array${type}\\${m ? "mutable" : "immutable"}\\")
            p.mkdirs()

            f = new File(p, "${mutability}TabledArray${typeSuffix}.java")
            f.createNewFile()

            pw = f.newPrintWriter()
            pw.print(tabledArrayBase(m, type))
            pw.flush()
            pw.close()

            f = new File(p, "${mutability}TabledArray0000${typeSuffix}.java")
            f.createNewFile()

            pw = f.newPrintWriter()
            pw.print(tabledArray0(m, type))
            pw.flush()
            pw.close()

            f = new File(p, "${mutability}TabledArray0001${typeSuffix}.java")
            f.createNewFile()

            pw = f.newPrintWriter()
            pw.print(tabledArray1(m, type))
            pw.flush()
            pw.close()

            for (int i = 0; i < limit; i++) {
                int start = 2 ** i
                int end = 2 * start

                f = new File(p, "${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}.java")
                f.createNewFile()

                pw = f.newPrintWriter()
                pw.print(tabledArray(m, type, start))
                pw.flush()
                pw.close()
            }
        }
    }
}

tabledArrayGenAll()
