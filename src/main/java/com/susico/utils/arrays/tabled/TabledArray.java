// Auto generated. Do not edit directly!
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

package com.susico.utils.arrays.tabled;

import com.susico.utils.arrays.access.ArrayAccess;

import com.susico.utils.functions.*;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;

import org.jetbrains.annotations.*;

import com.susico.utils.arrays.tabled.arrayboolean.immutable.ImmutableTabledArrayBoolean;
import com.susico.utils.arrays.tabled.arraybyte.immutable.ImmutableTabledArrayByte;
import com.susico.utils.arrays.tabled.arraychar.immutable.ImmutableTabledArrayChar;
import com.susico.utils.arrays.tabled.arrayshort.immutable.ImmutableTabledArrayShort;
import com.susico.utils.arrays.tabled.arrayint.immutable.ImmutableTabledArrayInt;
import com.susico.utils.arrays.tabled.arraylong.immutable.ImmutableTabledArrayLong;
import com.susico.utils.arrays.tabled.arrayfloat.immutable.ImmutableTabledArrayFloat;
import com.susico.utils.arrays.tabled.arraydouble.immutable.ImmutableTabledArrayDouble;
import com.susico.utils.arrays.tabled.array.immutable.ImmutableTabledArray;

import com.susico.utils.arrays.tabled.arrayboolean.mutable.MutableTabledArrayBoolean;
import com.susico.utils.arrays.tabled.arraybyte.mutable.MutableTabledArrayByte;
import com.susico.utils.arrays.tabled.arraychar.mutable.MutableTabledArrayChar;
import com.susico.utils.arrays.tabled.arrayshort.mutable.MutableTabledArrayShort;
import com.susico.utils.arrays.tabled.arrayint.mutable.MutableTabledArrayInt;
import com.susico.utils.arrays.tabled.arraylong.mutable.MutableTabledArrayLong;
import com.susico.utils.arrays.tabled.arrayfloat.mutable.MutableTabledArrayFloat;
import com.susico.utils.arrays.tabled.arraydouble.mutable.MutableTabledArrayDouble;
import com.susico.utils.arrays.tabled.array.mutable.MutableTabledArray;


public abstract class TabledArray {
    protected static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final ArrayAccess ARRAY_ACCESS;
    protected final int targetLength;
    protected final int definedAsValues;

    protected TabledArray(final boolean checked, final int definedAsValues, final int length) {
        this.ARRAY_ACCESS = ArrayAccess.checked(checked);
        this.targetLength = length;
        this.definedAsValues = definedAsValues;
    }

    public final int getDefinedAsValues() {
        return definedAsValues;
    }

    public final int getTargetLength() {
        return targetLength;
    }

    public abstract int getActualLength();

    public static  ImmutableTabledArrayBoolean getImmutableBooleanArray(
        final boolean checked, final int length, final @NotNull boolean ... values) {
        return ImmutableTabledArrayBoolean.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayByte getImmutableByteArray(
        final boolean checked, final int length, final @NotNull byte ... values) {
        return ImmutableTabledArrayByte.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayChar getImmutableCharArray(
        final boolean checked, final int length, final @NotNull char ... values) {
        return ImmutableTabledArrayChar.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayShort getImmutableShortArray(
        final boolean checked, final int length, final @NotNull short ... values) {
        return ImmutableTabledArrayShort.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayInt getImmutableIntArray(
        final boolean checked, final int length, final @NotNull int ... values) {
        return ImmutableTabledArrayInt.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayLong getImmutableLongArray(
        final boolean checked, final int length, final @NotNull long ... values) {
        return ImmutableTabledArrayLong.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayFloat getImmutableFloatArray(
        final boolean checked, final int length, final @NotNull float ... values) {
        return ImmutableTabledArrayFloat.getInstance(checked, length, values);
    }

    public static  ImmutableTabledArrayDouble getImmutableDoubleArray(
        final boolean checked, final int length, final @NotNull double ... values) {
        return ImmutableTabledArrayDouble.getInstance(checked, length, values);
    }

    public static <T> ImmutableTabledArray<T> getImmutableArray(
        final boolean checked, final int length, final @NotNull T ... values) {
        return ImmutableTabledArray.<T>getInstance(checked, length, values);
    }

    public static  MutableTabledArrayBoolean getMutableBooleanArray(
        final boolean checked, final int length, final @NotNull boolean ... values) {
        return MutableTabledArrayBoolean.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayByte getMutableByteArray(
        final boolean checked, final int length, final @NotNull byte ... values) {
        return MutableTabledArrayByte.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayChar getMutableCharArray(
        final boolean checked, final int length, final @NotNull char ... values) {
        return MutableTabledArrayChar.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayShort getMutableShortArray(
        final boolean checked, final int length, final @NotNull short ... values) {
        return MutableTabledArrayShort.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayInt getMutableIntArray(
        final boolean checked, final int length, final @NotNull int ... values) {
        return MutableTabledArrayInt.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayLong getMutableLongArray(
        final boolean checked, final int length, final @NotNull long ... values) {
        return MutableTabledArrayLong.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayFloat getMutableFloatArray(
        final boolean checked, final int length, final @NotNull float ... values) {
        return MutableTabledArrayFloat.getInstance(checked, length, values);
    }

    public static  MutableTabledArrayDouble getMutableDoubleArray(
        final boolean checked, final int length, final @NotNull double ... values) {
        return MutableTabledArrayDouble.getInstance(checked, length, values);
    }

    public static <T> MutableTabledArray<T> getMutableArray(
        final boolean checked, final int length, final @NotNull T ... values) {
        return MutableTabledArray.<T>getInstance(checked, length, values);
    }
}