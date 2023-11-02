package ru.job4j.serialization.json;

/**
 * Демонстрация возникновения исключения StackOverflowError.
 * В библиотеке JSON-Java (org.json) существует аннотация
 *
 * @JSONPropertyIgnore: - Для избежания исключения.
 */
public class A {
    private B b;

    /* @JSONPropertyIgnore */
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}
