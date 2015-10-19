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


import com.susico.utils.box.immutable.*;
import org.jetbrains.annotations.NotNull;

/**
 * Help instantiate boxing
 *
 * @author sirinath
 */
public class ImmutableUtils {
    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableBoolean boxed(final boolean v) {
        return new ImmutableBoolean(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableByte boxed(final byte v) {
        return new ImmutableByte(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableChar boxed(final char v) {
        return new ImmutableChar(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableDouble boxed(final double v) {
        return new ImmutableDouble(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableFloat boxed(final float v) {
        return new ImmutableFloat(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableInt boxed(final int v) {
        return new ImmutableInt(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableLong boxed(final long v) {
        return new ImmutableLong(v);
    }

    /**
     * @param v value to be boxed
     * @return boxed value
     */
    public static ImmutableShort boxed(final short v) {
        return new ImmutableShort(v);
    }

    /**
     * @param <T> Enum type
     * @param v   value to be boxed
     * @return boxed value
     */
    public static <T extends Enum<T>> ImmutableEnum<T> boxed(final @NotNull T v) {
        return new ImmutableEnum<T>(v);
    }
}
