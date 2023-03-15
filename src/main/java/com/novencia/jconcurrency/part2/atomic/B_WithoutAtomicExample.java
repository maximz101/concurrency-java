package com.novencia.jconcurrency.part2.atomic;

/**
 * @author max
 */
public class B_WithoutAtomicExample {
    private static final int NUM_THREADS = 4;
    private static final int NUM_INCREMENTS = 1000000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new IncrementTask());
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("Counter value: " + counter);
    }

    private static class IncrementTask implements Runnable {
        public void run() {
            for (int i = 0; i < NUM_INCREMENTS; i++) {
                counter++;
            }
        }
    }
}
