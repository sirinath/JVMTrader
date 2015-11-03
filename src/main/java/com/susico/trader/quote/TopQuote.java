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

package com.susico.trader.quote;

import com.susico.utils.memory.pool.PooledObject;

public final class TopQuote extends PooledObject {
    protected long instrument = 0;
    protected Side side = null;
    protected double price = Double.NaN;
    protected long size = 0;

    protected TopQuote() {}

    public static TopQuote getInstance(final long instrument, final Side side, final double price, final long size) {
        TopQuote tq = getFromPoolOrSupplierIfAbsent(TopQuote.class, TopQuote::new);

        tq.set(instrument, side, price, size);

        return tq;
    }

    protected void set(final long instrument, final Side side, final double price, final long size) {
        this.instrument = instrument;
        this.side = side;
        this.price = price;
        this.size = size;
    }

    public long getInstrument() {
        return this.instrument;
    }

    public Side getSide() {
        return this.side;
    }

    public double getPrice() {
        return this.price;
    }

    public long getSize() {
        return this.size;
    }
}
