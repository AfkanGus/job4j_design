package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа [#150].
 * Объекты класса модели должы реализовать интерфейс Iterator.
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * boolean hasNext() - проверяет налчие следующих друг за другом элементов.
     * Если (index < data.length && data[index] % 2 != 0) - false, перейти на следующий индекс.
     *
     * @return возвращает true, только если в массиве есть четные перед указателем
     */
    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index < data.length && data[index] % 2 == 0;
    }

    /**
     * Iterator проходит по элементам data[] при помощи курсора начиная
     * до первого элемента, если hasNext() вурнул false срабатывает NoSuchElementException(),
     * если true, вернул четные элементы.
     *
     * @return next() далее возвращает каждый следующий элемент - data[index++].
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
