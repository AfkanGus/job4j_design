package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MercedesMain {
    public static void main(String[] args) {
        Engine engine = new Engine("V8", 577);
        String[] features = {"Leather Seats", "Sunroof", "Navigation System"};
        Mercedes mercedes = new Mercedes(true, 2023, "Mercedes-AMG GT", engine, features);

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(mercedes);
        System.out.println(json);
        /* Создаём новую json-строку с модифицированными данными*/
        String modifiedJson =
                "{"
                        + "\"isElectric\":false,"
                        + "\"year\":2024,"
                        + "\"model\":\"Mercedes-AMG GT 63\","
                        + "\"engine\":"
                        + "{"
                        + "\"type\":\"V12\","
                        + "\"horsepower\":700"
                        + "},"
                        + "\"features\":"
                        + "[\"Leather Seats\", \"Sunroof\", \"Navigation System\", \"Massaging Seats\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        Mercedes modifiedMercedes = gson.fromJson(modifiedJson, Mercedes.class);
        System.out.println(modifiedMercedes);
    }
}
