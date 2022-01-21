package ru.job4j.list;

import java.util.*;

/**
 * 1. Добавление элементов.
 */
public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.add(1, "four");
        List<String> list = new ArrayList<>();
        list.add("five");
        list.add("six");
        rsl.addAll(list);
        /* добавл. элм. и сдвиг элм.в право со второго индекса) */
        rsl.addAll(6, list);
        System.out.println("4. Удаление элементов из списка");
        rsl.removeIf(s -> s.length() <= 3);
        for (String s : rsl) {
            System.out.println("Current element - " + s);
        }
        /*
        2. Чтение элементов из списка.
         */
        System.out.println("2. Чтение элементов из списка");
        System.out.println("get()");
        for (int i = 0; i < rsl.size(); i++) {
            System.out.println("Current element " + rsl.get(i));
            rsl.set(1, "two adn second");
        }
        System.out.println("Iterator<E> iterator()");
        Iterator<String> iterator = rsl.iterator();
        while (iterator.hasNext()) {
            System.out.println("Current element " + iterator.next());
        }
        System.out.println("ListIterator<E> listIterator(int index)");
        ListIterator<String> iterator1 = rsl.listIterator(2);
        while (iterator1.hasNext()) {
            System.out.println("Current element " + iterator1.next());
        }
        System.out.println("3. Изменение элементов в списке");
        List<String> rsl1 = new ArrayList<>();
        rsl1.add("one");
        rsl1.add("two");
        rsl1.add("three");
        rsl1.remove("three");
        rsl1.set(1, "two adn second");
        System.out.println("4. Удаление элементов из списка");
        rsl1.remove(1);
        rsl1.replaceAll(String::toUpperCase);
        rsl1.removeAll(rsl1);
        for (String a : rsl1) {
            System.out.println(rsl1);
        }
        List<String> l = new ArrayList<>();
        l.add("one");
        l.add("two");
        l.add("three");
        l.add("one");
        int r = l.lastIndexOf("one");
        System.out.println(r);
        boolean b = l.contains("two");
        System.out.println("list have element " + b);
        int i = l.indexOf("two");
        System.out.println(i);
        List<String> stringList = l.subList(1, 2);
        l.sort(Comparator.reverseOrder());
        for (String q : l) {
            System.out.println(q);
        }
    }
}
