package com.novencia.jconcurrency.part2.executors;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class C_MergeSortExample {

    static class MergeSort {
        public static void mergeSort(int[] array) {
            if (array.length <= 1) {
                return;
            }

            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }

        private static void merge(int[] array, int[] left, int[] right) {
            int i = 0, j = 0, k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }

            while (i < left.length) {
                array[k++] = left[i++];
            }

            while (j < right.length) {
                array[k++] = right[j++];
            }
        }

        // ~18s
        public static void main(String[] args) {
            var random = new Random();
            int[] array = IntStream.range(0, 100_000_000)
                    .map(operand -> random.nextInt())
                    .toArray();

            long t0 = System.currentTimeMillis();
            mergeSort(array);
            System.out.println(System.currentTimeMillis() - t0);
        }
    }

}
