package com.novencia.jconcurrency.part3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Credit : <a href="https://livebook.manning.com/book/the-well-grounded-java-developer-second-edition/chapter-5">The Well Grounded Java Dev</a>
 * FSO = Fully Synchronized Object
 */
public class FSOAccount {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    private final int id = NEXT_ID.getAndIncrement();
    private double balance;

    public FSOAccount(double openingBalance) {
        // Check to see openingBalance > 0, throw if not
        balance = openingBalance;
    }

    public static void main(String[] args) throws InterruptedException {
        int MAX_TRANSFERS = 1_000;

        FSOAccount a = new FSOAccount(10_000);
        FSOAccount b = new FSOAccount(10_000);
        Thread tA = new Thread(() -> {
            for (int i = 0; i < MAX_TRANSFERS; i += 1) {
                boolean ok = a.transferTo(b, 1);
                if (!ok) {
                    System.out.println("Thread A failed at " + i);
                }
            }
        });
        Thread tB = new Thread(() -> {
            for (int i = 0; i < MAX_TRANSFERS; i += 1) {
                boolean ok = b.transferTo(a, 1);
                if (!ok) {
                    System.out.println("Thread B failed at " + i);
                }
            }
        });
        tA.start();
        tB.start();
        tA.join();
        tB.join();

        System.out.println("Finished: " + a.getBalance() + " : " + b.getBalance());
    }

    public synchronized boolean withdraw(int amount) {
        // Check to see amount > 0, throw if not
        if (balance >= amount) {
            balance -= amount;
            return true;
        }

        return false;
    }

    public synchronized void deposit(int amount) {
        // Check to see amount > 0, throw if not
        balance += amount;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized boolean transferTo(FSOAccount other, int amount) {
        // Check to see amount > 0, throw if not
        // Simulate some other checks that need to occur
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (balance >= amount) {
            balance -= amount;
            other.deposit(amount);
            return true;
        }

        return false;
    }
}