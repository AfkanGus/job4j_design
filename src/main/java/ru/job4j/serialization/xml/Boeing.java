package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

/**
 * 4. JAXB. Преобразование XML в POJO. [#315063 # [#315063 #435145].
 * Добавим аннотации JAXB, которая даст библиотеке информацию о том как парсить объект.
 *
 * @XmlRootElement(name = "boeing") - эта аннотация указывает, что класс Boeing
 * является корневым элементом XML, и имя корневого элемента будет "boeing".
 * @XmlAttribute - эти аннотации указывают, что поля
 * будут представлены в XML, по умолчанию поле парсится как тег.
 * @XmlAccessorType -  JAXB (Java Architecture for XML Binding) будет работать
 * непосредственно с полями класса, а не с его методами (геттерами и сеттерами)
 * при выполнении процессов маршалинга (сериализации) и анмаршалинга (десериализации).
 */
@XmlRootElement(name = "boeing")
@XmlAccessorType(XmlAccessType.FIELD)
public class Boeing {
    @XmlAttribute
    private boolean isPassengerPlane;
    @XmlAttribute
    private int maxSpeed;
    @XmlAttribute
    private String model;
    private Engine engine;
    private String[] availableClasses;

    /* Добавим дефолтные конструктор чтобы JAXB мог создать
     * объек класса при десериализации
     */
    public Boeing() {
    }

    public Boeing(boolean isPassengerPlane, int maxSpeed, String model, Engine engine, String[] availableClasses) {
        this.isPassengerPlane = isPassengerPlane;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.engine = engine;
        this.availableClasses = availableClasses;
    }

    public static class Engine {
        @XmlAttribute
        private final String type;
        @XmlAttribute
        private final int thrust;

        /* Добавим дефолтные конструктор чтобы JAXB мог создать
         * объек класса при десериализации
         */
        public Engine() {
            this.type = null;
            this.thrust = 0;
        }

        public Engine(String type, int thrust) {
            this.type = type;
            this.thrust = thrust;
        }
    }
}
