package org.jc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetFilteringTest {
    @Test
    void check() {
        List<String> stringList = List.of("A", "", "B");
        SetFiltering<String> filtering = new SetFiltering<>(stringList);

        assertTrue(filtering.check("A"));
        assertTrue(filtering.check("B"));
        assertTrue(filtering.check(""));
        assertFalse(filtering.check(null));
        assertFalse(filtering.check("AB"));
    }

    @Test
    void add() {
        String added = "Add to test";
        SetFiltering<String> filtering = new SetFiltering<>(List.of());
        filtering.add(added);

        assertTrue(filtering.check(added));

    }

    @Test
    void addAll() {
        String added1 = "Add to test1";
        String added2 = "Add to test2";
        SetFiltering<String> filtering = new SetFiltering<>(List.of());
        filtering.addAll(List.of(added1, added2));

        assertTrue(filtering.check(added1));
        assertTrue(filtering.check(added2));
    }
}