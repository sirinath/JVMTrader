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
 * 
 */
@SuppressWarnings("serial")
public class VChar extends Number implements BoxOnce<VChar> {
	/**
	 * Value
	 */
	private char	value;
	
	/**
	 * @param i
	 *           Parameter
	 */
	public VChar(final char i) {
		value = i;
	}
	
	@Override
	public int hashCode() {
		return value;
	}
	
	@Override
	public VChar clone() throws CloneNotSupportedException {
		return new VChar(value);
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public int compareTo(final VChar o) {
		return value == o.value ? 0 : (value < o.value ? -1 : 1);
	}
	
	@Override
	public int intValue() {
		return value;
	}
	
	@Override
	public long longValue() {
		return value;
	}
	
	@Override
	public float floatValue() {
		return value;
	}
	
	@Override
	public double doubleValue() {
		return value;
	}

	public char getValue() {
		return value;
	}

	public void setValue(final char value) {
		this.value = value;
	}
}
