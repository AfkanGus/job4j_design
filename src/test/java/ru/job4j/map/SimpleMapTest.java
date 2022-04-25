package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void put() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl0 = map.put(0, "0");
        boolean rsl1 = map.put(1, "1");
        boolean rsl2 = map.put(2, "2");
        boolean rsl3 = map.put(3, "3");
        boolean rsl4 = map.put(4, "4");
        boolean rsl5 = map.put(5, "5");
        boolean rsl6 = map.put(6, "6");
        boolean rsl7 = map.put(7, "7");
        boolean rsl8 = map.put(8, "8");
        boolean rsl9 = map.put(9, "9");
        boolean rsl10 = map.put(10, "9");
        boolean rsl11 = map.put(11, "11");
        boolean rsl12 = map.put(12, "12");
        boolean rsl13 = map.put(13, "13");
        boolean rsl14 = map.put(14, "14");
        boolean rsl15 = map.put(15, "15");
        boolean rsl16 = map.put(16, "16");
        boolean rsl17 = map.put(17, "17");
        boolean rsl18 = map.put(18, "18");
        boolean rsl19 = map.put(19, "19");
        boolean rsl20 = map.put(20, "20");
        assertTrue(rsl20);
    }

    @Test
    public void putInBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "0");
        boolean rsl1 = map.put(0, "1");
        assertTrue(rsl);
        assertFalse(rsl1);
    }

    @Test
    public void getAndPut() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "0");
        boolean rsl1 = map.put(1, "0");
        String expected = "0";
        assertThat(map.get(0), is(expected));
        assertThat(map.get(1), is(expected));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "1");
        String expected = "1";
        assertThat(map.get(0), is(expected));
        assertNull(map.get(1));
    }

    @Test
    public void removeTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "1");
        map.put(1, "2");
        assertTrue(map.remove(0));
        assertTrue(map.remove(1));
    }

    @Test
    public void removeFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(0));
        assertFalse(map.remove(1));
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        Assert.assertEquals(Integer.valueOf(1), map.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), map.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}