package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        StringBuilder builder = new StringBuilder();
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");
        Integer[] indexes = new Integer[filters.length];
        Map<String, Integer> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            if (scanner.hasNext()) {
                /*разделим первую строку файла на отдельные заголовки столбцов*/
                String[] columHeaders = scanner.nextLine().split(delimiter);
                if (scanner.hasNextLine()) {
                    String[] headers = scanner.nextLine().split(delimiter);
                    for (String header : headers) {
                        map.put(header, map.size());
                    }
                    for (String filter : filters) {
                        Integer index = map.get(filter);
                        indexes[i] = index;
                        builder.append(filter);
                        if (filter.equals(filters[filters.length - 1])) {
                            break;
                        }
                        builder.append(delimiter);
                    }
                    builder.append(System.lineSeparator());
                }
                while (scanner.hasNextLine()) {
                    String[] tempLine = scanner.nextLine().split(delimiter);
                    for (int i = 0; i < indexes.length; i++) {
                        String temp = tempLine[indexes[i]];
                        if (i == filters.length - 1) {
                            builder.append(temp);
                            break;
                        }
                        builder.append(delimiter);
                    }
                    builder.append(System.lineSeparator());
                    if ("stdout".equals(out)) {
                        System.out.print(builder);
                    } else {
                        try (PrintWriter writer = new PrintWriter(out)) {
                            writer.print(builder);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName name) {

    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);

        validate(argsName);
        handle(argsName);
    }
}


