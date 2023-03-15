package com.novencia.jconcurrency.part2.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author max
 */
public class A_AtomicExample {
    private static final int NUM_THREADS = 4;
    private static final int NUM_INCREMENTS = 1000000;
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new IncrementTask());
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("Counter value: " + counter.get());
    }

    private static class IncrementTask implements Runnable {
        public void run() {
            for (int i = 0; i < NUM_INCREMENTS; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
