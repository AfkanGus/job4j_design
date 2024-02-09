package ru.job4j.io.searchkriteria;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

public class SearchArgs {
    private List<File> list = new ArrayList<>();
    private Queue<File> fileQueue = new PriorityQueue<>(); /*чтобы рекурсивно обойти все поддиректории.*/

    /*получить список содерживое папок в директории*/
    public List<File> filesDirectory(String parentDir, Predicate<File> predicate) {
        File parentFile = new File(parentDir); /* создал ссылку на путь */
        fileQueue.offer(parentFile); /* вставим файлы диектори в очередь чтоб потом обойти директории*/
        /*проверим пустоту очерди*/
        while (!fileQueue.isEmpty()) {
            File headObjectDir = fileQueue.poll();
            if (headObjectDir.isDirectory()) {
                /* Если есть директория то перебираем все файлы и поддириктории внутри haedObjectDir
                 * Objects.requireNonNull(headObjectDir.listFiles()) - если  headObjectDir null ->NullPointerException,
                 * и вернет массив файлов и дир в headObjectDid */
                /*for (File fileOrDir : Objects.requireNonNull(headObjectDir.listFiles())) {
                    fileQueue.offer(fileOrDir);
                }
            } else if (headObjectDir.isFile() && headObjectDir.getName().contains(".") && predicate.test(headObjectDir)) {
                list.add(headObjectDir);*/
                File[] files = headObjectDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        fileQueue.offer(file);
                    }
                }
            } else {
                list.add(headObjectDir);
            }
        }
        return list;
    }

    public List<File> searchByMask(String parent, List<String> extension) {
      /*  // Создаем предикат для фильтрации файлов по расширению
        Predicate<File> predicate = file -> {
            String[] fileNameParts = file.getName().split("\\.");
            return fileNameParts.length > 1 && extension.contains(fileNameParts[fileNameParts.length - 1]);
        };

        // Вызываем метод filesDirectory с созданным предикатом
        return filesDirectory(parent, predicate);*/


      /*  List<File> resultFiles = new ArrayList<>(); // Создаем список для хранения найденных файлов
        SearchArgs searchArgs = new SearchArgs(); // Создаем экземпляр класса SearchArgs
        // Перебираем все файлы и проверяем их расширение
        for (File file : allFiles) {
            // Получаем расширение файла
            String[] fileNameParts = file.getName().split("\\.");
            if (fileNameParts.length > 1) {
                String fileExtension = fileNameParts[fileNameParts.length - 1];
                // Если расширение файла содержится в списке расширений, добавляем файл в результат
                if (extension.contains(fileExtension)) {
                    resultFiles.add(file);
                }
            }
        }

        return resultFiles; // Возвращаем список файлов, удовлетворяющих критерию поиска по маске
    }*/
        List<File> resultFiles = new ArrayList<>();
        SearchArgs searchArgs = new SearchArgs();
        // Создаем предикат для проверки расширения файла
        Predicate<File> extensionPredicate = new Predicate<File>() {
            @Override
            public boolean test(File file) {
                String fileName = file.getName();
                int lastDotIndex = fileName.lastIndexOf('.');
                if (lastDotIndex != -1 && lastDotIndex != fileName.length() - 1) {
                    String fileExtension = fileName.substring(lastDotIndex + 1);
                    return extension.contains(fileExtension);
                }
                return false;
            }
        };
        // Получаем список файлов в директории, удовлетворяющих предикату extensionPredicate
        List<File> allFiles = searchArgs.filesDirectory(parent, extensionPredicate);
        // Фильтруем файлы по расширению и добавляем их в список результатов
        resultFiles.addAll(allFiles);
        return resultFiles;
    }

    public List<File> searchByName(String parent, String name) {
        // return filesDirectory(parent, (file) -> name.equals(file.getName().split("\\.")[0]));

        // Создаем предикат для проверки по имени
        Predicate<File> predicate = new Predicate<File>() {
            @Override
            public boolean test(File file) {
                String fileName = file.getName();
                int extensionIndex = fileName.lastIndexOf('.');
                String nameWithoutExtension = extensionIndex >= 0 ? fileName.substring(0, extensionIndex) : fileName;
                return name.equals(nameWithoutExtension);
            }
        };
        return filesDirectory(parent, predicate);
    }
}


