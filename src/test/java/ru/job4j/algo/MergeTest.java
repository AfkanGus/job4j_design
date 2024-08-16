package ru.job4j.algo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Сортировка слиянием [#504997].
 */
class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenAlreadySortedArrayThenNoChange() {
        int[] array = {1, 2, 3, 4, 5, 6};
        assertThat(Merge.mergesort(array)).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void whenReversedArrayThenSorted() {
        int[] array = {6, 5, 4, 3, 2, 1};
        assertThat(Merge.mergesort(array)).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void whenAllElementsAreSameThenNoChange() {
        int[] array = {5, 5, 5, 5, 5};
        assertThat(Merge.mergesort(array)).containsExactly(5, 5, 5, 5, 5);
    }

    @Test
    void whenSingleElementArrayThenNoChange() {
        int[] array = {42};
        assertThat(Merge.mergesort(array)).containsExactly(42);
    }

    @Test
    void whenEmptyArrayThenReturnEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }
}