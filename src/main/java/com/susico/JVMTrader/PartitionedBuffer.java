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

package com.susico.JVMTrader;

/**
 * Created by sirin_000 on 03/09/2015.
 */
public interface PartitionedBuffer {
    public final static int PARTITION_SIZE = Integer.MAX_VALUE >> 2;
    public final static int SEGMENT_BITS = Long.numberOfTrailingZeros(~PARTITION_SIZE);

    public static long segment(long i) {
        return i >> SEGMENT_BITS;
    }

    public static long offset(long i) {
        return PARTITION_SIZE & i;
    }

    public int getSegments();
}
