package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public static void main(String[] args) {
        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);

        List<? super Integer> list = new ArrayList<>();
        new GenericUsage().addAll(list);
    }

    //WIndCard -<?> - устраняет проблему не совместимости типов
    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator();
             it.hasNext();
        ) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    //Bounded Wildcard - <? extends Person> - ограничение сверху,
    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator();
             it.hasNext();
        ) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    /*
    Lower bounded Wildcard - <? super A>- Ограниченный снизу. Используется, когда нужно работать
    то только с типом А, но и с суперкласами обьекта А - Number и Object.
     */
    public void addAll(List<? super Integer> list) {
        for (int i = 0; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Current element " + o);
        }
    }
}

