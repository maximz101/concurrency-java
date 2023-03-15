package com.novencia.jconcurrency.part3;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationExample {
    public static void main(String[] args) {
        Thread lowPriorityThread = new Thread(new Worker("LowPriorityWorker"));
        Thread highPriorityThread = new Thread(new Worker("HighPriorityWorker"));

        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);

        lowPriorityThread.start();
        highPriorityThread.start();
    }

    static class Worker implements Runnable {
        private final ReentrantLock lock = new ReentrantLock();
        private final String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println(name + " is working.");
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

