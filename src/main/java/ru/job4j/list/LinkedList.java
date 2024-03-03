package ru.job4j.list;

public class LinkedList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    transient int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;

        } else {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        list.add("ONe");
        list.add("Two");
    }

}

