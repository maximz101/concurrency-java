package com.novencia.jconcurrency.part1.threadlocal;

import java.util.Scanner;

public class A_ThreadLocalExample {
    private static final ThreadLocal<Integer> threadLocalVar = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int value = threadLocalVar.get();
            threadLocalVar.set(value + 1);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocalVar.get());
            threadLocalVar.remove();
        };

        // Create and start 5 threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        // Wait for the threads to finish
        new Scanner(System.in).nextLine();
    }
}
