package com.novencia.jconcurrency.part2.locks;

import java.util.concurrent.locks.ReentrantLock;

public class A_ReentrantLockExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 acquired the lock.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted." + e.getMessage());
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println("Thread 1 released the lock.");
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 2 acquired the lock.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted." + e.getMessage());
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println("Thread 2 released the lock.");
            }
        });

        t1.start();
        t2.start();
    }
}
