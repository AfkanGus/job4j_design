package ru.job4j.serialization.json;

import java.util.Arrays;

public class Mercedes {
    private final boolean isElectric;
    private final int year;
    private final String model;
    private final Engine engine;
    private final String[] features;

    public Mercedes(boolean isElectric, int year, String model, Engine engine, String[] features) {
        this.isElectric = isElectric;
        this.year = year;
        this.model = model;
        this.engine = engine;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Mercedes{"
                + "isElectric="
                + isElectric + ", year="
                + year + ", model='"
                + model + '\'' + ", engine="
                + engine + ", features="
                + Arrays.toString(features)
                + '}';
    }
}

class Engine {
    private final String type;
    private final int horsepower;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", horsepower="
                + horsepower
                + '}';
    }
}
