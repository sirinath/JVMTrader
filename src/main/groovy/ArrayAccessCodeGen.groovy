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

package com.susico.utils.arrays;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

public class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true) {}; // Hinting to the JIT that this is final class
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false) {};

    private final boolean SAFE;

    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    private ArrayAccess(final boolean SAFE) {
        this.SAFE = SAFE;
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

    public static  boolean inRange(final int index, final int length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final long index, final long length) {
        return index < length && index >= 0;
    }

    public static  boolean inRange(final int index, final int offset, final int length) {
        final int indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final int index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final int offset, final int length) {
        final int indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final long offset, final int length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }

    public static  boolean inRange(final long index, final long offset, final long length) {
        final long indexEnd = index + offset;
        return indexEnd < length && index >= 0 && indexEnd >= 0;
    }
""")

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

    public final $generic long contentByteSize(final $typeName ... buffer) {
        return buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final $generic long totalByteSize(final $typeName ... buffer) {
        return ARRAY_${typeSuffixCap}_BASE_OFFSET + buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final $generic void init(byte value, final $typeName[] buffer) {
        UNSAFE.setMemory(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET,
            buffer.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
    }

    public final $generic $typeName[] copy(final $typeName[] destination, final $typeName ... source) {
        return copy(this.SAFE, destination, source);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source,
                ARRAY_${typeSuffixCap}_BASE_OFFSET, destination,
                ARRAY_${typeSuffixCap}_BASE_OFFSET,
                source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    @FunctionalInterface
    public static interface UnaryArrayOp${typeSuffix} {
        $typeName apply($typeName x);
    }

    @FunctionalInterface
    public static interface BiArrayOp${typeSuffix} {
        $typeName apply($typeName x, $typeName y);
    }

    @FunctionalInterface
    public static interface MultiArrayOp${typeSuffix} {
        $typeName apply($typeName x, $typeName ... values);
    }
""")

        Class<?>[] indexTypes = [Integer.TYPE, Long.TYPE]

        for (Class<?> indexType : indexTypes) {
            String indexTypeName = indexType.getSimpleName();

            buffer.append("""
    public static $generic boolean inRange(final ${indexTypeName} index, final $typeName ... buffer) {
        return inRange(index, buffer.length);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final $typeName ... buffer) {
        if (inRange(index, buffer))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range of 0 and array length %d", index, buffer.length));
    }

    public final $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public static $generic void checkIndexIfSafeOn(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, buffer);
    }

    public static $generic boolean inRange(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        return inRange(index, length, buffer.length);
    }

    public static $generic void checkIndex(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        if (inRange(index, length, buffer))
            new ArrayIndexOutOfBoundsException(String.format(
                "index range %d and %d of length %d is not in range of 0 and array length %d", index, index + length, length, buffer.length));
    }

    public static $generic void checkIndexIfSafeOn(final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        checkIndexIfSafeOn(this.SAFE, index, buffer);
    }

    public final $generic void checkIndexIfSafeOn(final boolean SAFE, final ${indexTypeName} index, final long length, final $typeName ... buffer) {
        if (SAFE)
            checkIndex(index, length, buffer);
    }

    public final $generic $typeName get(final ${indexTypeName} index, final $typeName ... buffer) {
        return get(this.SAFE, index, buffer);
    }

    public static $generic $typeName get(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] put(final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return put(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName[] put(final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put$typeSuffix(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    public final $generic $typeName getVolatile(final ${indexTypeName} index, final $typeName ... buffer) {
        return getVolatile(this.SAFE, index, buffer);
    }

    public static $generic $typeName getVolatile(final boolean SAFE, final ${indexTypeName} index, final $typeName ... buffer) {
        if (SAFE)
            return buffer[(int) index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] putVolatile(final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return putVolatile(this.SAFE, index, buffer, value);
    }

    public static $generic $typeName[] putVolatile(final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buffer;
    }

    public final $generic $typeName[] copy(final ${indexTypeName} index, final $typeName[] destination, final $typeName ... source) {
        return copy(this.SAFE, index, destination, source);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final ${indexTypeName} index, final $typeName[] destination, final $typeName ... source) {
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

    public final $generic $typeName[] copy(final ${indexTypeName} length, final ${indexTypeName} indexDestination, final $typeName[] destination, final ${indexTypeName} indexSource, final $typeName ... source) {
        return copy(this.SAFE, length, indexBuff, buffer, indexValues, values);
    }

    public static $generic $typeName[] copy(final boolean SAFE, final ${indexTypeName} length, final ${indexTypeName} indexDestination, final $typeName[] destination, final ${indexTypeName} indexSource, final $typeName ... source) {
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
                        type.equals(Double.TYPE) ? "Double.doubleToRawLongBits" : "Float.floatToRawIntBits"
                String transformBack = type.equals(Integer.TYPE) || type.equals(Long.TYPE) ? "" :
                        type.equals(Double.TYPE) ? "Double.longBitsToDouble" : "Float.intBitsToFloat"
                String sameSizeNum = type.equals(Integer.TYPE) ? "Int" :
                        type.equals(Long.TYPE) ? "Long" :
                                type.equals(Float.TYPE) ? "Int" :
                                        type.equals(Double.TYPE) ? "Long" : "Object"

                buffer.append("""
    public final $generic $typeName[] putOrdered(final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return putOrdered(this.SAFE, index, budder, value);
    }

    public static $generic $typeName[] putOrdered(final boolean SAFE, final ${
                    indexTypeName
                } index, final $typeName[] buffer, final $typeName value) {
        if (SAFE)
            buffer[(int) index] = value;
        else
            UNSAFE.putOrdered${sameSizeNum}(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
                ${valTransform}(value));

        return buffer;
    }

    public static $generic boolean compareAndSwap(final ${
                    indexTypeName
                } index, final $typeName[] buffer, final $typeName expected, final $typeName value) {
        return compareAndSwap(this.SAFE, index, buffer, expected, value);
    }

    public final $generic boolean compareAndSwap(final boolean SAFE, final ${
                    indexTypeName
                } index, final $typeName[] buffer, final $typeName expected, final $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return UNSAFE.compareAndSwap${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(expected), ${valTransform}(value));
    }

    public final $generic $typeName getAndSet(final ${indexTypeName} index, final $typeName[] buffer, final $typeName value) {
        return getAndSet(this.SAAFE, index, buffer, value);
    }

    public static $generic $typeName getAndSet(final boolean SAFE, final ${
                    indexTypeName
                } index, final $typeName[] buffer, final $typeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        return ${transformBack}(UNSAFE.getAndSet${sameSizeNum}(buffer,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT,
            ${valTransform}(value)));
    }
        """)

                if (!type.equals(Object.class)) {
                    String[] opTypes = ["BiArrayOp${typeSuffix}", "UnaryArrayOp${typeSuffix}", "MultiArrayOp${typeSuffix}"]

                    for (String opType : opTypes) {
                        String valueTypeName = opType.startsWith("Multi") ? "$typeName ... " : typeName

                        buffer.append("""
    public final $generic $typeName getAndUpdate(final ${
                            indexTypeName
                        } index, final $typeName[] buffer, final ${opType} op, final $valueTypeName value) {
        return getAndUpdate(this.SAFE, index, buffer, op, value);
    }

    public static $generic $typeName getAndUpdate(final boolean SAFE, final ${
                            indexTypeName
                        } index, final $typeName[] buffer, final ${opType} op, final $valueTypeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        do {
            current = UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(op.apply(current, value))));
        return current;
    }

    public final $generic $typeName updateAndGet(
        final ${indexTypeName} index, final $typeName[] buffer, final ${opType} op, final $valueTypeName value) {
        return updateAndGet(this.SAFE, index, buffer, op, value);
    }

    public static $generic $typeName updateAndGet(
        final boolean SAFE, final ${indexTypeName} index, final $typeName[] buffer, final ${opType} op, final $valueTypeName value) {
        checkIndexIfSafeOn(SAFE, index, buffer);

        $typeName current;
        $typeName newValue;
        do {
            current = UNSAFE.get${typeSuffix}Volatile(buffer,
                ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
            newValue = op.apply(current, value);
        } while (!UNSAFE.compareAndSwap${sameSizeNum}(buffer, index,
            ${valTransform}(current), ${valTransform}(newValue)));

        return newValue;
    }
""")
                    }
                }
            }
        }
    }

    return buffer.append("}").toString()
}

void arrayAccessGen() {
    File p = new File(".\\..\\java\\com\\susico\\utils\\arrays\\")
    p.mkdirs()

    File f = new File(p, "ArrayAccess.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(arrayAccess())
    pw.flush()
    pw.close()
}

arrayAccessGen()