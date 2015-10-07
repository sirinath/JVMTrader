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
    StringBuffer tmp = new StringBuffer("""// Auto generated. Do not edit directly!
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

import com.susico.utils.arrays.ArrayAccess;

""")

    StringBuffer theImports = new StringBuffer()
    StringBuffer str = new StringBuffer("""public abstract class TabledArray {
    protected final ArrayAccess ARRAY_ACCESS;
    protected final int targetLength;
    protected final int definedAsValues;

    protected TabledArray(final boolean checked, final int definedAsValues, final int length) {
        this.ARRAY_ACCESS = ArrayAccess.checked(checked);
        this.targetLength = length;
        this.definedAsValues = definedAsValues;
    }

    public final int getDefinedAsValues() {
        return definedAsValues;
    }

    public final int getTargetLength() {
        return targetLength;
    }

    public abstract int getActualLength();
""");

    boolean[] mutable = [false, true]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]

    for (boolean m : mutable) {
        String mutability = m ? "Mutable" : "Immutable"

        for (Class<?> type : types) {
            String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
            String packageName = type.isPrimitive() ? type.getSimpleName() : ""
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
            String generic = type.isPrimitive() ? "" : "<T>"

            theImports.append("import com.susico.utils.arrays.tabled.array${packageName}.${mutability.toLowerCase()}.${mutability}TabledArray${typeSuffix};\n")

            str.append("""
    public static $generic ${mutability}TabledArray${typeSuffix}$generic get${mutability}${
                typeSuffix
            }Array(final boolean checked, final int length, final $typeName ... values) {
        return ${mutability}TabledArray${typeSuffix}.${generic}getInstance(checked, length, values);
    }
""")
        }

        theImports.append("\n")
    }

    tmp.append(theImports).append("\n").append(str)

    return tmp.append("}").toString()
}

String tabledArrayBase(boolean mutable, Class<?> type) {
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String packageName = type.isPrimitive() ? type.getSimpleName() : ""
    String erasedType = type.isPrimitive() ? type.getSimpleName() : "Object"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    StringBuffer str = new StringBuffer()
    int limit = 10

    if (mutable)
        str.append("""// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.mutable;

import com.susico.utils.arrays.tabled.array${packageName}.immutable.ImmutableTabledArray$typeSuffix;

public abstract class MutableTabledArray$typeSuffix$generic extends ImmutableTabledArray$typeSuffix$generic {
    protected MutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }


    public abstract void put(final int index, final $typeName value);

    protected final void putToRest(final int index, final $typeName value) {
        ARRAY_ACCESS.put(index - definedAsValues, rest, value);
    }
""")
    else
        str.append("""// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.immutable;

import com.susico.utils.arrays.tabled.TabledArray;

public abstract class ImmutableTabledArray$typeSuffix$generic extends TabledArray {
    protected final $erasedType[] rest;
    protected final int actualLength;

    protected ImmutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length);

        final int effectiveLength = Math.max(length, values.length)
        rest = new $erasedType[effectiveLength > definedAsValues ? effectiveLength - definedAsValues : 0];

        final int copyLength = values.length - definedAsValues
        if (copyLength > 0)
            System.arraycopy(values, definedAsValues, rest, 0, copyLength);

        actualLength = definedAsValues + rest.length;
    }

    public abstract $typeName get(final int index);

    protected final $typeName getFromRest(final int index) {
        return ($typeName) ARRAY_ACCESS.get(index - definedAsValues, rest);
    }

    @Override
    public final int getActualLength() {
        return actualLength;
    }
""")

    String mutability = mutable ? "Mutable" : "Immutable"

    str.append("""
    public static $generic ${mutability}TabledArray${
        typeSuffix
    }$generic getInstance(final boolean checked, final int length, final $typeName ... values) {
        switch (length) {
            case 0:
                return ${mutability}TabledArray0000${typeSuffix}.${generic}getInstance(checked, length, values);
            case 1:
                return ${mutability}TabledArray0001${typeSuffix}.${generic}getInstance(checked, length, values);
"""
    )

    for (int i = 0; i < limit; i++) {
        int start = 2**i
        int end = 2 * start

        str.append("            ")

        for (int j = start + 1; j <= end; j++) {
            if (j % 16 == 0) {
                str.append("\n            ").append("case $j: ")
            } else {
                str.append("case $j: ")
            }
        }

        str.append("""
                return ${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}.${generic}getInstance(checked, length, values);

"""
        )
    }

    str.append("""
            default:
                return ${mutability}TabledArray${String.format("%04d", 2**limit)}${typeSuffix}.getInstance(checked, length, values);
        }
    }
""")

    return str.append("}").toString()
}

