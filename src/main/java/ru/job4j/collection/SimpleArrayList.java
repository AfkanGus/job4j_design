package ru.job4j.collection;

import java.util.*;

/**
 * 1. Динамический список на массиве. [#158].
 * SimpleArrayList - класс список на основе динамического массива.
 *
 * @param <T> - тип даннных в контейнер.
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    /**
     * size- счетчик - указывающий на актуальный размер массива container
     */
    private int size = 0;
    /**
     * modCount - счетчик для определения общего количества структурных изменений, сделанных в этой коллекции
     * необходим для корректной работы iterator
     */
    private int modCount;

    /**
     * Конструктор по умолчанию.
     *
     * @param capacity - размер конструктора.
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow(T value) {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
        container = Arrays.copyOf(container, container.length * 2);
        modCount++;
    }

    /**
     * Метод будет добавлять объект в массив container.
     * Проверим равенство счетчика size и массива container.
     * Если они равны, то расширяет массив метод grow().
     * Далее, добавляются объеты в container.
     * и инкремент счетчика modCount++.
     *
     * @param value - объект.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            grow(value);
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод вставляет новые значения в index.
     *
     * @param index    - указанный индекс.
     * @param newValue - новые объекты.
     * @return преджный индекс.
     */
    @Override
    public T set(int index, T newValue) {
        T prevValue = get(index);
        container[index] = newValue;
        return prevValue;
    }


    /**
     * Метод удалит объекты в index.
     *
     * @param index-
     * @return - prevIndex.
     */
    @Override
    public T remove(int index) {
        T prevIndex = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return prevIndex;
    }

    /**
     * Метод получает объекты из указанного индекса.
     *
     * @param index - указанный индекс.
     * @return - объкт с нужным index.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Метод size() - вернет количество элементов массива.
     *
     * @return - размер container.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
