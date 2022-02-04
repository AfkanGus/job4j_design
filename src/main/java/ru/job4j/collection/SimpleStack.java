package ru.job4j.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * pop() - возвращает значение и удаляет его из коллекции.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * push() - помещает значение в коллекцию.
     *
     * @param value - само значение.
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}
