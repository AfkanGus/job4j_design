package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * DuplicateFileVisitor - это пользовательский обходчик файлов,
 * который находит дубликаты файлов на основе их свойств (размер и имя).
 * Он расширяет класс SimpleFileVisitor и переопределяет метод visitFile
 * для обработки каждого посещенного файла.
 */
public class DuplicateFileVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Set<String>> fileDuplicates;

    public DuplicateFileVisitor(Map<FileProperty, Set<String>> fileDuplicates) {
        this.fileDuplicates = fileDuplicates;
    }
    /**
     * Посещает файл и добавляет его в карту fileDuplicates.
     *
     * @param file  посещаемый файл
     * @param attrs базовые атрибуты файла
     * @return результат посещения, указывающий, продолжать или прервать обход файлов
     * @throws IOException если происходит ошибка ввода-вывода во время операции посещения
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        Set<String> filePaths = fileDuplicates.getOrDefault(fileProperty, new HashSet<>());
        filePaths.add(file.toAbsolutePath().toString());
        fileDuplicates.put(fileProperty, filePaths);
        return FileVisitResult.CONTINUE;
    }
}
