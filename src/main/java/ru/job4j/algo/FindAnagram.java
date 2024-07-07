package ru.job4j.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Алгоритмы с Hash структурами [#505154].
 * В этом задании необходимо найти все анаграммы подстроки.
 * Решение этой задачи сводиться к поиску набора символов из подстроки в целевой  строке.
 */
public class FindAnagram {
    /**
     * Метод приводит строку во множество HashSet.
     * Преобразуем строку в поток символов int, потом каждый код символа
     * обратно в char, и собираем все символы в HashSet.
     *
     * @param string - входная строка.
     * @return - множество уникальных символов из строки.
     */
    public static Set<Character> toSet(String string) {
        return string.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    /**
     * Находит все анаграммы подстроки substr в строке str.
     *
     * @param str    основная строка, в которой ищем анаграммы
     * @param substr подстрока, для которой ищем анаграммы
     * @return список начальных индексов найденных анаграмм
     */
    public static List<Integer> findAnagrams(String str, String substr) {
        var resul = new ArrayList<Integer>(); /*список для хранения найденныхх анограмм*/
        var anagrams = toSet(substr); /*перобразуем строку во множество*/
        int windowSize = substr.length(); /*для извлечения подсткроки такой же длины из
         изходной подстроки*/
        for (int i = 0; i <= str.length() - windowSize; i++) {
            /*Извлекаем текущую подстроку (окно) из str, начиная с индекса i и длиной windowSize*/
            String window = str.substring(i, i + windowSize);
            /*Преобразуем текущее окно в множество уникальных символов и сравниваем с множеством подстроки*/
            if (anagrams.equals(toSet(window))) {
                /*Если текущее окно является анаграммой, добавляем начальный индекс окна в результат*/
                resul.add(i);
            }
        }
        return resul;
    }

    public static void main(String[] args) {
        String string = "cbaebabacd";
        String p = "abc";
        List<Integer> anagramIndex = findAnagrams(string, p);
        System.out.println(anagramIndex);
    }
}
