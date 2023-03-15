package com.novencia.jconcurrency.part2.executors;

import java.util.stream.LongStream;

public class D_ParallelStreamsExample {
    public static void main(String[] args) {
        // compare with and without parallel
        long t0 = System.currentTimeMillis();
        var sum = LongStream
                .range(0, 1_000_000_000L)
                //.parallel()
                .sum();
        System.out.println(System.currentTimeMillis() - t0);
        System.out.println(sum);
    }
}
