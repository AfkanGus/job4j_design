package ru.job4j.io.searchkriteria;

import java.util.HashMap;
import java.util.Map;

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
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                String key = args[i];
                String value = null;
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    value = args[i + 1];
                    i++;
                }
                commandLineArguments.put(key, value);
            }
        }
    }

   /* public String getDirectory() {
        return commandLineArguments.get("-d");
    }

    public String getResultFileName() {
        return commandLineArguments.get("-o");
    }

    public void getFileNameForSearch() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                String key = args[i];
                String value = null;
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    value = args[i + 1];
                    i++;
                }
                key = key.substring(1);
                commandLineArguments.put(key, value);
            }
        }*/
}
