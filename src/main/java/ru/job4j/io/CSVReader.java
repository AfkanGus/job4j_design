package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");
        String out = argsName.get("out");
        try (Scanner sc = new Scanner(new FileInputStream(argsName.get("path")))) {


        }

    }

    private static void validate(ArgsName name) {
        String delimiter = name.get("delimiter");
        String output = name.get("out");
        String filter = name.get("filter");

    }

    public static void main(String[] args)  {
        validate(argsName);
        handle(argsName);
    }
}
