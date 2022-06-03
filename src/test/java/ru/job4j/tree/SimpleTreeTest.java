package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenBinary() {
        Tree<Integer> integerTree = new SimpleTree<>(100);
        integerTree.add(50, 30);
        integerTree.add(40, 20);
        integerTree.add(101, 120);
        integerTree.add(120, 110);
        assertTrue(integerTree.isBinary());
    }

    @Test
    public void whenNotBinary() {
        Tree<Integer> integerTree = new SimpleTree<>(100);
        integerTree.add(100, 30);
        integerTree.add(100, 20);
        integerTree.add(100, 120);
        integerTree.add(120, 110);
        assertFalse(integerTree.isBinary());
    }
}