package ru.job4j.serialization.json;

/**
 * 2. Формат JSON [#313164  [#313164]].
 * Простой объект, предназначенный для хранения и работы с контактными данными.
 */
public class Contact {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }

    public String getPhone() {
        return phone;
    }
}
