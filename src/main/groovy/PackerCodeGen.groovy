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

String genPacker() {
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
    protected Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static final Packer PACKER = new Packer();
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
        String typeSuffix = upcase1st(type.getSimpleName())
        String typeSuffixCap = typeSuffix.toUpperCase()

        buffer.append("""
    public static final int CHAR_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Character.BYTES) - 1;
""")

        buffer.append("""
    protected long theCharFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theChar");

    protected char theChar = 0;

    public char packChar(@NotNull final byte... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        theChar = 0;

        UNSAFE.copyMemory(values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, this, theCharFieldOffset, len);

        return aChar;
    }

    public void unpackChar(final char value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        theChar = value;

        UNSAFE.copyMemory(this, theCharFieldOffset, values, UNSAFE.ARRAY_BYTE_BASE_OFFSET, len);
    }
""")
    }

    return buffer.append("}").toString()
}