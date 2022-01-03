package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 1. Что такое итератор. [#4951].
 */
public class BackwardArrayItTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{}
        );
        it.next();
    }
}