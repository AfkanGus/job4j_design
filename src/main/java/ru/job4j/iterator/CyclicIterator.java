package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 6. Циклический итератор [#505082].
 * Итератор должен обходит коллекцию, а потом после получания последнего элемента
 * снова вернутся к первому элементу и дальше продолжан итерацию.
 * Логика.Установим текущее положения курсора итератора point = 0;
 * Проверим наличие элементов в коллекции.
 * Получим элемент коллекции по текущему положению поинт.
 * Когда достигнет конец коллекции, перейдем к началу.
 * Иначе переходим к следующему элементу.
 * и вернем текущей элемент.
 */
public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;
    private int point;

    CyclicIterator(List<T> data) {
        this.data = data;
        this.point = 0;

    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T element = data.get(point);
        if (point == data.size() - 1) {
            point = 0;
        } else {
            point++;
        }
        return element;
    }
}
