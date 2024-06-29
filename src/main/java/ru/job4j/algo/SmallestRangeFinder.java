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
        /*Создаем массив для подсчета элементов*/
        int[] elementCount = new int[10];
        /*Инициализация указателей*/
        int left = 0, right = 0;
        /*Количество уникальных элементов*/
        int uniqueCount = 0;
        /*минимальная длина найденного диапазона*/
        int minLength = Integer.MAX_VALUE;
        /*индексы начала и конца наименьшего диапазона*/
        int[] result = null;
        /*На первой итерации: right = 0, left = 0, uniqueCount = 0.*/
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
                    /*Обновляем минимальную длину*/
                    minLength = right - left;
                    result = new int[]{left, right - 1};
                }
                /*Сужаем левую границу окна Уменьшаем счетчик текущего элемента*/
                elementCount[nums[left]]--;
                if (elementCount[nums[left]] == 0) {
                    uniqueCount--; /*Уменьшаем количество уникальных элементов*/
                }
                left++; /*Смещаем левый указатель вправо*/
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
        long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Память, использованная методом: " + memoryUsed + " байт");
    }
}
