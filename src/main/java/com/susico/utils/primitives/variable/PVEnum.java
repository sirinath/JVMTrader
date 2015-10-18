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

package com.susico.utils.primitives.variable;

import com.susico.utils.box.BoxOnce;

/**
 * Wrapper class
 *
 * @param <T> Enum type
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class PVEnum<T extends Enum<T>> extends Number implements
        BoxOnce<PVEnum<T>> {
    /**
     * Value
     */
    private T value;

    /**
     * @param i Parameter
     */
    public PVEnum(final T i) {
        value = i;
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }

    @Override
    public final PVEnum<T> clone() throws CloneNotSupportedException {
        return new PVEnum<T>(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    @Override
    public final int compareTo(final PVEnum<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public final int intValue() {
        return value.ordinal();
    }

    @Override
    public final long longValue() {
        return value.ordinal();
    }

    @Override
    public final float floatValue() {
        return value.ordinal();
    }

    @Override
    public double doubleValue() {
        return value.ordinal();
    }

    public final T getValue() {
        return value;
    }

    public final void setValue(final T value) {
        this.value = value;
    }
}
