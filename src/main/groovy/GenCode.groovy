import java.nio.file.Path

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
    if (str == null || str.length() == 0)
        return ""

    return str.substring(0, 1).toUpperCase() + str.substring(1)
}


String tabledArrayBase(boolean mutable, Class<?> type) {
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

import com.susico.utils.arrays.tabled.array${type.getSimpleName()}.immutable.ImmutableTabledArray$typeSuffix;

public abstract class MutableTabledArray$typeSuffix$generic extends ImmutableTabledArray$typeSuffix$generic {
    protected MutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values);
    }


    public abstract void put(int index, $typeName value);

    protected final void putToRest(int index, $typeName value) {
        ARRAY_ACCESS.put(rest, index - definedAsValues, value);
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
}
"""
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

public abstract class MutableTabledArray${typeSuffix}0000${generic} extends MutableTabledArray${typeSuffix}${generic} {
    protected MutableTabledArray${typeSuffix}0000(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected MutableTabledArray${typeSuffix}0000(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values);
    }

    public static MutableTabledArray${typeSuffix}0000${generic} getInstance(final boolean checked, final $typeName ... values) {
        return new MutableTabledArray${typeSuffix}0000${generic}(checked, values) {
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

public abstract class ImmutableTabledArray${typeSuffix}0000${generic} extends ImmutableTabledArray${typeSuffix}${generic} {
    protected ImmutableTabledArray${typeSuffix}0000(final boolean checked, final $typeName ... values) {
        this(checked, 0, values);
    }

    protected ImmutableTabledArray${typeSuffix}0000(final boolean checked, final int definedAsValues, final $typeName ... values) {
        super(checked, definedAsValues, values.length);
    }

    public static ImmutableTabledArray${typeSuffix}0000${generic} getInstance(final boolean checked, final $typeName ... values) {
        return new ImmutableTabledArray${typeSuffix}0000${generic}(checked, values) {
            @Override
            public final $typeName get(final int index) {
                return getFromRest(index);
            }
        };
    }
}

"""
}

String tabledArray(boolean mutable, Class<?> type, int start, int inc) {
    String mutability = mutable ? "Mutable" : "Immutable"
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
    String generic = type.isPrimitive() ? "" : "<T>"
    int end = start + inc

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
        return new ${mutability}TabledArray$end$typeSuffix$generic(checked, values) {
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

String tableArrayFactories() {
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
    int limit = 1024
    int inc = 8

    for(boolean m : mutable) {
        String mutability = mutable ? "Mutable" : "Immutable"

        for (Class<?> type : types) {
            String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : ""
            String generic = type.isPrimitive() ? "" : "<T>"

            tmp.append("""
        public static $generic ${mutable}TabledArray${typeSuffix}$generic get${typeSuffix}Array(final boolean checked, final int length, final $typeName ... values) {
            switch (index) {
                case 0:
                    return ${mutable}TabledArray0000${typeSuffix}.getInstance(checked, values);

"""
            )

            for (int i = 0; i < limit; i++) {
                int start = i * inc
                int end = start + inc

                for (int j = start + 1; j <= end; j++) {
                    tmp.append("""
                case $j:
"""
                    )
                }

                tmp.append("""
                    return ${mutable}TabledArray${String.format("%04d", end)}${typeSuffix}.getInstance(checked, values);

"""
                )
            }

            tmp.append("""
                default:
                    return ${mutable}TabledArray${String.format("%04d", limit * inc)}${typeSuffix}.getInstance(checked, values);
            }
        }
"""
            )
        }
    }

    return tmp.append("}").toString()
}

void tabledArrayGenAll() {
    boolean[] mutable = [true, false]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]
    int limit = 1024
    int inc = 8

    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\tabled\\")
    p.mkdirs()

    File f = new File(p, "TabledArray.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(tableArrayFactories())
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

            for (int i = 0; i < limit; i++) {
                int start = i * inc
                int end = start + inc

                f = new File(p, "${mutability}TabledArray${String.format("%04d", end)}${typeSuffix}.java")
                f.createNewFile()

                pw = f.newPrintWriter()
                pw.print(tabledArray(m, type, i * inc, inc))
                pw.flush()
                pw.close()
            }
        }
    }
}

tabledArrayGenAll()
