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
 * 
 */
@SuppressWarnings("serial")
public final class VBool extends Number implements BoxOnce<VBool> {
	/**
	 * Value
	 */
	private boolean	value;
	
	/**
	 * @param i
	 *           Parameter
	 */
	public VBool(final boolean i) {
		value = i;
	}
	
	@Override
	public final int hashCode() {
		return value ? 1 : 0;
	}
	
	@Override
	public final VBool clone() throws CloneNotSupportedException {
		return new VBool(value);
	}
	
	@Override
	public final String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public final int compareTo(final VBool o) {
		return value == o.value ? 0 : (o.value ? -1 : 1);
	}
	
	@Override
	public final int intValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public final long longValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public final float floatValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public final double doubleValue() {
		return value ? 1 : 0;
	}

	public final boolean getValue() {
        return value;
    }

    public final void setValue(final boolean value) {
        this.value = value;
    }
}