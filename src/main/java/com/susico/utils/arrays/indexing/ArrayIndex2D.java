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

package com.susico.utils.arrays.indexing;

/**
 * Created by sirin_000 on 03/10/2015.
 */
public final class ArrayIndex2D {
    private final int dimensionJ;
    private final int dimensionI;
    private final int length;

    public ArrayIndex2D(final int dimensionJ, final int dimensionI) {
        this.dimensionJ = dimensionJ;
        this.dimensionI = dimensionI;
        this.length = length(dimensionJ, dimensionI);
    }

    public static int length(final int dimensionJ, final int dimensionI) {
        return dimensionJ * dimensionI;
    }

    public static int linearised(final int dimensionI, final int j, final int i) {
        return dimensionI * j + i;
    }

    public final int linearised(final int j, final int i) {
        return dimensionI * j + i;
    }

    public final int getDimensionI() {
        return dimensionI;
    }

    public final int getDimensionJ() {
        return dimensionJ;
    }

    public final int getLength() {
        return length;
    }
}
