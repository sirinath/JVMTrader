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
 * Help instantiate boxing
 * 
 * @author sirinath
 * 
 */
public class VUtils {
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VBool VBox(final boolean v) {
		return new VBool(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VByte VBox(final byte v) {
		return new VByte(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VChar VBox(final char v) {
		return new VChar(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VDouble VBox(final double v) {
		return new VDouble(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VFloat VBox(final float v) {
		return new VFloat(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VInt VBox(final int v) {
		return new VInt(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VLong VBox(final long v) {
		return new VLong(v);
	}
	
	/**
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static VShort VBox(final short v) {
		return new VShort(v);
	}
	
	/**
	 * @param <T>
	 *           Enum type
	 * @param v
	 *           value to be boxed
	 * @return boxed value
	 */
	public static <T extends Enum<T>> VEnum<T> VBox(final T v) {
		return new VEnum<T>(v);
	}
}
