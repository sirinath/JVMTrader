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

package com.susico.utils.memory.offheap;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

/**
 * Created by sirin_000 on 03/10/2015.
 */
public class MemoryAccess {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    public static byte getByte(final long address, final long index) {
        return UNSAFE.getByte(address + index * Byte.BYTES);
    }

    public static void putByte(final long address, final long index, final byte value) {
        UNSAFE.putByte(address + index * Byte.BYTES, value);
    }

    public static char getChar(final long address, final long index) {
        return UNSAFE.getChar(address + index * Character.BYTES);
    }

    public static void putChar(final long address, final long index, final char value) {
        UNSAFE.putChar(address + index * Character.BYTES, value);
    }

    public static double getDouble(final long address, final long index) {
        return UNSAFE.getDouble(address + index * Double.BYTES);
    }

    public static void putDouble(final long address, final long index, final double value) {
        UNSAFE.putDouble(address + index * Double.BYTES, value);
    }

    public static float getFloat(final long address, final long index) {
        return UNSAFE.getFloat(address + index * Float.BYTES);
    }

    public static void putFloat(final long address, final long index, final float value) {
        UNSAFE.putFloat(address + index * Float.BYTES, value);
    }

    public static int getInt(final long address, final long index) {
        return UNSAFE.getInt(address + index * Integer.BYTES);
    }

    public static void putFloat(final long address, final long index, final int value) {
        UNSAFE.putFloat(address + index * Integer.BYTES, value);
    }

    public static long getLong(final long address, final long index) {
        return UNSAFE.getLong(address + index * Long.BYTES);
    }

    public static void putLong(final long address, final long index, final long value) {
        UNSAFE.putLong(address + index * Long.BYTES, value);
    }

    public static short getShort(final long address, final long index) {
        return UNSAFE.getShort(address + index * Short.BYTES);
    }

    public static void putShort(final long address, final long index, final short value) {
        UNSAFE.putShort(address + index * Short.BYTES, value);
    }
}