String tabledArray0(boolean mutable, Class<?> type) {
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String packageName = type.isPrimitive() ? type.getSimpleName() : ""
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"

    if (mutable)
        return """// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.mutable;

public abstract class MutableTabledArray0000${typeSuffix}${generic} extends MutableTabledArray${typeSuffix}${generic} {
    protected MutableTabledArray0000${typeSuffix}(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000${
            typeSuffix
        }(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static ${generic} MutableTabledArray0000${typeSuffix}${
            generic
        } getInstance(final boolean checked, final int length, final $typeName ... values) {
        return new MutableTabledArray0000${typeSuffix}${generic}(checked, length, values) {
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
        return """// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.immutable;

public abstract class ImmutableTabledArray0000${typeSuffix}${generic} extends ImmutableTabledArray${typeSuffix}${
            generic
        } {
    protected ImmutableTabledArray0000${typeSuffix}(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0000${
            typeSuffix
        }(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static ${generic} ImmutableTabledArray0000${typeSuffix}${
            generic
        } getInstance(final boolean checked, final int length, final $typeName ... values) {
        return new ImmutableTabledArray0000${typeSuffix}${generic}(checked, length, values) {
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
    String packageName = type.isPrimitive() ? type.getSimpleName() : ""
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    String defaultValue = type.equals(Boolean.TYPE) ? "false" : (type.isPrimitive() ? (type.equals(Float.TYPE) ? "Float.NaN" : (type.equals(Double.TYPE) ? "Double.NaN" : "0")) : "null")

    return """// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.${mutability.toLowerCase()};

import com.susico.utils.arrays.ArrayAccess;

public abstract class ${mutability}TabledArray0001$typeSuffix$generic extends ${
        mutability
    }TabledArray0000$typeSuffix$generic {
    ${
        StringBuilder tmp = new StringBuilder()

        if (mutable) {
            tmp.append("""
    protected $typeName value0000;""")
        } else {
            tmp.append("""
    protected final $typeName value0000;""")
        }


        tmp.append("""
    public final $typeName getValue0000() {
        return value0000;
    }
    """)


        if (mutable) {
            tmp.append("""
    public final void setValue0000(final $typeName value0000) {
        this.value0000 = value0000;
    }
    """)
        }

        return tmp.toString()
    }
    protected ${mutability}TabledArray0001$typeSuffix(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ${
        mutability
    }TabledArray0001$typeSuffix(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = ${defaultValue};
        }

    }

    public static ${generic} ${
        mutability
    }TabledArray0001$typeSuffix getInstance(final boolean checked, final int length, final $typeName ... values) {
        return new ${mutability}TabledArray0001$typeSuffix$generic(checked, length, values) {
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
    String packageName = type.isPrimitive() ? type.getSimpleName() : ""
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    int end = 2 * start
    String defaultValue = type.equals(Boolean.TYPE) ? "false" : (type.isPrimitive() ? (type.equals(Float.TYPE) ? "Float.NaN" : (type.equals(Double.TYPE) ? "Double.NaN" : "0")) : "null")

    return """ // Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled.array${packageName}.${mutability.toLowerCase()};

import com.susico.utils.arrays.ArrayAccess;

public abstract class ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix$generic extends ${
        mutability
    }TabledArray${String.format("%04d", start)}$typeSuffix$generic {
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
    protected ${mutability}TabledArray${
        String.format("%04d", end)
    }$typeSuffix(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ${mutability}TabledArray${
        String.format("%04d", end)
    }$typeSuffix(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues + ${end - start}, length, values);
        final int len = values.length;

        ${
        StringBuilder tmp = new StringBuilder()

        for (int i = end; i > start; i--) {
            tmp.append(String.format("""
        if (len >= %d) {
            this.value%04d = ArrayAccess.UNCHECKED.get(%d, values);
        } else {
            this.value%04d = ${defaultValue};
        }
            """, i, i - 1, i - 1, i - 1))
        }

        return tmp.toString()
    }
    }

    public static ${generic} ${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}${
        generic
    } getInstance(final boolean checked, final int length, final $typeName ... values) {
        return new ${mutability}TabledArray${String.format("%04d", end)}$typeSuffix$generic(checked, length, values) {
            ${
        StringBuilder put = new StringBuilder()
        if (mutable) {
            put.append("""
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                    """)

            for (int i = 0; i < end; i++) {
                put.append(String.format("""
                    case %d:
                        value%04d = value;
                        break;
                    """, i, i))
            }

            put.append("""
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
    boolean[] mutable = [false, true]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]
    int limit = 10

    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\")
    p.mkdirs()

    File f = new File(p, "TabledArray.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(tableArrayBase())
    pw.flush()
    pw.close()

    for (boolean m : mutable) {
        String mutability = m ? "Mutable" : "Immutable"

        for (Class<?> type : types) {
            String packageName = type.isPrimitive() ? type.getSimpleName() : ""
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""

            p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\array${packageName}\\${m ? "mutable" : "immutable"}\\")
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
                int start = 2**i
                int end = 2 * start

                f = new File(p, "${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}.java")
                f.createNewFile()

                pw = f.newPrintWriter()
                pw.print(tabledArray(m, type, start))
                pw.flush()
                pw.close()

                System.gc()
            }
        }
    }
}

tabledArrayGenAll()
