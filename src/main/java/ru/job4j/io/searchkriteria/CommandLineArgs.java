package ru.job4j.io.searchkriteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CommandLineArgs {
    private Map<String, String> commandLineArguments = new HashMap<>();
    private final String[] args;

    public CommandLineArgs(String[] args) {
        this.args = args;
    }

    public Map<String, String> getCommandLineArguments() {
        return this.commandLineArguments;
    }

    public void initializeArgs() {
        IntStream.range(0, args.length)
                .filter(i -> args[i].startsWith("-"))
                .forEach(i -> {
                    String key = args[i];
                    int index = i;
                    String value = (index + 1 < args.length && !args[index + 1].startsWith("-")) ? args[index + 1] : null;
                    commandLineArguments.put(key, value);
                });
    }

    public String getDirectory() {
        return commandLineArguments.get("-d");
    }

    public String getResultFileName() {
        return commandLineArguments.get("-o");
    }

    public void getFileNameForSearch() {
        // Преобразовываем массив args в поток строк
        Arrays.stream(args)
                // Фильтруем только аргументы, начинающиеся с символа "-"
                .filter(arg -> arg.startsWith("-"))
                // Преобразуем аргументы в пары ключ-значение
                .forEach(arg -> {
                    String key = arg;
                    String value = null;
                    // Получаем индекс аргумента в массиве
                    int index = Arrays.asList(args).indexOf(arg);
                    // Если следующий аргумент существует и не начинается с "-", он является значением
                    if (index + 1 < args.length && !args[index + 1].startsWith("-")) {
                        value = args[index + 1];
                    }
                    // Убираем символ "-" из ключа
                    key = key.substring(1);
                    // Помещаем пару ключ-значение в Map
                    commandLineArguments.put(key, value);
                });
    }
}
