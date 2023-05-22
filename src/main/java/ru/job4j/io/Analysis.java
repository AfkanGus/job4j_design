package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try  (BufferedReader in = new BufferedReader(new FileReader(source));
              BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            var builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                String[] list = line.split(" ");
                if (builder.length() == 0 && (list[0].equals("400") || list[0].equals("500"))) {
                    builder.append(list[1]).append(";");
                }
                if (builder.length() != 0 && (list[0].equals("200") || list[0].equals("300"))) {
                    out.write(builder + list[1]);
                    out.newLine();
                    builder = new StringBuilder();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/server.log", "./data/target.csv");
    }
}
