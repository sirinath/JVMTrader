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

String packer() {
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

package com.susico.utils.bits;

import com.susico.utils.UnsafeAccess;
import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

public final class Packer extends ThreadLocal<Packer> {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static final Packer PACKER = init();

    protected Packer() {}

    protected static Packer init() {
        final Packer packer = new Packer();
        packer.set(packer);

        return packer;
    }

    @Override
    protected Packer initialValue() {
        return new Packer();
    }
""")

    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE]

    def sizes = [
            (Boolean.TYPE) : 1,
            (Byte.TYPE) : Byte.BYTES,
            (Character.TYPE) : Character.BYTES,
            (Short.TYPE) : Short.BYTES,
            (Integer.TYPE) : Integer.BYTES,
            (Long.TYPE) : Long.BYTES,
            (Float.TYPE) : Float.BYTES,
            (Double.TYPE) : Double.BYTES
    ]

    for (Class<?> type : types) {
        String typeName = type.getSimpleName()
        String typeSuffix = upcase1st(type.getSimpleName())
        String typeSuffixCap = typeSuffix.toUpperCase()
        String boxTypeName = type.equals(Character.TYPE) ?
                "Character" :
                type.equals(Integer.TYPE) ?
                        "Integer" :
                        typeSuffix
        String defaulValue = type.equals(Boolean.TYPE) ?
                "false" :
                "0"
        String byteSize = type.equals(Boolean.TYPE) ? "1" : "${boxTypeName}.BYTES"

        buffer.append("""
    public static final int ${typeSuffixCap}_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(${byteSize}) - 1;

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

    protected long the${typeSuffix}FieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "the${typeSuffix}");

    protected ${typeName} the${typeSuffix} = ${defaulValue};
""")

        for (Class<?> originalType : types) {
            if (sizes[originalType] > sizes[type] || originalType.equals(type))
                continue

            String originalTypeName = originalType.getSimpleName()
            String originalTypeSuffixCap = originalTypeName.toUpperCase()

            buffer.append("""
    public ${typeName} pack${typeSuffix}(@NotNull final ${originalTypeName} ... values) {
        final long len = Math.min(values.length << ${originalTypeSuffixCap}_SHIFT, ${byteSize});

        this.the${typeSuffix} = ${defaulValue};

        if (len <= 0)
            return this.the${typeSuffix};

        UNSAFE.copyMemory(
            values,
            ARRAY_${originalTypeSuffixCap}_BASE_OFFSET,
            this,
            the${typeSuffix}FieldOffset,
            len);

        return this.the${typeSuffix};
    }

    public void unpack${typeSuffix}(final ${typeName} value, @NotNull final ${originalTypeName}[] values) {
        final long len = Math.min(values.length << ${originalTypeSuffixCap}_SHIFT, ${byteSize});

        if (len <= 0)
            return;

        this.the${typeSuffix} = value;

        UNSAFE.copyMemory(
            this,
            the${typeSuffix}FieldOffset,
            values,
            ARRAY_${originalTypeSuffixCap}_BASE_OFFSET,
            len);
    }


    public ${typeName} pack${typeSuffix}(final long index, @NotNull final ${originalTypeName} ... values) {
        final long shiftIndex = index << ARRAY_${originalTypeSuffixCap}_INDEX_SHIFT;
        final long len = Math.min(
            values.length << ${originalTypeSuffixCap}_SHIFT - shiftIndex,
            ${byteSize});

        this.the${typeSuffix} = ${defaulValue};

        if (len <= 0)
            return this.the${typeSuffix};

        UNSAFE.copyMemory(
            values,
            ARRAY_${originalTypeSuffixCap}_BASE_OFFSET + shiftIndex,
            this,
            the${typeSuffix}FieldOffset,
            len);

        return this.the${typeSuffix};
    }

    public void unpack${typeSuffix}(
        final ${typeName} value, final long index, @NotNull final ${originalTypeName}[] values) {
        final long shiftIndex = index << ARRAY_${originalTypeSuffixCap}_INDEX_SHIFT;
        final long len = Math.min(
            values.length << ${originalTypeSuffixCap}_SHIFT - shiftIndex,
            ${byteSize});

        if (len <= 0)
            return;

        this.the${typeSuffix} = value;

        UNSAFE.copyMemory(
            this,
            the${typeSuffix}FieldOffset,
            values,
            ARRAY_${originalTypeSuffixCap}_BASE_OFFSET + shiftIndex,
            len);
    }
""")
        }
    }

    return buffer.append("}").toString()
}

void genPacker() {
    File p = new File(".\\..\\java\\com\\susico\\utils\\bits\\")
    p.mkdirs()

    File f = new File(p, "Packer.java")
    f.createNewFile()

    PrintWriter pw = f.newPrintWriter()
    pw.print(packer())
    pw.flush()
    pw.close()
}

genPacker()