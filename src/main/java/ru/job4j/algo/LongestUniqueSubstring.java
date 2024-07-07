package ru.job4j.algo;

import java.util.HashSet;
import java.util.Set;

/**
 * Алгоритмы с Hash структурами [#505154]
 * найти самую длинную подстроку состоящую из уникальных элементов.
 */
public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        int n = str.length();
        int maxLength = 0;
        int start = 0, end = 0;
        int left = 0, right = 0;
        Set<Character> uniqueChars = new HashSet<>();
        while (right < n) {
            char currentChar = str.charAt(right);
            while (uniqueChars.contains(currentChar)) {
                uniqueChars.remove(str.charAt(left));
                left++;
            }
            uniqueChars.add(currentChar);
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
                end = right;
            }
            right++;
        }
        return str.substring(start, end + 1);
    }

    public static void main(String[] args) {
        long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Память, использованная методом: " + memoryUsed + " байт");
    }
}
