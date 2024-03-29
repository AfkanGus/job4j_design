package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс AnalysisTest содержит тесты для класса Analysis.
 */
class AnalysisTest {
    /**
     * Тестирование метода unavailable() класса Analysis.
     * Проверяет правильность определения периодов недоступности сервера на основе лог-файла.
     *
     * @param tempDir временная директория для создания временных файлов
     * @throws IOException если возникают ошибки ввода-вывода
     */
    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String rsl = "10:57:01;10:59:01;" + "11:01:02;11:02:02;";
        StringBuilder stringTarget = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(stringTarget::append);
        }
        assertThat(stringTarget.toString()).isEqualTo(rsl);
    }
}