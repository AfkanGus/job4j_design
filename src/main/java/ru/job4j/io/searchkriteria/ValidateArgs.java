package ru.job4j.io.searchkriteria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateArgs {
    public boolean validate(String[] valid) {
        boolean result = true;
        CommandLineArgs args = new CommandLineArgs(valid);
        args.initializeArgs();
        Map<String, String> validInfo = args.getCommandLineArguments();

        if (!validInfo.containsKey("-d") || !validInfo.containsKey("-n") || !validInfo.containsKey("-o") || !(validInfo.containsKey("-m") || validInfo.containsKey("-t"))) {
            System.out.println("Командная строка задана некорректно!");
            System.out.println("Формат командной строки: d=c: -n=*.?xt -t=mask -o=log.txt");
            result = false;
        }

        return result;
    }

    private void find(List<File> sources, File target) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            for (File source : sources) {
                bw.write(String.format("%s%s", source.getName(), System.lineSeparator()));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ValidateArgs ff = new ValidateArgs();
        if (ff.validate(args)) {
            CommandLineArgs args1 = new CommandLineArgs(args);
            args1.initializeArgs();
            String directory = args1.getCommandLineArguments().get("-d");
            String fileName = args1.getCommandLineArguments().get("-o");
            List<String> extensions = new ArrayList<>();
            extensions.add(args1.getCommandLineArguments().get("-n"));
            List<File> resultFiles = new ArrayList<>();
            SearchArgs searchArgs = new SearchArgs();
            if (args1.getCommandLineArguments().containsKey("-m")) {
                resultFiles.addAll(searchArgs.searchByMask(directory, extensions));
            } else {
                resultFiles.addAll(searchArgs.searchByName(directory, extensions.get(0)));
            }
            File resultFile = new File(fileName);
            ff.find(resultFiles, resultFile);
        }

    }
}
