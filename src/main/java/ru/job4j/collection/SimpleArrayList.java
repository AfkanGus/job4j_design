package ru.job4j.collection;

import java.util.*;

/**
 * SimpleArrayList - класс список на основе динамического массива.
 *
 * @param <T> - тип даннных в контейнер.
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    /**
     * Конструктор по умолчанию.
     *
     * @param capacity - размер конструктора.
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод grow() - расширит container в два раза.
     *
     * @return
     */
    private T[] grow(T value) {
        container = Arrays.copyOf(container, container.length * 2);
        return Arrays.copyOf(container, 10);
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
     * @param index
     * @return - prevIndex.
     */
    @Override
    public T remove(int index) {
        T prevIndex = get(index);
        System.arraycopy(container, index, container, index + 1, size - index);
        container[size--] = null;
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
            private int expectedModCount = modCount;

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
