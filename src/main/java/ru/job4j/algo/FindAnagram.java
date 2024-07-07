package ru.job4j.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Алгоритмы с Hash структурами [#505154].
 * В этом задании необходимо найти все анаграммы подстроки.
 * Решение этой задачи сводиться к поиску набора символов из подстроки в целевой  строке.
 */
public class FindAnagram {
    public static List<Integer> findAnagrams(String str, String substring) {
        var resul = new ArrayList<Integer>();

    }

    public static void main(String[] args) {
        String string = "cbaebabacd";
        String p = "abc";
        List<Integer> anagramIndex = findAnagrams(string, p);
        System.out.println(anagramIndex);
    }
}
