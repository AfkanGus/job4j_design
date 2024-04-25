package ru.job4j;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор. [#4951].
 * Итератор должен отдавать элементы в обратном порядке
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        BackwardArrayIt it = new BackwardArrayIt(array);
        System.out.println("iterator the array");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
