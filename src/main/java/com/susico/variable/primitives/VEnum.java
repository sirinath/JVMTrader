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

package com.susico.variable.primitives;

/**
 * Wrapper class
 * 
 * @author sirinath
 * @param <T>
 *           Enum type
 * 
 */
@SuppressWarnings("serial")
public class VEnum<T extends Enum<T>> extends Number implements
		BoxOnce<VEnum<T>> {
	/**
	 * Value
	 */
	public T	value;
	
	/**
	 * @param i
	 *           Parameter
	 */
	public VEnum(final T i) {
		value = i;
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public VEnum<T> clone() throws CloneNotSupportedException {
		return new VEnum<T>(value);
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public int compareTo(final VEnum<T> o) {
		return value.compareTo(o.value);
	}
	
	@Override
	public int intValue() {
		return value.ordinal();
	}
	
	@Override
	public long longValue() {
		return value.ordinal();
	}
	
	@Override
	public float floatValue() {
		return value.ordinal();
	}
	
	@Override
	public double doubleValue() {
		return value.ordinal();
	}
}
