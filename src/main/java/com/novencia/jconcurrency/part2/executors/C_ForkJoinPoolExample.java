package com.novencia.jconcurrency.part2.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class C_ForkJoinPoolExample {
    public static void main(String[] args) {
        int[] array = IntStream.range(1, 50_000_001).toArray();

        try (var pool = new ForkJoinPool(12)) {
            int sum = pool.invoke(new ArraySumTask(array, 0, array.length));
            System.out.println("Sum: " + sum);
        }
    }

    private static class ArraySumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public ArraySumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 1) {
                return array[start];
            } else {
                int mid = start + (end - start) / 2;
                var left = new ArraySumTask(array, start, mid);
                var right = new ArraySumTask(array, mid, end);
                left.fork();
                int rightSum = right.compute();
                int leftSum = left.join();
                return leftSum + rightSum;
            }
        }
    }
}