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
    StringBuilder buffer = new StringBuffer("""
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

    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : type.isEnum() ? "Enum" : "Reference"
    String genericClassSuffix = type.isPrimitive() ? "" : type.isEnum() ? "<T extends Enum<T>>" : "<T>"
    String generic = type.isPrimitive() ? "" : "<T>"
    String packageName = mutable ? "mutable" : "immutable"
    String classPrefix = upcase1st(packageName)
    String comparativeOtherClassPrefix = upcase1st(!mutable ? "mutable" : "immutable")
    String finalValue = mutable ? "final" : ""
    String boxedTypeStatic = type.isPrimitive() ? (type.equals(Integer.TYPE) ? "Integer" : upcase1st(type.getSimpleName())) : "Objects"
    String boxedType = type.isPrimitive() ? (type.equals(Integer.TYPE) ? "Integer" : upcase1st(type.getSimpleName())) : type.isEnum() ? "Enum" : "Object"
    String equalsComparison = type.isPrimitive() ? "(${boxedType} other)." + type.getSimpleName() + "Value()" : "other.equals(value)"
    boolean isObject = type.equals(Object.class)
    boolean isBoolean = type.equals(Boolean.TYPE)
    boolean isEnum = type.isEnum()

    buffer.append("""
package com.susico.utils.primitives.${packageName};

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class ${classPrefix}${typeSuffix}${genericClassSuffix} extends Number implements BoxOnce<PV${typeSuffix}${generic}> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    @FunctionalInterface
    public static interface BiOp${typeSuffix} {
        $typeName apply($typeName x, $typeName y);
    }

    @FunctionalInterface
    public static interface MultiOp${typeSuffix} {
        $typeName apply($typeName x, $typeName ... values);
    }

    protected final static valueFieldOffset =
        UNSAFE.objectFieldOffset(${classPrefix}${typeSuffix}.class.getField("value"));

    /**
     * Value
     */
    private ${finalValue} ${typeName} value;

    /**
     * @param i Parameter
     */
    public ${classPrefix}${typeSuffix}(final ${typeName} i) {
        value = i;
    }

    @Override
    public final ${classPrefix}${typeSuffix}${generic} clone() throws CloneNotSupportedException {
        return new PV${typeSuffix}${generic}(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    @Override
    public final int hashCode() {
        return ${boxedTypeStatic}.hashCode(value);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof ${classPrefix}${typeSuffix}${generic})
            return value == ((${classPrefix}${typeSuffix}${generic}) other).getValue();
        else if (other instanceof ${comparativeOtherClassPrefix}${typeSuffix}${generic})
            return value == ((${comparativeOtherClassPrefix}${typeSuffix}${generic}) other).getValue();
        else if (other instanceof ${boxedType})
            return ${equalsComparison};
        else
            return false;
    }

    public final ${typeName} getValue() {
        return value;
    }

    public final ${typeName} get() {
        return value;
    }

    public final $typeName getValueVolatile() {
        return UNSAFE.get${typeSuffix}Volatile(this, valueFieldOffset);
    }
    """)

    if (mutable) {
        buffer.append("""
    public final void setValue(final ${typeName} value) {
        this.value = value;
    }

    public final void set(final ${typeName} value) {
        this.value = value;
    }

    public final void setValueVolatile(final $typeName value) {
        return UNSAFE.put${typeSuffix}Volatile(this, valueFieldOffset, value);
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

            buffer.append("""
    public static $generic $typeName[] setValueOrdered(
        final $typeName value) {
            UNSAFE.putOrdered${sameSizeNum}(this, valueFieldOffset, ${valTransform}(value));

        return buffer;
    }

    public final $generic boolean compareAndSwapValue(final $typeName expected,
        final $typeName value) {
        return UNSAFE.compareAndSwap${sameSizeNum}(this,
            valueFieldOffset,
            ${valTransform}(expected), ${valTransform}(value));
    }

    public static $generic $typeName getAndSetValue(
        final $typeName value) {
        return ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(this,
            valueFieldOffset,
            ${valTransform}(value)));
    }
        """)

            if (!type.equals(Object.class)) {
                String[] opTypes = ["BiOp${typeSuffix}", "UnaryOp${typeSuffix}", "MultiOp${typeSuffix}"]

                for (String opType : opTypes) {
                    String valueTypeName = opType.startsWith("Multi") ? "$typeName ... " : typeName

                    buffer.append("""
    public static $generic $typeName getAndUpdateValue(final ${opType} op, final $valueTypeName value) {
        $typeName current;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this,
                valueFieldOffset);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, valueFieldOffset,
            ${valTransform}(current), ${valTransform}(op.apply(current, value))));
        return current;
    }

    public static $generic $typeName updateAndGetValue(final ${opType} op, final $valueTypeName value) {
        $typeName current;
        $typeName newValue;

        do {
            current = UNSAFE.get${typeSuffix}Volatile(this, valueFieldOffset);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(this, valueFieldOffset,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
                }
            }
        }
    }

    if (isBoolean) {
        buffer.append("""
    @Override
    public final int compareTo(final ${classPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }

    public final int compareTo(final ${comparativeOtherClassPrefix}${typeSuffix}${generic} other) {
        return value == other.getValue() ? 0 : (other.getValue() ? -1 : 1);
    }
""")

        buffer.append("""
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
    } else if (isObject) {
        buffer.append("""
    @Override
    public final int compareTo(final ${classPrefix}${typeSuffix}${generic} other) {
        final ${typeName} otherValue = other.getValue;
        if (value instanceof Comparable)
            return value.compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - otherValue.compareTo(value);
        else
            throw IllegalStateException(value + " cannot be compaired with: " + otherValue + " as neither Object impliments Comparable");
    }

    public final int compareTo(final ${comparativeOtherClassPrefix}${typeSuffix}${generic} other) {
        final ${typeName} otherValue = other.getValue;
        if (value instanceof Comparable)
            return value.compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - otherValue.compareTo(value);
        else
            throw IllegalStateException(value + " cannot be compaired with: " + otherValue + " as neither Object impliments Comparable");
    }
""")
    } else if (isEnum) {
        buffer.append("""
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
    } else {
        buffer.append("""
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


genClass() {
    boolean[] mutable = [false, true]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class, Enum.class]
}