package com.novencia.jconcurrency.part2.synctools;

import com.novencia.jconcurrency.Util;

import java.util.concurrent.Semaphore;

/**
 * @author max
 */
public class A_SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        var t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquired the semaphore.");
                Thread.sleep(3000);
                System.out.println("Thread 1 releasing the semaphore.");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 2 acquired the semaphore.");
                Thread.sleep(3000);
                System.out.println("Thread 2 releasing the semaphore.");
                semaphore.release();
            } catch (InterruptedException e) {
                Util.handleInterruptedException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
