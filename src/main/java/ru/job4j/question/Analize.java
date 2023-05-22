package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    /**
     * Метод diff вычисляет разницу между двумя состояниями множества пользователей.
     *
     * @param previous предыдущее состояние множества пользователей
     * @param current  текущее состояние множества пользователей
     * @return объект Info с информацией о добавленных, измененных и удаленных пользователях
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int change = 0;
        int deleted = 0;

        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            Integer id = user.getId();
            String name = user.getName();
            if (!previousMap.containsKey(id)) {
                added++;
            } else {
                String previousName = previousMap.get(id);
                if (!previousName.equals(name)) {
                    change++;
                }
                previousMap.remove(id);
            }
        }
        deleted = previousMap.size();
        return new Info(added, change, deleted);
    }
}

