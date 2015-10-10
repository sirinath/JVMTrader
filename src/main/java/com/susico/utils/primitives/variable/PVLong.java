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

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class PVLong extends Number implements BoxOnce<PVLong> {
    /**
     * Value
     */
    private long value;

    /**
     * @param i Parameter
     */
    public PVLong(final long i) {
        value = i;
    }

    @Override
    public final int hashCode() {
        return (int) (value + (value >> Integer.SIZE));
    }

    @Override
    public final PVLong clone() throws CloneNotSupportedException {
        return new PVLong(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    @Override
    public final int compareTo(final PVLong o) {
        return value == o.value ? 0 : (value < o.value ? -1 : 1);
    }

    @Override
    public final int intValue() {
        return (int) value;
    }

    @Override
    public final long longValue() {
        return value;
    }

    @Override
    public final float floatValue() {
        return value;
    }

    @Override
    public final double doubleValue() {
        return value;
    }

    public final long getValue() {
        return value;
    }

    public final void setValue(final long value) {
        this.value = value;
    }
}
