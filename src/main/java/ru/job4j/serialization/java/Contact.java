package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.io.Serializable;

/**
 * Класс Contact позволяет создавать объекты с почтовыми индексами и номерами
 * телефонов, а также сохранять их во временный файл и восстанавливать из файлов при необходимости.
 * Интерфейс Serializable позволяет объектам этого класса быть сериализованными
 * и десериализованными для передачи по сети или сохранения/загрузки на диск.
 * Поле serialVersionUID должно быть объявлено в классе, который реализует интерфейс Serializable,
 * чтобы обеспечить совместимость версий классов при десериализации объектов.
 */
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + '\'' + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println("Первоначальный объект");
            System.out.println(contact);
            System.out.println("Десерилизованный объект");
            System.out.println(contactFromFile);
            if (contact.equals(contactFromFile)) {
                System.out.println("Объекты равны");
            } else {
                System.out.println("Объекты не равны");
            }
        }
    }
}
