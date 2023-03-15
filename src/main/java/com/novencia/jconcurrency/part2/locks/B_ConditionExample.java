package com.novencia.jconcurrency.part2.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.novencia.jconcurrency.Util.handleInterruptedException;

public class B_ConditionExample {
    public static final int CAPACITY = 5;
    private final List<String> list = new ArrayList<>(CAPACITY);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition listEmptyCondition = lock.newCondition();
    private final Condition listFullCondition = lock.newCondition();

    public static void main(String[] args) {
        B_ConditionExample example = new B_ConditionExample();
        var thread1 = new Thread(() -> {
            while (true) {
                example.writeInList();
            }
        }, "Writer 1");
        var thread2 = new Thread(() -> {
            while (true) {
                example.writeInList();
            }
        }, "Writer 2");
        var thread3 = new Thread(() -> {
            while (true) {
                example.consume();
            }
        }, "Reader 1");
        thread1.start();
        thread2.start();
        thread3.start();

        new Scanner(System.in).nextLine();
    }

    private void writeInList() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " : lock acquired");
        try {
            while (list.size() == CAPACITY) {
                listFullCondition.await();
            }
            list.add("Hello#%d".formatted(list.size()));
            listEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " : lock released");
        }
    }

    private void consume() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " : lock acquired");
        try {
            while (list.isEmpty()) {
                listEmptyCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + " : consumed " + list.remove(0));
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        } finally {
            listFullCondition.signalAll();
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " : lock released");
        }
    }
}
