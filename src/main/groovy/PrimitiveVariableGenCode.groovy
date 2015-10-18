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

String genClass(boolean mutable, Class<?> type) {
    StringBuilder buffer = new StringBuilder("""
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

""")

    boolean isEnum = type.getSimpleName().contains("Enum")
    boolean isObject = type.equals(Object.class)
    boolean isBoolean = type.equals(Boolean.TYPE)
    String typeName = type.isPrimitive() ? type.getSimpleName() : (isEnum ? "Enum" : "T")
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : isEnum ? "Enum" : "Object"
    String genericClassSuffix = type.isPrimitive() ? "" : isEnum ? "<T extends Enum<T>>" : "<T>"
    String generic = type.isPrimitive() ? "" : "<T>"
    String packageName = mutable ? "mutable" : "immutable"
    String classPrefix = upcase1st(packageName)
    String comparativeOtherClassPrefix = upcase1st(!mutable ? "mutable" : "immutable")
    String finalValue = mutable ? "" : "final"
    String boxedTypeStatic = type.isPrimitive() ? (type.equals(Integer.TYPE) ? "Integer" : (type.equals(Character.TYPE) ? "Character" : upcase1st(type.getSimpleName()))) : isEnum ? "Enum" : "Object"
    String boxedType = type.isPrimitive() ? (type.equals(Integer.TYPE) ? "Integer" : (type.equals(Character.TYPE) ? "Character" : upcase1st(type.getSimpleName()))) : isEnum ? "Enum" : "Object"
    String equalsComparison = type.isPrimitive() ? "((${boxedType}) other)." + type.getSimpleName() + "Value() == value" : "other.equals(value)"

    buffer.append("""
package com.susico.utils.box.${packageName};

import java.util.Objects;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;
import com.susico.utils.box.*;
import com.susico.utils.box.immutable.*;
import com.susico.utils.box.mutable.*;

import org.jetbrains.annotations.*;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class ${classPrefix}${typeSuffix}${genericClassSuffix} extends Number
    implements BoxOnce<${classPrefix}${typeSuffix}${genericClassSuffix}> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = getFieldOffset(${classPrefix}${typeSuffix}.class, "value");

    protected static long getFieldOffset(final @NotNull Class<?> cls, final @NotNull String field) {
        try {
            return UNSAFE.objectFieldOffset(cls.getField(field));
        } catch (NoSuchFieldException e) {
            com.susico.utils.UncheckedExceptions.rethrow(e);
        }

        return 0;
    }

    /**
     * Value
     */
    private ${finalValue} ${typeName} value;

    /**
     * @param i Parameter
     */
    public ${classPrefix}${typeSuffix}(final @NotNull ${typeName} i) {
        value = i;
    }

    @Override
    public final ${classPrefix}${typeSuffix}${generic} clone() throws CloneNotSupportedException {
        return new ${classPrefix}${typeSuffix}${genericClassSuffix}(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    @Override
    public final int hashCode() {
        return ${boxedTypeStatic}.hashCode(value);
    }

    public final @NotNull ${typeName} getValue() {
        return (${typeName}) value;
    }

    public final @NotNull ${typeName} get() {
        return (${typeName}) value;
    }

    public final @NotNull $typeName getValueVolatile() {
        return (${typeName}) UNSAFE.get${typeSuffix}Volatile(this, valueFieldOffset);
    }
    """)

    if (mutable) {
        buffer.append("""
    public final void setValue(final @NotNull ${typeName} value) {
        this.value = value;
    }

    public final void set(final @NotNull ${typeName} value) {
        this.value = value;
    }

    public final void setValueVolatile(final @NotNull $typeName value) {
        UNSAFE.put${typeSuffix}Volatile(this, valueFieldOffset, value);
    }
""")

        if (type.equals(Float.TYPE) || type.equals(Double.TYPE) ||
                type.equals(Integer.TYPE) || type.equals(Long.TYPE) ||
                type.equals(Object.class)) {

            String valTransform = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                    type.equals(Double.TYPE) ? "Double.doubleToRawLongBits" :
                            type.equals(Float.TYPE) ? "Float.floatToRawIntBits" :
                                    ""
            String transformBack = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                    type.equals(Double.TYPE) ? "Double.longBitsToDouble" :
                            type.equals(Float.TYPE) ? "Float.intBitsToFloat" :
                                    ""
            String sameSizeNum = type.equals(Integer.TYPE) ? "Int" :
                    type.equals(Long.TYPE) ? "Long" :
                            type.equals(Float.TYPE) ? "Int" :
                                    type.equals(Double.TYPE) ? "Long" : "Object"

            buffer.append("""
    public final void setValueOrdered(
        final @NotNull $typeName value) {
            UNSAFE.putOrdered${sameSizeNum}(this, valueFieldOffset, ${valTransform}(value));
    }

    public final boolean compareAndSwapValue(final @NotNull $typeName expected,
        final @NotNull $typeName value) {
        return UNSAFE.compareAndSwap${sameSizeNum}(this,
            valueFieldOffset,
            ${valTransform}(expected), ${valTransform}(value));
    }

    public final $typeName getAndSetValue(
        final @NotNull $typeName value) {
        return ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(this,
            valueFieldOffset,
            ${valTransform}(value)));
    }
        """)

            String[] opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

            for (String opType : opTypes) {
                String valueParam = opType.startsWith("Multi") ?
                        ", final @NotNull $typeName ... value" :
                        opType.startsWith("Bi") ?
                                ", final @NotNull $typeName value" :
                                ""

                String applyOp = opType.startsWith("Multi") ?
                        "op.apply(current, value)" :
                        opType.startsWith("Bi") ?
                                "op.apply(current, value)" :
                                "op.apply(current)"

                buffer.append("""
    public final $typeName getAndUpdateValue(final @NotNull ${opType} op$valueParam) {
        $typeName current;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, valueFieldOffset,
            ${valTransform}(current), ${valTransform}(${applyOp})));
        return current;
    }

    public final $typeName updateAndGetValue(final @NotNull ${opType} op$valueParam) {
        $typeName current;
        $typeName newValue;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this, valueFieldOffset);
            newValue = ${applyOp};
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, valueFieldOffset,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
            }
        }
    }

    if (isObject) {
        buffer.append("""
    @Override
    public final boolean equals(Object other) {
        if (other instanceof ${classPrefix}${typeSuffix})
            return value.equals(((${classPrefix}${typeSuffix}${generic}) other).getValue());
        else if (other instanceof ${comparativeOtherClassPrefix}${typeSuffix})
            return value.equals(((${comparativeOtherClassPrefix}${typeSuffix}) other).getValue());
        else if (other instanceof ${boxedType})
            return ${equalsComparison};
        else
            return false;
    }""")
    } else {
        buffer.append("""
    @Override
    public final boolean equals(Object other) {
        if (other instanceof ${classPrefix}${typeSuffix})
            return value == ((${classPrefix}${typeSuffix}${generic}) other).getValue();
        else if (other instanceof ${comparativeOtherClassPrefix}${typeSuffix})
            return value == ((${comparativeOtherClassPrefix}${typeSuffix}) other).getValue();
        else if (other instanceof ${boxedType})
            return ${equalsComparison};
        else
            return false;
    }""")
    }

    if (isBoolean) {
        buffer.append("""
    @Override
    public final int compareTo(final @NotNull ${classPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ${comparativeOtherClassPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }
""")

        buffer.append("""
    // Boolean

    @Override
    public final int intValue() {
        return value ? 1 : 0;
    }

    @Override
    public final long longValue() {
        return value ? 1L : 0L;
    }

    @Override
    public final float floatValue() {
        return value ? 1.0F : 0.0F;
    }

    @Override
    public final double doubleValue() {
        return value ? 1.0D : 0.0D;
    }
""")
    } else if (isEnum) {
        buffer.append("""
    // Enum

    @Override
    public final int intValue() {
        return (int) value.ordinal();
    }

    @Override
    public final long longValue() {
        return (long) value.ordinal();
    }

    @Override
    public final float floatValue() {
        return (float) value.ordinal();
    }

    @Override
    public final double doubleValue() {
        return (double) value.ordinal();
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }
""")
    } else if (isObject) {
        buffer.append("""
    @Override
    public final int compareTo(final @NotNull ${classPrefix}${typeSuffix}${generic} other) {
        final @NotNull ${typeName} otherValue = other.getValue;
        if (value instanceof Comparable)
            return value.compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - otherValue.compareTo(value);
        else
            throw IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }

    public final int compareTo(final @NotNull ${comparativeOtherClassPrefix}${typeSuffix}${generic} other) {
        final @NotNull ${typeName} otherValue = other.getValue;
        if (value instanceof Comparable)
            return value.compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - otherValue.compareTo(value);
        else
            throw IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }
""")
    } else {
        buffer.append("""
    @Override
    public final int compareTo(final @NotNull ${classPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }

    public final int compareTo(final @NotNull ${comparativeOtherClassPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (value < other.getValue() ? -1 : 1);
    }
""")

        buffer.append("""
    // Others

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return (long) value;
    }

    @Override
    public final float floatValue() {
        return (float) value;
    }

    @Override
    public final double doubleValue() {
        return (double) value;
    }
""")
    }

    return buffer.append("}").toString()
}

void genClass() {
    boolean[] mutables = [false, true]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class, Enum.class]

    File b = new File(".\\..\\java\\com\\susico\\utils\\box\\")
    b.mkdirs()

    for (boolean mutable : mutables) {
        String packageName = mutable ? "mutable" : "immutable"

        File p = new File(b, "${packageName}\\")
        p.mkdirs()

        for (Class<?> type : types) {
            boolean isEnum = type.getSimpleName().contains("Enum")
            String classPrefix = upcase1st(packageName)
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : isEnum ? "Enum" : "Object"

            File f = new File(p, "${classPrefix}${typeSuffix}.java")
            f.createNewFile()

            PrintWriter pw = f.newPrintWriter()
            pw.print(genClass(mutable, type))
            pw.flush()
            pw.close()
        }
    }
}

genClass()