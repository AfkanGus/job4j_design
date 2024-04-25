package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArIte implements Iterator<Integer> {
    /* отдавать элементы в обатном порядке*/
    private final int[] data;
    private int point;

    public BackwardArIte(int[] data) {
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
}
