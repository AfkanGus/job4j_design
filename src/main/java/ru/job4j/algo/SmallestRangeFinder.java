package ru.job4j.algo;

import java.util.Arrays;

/**
 * Aлгоритм двух указателей [#505153]
 * необходимо разработать алгоритм нахождение наименьшего диапазона
 * с данным количеством различных элементов
 */
public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int n = nums.length;
        if (n < k) {
            return null;
        }
        int[] elementCount = new int[10];
        int left = 0, right = 0;
        int uniqueCount = 0;
        /*минимальная длина найденного диапазона*/
        int minLength = Integer.MAX_VALUE;
        /*индексы начала и конца наименьшего диапазона*/
        int[] result = null;

        while (right < n) {
            /*Расширяем правую границу диапазона*/
            if (elementCount[nums[right]] == 0) {
                uniqueCount++;
            }
            elementCount[nums[right]]++;
            right++;

            /*Когда в окне достаточно уникальных элементов, пытаемся сузить окно*/
            while (uniqueCount >= k) {
                if (right - left < minLength) {
                    minLength = right - left;
                    result = new int[]{left, right - 1};
                }
                /*Сужаем левую границу окна*/
                elementCount[nums[left]]--;
                if (elementCount[nums[left]] == 0) {
                    uniqueCount--;
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
