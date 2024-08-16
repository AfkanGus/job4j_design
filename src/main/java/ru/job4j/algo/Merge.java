package ru.job4j.algo;

import java.util.Arrays;

/**
 * Сортировка слиянием [#504997].
 */
public class Merge {
    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        /*Создаем результирующий массив размером, равным сумме длин left и right*/
        int[] result = new int[left.length + right.length];
        /*Индексы для обхода массивов left, right и result*/
        int indexLeft = 0, indexRight = 0, indexResult = 0;

        /*Пока оба массива не пусты, сравниваем их элементы и добавляем в result*/
        while (indexLeft < left.length && indexRight < right.length) {
            if (left[indexLeft] <= right[indexRight]) {
                result[indexResult++] = left[indexLeft++];
            } else {
                result[indexResult++] = right[indexRight++];
            }
        }

        /*Если остались элементы в левом массиве, добавляем их в result*/
        while (indexLeft < left.length) {
            result[indexResult++] = left[indexLeft++];
        }

        /*Если остались элементы в правом массиве, добавляем их в result*/
        while (indexRight < right.length) {
            result[indexResult++] = right[indexRight++];
        }
        /*Возвращаем отсортированный массив*/
        return result;
    }
}
