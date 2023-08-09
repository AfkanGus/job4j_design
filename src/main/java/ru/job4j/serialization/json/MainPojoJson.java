package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 5. Преобразование JSON в POJO. JsonObject [#315064]
 */
public class MainPojoJson {
    public static void main(String[] args) {
        try {
            final Person person = new Person(false, 30, new Contact("11-111"),
                    new String[]{"Worker", "Married"});

            /* Преобразуем объект person в JSONObject. */
            JSONObject personJson = new JSONObject();
            personJson.put("sex", person.isSex());
            personJson.put("age", person.getAge());

            JSONObject contactJson = new JSONObject();
            contactJson.put("phone", person.getContact().getPhone());
            personJson.put("contact", contactJson);

            JSONArray statusesJson = new JSONArray(person.getStatuses());
            personJson.put("statuses", statusesJson);

            System.out.println(personJson.toString());

            /* Создаём новую JSONObject с модифицированными данными */
            JSONObject personModJson = new JSONObject("{"
                    + "\"sex\":false,"
                    + "\"age\":35,"
                    + "\"contact\":"
                    + "{"
                    + "\"phone\":\"+7(924)111-111-11-11\""
                    + "},"
                    + "\"statuses\":"
                    + "[\"Student\",\"Free\"]"
                    + "}");
            System.out.println(personModJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

