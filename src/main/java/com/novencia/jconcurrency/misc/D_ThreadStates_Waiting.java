package com.novencia.jconcurrency.misc;

import java.util.Scanner;

/**
 * @author max
 * Check states in visual VM
 */
public class D_ThreadStates_Waiting {
    public static void main(String[] args) throws InterruptedException {

        var t1 = new Thread(() -> {
            try {
                //Thread.sleep(10000); // "Wait" in visualVm

                // uncomment below line to see the difference
                //LockSupport.park(); // "Park" in VisualVm
                while (true) {
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
        t1.setName("t1");

        t1.start();
        Thread.sleep(10000);

        // test interrupting the thread interrupt
        // t1.interrupt();

        // wait for input
        new Scanner(System.in).next();
    }
}
