package com.novencia.jconcurrency.misc;

public class F_PingPongThreads {
    public static void main(String[] args) throws InterruptedException {
        var obj = new Object();

        var t1 = new Thread(() -> {
            while (true) {
                synchronized (obj) {
                    System.out.println("ping");
                    try {
                        Thread.sleep(1000);
                        obj.wait();
                        obj.notifyAll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        var t2 = new Thread(() -> {
            while (true) {
                synchronized (obj) {
                    System.out.println("pong");
                    try {
                        Thread.sleep(1000);
                        obj.notifyAll();
                        obj.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
