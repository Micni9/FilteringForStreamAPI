package org.jc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class BaseFilteringTest {

    private static class MockTest extends BaseFiltering<String> {
        @Override
        public Boolean check(String obj) {
            return obj != null && obj.contains("a");
        }
    }

    private final MockTest mockTest = new MockTest();

    @Test
    void getPredicate() {
        Predicate<String> predicate = mockTest.getPredicate();

        assertFalse(predicate.test(""));
        assertTrue(predicate.test("a"));
        assertFalse(predicate.test(null));
        assertTrue(predicate.test("Haha"));
        assertFalse(predicate.test("AAAA"));
    }

    @Test
    void filter() {
        List<String> stringList = List.of(
                "",
                "AAA",
                "abc",
                "Aa",
                "Hello World!"
        );

        Set<String> filteredSet = Set.of(stringList.get(2), stringList.get(3));

        List<String> filteredList = mockTest.filter(stringList);

        assertEquals(2, filteredList.size());
        filteredList.forEach(str -> {
            assertTrue(filteredSet.contains(str));
        });
    }
}