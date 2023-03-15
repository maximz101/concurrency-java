package com.novencia.jconcurrency.part1;

import java.util.Scanner;

public class B_VolatileExample {
    // try changing this variable to volatile
    private static boolean running = true;

    public static void main(String[] args) {
        var t = new Thread(() -> {
            // thread may cache "running" variable
            // unless we mark it as volatile => hence it will always be read from main memory
            while (running) {
                System.out.println("running " + running);
                try {
                    Thread.sleep(2000);
                    Thread.onSpinWait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Thread finished");
        }, "thread-1");
        t.start();

        // wait for an input
        new Scanner(System.in).nextLine();

        System.out.println("Changing running to false");

        running = false;

        new Scanner(System.in).nextLine();
    }
}
