package com.novencia.jconcurrency.part2.synctools;

import com.novencia.jconcurrency.Util;

import java.util.concurrent.Phaser;

public class D_PhaserExample {
    public static void main(String[] args) throws InterruptedException {
        var phaser = new Phaser(2 + 1); // 2 threads + main thread

        var t1 = new Thread(() -> {
            for (var i = 0; i < 10; i++) {
                phaser.arriveAndAwaitAdvance();
                System.out.println("thread 1 @" + System.nanoTime());
            }
        });

        var t2 = new Thread(() -> {
            for (var i = 0; i < 10; i++) {
                phaser.arriveAndAwaitAdvance();
                System.out.println("thread 2 @" + System.nanoTime());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Util.handleInterruptedException(e);
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(1000);
        phaser.arriveAndDeregister(); //main thread de-registers itself so a phase = 2 threads

    }
}
