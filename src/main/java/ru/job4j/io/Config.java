package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(s -> s.length() > 0 && s.charAt(0) != '#')
                    .filter(this::check)
                    .forEach(str -> {
                        String[] map = str.split("=", 2);
                        values.put(map[0], map[1]);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean check(String error) {
        if (!error.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("Line \"%s\" does not contain the symbol \"=\"", error));
        }
        if (error.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("Line \"%s\" does not contain a key", error));
        }
        if (error.indexOf("=") == error.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Line \"%s\" does not contain a value", error));
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}
