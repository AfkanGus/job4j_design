package ru.job4j.memory;

import java.util.Random;

/**
 * 1. Виды сборщиков мусора [#6852 #520553].
 */
public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 10;
        String[] data = new String[1_0];
        for (int i = 0; ; i = (i + 1) % data.length) {
            /*внтр цкл бдт сздвтс нв стрк в data[i]*/
            data[i] = String.valueOf( /*сздм слчн чсл до 255,првдм к char
            зтм прбрзм эт смвл в стрк*/
                    (char) random.nextInt(255)
            ).repeat(length); /*Этт мтд пвтрт стрк length раз сздв длн стрк*/
        }
    }
}
