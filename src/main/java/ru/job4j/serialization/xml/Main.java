package ru.job4j.serialization.xml;

import com.google.gson.Gson;

/**
 * 3. Формат XML [#313165 # [#313165].
 */
public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Создаем объект Gson для преобразования объектов в JSON */
        Gson gson = new Gson();

        /* Преобразуем объект Person в JSON и выводим на консоль*/
        String json = gson.toJson(person);
        System.out.println(json);
    }
}
