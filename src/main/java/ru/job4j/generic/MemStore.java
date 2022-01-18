package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    /**
     * метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает.
     *
     * @param model -объект типа T.
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * метод заменяет объект по id, на объект который передается в параметрах метода
     *
     * @param id    - id объекта.
     * @param model - объект.
     * @return true.
     */
    @Override
    public boolean replace(String id, T model) {
        return storage.put(id, model) != null;
    }

    /**
     * метод удаляет пару ключ-значение по id, который передается в метод.
     *
     * @param id - id объекта.
     * @return true -если удаление выполнено успешно.
     */
    @Override
    public boolean delete(String id) {
        return storage.remove(id, storage.get(id));

    }

    /**
     * метод ищет объект по id.
     *
     * @param id - ключ объкта.
     * @return объект или null,если нет id.
     */
    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}
