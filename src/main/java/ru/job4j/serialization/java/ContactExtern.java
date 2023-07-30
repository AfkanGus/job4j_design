package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

/**
 * При использовании интерфейса Externalizable не требуется определять статическое поле
 * serialVersionUID, которое используется при стандартной сериализации с помощью
 * интерфейса Serializable. Так как  Интерфейс Externalizable предоставляет полный
 * контроль над сериализацией и десериализацией объектов.
 */
public class ContactExtern implements Externalizable {
    private int zipCode;
    private String phone;

    public ContactExtern() {
    }

    public ContactExtern(int sipCode, String phone) {
        this.zipCode = sipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + '\'' + '}';
    }

    @Override/*пишем поля в поток*/
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(zipCode);
        out.writeUTF(phone);
    }

    @Override /*читаем поля объекта из потока вручную*/
    public void readExternal(ObjectInput in) throws IOException {
        zipCode = in.readInt();
        phone = in.readUTF();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final ContactExtern contactExtern = new ContactExtern(123456, "+994 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contactExtern);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final ContactExtern contactFromFile = (ContactExtern) ois.readObject();
            /* Вывод первоначального объекта в консоль */
            System.out.println("Первоначальный объект:");
            System.out.println(contactExtern);

            /* Вывод десериализованного объекта в консоль */
            System.out.println("Десериализованный объект:");
            System.out.println(contactFromFile);

            /* Сравнение первоначального и десериализованного объектов */
            if (contactExtern.equals(contactFromFile)) {
                System.out.println("Объекты равны.");
            } else {
                System.out.println("Объекты не равны.");
            }
        }
    }
}
