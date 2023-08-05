package ru.job4j.serialization.xml;

/**
 * 3. Формат XML [#313165 # [#313165].
 */
public class Contact {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone
                + '\'' + '}';
    }
}
