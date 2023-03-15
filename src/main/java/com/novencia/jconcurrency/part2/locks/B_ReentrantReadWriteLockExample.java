package com.novencia.jconcurrency.part2.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.novencia.jconcurrency.Util.handleInterruptedException;

/**
 * @author max
 * Wrapper around a non-thread-safe map
 */
public class B_ReentrantReadWriteLockExample {
    private final Map<String, String> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        B_ReentrantReadWriteLockExample example = new B_ReentrantReadWriteLockExample();

        // Start writer thread
        var writerThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String key = "key" + i;
                String value = "value" + i;
                example.put(key, value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    handleInterruptedException(e);
                }
            }
        });

        // Start reader threads
        var readerThread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String key = "key" + i;
                String value = example.get(key);
                System.out.println("Reader 1: " + key + " = " + value);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    handleInterruptedException(e);
                }
            }
        });

        var readerThread2 = new Thread(() -> {
            for (int i = 5; i >= 1; i--) {
                String key = "key" + i;
                String value = example.get(key);
                System.out.println("Reader 2: " + key + " = " + value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    handleInterruptedException(e);
                }
            }
        });

        writerThread.start();
        readerThread1.start();
        readerThread2.start();
    }

    private void put(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println("Writing " + key + " = " + value);
            map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String get(String key) {
        lock.readLock().lock();
        try {
            System.out.println("Reading : " + key);
            return map.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
}
