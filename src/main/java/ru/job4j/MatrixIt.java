package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод проверяет наличие элементов в ячейках двумерного массива.
     *
     * @return true если next вернет элементы и не выбросит NoSuchElementException.
     */
    @Override
    public boolean hasNext() {
        for (int i = row; i < data.length; i++) {
            if (data[i].length == column) {
                row++;
                column = 0;
            } else {
                break;
            }
        }
        return data.length > row;
    }

    /**
     * Итератор получает элементы и через проверку, при отсутсвии элемента
     * выбросит NoSuchElementException.
     * @return вернет последовательно элементы массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}

