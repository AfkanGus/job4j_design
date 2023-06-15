package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * DuplicatesFinder - это класс, который находит дубликаты файлов
 * в указанной папке и выводит результаты на консоль.
 * Он использует объект DuplicateFileVisitor для обхода
 * файлов и определения дубликатов на основе их свойств.
 * Метод  Files.walkFileTree() - используется для обход файлов и поиск дубликатов.
 * В цикле for-each() обходим элементы fileDuplicates, который является картой (Map)
 * с ключами типа FileProperty и значениями типа Set<String>.
 * В условии if(), проверям если  размер набора путей filePaths больше 1, то это означает,
 * что есть более одного пути к файлу с одинаковыми свойствами (имя и размер).
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        String folderPath = "./data";
        Map<FileProperty, Set<String>> fileDuplicates = new HashMap<>();
        Files.walkFileTree(Paths.get(folderPath), new DuplicateFileVisitor(fileDuplicates));
        for (Map.Entry<FileProperty, Set<String>> entry : fileDuplicates.entrySet()) {
            FileProperty fileProperty = entry.getKey();
            Set<String> filePaths = entry.getValue();
            if (filePaths.size() > 1) {
                System.out.println("Найденые дубликаты фалов :");
                System.out.println("Имя фалов : " + fileProperty.getName() + ", Размер файлов : " + fileProperty.getSize());
                for (String filePath : filePaths) {
                    System.out.println(filePath);
                }
                System.out.println();
            }
        }
        if (fileDuplicates.isEmpty()) {
            System.out.println("Дубликаты файлов не найдены.");
        }
    }
}
