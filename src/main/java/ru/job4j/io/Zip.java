package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс Zip предназначен для упаковки файлов и директорий в ZIP-архив.
 * Перед запуском класса необходимо прописать аргументы в EditConfiguration
 * -d=c:\projects\job4j_design\ -e=.class -o=project.zip
 *
 * @author afkan
 * @since 23/06/2023
 */
public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zipOutputStream.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateZip(ArgsName args) throws IOException {
        if (!Files.exists(Paths.get(args.get("d")))) {
            throw new IllegalArgumentException(String.format("Not exist %s", args.get("d")));
        }
        if (!Files.isDirectory(Paths.get(args.get("d")))) {
            throw new IllegalArgumentException(String.format("Not directory %s", args.get("d")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Error: wrong number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validateZip(argsName);
        Path root = Paths.get(argsName.get("d"));
        new Zip().packFiles(Search.search(root, s -> !s.toFile().getName().endsWith(argsName.get("e"))),
                new File(argsName.get("o")));
    }
}

