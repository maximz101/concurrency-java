package com.novencia.jconcurrency.misc;

import java.util.Scanner;

/**
 * @author max
 */
public class C_FieldVisibility {
    // how can I make it visible to the reader thread?
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        // writer thread
        var t1 = new Thread(() -> {
            while (true) {
                flag = !flag;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        //reader thread
        var t2 = new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("reader thread : flag=" + flag);
                    try {
                        Thread.sleep(1000);
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

        new Scanner(System.in).nextLine();
    }

}
