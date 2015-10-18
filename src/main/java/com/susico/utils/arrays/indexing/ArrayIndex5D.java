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
public final class ArrayIndex5D {
    private final int dimensionM;
    private final int dimensionL;
    private final int dimensionK;
    private final int dimensionJ;
    private final int dimensionI;
    private final int length;

    public ArrayIndex5D(final int dimensionM, final int dimensionL, final int dimensionK, final int dimensionJ, final int dimensionI) {
        this.dimensionM = dimensionM;
        this.dimensionL = dimensionL;
        this.dimensionK = dimensionK;
        this.dimensionJ = dimensionJ;
        this.dimensionI = dimensionI;
        this.length = length(dimensionM, dimensionL, dimensionK, dimensionJ, dimensionI);
    }

    public static int length(final int dimensionM, final int dimensionL, final int dimensionK, final int dimensionJ, final int dimensionI) {
        return dimensionM * dimensionL * dimensionK * dimensionJ * dimensionI;
    }

    public static int linearised(final int dimensionL, final int dimensionK, final int dimensionJ, final int dimensionI, final int m, final int l, final int k, final int j, final int i) {
        return dimensionL * m + dimensionK * l + dimensionJ * k + dimensionI * j + i;
    }

    public final int linearised(final int m, final int l, final int k, final int j, final int i) {
        return dimensionL * m + dimensionK * l + dimensionJ * k + dimensionI * j + i;
    }

    public final int getDimensionI() {
        return dimensionI;
    }

    public final int getDimensionJ() {
        return dimensionJ;
    }

    public final int getDimensionK() {
        return dimensionK;
    }

    public final int getDimensionL() {
        return dimensionL;
    }

    public final int getDimensionM() {
        return dimensionM;
    }

    public final int getLength() {
        return length;
    }
}
