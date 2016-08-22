/*
 * Microtrader is available under either the terms of the Apache License, Version 2.0 (ASF 2.0)
 * the Academic Free License Version 3.0, (AFL 3.0) or MIT License (MIT). As a recipient of
 * Microtrader, you may choose which license to receive this code or content under
 * (except as noted in per-module LICENSE files). Some modules may not be the copyright
 * of the Suminda Sirinath Salpitikorala Dharmasena and Project Contributors.
 * These modules contain explicit declarations of copyright in both the LICENSE files
 * in the directories in which they reside and in the code or content itself.
 *
 * No external contributions are allowed under licenses which are fundamentally
 * incompatible with the ASL 2.0, AFL 3.0 and MIT that Microtrader is distributed under.
 * By contributing to this project by means of including but not limited to patches,
 * pull requests, code submissions, issues, bug report, code snippets, discussions,
 * email message, chat messages such content will be licensed under the terms of
 * ASL 2.0, AFL 3.0 and MIT where the recipients are free to choose under which license
 * code or content is received under.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
            String originalTypeName = originalType.getSimpleName()
            String originalTypeSuffixCap = originalTypeName.toUpperCase()

            buffer.append("""
    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final ${typeName}[] destination,
        final long sourceIndex, @NotNull final ${originalTypeName} ... source) {

        final long bytesToCopy = length << ARRAY_${originalTypeSuffixCap}_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << ${typeSuffixCap}_SHIFT;
        final long byteDestinationLength = destination.length << ${typeSuffixCap}_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << ${originalTypeSuffixCap}_SHIFT;
        final long byteSourceLength = source.length << ${originalTypeSuffixCap}_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_${originalTypeSuffixCap}_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_${typeSuffixCap}_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }
""")

            if (sizes[originalType] > sizes[type] || originalType.equals(type))
                continue

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