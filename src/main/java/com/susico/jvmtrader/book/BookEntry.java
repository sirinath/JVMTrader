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

package com.susico.jvmtrader.book;

import com.susico.utils.memory.pool.PooledObject;

/**
 * Created by sirin_000 on 21/09/2015.
 */
public final class BookEntry extends PooledObject {
    protected long marketMaker;
    protected long instrument;
    protected Side side;
    protected double price;
    protected long size;

    protected BookEntry() {}

    public static BookEntry getInstance(final long marketMaker, final long instrument, final Side side, final double price, final long size) {
        BookEntry be = getFromPoolOrSupplierIfAbsent(BookEntry.class, BookEntry::new);
        be.set(marketMaker, instrument, side, price, size);

        return be;
    }

    protected void set(final long marketMaker, final long instrument, final Side side, final double price, final long size) {
        this.marketMaker = marketMaker;
        this.instrument = instrument;
        this.side = side;
        this.price = price;
        this.size = size;
    }

    public long getMarketMaker() {
        return this.marketMaker;
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

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof BookEntry &&
                ((BookEntry) obj).marketMaker == this.marketMaker &&
                ((BookEntry) obj).instrument == this.instrument &&
                ((BookEntry) obj).side == this.side &&
                ((BookEntry) obj).price == this.price &&
                ((BookEntry) obj).size == this.size;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(marketMaker) ^
                Long.hashCode(instrument) ^
                Double.hashCode(price) ^
                Long.hashCode(size) ^
                side.hashCode();
    }
}
