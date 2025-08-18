package org.jc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListFiltering<T> extends BaseFiltering<T> {

    private final Set<T> validSet;

    public ListFiltering(List<T> validList) {
        validSet = new HashSet<>(validList);
    }

    @Override
    public Boolean check(T obj) {
        return validSet.contains(obj);
    }

}
