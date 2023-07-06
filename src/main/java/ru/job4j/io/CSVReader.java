package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        StringBuilder builder = new StringBuilder();
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");
        Integer[] columnIndices = new Integer[filters.length];
        Map<String, Integer> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            if (scanner.hasNext()) {
                /*разделим первую строку файла на отдельные заголовки столбцов*/
                String[] columHeaders = scanner.nextLine().split(delimiter);
                /*по columHeaders */
                for (int i = 0; i < columHeaders.length; i++) {
                    map.put(columHeaders[i], i);
                }
                /* по filters*/
                for (int j = 0; j < filters.length; j++) {
                    String filterValue = filters[j];
                    columnIndices[j] = map.get(filterValue);
                    if (j == filters.length - 1) {
                        builder.append(filterValue);
                        break;
                    }
                    builder.append(filterValue + delimiter);
                }
                builder.append(System.lineSeparator());
            }
            while (scanner.hasNextLine()) {
                String[] lineValues = scanner.nextLine().split(delimiter);
                for (int i = 0; i < columnIndices.length; i++) {
                    String temp = lineValues[columnIndices[i]];
                    if (i == filters.length - 1) {
                        builder.append(temp);
                        break;
                    }
                    builder.append(temp + delimiter);
                }
                builder.append(System.lineSeparator());
            }
            if ("stdout".equals(out)) {
                System.out.print(builder);
            } else {
                try (PrintWriter writer = new PrintWriter(out)) {
                    writer.print(builder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String output = argsName.get("out");
        String filter = argsName.get("filter");
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File does not exist: %s", file.getAbsoluteFile()));
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Invalid delimiter: ");
        }
        if (!("stdout".equals(output) || (output.length() >= 5 && output.endsWith(".csv")))) {
            throw new IllegalArgumentException("Invalid output file name");
        }
        if ("".equals(filter)) {
            throw new IllegalArgumentException("Invalid filter: ");
        }
        handle(argsName);
    }
}


