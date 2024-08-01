package ru.job4j.algo;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Сортировки [#505155].
 * Задан массив из интервалов. Необходимо объединить перекрывающиеся интервалы.
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 1--2--3 это перекревающие интревалы
 * 2--3--4--5--6  -> output [1,6]
 * Output: [[1,6],[8,10],[15,18]]
 */
public class Intervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][];
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            if (currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                mergedIntervals.add(currentInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("memory: " + memoryUsed + " b.");
    }
}
