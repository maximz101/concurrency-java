package com.novencia.jconcurrency.part2.synctools;

import com.novencia.jconcurrency.Util;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class C_CyclicBarrierExample {

    // in this example threads run in cycles
    // each cycle we create threads to do the computation needed
    // synchronizing on the cyclic barrier
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        List<Integer> numbers = List.of(1, 2, 3);

        for (int i = 1; i <= 10; i++) {// 10 cycles
            var cycle = i;
            numbers.forEach(n -> {
                new Thread(() -> {
                    try {
                        System.out.println("cycle " + cycle + ", thread " + Thread.currentThread().getName());
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        Util.handleInterruptedException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }, "thread-" + cycle + "-n-" + n).start();
            });
            cyclicBarrier.await();
        }

        System.out.println("main thread end");
    }
}
