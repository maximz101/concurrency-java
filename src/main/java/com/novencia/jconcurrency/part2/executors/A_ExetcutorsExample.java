package com.novencia.jconcurrency.part2.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class A_ExetcutorsExample {
    static final LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        var t0 = System.nanoTime();

        // Uncomment one of the following lines to test different executors
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            // try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            // try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            // try (ExecutorService executorService = Executors.newScheduledThreadPool(4)) {
            // try (ExecutorService executorService = Executors.newWorkStealingPool(8)) {
            // try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1000_000)
                    .forEach(value -> executorService.submit(longAdder::increment));
            executorService.shutdown();
        }

        var t1 = System.nanoTime();

        System.out.println((t1 - t0) / 1000_000 + "ms");

        Thread.sleep(1000);

        System.out.println(longAdder.longValue());
    }
}
