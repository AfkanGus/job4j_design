package ru.job4j.serialization.xml;

/**
 * 3. Формат XML [#313165 # [#313165].
 */
public class Boeing {
    private final boolean isPassengerPlane;
    private final int maxSpeed;
    private final String model;
    private final Engine engine;
    private final String[] availableClasses;

    public Boeing(boolean isPassengerPlane, int maxSpeed, String model, Engine engine, String[] availableClasses) {
        this.isPassengerPlane = isPassengerPlane;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.engine = engine;
        this.availableClasses = availableClasses;
    }

    public static class Engine {
        private final String type;
        private final int thrust;

        public Engine(String type, int thrust) {
            this.type = type;
            this.thrust = thrust;
        }
    }
}
