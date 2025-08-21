package org.jc;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetFiltering<T> extends BaseFiltering<T> {

    private final Set<T> validSet;

    public SetFiltering(List<T> validList) {
        validSet = new HashSet<>(validList);
    }

    public void add(T obj) {
        validSet.add(obj);
    }

    public void addAll(Collection<T> objects) {
        validSet.addAll(objects);
    }

    @Override
    public Boolean check(T obj) {
        return validSet.contains(obj);
    }

}
