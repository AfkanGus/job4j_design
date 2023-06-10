package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Класс вывода содержимого директории и размеров файлов.
 */
public class Dir {
    /**
     * Главный метод класса.
     *
     * @param args аргументы командной строки.
     * @throws IOException выбрасывает исключение при возникают проблемы доступа к файловой системе.
     */
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("No exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("No directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Directory : %s", file.getAbsolutePath()));
        File[] files = file.listFiles();
        Arrays.stream(files)
                .forEach(subfile -> {
                    if (subfile.isFile()) {
                        System.out.println(String.format("Name : %s, Size: %d bytes ", subfile.getName(), subfile.length()));
                    } else {
                        System.out.println(String.format("Name: %s", subfile.getName()));
                    }
                });
    }
}
