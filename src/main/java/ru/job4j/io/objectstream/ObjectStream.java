package ru.job4j.io.objectstream;

import java.io.*;

public class ObjectStream {
    public static void main(String[] args) throws FileNotFoundException {
        /*создаётся объект класса Car*/
        Car car = new Car("Фирма", "Модель", 2000);
        /* открываем поток записи объектов out и с
        помощью метода writeObject() происходит запись объекта в файл в байтовом представлении.*/
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/serialized.dat"));
             /*открыли поток чтения объекта*/
             ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/serialized.dat"))) {
            out.writeObject(car);
            /*преобразовали байтовые данные обратно в объект Car*/
            Car deserialized = (Car) in.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
