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
    StringBuilder buff = new StringBuilder("""
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

public final class ArrayAccess {
    public static final ArrayAccess CHECKED = new ArrayAccess(true);
    public static final ArrayAccess UNCHECKED = new ArrayAccess(false);

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
""")

    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class]

    for (Class<?> type : types) {
        String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : "Object"
        String typeSuffixCap = typeSuffix.toUpperCase()
        String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
        String generic = type.isPrimitive() ? "" : "<T>"
        
        buff.append("""
    public static final long ARRAY_${typeSuffixCap}_BASE_OFFSET = UNSAFE.ARRAY_${typeSuffixCap}_BASE_OFFSET;
    public static final long ARRAY_${typeSuffixCap}_INDEX_SCALE = UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE > 0 ? UNSAFE.ARRAY_${typeSuffixCap}_INDEX_SCALE : ${typeSuffix.equalsIgnoreCase("BOOLEAN") ? 1 : typeSuffix.equalsIgnoreCase("INT") ? "Integer.BYTES" : typeSuffix.equalsIgnoreCase("CHAR") ? "Character.BYTES" : typeSuffix.equalsIgnoreCase("OBJECT") ? "UNSAFE.ADDRESS_SIZE" : typeSuffix + ".BYTES"};
    public static final long ARRAY_${typeSuffixCap}_INDEX_SHIFT = Long.SIZE - Long.numberOfLeadingZeros(ARRAY_${typeSuffixCap}_INDEX_SCALE) - 1;

    public static boolean inRange(final long index, final $typeName ... buff) {
        return index < 0 || index >= buff.length;
    }

    public static boolean inRange(final int index, final $typeName ... buff) {
        return index < 0 || index >= buff.length;
    }

    public static void checkIndex(final long index, final $typeName ... buff) {
        if (inRange(index, buff))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range or 0 and array length %d", index, buff.length));
    }

    public static void checkIndex(final int index, final $typeName ... buff) {
        if (inRange(index, buff))
            new ArrayIndexOutOfBoundsException(String.format("index %d not in range or 0 and array length %d", index, buff.length));
    }

    public final void checkIndexIfSafeOn(final long index, final $typeName ... buff) {
        if (SAFE)
            checkIndex(index, buff);
    }
    
    public final void checkIndexIfSafeOn(final int index, final $typeName ... buff) {
        if (SAFE)
            checkIndex(index, buff);
    }
    
    public static boolean inRange(final long index, final long length, final $typeName ... buff) {
        return inRange(index + length);
    }

    public static boolean inRange(final int index, final int length, final $typeName ... buff) {
        return inRange(index + length);
    }

    public static void checkIndex(final long index, final long length, final $typeName ... buff) {
        checkIndex(index + length, buff);
    }

    public static void checkIndex(final int index, final int length, final $typeName ... buff) {
        checkIndex(index + length, buff);
    }

    public final void checkIndexIfSafeOn(final long index, final long length, final $typeName ... buff) {
        checkIndexIfSafeOn(index + length, buff);
    }
    
    public final void checkIndexIfSafeOn(final int index, final int length, final $typeName ... buff) {
        checkIndexIfSafeOn(index + length, buff);
    }

    public final $generic long contentByteSize(final $typeName ... buff) {
        return buff.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final $generic long totalByteSize(final $typeName ... buff) {
        return ARRAY_${typeSuffixCap}_BASE_OFFSET + buff.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT;
    }

    public final $generic void init(byte value, final $typeName[] buff) {
        return UNSAFE.setMemory(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET, buff.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
    }

    public final $generic $typeName get(final long index, final $typeName ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] put(final long index, final $typeName[] buff, final $typeName value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.put$typeSuffix(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buff;
    }

    public final $generic $typeName get(final int index, final $typeName ... buff) {
        if (SAFE)
            return buff[index];
        else
            return ($typeName) UNSAFE.get$typeSuffix(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] put(final int index, final $typeName[] buff, final $typeName value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.put$typeSuffix(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buff;
    }

    public final $generic $typeName getVolatile(final long index, final $typeName ... buff) {
        if (SAFE)
            return buff[(int) index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] putVolatile(final long index, final $typeName[] buff, final $typeName value) {
        if (SAFE)
            buff[(int) index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buff;
    }

    public final $generic $typeName getVolatile(final int index, final $typeName ... buff) {
        if (SAFE)
            return buff[index];
        else
            return ($typeName) UNSAFE.get${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
    }

    public final $generic $typeName[] putVolatile(final int index, final $typeName[] buff, final $typeName value) {
        if (SAFE)
            buff[index] = value;
        else
            UNSAFE.put${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

        return buff;
    }

    public final $generic $typeName[] get(final $typeName[] buff, final $typeName ... values) {
        return copy(buff, values);
    }

    public final $generic $typeName[] put(final $typeName[] buff, final $typeName ... values) {
        return copy(buff, values);
    }

    public final $generic $typeName[] copy(final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, 0, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_${typeSuffixCap}_BASE_OFFSET, destination, ARRAY_${typeSuffixCap}_BASE_OFFSET, source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final $generic $typeName[] get(final long index, final $typeName[] buff, final $typeName ... values) {
        return copy(index, buff, values);
    }

    public final $generic $typeName[] put(final long index, final $typeName[] buff, final $typeName ... values) {
        return copy(index, buff, values);
    }

    public final $generic $typeName[] copy(final long index, final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, (int) index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_${typeSuffixCap}_BASE_OFFSET, destination, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final $generic $typeName[] get(final long length, final long indexBuff, final $typeName[] buff, final long indexValues, final $typeName ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final $generic $typeName[] put(final long length, final long indexBuff, final $typeName[] buff, final long indexValues, final $typeName ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final $generic $typeName[] copy(final long length, final long indexDestination, final $typeName[] destination, final long indexSource, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, (int) indexSource, destination, (int) indexDestination, (int) length);
        else
            UNSAFE.copyMemory(source, ARRAY_${typeSuffixCap}_BASE_OFFSET + indexSource << ARRAY_${typeSuffixCap}_INDEX_SCALE, destination, ARRAY_${typeSuffixCap}_BASE_OFFSET + indexDestination << ARRAY_${typeSuffixCap}_INDEX_SHIFT, length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final $generic $typeName[] get(final int index, final $typeName[] buff, final $typeName ... values) {
        return copy(index, buff, values);
    }

    public final $generic $typeName[] put(final int index, final $typeName[] buff, final $typeName ... values) {
        return copy(index, buff, values);
    }

    public final $generic $typeName[] copy(final int index, final $typeName[] destination, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, 0, destination, index, source.length);
        else
            UNSAFE.copyMemory(source, ARRAY_${typeSuffixCap}_BASE_OFFSET, destination, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, source.length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }

    public final $generic $typeName[] get(final int length, final int indexBuff, final $typeName[] buff, final int indexValues, final $typeName ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final $generic $typeName[] put(final int length, final int indexBuff, final $typeName[] buff, final int indexValues, final $typeName ... values) {
        return copy(length, indexBuff, buff, indexValues, values);
    }

    public final $generic $typeName[] copy(final int length, final int indexDestination, final $typeName[] destination, final int indexSource, final $typeName ... source) {
        if (SAFE)
            System.arraycopy(source, indexSource, destination, indexDestination, length);
        else
            UNSAFE.copyMemory(source, ARRAY_${typeSuffixCap}_BASE_OFFSET + indexSource << ARRAY_${typeSuffixCap}_INDEX_SHIFT, destination, ARRAY_${typeSuffixCap}_BASE_OFFSET + indexDestination << ARRAY_${typeSuffixCap}_INDEX_SHIFT, length << ARRAY_${typeSuffixCap}_INDEX_SHIFT);

        return destination;
    }
""")

        if (type.equals(Integer.TYPE) || type.equals(Long.TYPE) || type.equals(Object.class)) {
            buff.append("""
            public final $generic $typeName[] putOrdered(final long index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrdered${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

                return buff;
            }

            public final $generic $typeName[] putOrdered(final int index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrdered${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);

                return buff;
            }

            public final $generic boolean compareAndSwap(final long index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return UNSAFE.compareAndSwap${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, expected, value);
            }

            public final $generic boolean compareAndSwap(final int index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                return UNSAFE.compareAndSwap${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, expected, value);
            }
            """)

            if (!type.equals(Object.class)) {
                buff.append("""
            public final $generic $typeName getAndAdd(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return UNSAFE.getAndAdd${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
            }

            public final $generic $typeName getAndAdd(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return UNSAFE.getAndAdd${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
            }

            public final $generic $typeName addAndGet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                $typeName current;
                $typeName newValue;
                do {
                    current = get${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value;
                } while (!compareAndSwap${typeSuffix}(buff, offset, current, newValue));

                return newValue;
            }

            public final $generic $typeName addAndGet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                $typeName current;
                $typeName newValue;
                do {
                    current = get${typeSuffix}Volatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value;
                } while (!compareAndSwap${typeSuffix}(buff, offset, current, newValue));

                return newValue;
            }
            """)
            }

            buff.append("""
            public final $generic $typeName getAndSet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return UNSAFE.getAndSet${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
            }

            public final $generic $typeName getAndSet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return UNSAFE.getAndSet${typeSuffix}(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, value);
            }
            """)
        } else if (type.equals(Float.TYPE)) {
            buff.append("""
            public final $generic $typeName[] putOrdered(final long index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(value));

                return buff;
            }

            public final $generic $typeName[] putOrdered(final int index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(value));

                return buff;
            }

            public final $generic boolean compareAndSwap(final long index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                return UNSAFE.compareAndSwapInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
            }

            public final $generic boolean compareAndSwap(final int index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                return UNSAFE.compareAndSwapInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(expected), Float.floatToRawIntBits(value));
            }

            public final $generic $typeName getAndAdd(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                float current;
                do {
                    current = getFloatVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                } while (!compareAndSwapInt(buff, offset, Float.floatToRawIntBits(current), Float.floatToRawIntBits(current + value)));
                return current;
            }

            public final $generic $typeName getAndAdd(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                float current;
                do {
                    current = getFloatVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                } while (!compareAndSwapInt(buff, offset, Float.floatToRawIntBits(current), Float.floatToRawIntBits(current + value)));
                return current;
            }

            public final $generic $typeName addAndGet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                float current;
                float newValue;
                do {
                    current = getFloatVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value;
                } while (!compareAndSwapInt(buff, offset, Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

                return newValue;
            }

            public final $generic $typeName addAndGet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                float current;
                float newValue;
                do {
                    current = getFloatVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value;
                } while (!compareAndSwapInt(buff, offset, Float.floatToRawIntBits(current), Float.floatToRawIntBits(newValue)));

                return newValue;
            }

            public final $generic $typeName getAndSet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return Float.intBitsToFloat(UNSAFE.getAndSetInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(value)));
            }

            public final $generic $typeName getAndSet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return Float.intBitsToFloat(UNSAFE.getAndSetInt(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Float.floatToRawIntBits(value)));
            }
            """)
        } else if (type.equals(Double.TYPE)) {
            buff.append("""
            public final $generic $typeName[] putOrdered(final long index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[(int) index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(value));

                return buff;
            }

            public final $generic $typeName[] putOrdered(final int index, final $typeName[] buff, final $typeName value) {
                if (SAFE)
                    buff[index] = value;
                else
                    UNSAFE.putOrderedLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(value));

                return buff;
            }

            public final $generic boolean compareAndSwap(final long index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                return UNSAFE.compareAndSwapLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
            }

            public final $generic boolean compareAndSwap(final int index, final $typeName[] buff, final $typeName expected, final $typeName value) {
                checkIndexIfSafeOn(index, buff);
                
                return UNSAFE.compareAndSwapLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(value));
            }

            public final $generic $typeName getAndAdd(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                double current;
                do {
                    current = getDoubleVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                } while (!compareAndSwapLong(buff, offset, Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(current + value)));

                return current;
            }

            public final $generic $typeName getAndAdd(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                double current;
                do {
                    current = getDoubleVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                } while (!compareAndSwapLong(buff, offset, Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(current + value)));

                return current;
            }

            public final $generic $typeName addAndGet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                double current;
                double newValue;
                do {
                    current = getDoubleVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value
                } while (!compareAndSwapLong(buff, offset, Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

                return newValue;
            }

            public final $generic $typeName addAndGet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                double current;
                double newValue;
                do {
                    current = getDoubleVolatile(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT);
                    newValue = current + value
                } while (!compareAndSwapLong(buff, offset, Double.doubleToRawLongBits(current), Double.doubleToRawLongBits(newValue)));

                return newValue;
            }

            public final $generic $typeName getAndSet(final long index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return Double.longBitsToDouble(UNSAFE.getAndSetLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(value)));
            }

            public final $generic $typeName getAndSet(final int index, final $typeName[] buff, final $typeName value) {
                checkIndexIfSafeOn(index, buff);

                return Double.longBitsToDouble(UNSAFE.getAndSetLong(buff, ARRAY_${typeSuffixCap}_BASE_OFFSET + index << ARRAY_${typeSuffixCap}_INDEX_SHIFT, Double.doubleToRawLongBits(value)));
            }
            """)
        }
    }

    return buff.append("}").toString()
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