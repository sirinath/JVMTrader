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

String arrayAccess() {
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

package com.susico.utils.arrays.access;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import com.susico.utils.functions.*;

import org.jetbrains.annotations.*;

public final class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true);
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false);

    protected final boolean SAFE;

    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    private ArrayAccess(final boolean SAFE) {
        this.SAFE = SAFE;
    }

    public final boolean isSafe() {
        return SAFE;
    }

    public static ArrayAccess checked(final boolean checked) {
        return checked ? CHECKED : UNCHECKED;
    }

    public static ArrayAccess checked() {
        return CHECKED;
    }

    public static ArrayAccess unchecked() {
        return UNCHECKED;
    }
""")

    Class<?>[] indexTypes = [Integer.TYPE, Long.TYPE]

    for (Class<?> indexType : indexTypes) {
        String indexTypeName = indexType.getSimpleName();

        buffer.append("""
    public static  boolean inRange(final ${indexTypeName} index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final ${indexTypeName} index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }

    public static  boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && indexEnd >= 0;
    }
    """)
    }

    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]

    for (Class<?> type : types) {
        String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : "Object"
        String typeSuffixCap = typeSuffix.toUpperCase()
        String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
        String generic = type.isPrimitive() ? "" : "<T>"

        buffer.append("""
    public static final long ARRAY_${typeSuffixCap}_BASE_OFFSET = UNSAFE.ARRAY_${typeSuffixCap}_BASE_OFFSET;
    public static final long ARRAY_${typeSuffixCap}_INDEX_SCALE = UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE :
            ${
            typeSuffix.equalsIgnoreCase("BOOLEAN") ?
                    1 :
                    typeSuffix.equalsIgnoreCase("INT") ?
                            "Integer.BYTES" :
                            typeSuffix.equalsIgnoreCase("CHAR") ?
                                    "Character.BYTES" :
                                    typeSuffix.equalsIgnoreCase("OBJECT") ?
                                            "UNSAFE.ADDRESS_SIZE" :
                                            typeSuffix + ".BYTES"
        };

    public static final long ARRAY_${typeSuffixCap}_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_${typeSuffixCap}_INDEX_SCALE) - 1;

    public static $generic long contentByteSize(final @NotNull $typeName ... buffer) {
        return buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public static $generic long totalByteSize(final @NotNull $typeName ... buffer) {
        return ARRAY_${typeSuffixCap}_BASE_OFFSET + buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public static $generic void init(final byte value, final @NotNull $typeName[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET,
            buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
    }

    public final @NotNull $generic $typeName[] copy(final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static @NotNull $generic $typeName[] copy(final boolean SAFE, final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET, destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET,
                source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }
""")
        for (Class<?> indexType : indexTypes) {
            String indexTypeName = indexType.getSimpleName();

            buffer.append("""
    public static $generic boolean inRange(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return inRange(index, buffer.length);
    }

    public static $generic boolean inRangeWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        return inRange(index, offset, buffer.length);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static $generic void checkIndexIfSafeOn(final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static $generic void checkIndexWithOffset(final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        if (inRangeWithOffset(index, offset, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array offset %d", index, index + offset, offset, buffer.length));
    }

    public final $generic void checkIndexIfSafeOnWithOffset(
        final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        checkIndexIfSafeOnWithOffset(this.SAFE, index, offset, buffer);
    }

    public static $generic void checkIndexIfSafeOnWithOffset(
        final boolean SAFE, final ${indexTypeName} index, final ${indexTypeName} offset, final @NotNull $typeName ... buffer) {
        if (SAFE)
            checkIndexWithOffset(index, offset, buffer);
    }

    public final @NotNull $generic $typeName get(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static @NotNull $generic $typeName get(final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final @NotNull $generic $typeName[] put(final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static @NotNull $generic $typeName[] put(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull $generic $typeName getVolatile(final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static @NotNull $generic $typeName getVolatile(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final @NotNull $generic $typeName[] putVolatile(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static @NotNull $generic $typeName[] putVolatile(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer,
        final @NotNull $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    public final @NotNull $generic $typeName[] copy(
        final ${indexTypeName} index, final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static @NotNull $generic $typeName[] copy(final boolean SAFE, final ${
                indexTypeName
            } index, final @NotNull $typeName[] destination, final @NotNull $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET,
                destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final @NotNull $generic $typeName[] copy(
        final ${indexTypeName} length, final ${indexTypeName} indexDestination, final @NotNull $typeName[] destination,
        final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        return copy(this.SAFE, length, indexDestination, destination, indexSource, source);
    }

    public static @NotNull $generic $typeName[] copy(
        final boolean SAFE, final ${indexTypeName} length, final ${indexTypeName} indexDestination,
        final @NotNull $typeName[] destination, final ${indexTypeName} indexSource, final @NotNull $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + indexSource << ARRAY_${typeSuffixCap}_INDEX_SCALE,
                destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + indexDestination << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
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
    public final @NotNull $generic $typeName[] putOrdered(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return putOrdered(this.SAFE, index, buffer, value);
    }

    public static @NotNull $generic $typeName[] putOrdered(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrdered${sameSizeNum}(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                ${valTransform}(value));

        return buffer;
    }

    public final $generic boolean compareAndSwap(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName expected, final @NotNull $typeName value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public static $generic boolean compareAndSwap(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName expected,
        final @NotNull $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwap${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(expected), ${valTransform}(value));
    }

    public final @NotNull $generic $typeName getAndSet(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        return ($typeName) getAndSet(this.SAFE, index, buffer, value);
    }

    public static @NotNull $generic $typeName getAndSet(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return ($typeName) ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
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

                    String applyOpMulti = opType.startsWith("Multi") ?
                            ", value" :
                            opType.startsWith("Bi") ?
                                    ", value" :
                                    ""

                    buffer.append("""
    public final @NotNull $generic $typeName getAndUpdate(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull ${opType}$generic op$valueParam) {
        return ($typeName) getAndUpdate(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static @NotNull $generic $typeName getAndUpdate(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull ${opType}$generic op$valueParam) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        do {
            current = ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(${applyOp})));
        return current;
    }

    public final @NotNull $generic $typeName updateAndGet(
        final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull ${opType}$generic op$valueParam) {
        return updateAndGet(this.SAFE, index, buffer, op${applyOpMulti});
    }

    public static @NotNull $generic $typeName updateAndGet(
        final boolean SAFE, final ${indexTypeName} index, final @NotNull $typeName[] buffer, final @NotNull ${opType}$generic op$valueParam) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        $typeName newValue;
        do {
            current = ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
            newValue = ${applyOp};
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
                }
            }
        }
    }

    return buffer.append("}").toString()
}

void arrayAccessGen() {
    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\access\\")
    p.mkdirs()

    File f = new File(p, "ArrayAccess.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(arrayAccess())
    pw.flush()
    pw.close()
}

arrayAccessGen()