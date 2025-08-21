package org.jc;

import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class BaseFiltering<T> {
    abstract Boolean check(T obj);

    // To use in stream api.
    public Predicate<T> getPredicate() {
        return this::check;
    }
    public Stream<T> filter(Stream<T> objStream) {
        return objStream.filter(getPredicate());
    }
}
