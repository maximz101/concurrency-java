package com.novencia.jconcurrency.part1;

public class A_CreatingAThread {

    public static void main(String[] args) {
        // passing a runnable
        var t1 = new Thread(() -> System.out.println(""));
        t1.start();

        // extending Thread
        var t2 = new MyThread();
        t2.start();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("");
        }
    }
}
