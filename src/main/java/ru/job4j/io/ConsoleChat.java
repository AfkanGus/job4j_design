package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Random random = new Random();
        List<String> botAnswers = readPhrases();
        List<String> chatLog = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Спроси, где Бот?");
            String input = scanner.nextLine();
            chatLog.add(input);
            while (!input.equals(OUT)) {
                if (input.equals(STOP)) {
                    chatLog.add(STOP);
                    input = scanner.nextLine();
                    chatLog.add(input);
                    while (!input.equals(CONTINUE)) {
                        input = scanner.nextLine();
                        chatLog.add(input);
                    }
                }
                String botResponse = botAnswers.get(random.nextInt(botAnswers.size()));
                System.out.println("Бот: " + botResponse);
                chatLog.add("Бот: " + botResponse);
                input = scanner.nextLine();
                chatLog.add(input);
            }
        }
        saveLog(chatLog);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("./data/chat_bot_answers.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("./data/chat_save_log.txt",
                StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat_bot_answers.txt",
                "./data/chat_save_log.txt");
        cc.run();
    }
}

