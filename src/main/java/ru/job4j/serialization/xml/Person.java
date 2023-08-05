package ru.job4j.serialization.xml;

/**
 * 3. Формат XML [#313165 # [#313165].
 */
public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String worker;
    private final String married;


    public Person(boolean sex, int age, Contact contact, String worker, String married) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.worker = worker;
        this.married = married;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact;
    }
}
