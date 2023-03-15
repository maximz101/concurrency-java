package com.novencia.jconcurrency.part3;

public class RaceConditionExample {
    /**
     * Solutions?
     */
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
                counter.count++;
            }
        };
        var t1 = new Thread(runnable);
        var t2 = new Thread(runnable);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public void increment() {
            count++;// 3 operations: read, increment, write (count = count + 1), it's not atomic
        }

        public int getCount() {
            return count;
        }
    }
}
