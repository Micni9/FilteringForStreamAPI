package org.jc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CachedFiltering<T> extends BaseFiltering<T>{

    private final Map<T, Boolean> cacheMap;
    private final Predicate<T> predicate;

    public CachedFiltering(Predicate<T> validator) {
        this.predicate = validator;
        this.cacheMap = new HashMap<>();
    }

    @Override
    public Boolean check(T obj) {
        if (obj == null) {
            return false;
        }
        cacheMap.computeIfAbsent(obj, predicate::test);
        return cacheMap.get(obj);
    }
}