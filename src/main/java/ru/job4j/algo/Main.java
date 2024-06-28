package ru.job4j.algo;

import java.util.Arrays;

/**
 * Aлгоритм двух указателей [#505153].
 * идея алгоритма двух указателей заключается в том, что оба указателя начинают
 * с какого-то начального положения и затем перемещаются по структуре данных в определенном порядке
 */
public class Main {
    public static int[] squareSortedArray(int[] arr) {
        int n = arr.length;
        /*хранит квадрат числа*/
        int[] result = new int[n];
        int left = 0, right = n - 1;
        /*указатель на место в массиве для записи следующ квадрата*/
        int resultIndex = n - 1;
        while (left <= right) {
            if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                result[resultIndex] = arr[left] * arr[left];
                left++;
            } else {
                result[resultIndex] = arr[right] * arr[right];
                right--;
            }
            resultIndex--; /*смещение для перехода к следющей позиции в result*/
        }
        return result;

    }

    public static void main(String[] args) {
        int[] sortedArray = {-4, -2, 0, 2, 3, 5};
        int[] resultArray = squareSortedArray(sortedArray);
        System.out.println(Arrays.toString(resultArray));

    }
}
