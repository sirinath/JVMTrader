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


import com.susico.utils.box.mutable.*;
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
    public static MutableBoolean Box(final boolean v) {
        return new MutableBoolean(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableByte Box(final byte v) {
        return new MutableByte(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableChar Box(final char v) {
        return new MutableChar(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableDouble Box(final double v) {
        return new MutableDouble(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableFloat Box(final float v) {
        return new MutableFloat(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableInt Box(final int v) {
        return new MutableInt(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableLong Box(final long v) {
        return new MutableLong(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static MutableShort Box(final short v) {
        return new MutableShort(v);
    }

    /**
     * @param <T> Enum type
     * @param v   value to be boxed
     * @return boxed value
     */
    public static <T extends Enum<T>> MutableEnum<T> Box(final @NotNull T v) {
        return new MutableEnum<T>(v);
    }
}
