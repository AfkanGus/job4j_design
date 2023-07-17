package ru.job4j.io.objectstream;

import java.io.Serializable;

/**
 * Класс демонстрирует процесс серилизации и десирилизации.
 * implements Serializable -маркерный интерфейс без методов, для jvm - для сохранения объекта в байтах.
 * private static final long serialVersionUID = 1L - уникальный ключ,
 * который при начале сериализации и окончании десериализации должен совпадать.
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", year=" + year
                + '}';
    }
}
