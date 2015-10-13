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

import com.susico.utils.functions.*;

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
    public static $generic ${mutability}TabledArray${typeSuffix}$generic get${mutability}${typeSuffix}Array(
        final boolean checked, final int length, final $typeName ... values) {
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

import com.susico.utils.functions.*;

public abstract class MutableTabledArray$typeSuffix$generic extends ImmutableTabledArray$typeSuffix$generic {
    protected MutableTabledArray$typeSuffix(final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }

    protected final void putToRest(final int index, final $typeName value) {
        ARRAY_ACCESS.put(index - definedAsValues, rest, value);
    }

    protected final void putVolatileToRest(final int index, final $typeName value) {
        ARRAY_ACCESS.putVolatile(index - definedAsValues, rest, value);
    }

    public abstract void put(final int index, final $typeName value);

    public abstract void putUnsafe(final int index, final $typeName value);

    public abstract void putVolatile(final int index, final $typeName value);
""")

    if (type.equals(Object.class) || type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
            type.equals(Integer.TYPE) || type.equals(Long.TYPE)) {
            str.append("""
    protected final void putOrderedToRest(final int index, final $typeName value) {
        ARRAY_ACCESS.putOrdered(index - definedAsValues, rest, value);
    }

    protected final void getAndSetToRest(final int index, final $typeName value) {
        ARRAY_ACCESS.putOrdered(index - definedAsValues, rest, value);
    }

    public abstract void putOrdered(final int index, final $typeName value);

    public abstract void getAndSet(final int index, final $typeName value);
""")
        }

    if (type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
            type.equals(Integer.TYPE) || type.equals(Long.TYPE)) {
            str.append("""
            public abstract void compairAndSwap(final int index, final $typeName value);

            public abstract void updateAndGet(final int index, final $typeName value);

            public abstract void getAndUpdate(final int index, final $typeName value);
""")
        }
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

import com.susico.utils.functions.*;

public abstract class ImmutableTabledArray$typeSuffix$generic extends TabledArray {
    @FunctionalInterface
    public static interface UnaryOp${typeSuffix} {
        $typeName apply($typeName x);
    }

    @FunctionalInterface
    public static interface BiOp${typeSuffix} {
        $typeName apply($typeName x, $typeName y);
    }

    @FunctionalInterface
    public static interface MultiOp${typeSuffix} {
        $typeName apply($typeName x, $typeName ... values);
    }

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

    @Override
    public final int getActualLength() {
        return actualLength;
    }

    protected final $typeName getFromRest(final int index) {
        return ($typeName) ARRAY_ACCESS.get(index - definedAsValues, rest);
    }

    protected final $typeName getVolatileFromRest(final int index) {
        return ($typeName) ARRAY_ACCESS.getVolatile(index - definedAsValues, rest);
    }

    public abstract $typeName get(final int index);

    public abstract $typeName getUnsafe(final int index);

    public abstract $typeName getVolatile(final int index);
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
        String formatEnd = String.format("%04d", end)

        str.append("""
                return ${mutability}TabledArray${formatEnd}${typeSuffix}.${generic}getInstance(checked, length, values);

"""
        )
    }
    String formatLimit = String.format("%04d", 2**limit)

    str.append("""
            default:
                return ${mutability}TabledArray${formatLimit}${typeSuffix}.getInstance(checked, length, values);
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

import com.susico.utils.functions.*;

public abstract class MutableTabledArray0000${typeSuffix}${generic} extends MutableTabledArray${typeSuffix}${generic} {
    protected MutableTabledArray0000${typeSuffix}(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected MutableTabledArray0000${typeSuffix}(
        final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static ${generic} MutableTabledArray0000${typeSuffix}${generic} getInstance(
            final boolean checked, final int length, final $typeName ... values) {
        return new MutableTabledArray0000${typeSuffix}${generic}(checked, length, values) {
            @Override
            public final void put(final int index, final $typeName value) {
                putToRest(index, value);
            }

            public final void putVolatile(final int index, final $typeName value) {
                putVolatileToRest(index, value);
            }

            @Override
            public final $typeName get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final $typeName getVolatile(final int index) {
                return getVolatileFromRest(index);
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

import com.susico.utils.functions.*;

public abstract class ImmutableTabledArray0000${typeSuffix}${generic}
    extends ImmutableTabledArray${typeSuffix}${generic} {
    protected ImmutableTabledArray0000${typeSuffix}(final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ImmutableTabledArray0000${typeSuffix}(
        final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues, length, values);
    }

    public static ${generic} ImmutableTabledArray0000${typeSuffix}${generic} getInstance(
        final boolean checked, final int length, final $typeName ... values) {
        return new ImmutableTabledArray0000${typeSuffix}${generic}(checked, length, values) {
            @Override
            public final $typeName get(final int index) {
                return getFromRest(index);
            }

            @Override
            public final $typeName getVolatile(final int index) {
                return getVolatileFromRest(index);
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

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;

public abstract class ${mutability}TabledArray0001$typeSuffix$generic extends
    ${mutability}TabledArray0000$typeSuffix$generic {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    ${
        StringBuilder tmp = new StringBuilder()

        tmp.append("""
    protected final static value0000FieldOffset =
        UNSAFE.objectFieldOffset(${mutability}TabledArray0001${typeSuffix}.class.getField("value0000"));""")

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

    public final $typeName getValue0000Volatile() {
        return UNSAFE.get${typeSuffix}Volatile(this, value0000FieldOffset);
    }

    public final $typeName getValue0000Unsafe() {
        return UNSAFE.get${typeSuffix}(this, value0000FieldOffset);
    }
    """)


        if (mutable) {
            tmp.append("""
    public final void setValue0000(final $typeName value0000) {
        this.value0000 = value0000;
    }

    public final void putValue0000Volatile(final $typeName value0000) {
        return UNSAFE.put${typeSuffix}Volatile(this, value0000FieldOffset, value0000);
    }

    public final void putValue0000Unsafe(final $typeName value0000) {
        return UNSAFE.put${typeSuffix}(this, value0000FieldOffset, value0000);
    }
    """)

            if (type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
                    type.equals(Integer.TYPE) || type.equals(Long.TYPE) ||
                    type.equals(Object.class)) {

                String valTransform = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                        type.equals(Double.TYPE) ? "Double.doubleToRawLongBits" : "Float.floatToRawIntBits"
                String transformBack = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                        type.equals(Double.TYPE) ? "Double.longBitsToDouble" : "Float.intBitsToFloat"
                String sameSizeNum = type.equals(Integer.TYPE) ? "Int" :
                        type.equals(Long.TYPE) ? "Long" :
                                type.equals(Float.TYPE) ? "Int" :
                                        type.equals(Double.TYPE) ? "Long" : "Object"

                tmp.append("""
    public static $generic $typeName[] putValue0000Ordered(
        final $typeName value0000) {
            UNSAFE.putOrdered${sameSizeNum}(this, value0000FieldOffset, ${valTransform}(value0000));

        return buffer;
    }

    public final $generic boolean compareAndSwapValue0000(final $typeName expected,
        final $typeName value0000) {
        return UNSAFE.compareAndSwap${sameSizeNum}(this,
            value0000FieldOffset,
            ${valTransform}(expected), ${valTransform}(value0000));
    }

    public static $generic $typeName getAndSetValue0000(
        final $typeName value0000) {
        return ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(this,
            value0000FieldOffset,
            ${valTransform}(value0000)));
    }
        """)

                if (!type.equals(Object.class)) {
                    String[] opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

                    for (String opType : opTypes) {
                        String valueTypeName = opType.startsWith("Multi") ? "$typeName ... " : typeName

                        tmp.append("""
    public static $generic $typeName getAndUpdateValue0000(final ${opType} op, final $valueTypeName value0000) {
        $typeName current;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, value0000FieldOffset,
            ${valTransform}(current), ${valTransform}(op.apply(current, value0000))));
        return current;
    }

    public static $generic $typeName updateAndGetValue0000(final ${opType} op, final $valueTypeName value0000) {
        $typeName current;
        $typeName newValue;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this, value0000FieldOffset);
            newValue = op.apply(current, value0000);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, value0000FieldOffset,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
                    }
                }
            }
        }

        return tmp.toString()
    }
    protected ${mutability}TabledArray0001$typeSuffix(
        final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ${mutability}TabledArray0001$typeSuffix(
        final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues + 1, length, values);

        if (values.length >= 1) {
            this.value0000 = ArrayAccess.UNCHECKED.get(0, values);
        } else {
            this.value0000 = ${defaultValue};
        }

    }

    public static ${generic} ${mutability}TabledArray0001$typeSuffix getInstance(
        final boolean checked, final int length, final $typeName ... values) {
        return new ${mutability}TabledArray0001$typeSuffix$generic(checked, length, values) {
    ${
        StringBuilder put = new StringBuilder()

        if (mutable) {
            put.append("""
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                """)

            put.append("""
                    case 0:
                        setValue0000(value);
                        break;
                    """)

            put.append("""
                    default:
                        putToRest(index, value);
                }
            }
            """)

            put.append("""
            @Override
            public final void putUnsafe(final int index, final $typeName value) {
                switch (index) {
                """)

            put.append("""
                    case 0:
                        putValue0000Unsafe(value);
                        break;
                    """)

            put.append("""
                    default:
                        putToRest(index, value);
                }
            }
            """)

            put.append("""
            @Override
            public final void putVolatile(final int index, final $typeName value) {
                switch (index) {
                """)

            put.append("""
                    case 0:
                        putValue0000Volatile(value);
                        break;
                    """)

            put.append("""
                    default:
                        putVolatileToRest(index, value);
                }
            }
            """)
        }

        return put.toString()
    }
    ${
        StringBuilder get = new StringBuilder()
        get.append("""
            @Override
            public final $typeName get(final int index) {
                switch (index) {
        """)

        get.append("""
                    case 0:
                        return getValue0000();
                                """)

        get.append("""
                    default:
                        return getFromRest(index);
                }
            }
            """)

        get.append("""
            @Override
            public final $typeName getUnsafe(final int index) {
                switch (index) {
        """)

        get.append("""
                    case 0:
                        return getValue0000Unsafe();
                                """)

        get.append("""
                    default:
                        return getFromRest(index);
                }
            }
            """)

        get.append("""
            @Override
            public final $typeName getVolatile(final int index) {
                switch (index) {
        """)

        get.append("""
                    case 0:
                        return getValue0000Volatile();
                                """)

        get.append("""
                    default:
                        return getVolatileFromRest(index);
                }
            }
            """)

        return get.toString()
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
    String classEnding = String.format("%04d", end)
    String classStart = String.format("%04d", end)


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

import com.susico.utils.functions.*;

public abstract class ${mutability}TabledArray${classEnding}$typeSuffix$generic extends
    ${mutability}TabledArray${classStart}$typeSuffix$generic {
    ${
        StringBuilder tmp = new StringBuilder()

        for (int i = start; i < end; i++) {
            String formatI = String.format("%04d", i)
            tmp.append("""
    protected final static value%04dFieldOffset =
        UNSAFE.objectFieldOffset(${mutability}TabledArray${classEnding}${typeSuffix}.class.getField("value%04d"));""")

            if (mutable) {
                tmp.append("""
    protected $typeName value${formatI};""")
            } else {
                tmp.append("""
    protected final $typeName value${formatI};""")
            }
        }

        tmp.append("""
        """)

        for (int i = start; i < end; i++) {
            String formatI = String.format("%04d", i)

            tmp.append("""
    public final $typeName getValue${formatI}() {
        return value${formatI};
    }

    public final $typeName getValue${formatI}Unsafe() {
        return UNSAFE.get${typeSuffix}(this, value${formatI}FieldOffset);
    }

    public final $typeName getValue${formatI}Volatile() {
        return UNSAFE.get${typeSuffix}Volatile(this, value${formatI}FieldOffset);
    }
    """)
        }


        if (mutable) {
            for (int i = start; i < end; i++) {
                String formatI = String.format("%04d", i)

                tmp.append("""
    public final void setValue${formatI}(final $typeName value${formatI}) {
        this.value${formatI} = value${formatI};
    }

    public final void putValue${formatI}Unsafe(final $typeName value${formatI}) {
        return UNSAFE.put${typeSuffix}(this, value${formatI}FieldOffset, value${formatI});
    }

    public final void putValue${formatI}Volatile(final $typeName value${formatI}) {
        return UNSAFE.put${typeSuffix}Volatile(this, value${formatI}FieldOffset, value${formatI});
    }
    """)
                if (type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
                        type.equals(Integer.TYPE) || type.equals(Long.TYPE) ||
                        type.equals(Object.class)) {

                    String valTransform = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                            type.equals(Double.TYPE) ? "Double.doubleToRawLongBits" : "Float.floatToRawIntBits"
                    String transformBack = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                            type.equals(Double.TYPE) ? "Double.longBitsToDouble" : "Float.intBitsToFloat"
                    String sameSizeNum = type.equals(Integer.TYPE) ? "Int" :
                            type.equals(Long.TYPE) ? "Long" :
                                    type.equals(Float.TYPE) ? "Int" :
                                            type.equals(Double.TYPE) ? "Long" : "Object"

                    tmp.append("""
    public static $generic $typeName[] putValue${formatI}Ordered(
        final $typeName value${formatI}) {
            UNSAFE.putOrdered${sameSizeNum}(this, value${formatI}FieldOffset, ${valTransform}(value${formatI}));

        return buffer;
    }

    public final $generic boolean compareAndSwapValue${formatI}(final $typeName expected,
        final $typeName value${formatI}) {
        return UNSAFE.compareAndSwap${sameSizeNum}(this,
            value${formatI}FieldOffset,
            ${valTransform}(expected), ${valTransform}(value${formatI}));
    }

    public static $generic $typeName getAndSetValue${formatI}(
        final $typeName value${formatI}) {
        return ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(this,
            value0000FieldOffset,
            ${valTransform}(value${formatI})));
    }
        """)

                    if (!type.equals(Object.class)) {
                        String[] opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

                        for (String opType : opTypes) {
                            String valueTypeName = opType.startsWith("Multi") ? "$typeName ... " : typeName

                            tmp.append("""
    public static $generic $typeName getAndUpdateValue${formatI}(final ${opType} op, final $valueTypeName value${formatI}) {
        $typeName current;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this,
                value${formatI}FieldOffset);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, value${formatI}FieldOffset,
            ${valTransform}(current), ${valTransform}(op.apply(current, value${formatI}))));
        return current;
    }

    public static $generic $typeName updateAndGetValue${formatI}(final ${opType} op, final $valueTypeName value${formatI}) {
        $typeName current;
        $typeName newValue;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this, value${formatI}FieldOffset);
            newValue = op.apply(current, value${formatI});
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, value${formatI}FieldOffset,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
                        }
                    }
                }
            }
        }

        return tmp.toString()
    }
    protected ${mutability}TabledArray${classEnding}$typeSuffix(
        final boolean checked, final int length, final $typeName ... values) {
        this(checked, 0, length, values);
    }

    protected ${mutability}TabledArray${classEnding}$typeSuffix(
        final boolean checked, final int definedAsValues, final int length, final $typeName ... values) {
        super(checked, definedAsValues + ${end - start}, length, values);
        final int len = values.length;

        ${
        StringBuilder tmp = new StringBuilder()

        for (int i = end; i > start; i--) {
            String formatI = String.format("%04d", i)

            tmp.append("""
        if (len >= ${formatI}) {
            this.value${formatI} = ArrayAccess.UNCHECKED.get(${formatI}, values);
        } else {
            this.value${formatI} = ${defaultValue};
        }
            """)
        }

        return tmp.toString()
    }
    }

    public static ${generic} ${mutability}TabledArray${classEnding}${typeSuffix}${generic} getInstance(
        final boolean checked, final int length, final $typeName ... values) {
        return new ${mutability}TabledArray${classEnding}$typeSuffix$generic(checked, length, values) {
    ${
        StringBuilder put = new StringBuilder()
        if (mutable) {
            put.append("""
            @Override
            public final void put(final int index, final $typeName value) {
                switch (index) {
                    """)

            for (int i = 0; i < end; i++) {
                String formatI = String.format("%04d", i)
                put.append("""
                    case ${formatI}:
                        putValue${formatI}(value);
                        break;
                    """)
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
    ${
        StringBuilder get = new StringBuilder()
        get.append("""
            @Override
            public final $typeName get(final int index) {
                switch (index) {
                """)

            for (int i = 0; i < end; i++) {
                String formatI = String.format("%04d", i)

                get.append("""
                    case ${formatI}:
                        return getValue${formatI}();
                                """)
            }

        get.append("""
                    default:
                        return getFromRest(index);
                }
            }""")

        return get.toString()
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
