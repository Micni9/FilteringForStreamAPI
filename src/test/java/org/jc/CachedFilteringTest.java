package org.jc;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class CachedFilteringTest {

    @Test
    void check() {
        Predicate<String> containsA = str -> str.contains("A");
        CachedFiltering<String> filtering = new CachedFiltering<>(containsA);

        assertTrue(filtering.check("A"));
        assertFalse(filtering.check("B"));
        assertFalse(filtering.check(""));
        assertFalse(filtering.check(null));
        assertTrue(filtering.check("AB"));
    }
}