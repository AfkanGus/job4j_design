package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Программа для поиска файлов с заданным расширением в указанной директории.
 */
public class Search {
    /**
     * Метод, с которого начинается выполнение программы.
     *
     * @param args аргументы командной строки: [0] - начальная папка, [1] - расширение файлов.
     * @throws IOException если происходит ошибка ввода-вывода при поиске файлов.
     */
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        validator(args);
    }

    /**
     * Метод для поиска файлов, удовлетворяющих заданному условию, в указанной директории и её поддиректориях.
     *
     * @param root      корневая директория для поиска файлов.
     * @param condition условие, которому должны удовлетворять файлы.
     * @return список найденных файлов.
     * @throws IOException если происходит ошибка ввода-вывода при поиске файлов.
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод для валидации аргументов командной строки.
     *
     * @param args аргументы командной строки.
     * @throws IllegalArgumentException если переданы некорректные аргументы командной строки.
     */
    public static void validator(String[] args) {
        if (!Files.exists(Paths.get(args[0])) || !Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Первый параметр - путь к существующему каталогу");
        }
        if (!args[1].startsWith(".") || args[1].length() == 1) {
            throw new IllegalArgumentException("Второй параметр - расширение файла, начинающееся с символа '.' и содержащее дополнительные символы.");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Программа должна запускаться с двумя параметрами.");
        }
    }
}
