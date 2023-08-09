package ru.job4j.serialization.json;

import java.util.Arrays;

/**
 * 2. Формат JSON [#313164  [#313164]].
 * Класс Person представляет объект, который содержит информацию о пользователе.
 */
public class Person {
    private boolean sex;
    private int age;
    private Contact contact;
    private String[] statuses;

    public Person(boolean sex, int age, Contact contact, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public Person(boolean b, int i, Contact contact, String worker, String married) {
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public boolean getSex() {
        return false;
    }
}
