package ru.job4j.io;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private static void validateZip(List<String> args) {
        if (args.size() != 3) {
            throw new IllegalArgumentException("Set all arguments");
        }
        if (!Files.exists(Paths.get(args.get(0)))) {
            throw new IllegalArgumentException(String.format("Not exist %s", args.get(0)));
        }
        if (!Files.isDirectory(Paths.get(args.get(0)))) {
            throw new IllegalArgumentException(String.format("Not directory %s", args.get(0)));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zipArchive = new Zip();
        Map<String, String> arguments = ArgsName.of(args).values;
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> argEntry : arguments.entrySet()) {
            values.add(argEntry.getValue());
        }
        validateZip(values);
        List<Path> files = Search.search(Paths.get(values.get(0)), (s -> !s.toFile().getName().endsWith(values.get(1))));
        zipArchive.packFiles(files, Paths.get(values.get(2)).toFile());
    }
}

