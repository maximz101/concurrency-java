package com.novencia.jconcurrency.part1;

import java.util.concurrent.locks.LockSupport;

public class A_CreatingAThread {

    public static void main(String[] args) {
        // passing a runnable
        var t1 = new Thread(() -> System.out.println("Hello from thread " + Thread.currentThread()));
        t1.start();

        // extending Thread
        var t2 = new MyThread();
        t2.start();

        // 21
        var t3 = Thread.ofPlatform()
                .name("t3")
                .start(() -> System.out.println("Hello from thread " + Thread.currentThread()));

        var t4 = Thread.ofVirtual()
                .name("t4")
                .start(() -> System.out.println("Hello from thread " + Thread.currentThread()));

        LockSupport.parkNanos(1_000_000_000);
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from thread " + Thread.currentThread());
        }
    }
}
