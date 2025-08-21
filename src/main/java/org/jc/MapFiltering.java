package org.jc;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapFiltering<T,V> extends BaseFiltering<T>{
    Map<V, SetFiltering<T>> validMap;

    public MapFiltering(Map<T,V> objMap) {
        validMap = new HashMap<>();
        objMap.values().stream().distinct().forEach(value -> validMap.put(value, new SetFiltering<>(List.of())));
        objMap.forEach((key, value) -> validMap.get(value).add(key));
    }

    public MapFiltering(Collection<T> objects, Function<T, V> classifier) {
        validMap = new HashMap<>();
        objects.forEach(
                obj -> add(classifier.apply(obj), obj)
        );
    }

    public void add(V value, T obj) {
        if (validMap.containsKey(value)) {
            validMap.get(value).add(obj);
        } else {
            validMap.put(value, new SetFiltering<>(List.of(obj)));
        }
    }

    public void addAll(V value, Collection<T> objects) {
        if (validMap.containsKey(value)) {
            validMap.get(value).addAll(objects);
        } else {
            validMap.put(value, new SetFiltering<>(objects));
        }
    }

    @Override
    Boolean check(T obj) {
        return validMap.values().stream().anyMatch(setFiltering -> setFiltering.check(obj));
    }

    Boolean check(V value, T obj) {
        if (validMap.containsKey(value)) {
            return validMap.get(value).check(obj);
        }
        return false;
    }

    Boolean check(T obj, Function<T, V> classifier) {
        return check(classifier.apply(obj), obj);
    }

    Predicate<T> getPredicate(V value) {
        return obj -> check(value, obj);
    }

    Predicate<T> getPredicate(Function<T, V> classifier) {
        return obj -> check(obj, classifier);
    }
}
