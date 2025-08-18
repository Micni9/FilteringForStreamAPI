package org.jc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListFilteringTest {
    @Test
    void check() {
        List<String> stringList = List.of("A", "", "B");
        ListFiltering<String> filtering = new ListFiltering<>(stringList);

        assertTrue(filtering.check("A"));
        assertTrue(filtering.check("B"));
        assertTrue(filtering.check(""));
        assertFalse(filtering.check(null));
        assertFalse(filtering.check("AB"));
    }
}