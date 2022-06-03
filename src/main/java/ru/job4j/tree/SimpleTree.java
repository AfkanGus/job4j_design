package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> nodeParent = findBy(parent);
        boolean rsl = nodeParent.isPresent() && findBy(child).isEmpty();
        if (rsl) {
            nodeParent.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = E -> E.value.equals(value);
        return findByPredicate(predicate);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> predicate = E -> E.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    @Override
    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
