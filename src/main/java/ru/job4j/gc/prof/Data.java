package ru.job4j.gc.prof;

/**
 * 3. Эксперименты с различными GC [#1590].
 */
public interface Data {
    void insert(int elements);

    int[] getClone();
}
