package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncode {
    public String readFiles(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(path))) {
            bufferedReader.lines().map(
                    s -> s + System.lineSeparator()).forEach(builder::append);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text.txt";
        UsageEncode encode = new UsageEncode();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
            encode.writeDataInFile(path, strings);
        String s = encode.readFiles(path);
        System.out.println("Data form files: ");
        System.out.println(s);
    }
}
