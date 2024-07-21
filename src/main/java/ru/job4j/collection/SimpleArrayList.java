package ru.job4j.collection;

import java.util.*;

/**
 * 1. Динамический список на массиве. [#158].
 * SimpleArrayList - класс список на основе динамического массива.
 *
 * @param <T> - тип данных в контейнер.
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

    private void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
        modCount++;
    }

    /**
     * Метод будет добавлять объект в массив container.
     * Проверим равенство счетчика size и массива container.
     * Если они равны, то расширяет массив метод grow().
     * Далее, добавляются объекты в container.
     * и инкремент счетчика modCount++.
     *
     * @param value - объект.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод вставляет новые значения в index.
     *
     * @param index    - указанный индекс.
     * @param newValue - новые объекты.
     * @return предыдущее значение.
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
     * @return - предыдущее значение.
     */
    @Override
    public T remove(int index) {
        T prevValue = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return prevValue;
    }

    /**
     * Метод получает объекты из указанного индекса.
     *
     * @param index - указанный индекс.
     * @return - объект с нужным index.
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
    public List<T> subList(int fromIndex, int toIndex) {
        Objects.checkFromToIndex(fromIndex, toIndex, size);
        List<T> sublist = (List<T>) new ArrayList<T>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(container[i]);
        }
        return sublist;
}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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

    // Реализация остальных методов интерфейса List

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(container, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(container, size, a.getClass());
        }
        System.arraycopy(container, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(o, container[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.checkIndex(index, size);
        int numNew = c.size();
        if (numNew == 0) {
            return false;
        }
        if (numNew > container.length - size) {
            grow();
        }
        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(container, index, container, index + numNew, numMoved);
        }
        for (T e : c) {
            container[index++] = e;
        }
        size += numNew;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c) {
            while (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(container[i])) {
                remove(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            container[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, container[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, container[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<>() {
            private int cursor = index;
            private int lastRet = -1;
            int expectedModCount = modCount;

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
                lastRet = cursor;
                return container[cursor++];
            }

            @Override
            public boolean hasPrevious() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                lastRet = --cursor;
                return container[cursor];
            }

            @Override
            public int nextIndex() {
                return cursor;
            }

            @Override
            public int previousIndex() {
                return cursor - 1;
            }

            @Override
            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                SimpleArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            }

            @Override
            public void set(T e) {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                SimpleArrayList.this.set(lastRet, e);
            }

            @Override
            public void add(T e) {
                int i = cursor;
                SimpleArrayList.this.add(e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            }
        };
    }
}

