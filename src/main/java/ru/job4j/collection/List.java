package ru.job4j.collection;

import java.util.Collection;
import java.util.ListIterator;

/**
 * 1. Динамический список на массиве. [#158].
 */
public interface List<T> extends Iterable<T> {
    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();

    List<T> subList(int fromIndex, int toIndex);

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    <T1> T1[] toArray(T1[] a);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends T> c);

    boolean addAll(int index, Collection<? extends T> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);
}
