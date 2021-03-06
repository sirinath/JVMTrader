
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

    public static final int BOOLEAN_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(1) - 1;

    public static final long ARRAY_BOOLEAN_BASE_OFFSET = UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET;
    public static final long ARRAY_BOOLEAN_INDEX_SCALE = UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE :
            1;

    public static final long ARRAY_BOOLEAN_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BOOLEAN_INDEX_SCALE) - 1;

    protected long theBooleanFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theBoolean");

    protected boolean theBoolean = false;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean packBoolean(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, 1);

        this.theBoolean = false;

        if (len <= 0)
            return this.theBoolean;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theBooleanFieldOffset,
            len);

        return this.theBoolean;
    }

    public void unpackBoolean(final boolean value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, 1);

        if (len <= 0)
            return;

        this.theBoolean = value;

        UNSAFE.copyMemory(
            this,
            theBooleanFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public boolean packBoolean(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            1);

        this.theBoolean = false;

        if (len <= 0)
            return this.theBoolean;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theBooleanFieldOffset,
            len);

        return this.theBoolean;
    }

    public void unpackBoolean(
        final boolean value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            1);

        if (len <= 0)
            return;

        this.theBoolean = value;

        UNSAFE.copyMemory(
            this,
            theBooleanFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final boolean[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BOOLEAN_SHIFT;
        final long byteDestinationLength = destination.length << BOOLEAN_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BOOLEAN_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int BYTE_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Byte.BYTES) - 1;

    public static final long ARRAY_BYTE_BASE_OFFSET = UNSAFE.ARRAY_BYTE_BASE_OFFSET;
    public static final long ARRAY_BYTE_INDEX_SCALE = UNSAFE.ARRAY_BYTE_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_BYTE_INDEX_SCALE :
            Byte.BYTES;

    public static final long ARRAY_BYTE_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_BYTE_INDEX_SCALE) - 1;

    protected long theByteFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theByte");

    protected byte theByte = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public byte packByte(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Byte.BYTES);

        this.theByte = 0;

        if (len <= 0)
            return this.theByte;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theByteFieldOffset,
            len);

        return this.theByte;
    }

    public void unpackByte(final byte value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Byte.BYTES);

        if (len <= 0)
            return;

        this.theByte = value;

        UNSAFE.copyMemory(
            this,
            theByteFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public byte packByte(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Byte.BYTES);

        this.theByte = 0;

        if (len <= 0)
            return this.theByte;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theByteFieldOffset,
            len);

        return this.theByte;
    }

    public void unpackByte(
        final byte value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Byte.BYTES);

        if (len <= 0)
            return;

        this.theByte = value;

        UNSAFE.copyMemory(
            this,
            theByteFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final byte[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << BYTE_SHIFT;
        final long byteDestinationLength = destination.length << BYTE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_BYTE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int CHAR_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Character.BYTES) - 1;

    public static final long ARRAY_CHAR_BASE_OFFSET = UNSAFE.ARRAY_CHAR_BASE_OFFSET;
    public static final long ARRAY_CHAR_INDEX_SCALE = UNSAFE.ARRAY_CHAR_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_CHAR_INDEX_SCALE :
            Character.BYTES;

    public static final long ARRAY_CHAR_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_CHAR_INDEX_SCALE) - 1;

    protected long theCharFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theChar");

    protected char theChar = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public char packChar(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(final char value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public char packChar(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(
        final char value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public char packChar(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(final char value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public char packChar(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(
        final char value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public char packChar(@NotNull final short ... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(final char value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET,
            len);
    }


    public char packChar(final long index, @NotNull final short ... values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Character.BYTES);

        this.theChar = 0;

        if (len <= 0)
            return this.theChar;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            this,
            theCharFieldOffset,
            len);

        return this.theChar;
    }

    public void unpackChar(
        final char value, final long index, @NotNull final short[] values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Character.BYTES);

        if (len <= 0)
            return;

        this.theChar = value;

        UNSAFE.copyMemory(
            this,
            theCharFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final char[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << CHAR_SHIFT;
        final long byteDestinationLength = destination.length << CHAR_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_CHAR_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int SHORT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Short.BYTES) - 1;

    public static final long ARRAY_SHORT_BASE_OFFSET = UNSAFE.ARRAY_SHORT_BASE_OFFSET;
    public static final long ARRAY_SHORT_INDEX_SCALE = UNSAFE.ARRAY_SHORT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_SHORT_INDEX_SCALE :
            Short.BYTES;

    public static final long ARRAY_SHORT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_SHORT_INDEX_SCALE) - 1;

    protected long theShortFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theShort");

    protected short theShort = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public short packShort(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(final short value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public short packShort(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(
        final short value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public short packShort(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(final short value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public short packShort(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(
        final short value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public short packShort(@NotNull final char ... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(final short value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET,
            len);
    }


    public short packShort(final long index, @NotNull final char ... values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Short.BYTES);

        this.theShort = 0;

        if (len <= 0)
            return this.theShort;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            this,
            theShortFieldOffset,
            len);

        return this.theShort;
    }

    public void unpackShort(
        final short value, final long index, @NotNull final char[] values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Short.BYTES);

        if (len <= 0)
            return;

        this.theShort = value;

        UNSAFE.copyMemory(
            this,
            theShortFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final short[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << SHORT_SHIFT;
        final long byteDestinationLength = destination.length << SHORT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_SHORT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int INT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Integer.BYTES) - 1;

    public static final long ARRAY_INT_BASE_OFFSET = UNSAFE.ARRAY_INT_BASE_OFFSET;
    public static final long ARRAY_INT_INDEX_SCALE = UNSAFE.ARRAY_INT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_INT_INDEX_SCALE :
            Integer.BYTES;

    public static final long ARRAY_INT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_INT_INDEX_SCALE) - 1;

    protected long theIntFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theInt");

    protected int theInt = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public int packInt(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(final int value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public int packInt(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(
        final int value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public int packInt(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(final int value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public int packInt(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(
        final int value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public int packInt(@NotNull final char ... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(final int value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET,
            len);
    }


    public int packInt(final long index, @NotNull final char ... values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(
        final int value, final long index, @NotNull final char[] values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public int packInt(@NotNull final short ... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(final int value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET,
            len);
    }


    public int packInt(final long index, @NotNull final short ... values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(
        final int value, final long index, @NotNull final short[] values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public int packInt(@NotNull final float ... values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(final int value, @NotNull final float[] values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            len);
    }


    public int packInt(final long index, @NotNull final float ... values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Integer.BYTES);

        this.theInt = 0;

        if (len <= 0)
            return this.theInt;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            this,
            theIntFieldOffset,
            len);

        return this.theInt;
    }

    public void unpackInt(
        final int value, final long index, @NotNull final float[] values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Integer.BYTES);

        if (len <= 0)
            return;

        this.theInt = value;

        UNSAFE.copyMemory(
            this,
            theIntFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final int[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << INT_SHIFT;
        final long byteDestinationLength = destination.length << INT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_INT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int LONG_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Long.BYTES) - 1;

    public static final long ARRAY_LONG_BASE_OFFSET = UNSAFE.ARRAY_LONG_BASE_OFFSET;
    public static final long ARRAY_LONG_INDEX_SCALE = UNSAFE.ARRAY_LONG_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_LONG_INDEX_SCALE :
            Long.BYTES;

    public static final long ARRAY_LONG_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_LONG_INDEX_SCALE) - 1;

    protected long theLongFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theLong");

    protected long theLong = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final char ... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final char ... values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final char[] values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final short ... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final short ... values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final short[] values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final int ... values) {
        final long len = Math.min(values.length << INT_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final int[] values) {
        final long len = Math.min(values.length << INT_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final int ... values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final int[] values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final float ... values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final float[] values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final float ... values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final float[] values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final long[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << LONG_SHIFT;
        final long byteDestinationLength = destination.length << LONG_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_LONG_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public long packLong(@NotNull final double ... values) {
        final long len = Math.min(values.length << DOUBLE_SHIFT, Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_DOUBLE_BASE_OFFSET,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(final long value, @NotNull final double[] values) {
        final long len = Math.min(values.length << DOUBLE_SHIFT, Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_DOUBLE_BASE_OFFSET,
            len);
    }


    public long packLong(final long index, @NotNull final double ... values) {
        final long shiftIndex = index << ARRAY_DOUBLE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << DOUBLE_SHIFT - shiftIndex,
            Long.BYTES);

        this.theLong = 0;

        if (len <= 0)
            return this.theLong;

        UNSAFE.copyMemory(
            values,
            ARRAY_DOUBLE_BASE_OFFSET + shiftIndex,
            this,
            theLongFieldOffset,
            len);

        return this.theLong;
    }

    public void unpackLong(
        final long value, final long index, @NotNull final double[] values) {
        final long shiftIndex = index << ARRAY_DOUBLE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << DOUBLE_SHIFT - shiftIndex,
            Long.BYTES);

        if (len <= 0)
            return;

        this.theLong = value;

        UNSAFE.copyMemory(
            this,
            theLongFieldOffset,
            values,
            ARRAY_DOUBLE_BASE_OFFSET + shiftIndex,
            len);
    }

    public static final int FLOAT_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Float.BYTES) - 1;

    public static final long ARRAY_FLOAT_BASE_OFFSET = UNSAFE.ARRAY_FLOAT_BASE_OFFSET;
    public static final long ARRAY_FLOAT_INDEX_SCALE = UNSAFE.ARRAY_FLOAT_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_FLOAT_INDEX_SCALE :
            Float.BYTES;

    public static final long ARRAY_FLOAT_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_FLOAT_INDEX_SCALE) - 1;

    protected long theFloatFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theFloat");

    protected float theFloat = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public float packFloat(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(final float value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public float packFloat(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(
        final float value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public float packFloat(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(final float value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public float packFloat(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(
        final float value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public float packFloat(@NotNull final char ... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(final float value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET,
            len);
    }


    public float packFloat(final long index, @NotNull final char ... values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(
        final float value, final long index, @NotNull final char[] values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public float packFloat(@NotNull final short ... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(final float value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET,
            len);
    }


    public float packFloat(final long index, @NotNull final short ... values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(
        final float value, final long index, @NotNull final short[] values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public float packFloat(@NotNull final int ... values) {
        final long len = Math.min(values.length << INT_SHIFT, Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(final float value, @NotNull final int[] values) {
        final long len = Math.min(values.length << INT_SHIFT, Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET,
            len);
    }


    public float packFloat(final long index, @NotNull final int ... values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Float.BYTES);

        this.theFloat = 0;

        if (len <= 0)
            return this.theFloat;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            this,
            theFloatFieldOffset,
            len);

        return this.theFloat;
    }

    public void unpackFloat(
        final float value, final long index, @NotNull final int[] values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Float.BYTES);

        if (len <= 0)
            return;

        this.theFloat = value;

        UNSAFE.copyMemory(
            this,
            theFloatFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final float[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << FLOAT_SHIFT;
        final long byteDestinationLength = destination.length << FLOAT_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_FLOAT_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public static final int DOUBLE_SHIFT = Integer.SIZE - Integer.numberOfLeadingZeros(Double.BYTES) - 1;

    public static final long ARRAY_DOUBLE_BASE_OFFSET = UNSAFE.ARRAY_DOUBLE_BASE_OFFSET;
    public static final long ARRAY_DOUBLE_INDEX_SCALE = UNSAFE.ARRAY_DOUBLE_INDEX_SCALE > 0 ?
            UNSAFE.ARRAY_DOUBLE_INDEX_SCALE :
            Double.BYTES;

    public static final long ARRAY_DOUBLE_INDEX_SHIFT =
        Long.SIZE - Long.numberOfLeadingZeros(ARRAY_DOUBLE_INDEX_SCALE) - 1;

    protected long theDoubleFieldOffset = UnsafeAccess.getFieldOffset(Packer.class, "theDouble");

    protected double theDouble = 0;

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final boolean ... source) {

        final long bytesToCopy = length << ARRAY_BOOLEAN_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BOOLEAN_SHIFT;
        final long byteSourceLength = source.length << BOOLEAN_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BOOLEAN_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final boolean ... values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final boolean[] values) {
        final long len = Math.min(values.length << BOOLEAN_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final boolean ... values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final boolean[] values) {
        final long shiftIndex = index << ARRAY_BOOLEAN_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BOOLEAN_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_BOOLEAN_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final byte ... source) {

        final long bytesToCopy = length << ARRAY_BYTE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << BYTE_SHIFT;
        final long byteSourceLength = source.length << BYTE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_BYTE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final byte ... values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final byte[] values) {
        final long len = Math.min(values.length << BYTE_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final byte ... values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final byte[] values) {
        final long shiftIndex = index << ARRAY_BYTE_INDEX_SHIFT;
        final long len = Math.min(
            values.length << BYTE_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_BYTE_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final char ... source) {

        final long bytesToCopy = length << ARRAY_CHAR_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << CHAR_SHIFT;
        final long byteSourceLength = source.length << CHAR_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_CHAR_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final char ... values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final char[] values) {
        final long len = Math.min(values.length << CHAR_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final char ... values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final char[] values) {
        final long shiftIndex = index << ARRAY_CHAR_INDEX_SHIFT;
        final long len = Math.min(
            values.length << CHAR_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_CHAR_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final short ... source) {

        final long bytesToCopy = length << ARRAY_SHORT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << SHORT_SHIFT;
        final long byteSourceLength = source.length << SHORT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_SHORT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final short ... values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final short[] values) {
        final long len = Math.min(values.length << SHORT_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final short ... values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final short[] values) {
        final long shiftIndex = index << ARRAY_SHORT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << SHORT_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_SHORT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final int ... source) {

        final long bytesToCopy = length << ARRAY_INT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << INT_SHIFT;
        final long byteSourceLength = source.length << INT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_INT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final int ... values) {
        final long len = Math.min(values.length << INT_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final int[] values) {
        final long len = Math.min(values.length << INT_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final int ... values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final int[] values) {
        final long shiftIndex = index << ARRAY_INT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << INT_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_INT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final long ... source) {

        final long bytesToCopy = length << ARRAY_LONG_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << LONG_SHIFT;
        final long byteSourceLength = source.length << LONG_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_LONG_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final long ... values) {
        final long len = Math.min(values.length << LONG_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_LONG_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final long[] values) {
        final long len = Math.min(values.length << LONG_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_LONG_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final long ... values) {
        final long shiftIndex = index << ARRAY_LONG_INDEX_SHIFT;
        final long len = Math.min(
            values.length << LONG_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_LONG_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final long[] values) {
        final long shiftIndex = index << ARRAY_LONG_INDEX_SHIFT;
        final long len = Math.min(
            values.length << LONG_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_LONG_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final float ... source) {

        final long bytesToCopy = length << ARRAY_FLOAT_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << FLOAT_SHIFT;
        final long byteSourceLength = source.length << FLOAT_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_FLOAT_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }

    public double packDouble(@NotNull final float ... values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(final double value, @NotNull final float[] values) {
        final long len = Math.min(values.length << FLOAT_SHIFT, Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET,
            len);
    }


    public double packDouble(final long index, @NotNull final float ... values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Double.BYTES);

        this.theDouble = 0;

        if (len <= 0)
            return this.theDouble;

        UNSAFE.copyMemory(
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            this,
            theDoubleFieldOffset,
            len);

        return this.theDouble;
    }

    public void unpackDouble(
        final double value, final long index, @NotNull final float[] values) {
        final long shiftIndex = index << ARRAY_FLOAT_INDEX_SHIFT;
        final long len = Math.min(
            values.length << FLOAT_SHIFT - shiftIndex,
            Double.BYTES);

        if (len <= 0)
            return;

        this.theDouble = value;

        UNSAFE.copyMemory(
            this,
            theDoubleFieldOffset,
            values,
            ARRAY_FLOAT_BASE_OFFSET + shiftIndex,
            len);
    }

    public boolean copy(
        final long length,
        final long destinationIndex, @NotNull final double[] destination,
        final long sourceIndex, @NotNull final double ... source) {

        final long bytesToCopy = length << ARRAY_DOUBLE_INDEX_SHIFT;

        final long byteDestinationIndex = destinationIndex << DOUBLE_SHIFT;
        final long byteDestinationLength = destination.length << DOUBLE_SHIFT;

        if (byteDestinationIndex + bytesToCopy > byteDestinationLength)
            return false;

        final long byteSourceIndex = sourceIndex << DOUBLE_SHIFT;
        final long byteSourceLength = source.length << DOUBLE_SHIFT;

        if (byteSourceIndex + bytesToCopy > byteSourceLength)
            return false;

        UNSAFE.copyMemory(
            source,
            ARRAY_DOUBLE_BASE_OFFSET + byteSourceIndex,
            destination,
            ARRAY_DOUBLE_BASE_OFFSET + byteDestinationIndex,
            bytesToCopy
        );

        return true;
    }
}