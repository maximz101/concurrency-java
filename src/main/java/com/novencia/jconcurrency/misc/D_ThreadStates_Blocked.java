package com.novencia.jconcurrency.misc;

import java.util.Scanner;

public class D_ThreadStates_Blocked {
    public static void main(String[] args) throws InterruptedException {
        var obj = new Object();

        Thread t1 = new Thread(() -> {
            // obj is already acquired by the main thread
            // when this thread tries to acquire obj it will remain blocked
            // until obj is release
            synchronized (obj) {
                System.out.println("I am t1");
            }
        });
        t1.setName("t1");

        synchronized (obj) {
            t1.start();

            // just to wait on input
            new Scanner(System.in).next();
        }

    }
}
