package com.novencia.jconcurrency.part2.executors;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class C_MergeSortFJP {
    // ~3s average
    public static void main(String[] args) {
        var random = new Random();
        int[] array = IntStream.range(0, 100_000_000)
                .map(operand -> random.nextInt())
                .toArray();
        long t0 = System.currentTimeMillis();

        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            ParallelMergeSort mergeSort = new ParallelMergeSort(array, 0, array.length - 1);
            pool.invoke(mergeSort);
        }

        System.out.println(System.currentTimeMillis() - t0);

    }

    static class ParallelMergeSort extends RecursiveAction {
        private final int[] array;
        private final int low;
        private final int high;

        public ParallelMergeSort(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                if (high - low <= 16) {
                    // Use a sequential sort for small subarrays
                    Arrays.sort(array, low, high + 1);
                } else {
                    int mid = (low + high) / 2;

                    ParallelMergeSort leftSort = new ParallelMergeSort(array, low, mid);
                    ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, high);

                    invokeAll(leftSort, rightSort);

                    merge(mid);
                }
            }
        }

        private void merge(int mid) {
            int leftSize = mid - low + 1;

            int[] left = new int[leftSize];

            System.arraycopy(array, low, left, 0, leftSize);
            int rightSize = high - mid;
            int[] right = new int[rightSize];
            System.arraycopy(array, mid + 1, right, 0, rightSize);

            int i = 0;
            int j = 0;
            int k = low;
            while (i < leftSize && j < rightSize) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }

            while (i < leftSize) {
                array[k++] = left[i++];
            }

            while (j < rightSize) {
                array[k++] = right[j++];
            }
        }
    }
}
