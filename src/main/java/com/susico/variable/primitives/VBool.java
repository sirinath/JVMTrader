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

import com.susico.boxonce.BoxOnce;

/**
 * Wrapper class
 * 
 * @author sirinath
 * 
 */
@SuppressWarnings("serial")
public class VBool extends Number implements BoxOnce<VBool> {
	/**
	 * Value
	 */
	public boolean	value;
	
	/**
	 * @param i
	 *           Parameter
	 */
	public VBool(final boolean i) {
		value = i;
	}
	
	@Override
	public int hashCode() {
		return value ? 1 : 0;
	}
	
	@Override
	public VBool clone() throws CloneNotSupportedException {
		return new VBool(value);
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public int compareTo(final VBool o) {
		return value == o.value ? 0 : (o.value ? -1 : 1);
	}
	
	@Override
	public int intValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public long longValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public float floatValue() {
		return value ? 1 : 0;
	}
	
	@Override
	public double doubleValue() {
		return value ? 1 : 0;
	}
}
