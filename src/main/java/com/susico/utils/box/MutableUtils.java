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

package com.susico.utils.box;


import com.susico.utils.primitives.variable.*;
import org.jetbrains.annotations.NotNull;

/**
 * Help instantiate boxing
 *
 * @author sirinath
 */
public class MutableUtils {
    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVBool Box(final boolean v) {
        return new PVBool(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVByte Box(final byte v) {
        return new PVByte(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVChar Box(final char v) {
        return new PVChar(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVDouble Box(final double v) {
        return new PVDouble(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVFloat Box(final float v) {
        return new PVFloat(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVInt Box(final int v) {
        return new PVInt(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVLong Box(final long v) {
        return new PVLong(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static PVShort Box(final short v) {
        return new PVShort(v);
    }

    /**
     * @param <T> Enum type
     * @param v   value to be boxed
     * @return boxed value
     */
    public static <T extends Enum<T>> PVEnum<T> Box(final @NotNull T v) {
        return new PVEnum<T>(v);
    }
}
