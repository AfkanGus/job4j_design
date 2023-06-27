package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * peter-2022@example.com - разобьем данную запись на простейшие части:
 * любой символ от 1 раза, знак собаки, любой символ от 1 раза, точка, любой
 * символ от 1 раза
 */
public class RegexExamplePractice {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        Pattern pattern1 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        /*String text = "22.11.1987 11.11.1111111 99.99.999999991 99.00.000.00";*/
        String text1 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
       /* Matcher matcher = pattern.matcher(text);*/
        Matcher matcher1 = pattern1.matcher(text1);
        while (matcher1.find()) {
           /* System.out.println("Match found: " + matcher.group());*/
            System.out.println("Match found email: " + matcher1.group());
        }
    }
}
