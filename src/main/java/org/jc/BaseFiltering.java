package org.jc;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseFiltering<T> {
    abstract Boolean check(T obj);

    // To use in stream api.
    public Predicate<T> getPredicate() {
        return this::check;
    }
    public List<T> filter(List<T> objList) {
        return objList.stream().filter(getPredicate()).toList();
    }
}
