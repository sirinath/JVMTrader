
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


package com.susico.utils.box.immutable;

import java.util.Objects;

import com.susico.utils.UnsafeAccess;
import sun.misc.Unsafe;
import sun.misc.Contended;

import com.susico.utils.functions.*;
import com.susico.utils.box.*;
import com.susico.utils.box.immutable.*;
import com.susico.utils.box.mutable.*;

import org.jetbrains.annotations.*;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class ImmutableObject<T> 
    implements BoxOnce<ImmutableObject<T>> {
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(ImmutableObject.class, "value");

    /**
     * Value
     */
    @Contended private final T value;

    /**
     * @param i Parameter
     */
    public ImmutableObject(final @NotNull T i) {
        value = i;
    }

    @Override
    public final ImmutableObject<T> clone() throws CloneNotSupportedException {
        return new ImmutableObject<T>(value);
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final @NotNull T getValue() {
        return (T) value;
    }

    public final @NotNull T get() {
        return (T) value;
    }

    public final @NotNull T getValueVolatile() {
        return (T) UNSAFE.getObjectVolatile(this, valueFieldOffset);
    }
    
    @Override
    public final boolean equals(Object other) {
        if (other instanceof ImmutableObject)
            return value.equals(((ImmutableObject<T>) other).getValue());
        else if (other instanceof MutableObject)
            return value.equals(((MutableObject) other).getValue());
        else if (other instanceof Object)
            return other.equals(value);
        else
            return false;
    }
    @Override
    public final int compareTo(final @NotNull ImmutableObject<T> other) {
        final @NotNull T otherValue = other.getValue();
        if (value instanceof Comparable)
            return ((Comparable) value).compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - ((Comparable) otherValue).compareTo(value);
        else
            throw new IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }

    public final int compareTo(final @NotNull MutableObject<T> other) {
        final @NotNull T otherValue = other.getValue();
        if (value instanceof Comparable)
            return ((Comparable) value).compareTo(otherValue);
        else if (value == otherValue || (value != null && value.equals(otherValue)))
            return 0;
        else if (otherValue instanceof Comparable)
            return - ((Comparable) otherValue).compareTo(value);
        else
            throw new IllegalStateException(value + " cannot be compared with: " + otherValue + " as neither Object impliments Comparable");
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }
}