package com.novencia.jconcurrency.part2.synctools;

import com.novencia.jconcurrency.Util;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class B_CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        //uncomment the method you want to test

        // asExitBarrier();
        asEntryBarrier();

    }

    /**
     * make thread 1 and 2 start at the same time
     */
    private static void asEntryBarrier() {
        var ctdl = new CountDownLatch(1);

        var t1 = new Thread(() -> {
            try {
                ctdl.await();
                System.out.println("thread 1 @" + System.nanoTime());
            } catch (InterruptedException e) {
                Util.handleInterruptedException(e);
            }
        });


        var t2 = new Thread(() -> {
            try {
                ctdl.await();
                System.out.println("thread 2 @" + System.nanoTime());
            } catch (InterruptedException e) {
                Util.handleInterruptedException(e);
            }
        });

        t1.start();
        t2.start();
        System.out.println("main thread : enter anything to start threads");
        new Scanner(System.in).nextLine();
        ctdl.countDown();

        new Scanner(System.in).nextLine();
    }

    /**
     * making main thread wait for 2 threads
     */
    private static void asExitBarrier() throws InterruptedException {
        var ctdl = new CountDownLatch(2);

        var t1 = new Thread(() -> {
            System.out.println("thread 1 @" + System.nanoTime());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ctdl.countDown();
        });


        var t2 = new Thread(() -> {
            System.out.println("thread 2 @" + System.nanoTime());
            ctdl.countDown();
        });

        t1.start();
        t2.start();
        ctdl.await();

        System.out.println("main thread : all work is done");
    }
}
