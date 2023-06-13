package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс поиска файлов с помощью FileVisitor.
 * Класс SearchFiles представляет собой посетителя файлов,
 * который выполняет поиск файлов в древе каталогов, соответствующих заданному условию.
 * Он наследует класс SimpleFileVisitor<Path>, который предоставляет реализации по
 * умолчанию для методов интерфейса FileVisitor.
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    /**
     * condition - условия,которому должны соответсвовать файлы при поиске.
     * paths - список путей.
     */
    private final Predicate<Path> condition;
    private final List<Path> paths;

    /**
     * Конструирует объект SearchFiles с указанным условием.
     *
     * @param condition условие для сопоставления файлов
     */
    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        this.paths = new ArrayList<>();
    }

    /**
     * Посещает файл и проверяет, соответствует ли он условию.
     * Если файл удовлетворяет условию, он добавляется в список путей.
     *
     * @param file       посещаемый файл.
     * @param attributes основные атрибуты файла.
     * @return результат посещения файла, указывающий следующее действие.
     * @throws IOException если происходит ошибка ввода-вывода во время посещения файла.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * getPaths() - получить пути.
     * @return возврщает список найденых путей
     */
    public List<Path> getPaths() {
        return paths;
    }
}

