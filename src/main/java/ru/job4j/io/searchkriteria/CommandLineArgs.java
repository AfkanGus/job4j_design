package ru.job4j.io.searchkriteria;

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

    public String getFileNameForSearch() {
        
    }
}
