package ru.job4j.io.searchkriteria;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

public class SearchArgs {
    private List<File> list = new ArrayList<>();
    /*чтобы рекурсивно обойти все поддиректории.*/
    private Queue<File> fileQueue = new PriorityQueue<>();

    public List<File> filesDirectory(String parentDir, Predicate<File> predicate) {
        /* создал ссылку на путь */
        File parentFile = new File(parentDir);
        fileQueue.offer(parentFile);
        /*проверим пустоту очерди*/
        while (!fileQueue.isEmpty()) {
            File headObjectDir = fileQueue.poll();
            if (headObjectDir.isDirectory()) {
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
        List<File> resultFiles = new ArrayList<>();
        SearchArgs searchArgs = new SearchArgs();
        /*Создаем предикат для проверки расширения файла*/
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
        /* Получаем список файлов в директории, удовлетворяющих предикату extensionPredicate*/
        List<File> allFiles = searchArgs.filesDirectory(parent, extensionPredicate);
        resultFiles.addAll(allFiles);
        return resultFiles;
    }

    public List<File> searchByName(String parent, String name) {
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


