package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Analysis {
    public void unavailable(String source, String target) {

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "target.csv");
    }
}
