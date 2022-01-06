package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 5.1.4. FlatMap для Iterator<Iterator>.
 */
public class FlatMap<T> implements Iterator<T> {
    /**
     * C- коллекции
     * cursor = Collections.emptyIterator() - метод
     */
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Метод вернет true, проверив наличие элементов в Iterator<Iterator<T>> data;
     * (!cursor.hasNext() && data.hasNext()) - пока условие true,
     * будет проверятся наличие следующего
     * элемента.
     *
     * @return true при наличии cursor
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    /**
     * Метод next должен последовательно вернуть числа из вложенных итераторов.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
