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

import java.util.AbstractList;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class OrderBookSegment extends AbstractList<BookEntry> {
    @Override
    public void add(int index, BookEntry element) {
        set(index, element);
    }

    @Override
    public BookEntry set(int index, BookEntry element) {
    }

    @Override
    public BookEntry remove(int index) {
    }

    @Override
    public BookEntry get(final int index) {
        return null;
    }

    @Override
    public void forEach(final Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return Spliterators.spliterator(iterator(), size(), Spliterator.CONCURRENT);
    }

    @Override
    public Stream stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Stream parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean removeIf(final Predicate filter) {
        return false;
    }

    @Override
    public void replaceAll(final UnaryOperator operator) {

    }

    @Override
    public void sort(final Comparator c) {

    }
}
