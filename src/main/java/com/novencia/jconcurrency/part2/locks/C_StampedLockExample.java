package com.novencia.jconcurrency.part2.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * @author max
 */
public class C_StampedLockExample {
    private final StampedLock lock = new StampedLock();
    private double x;
    private double y;

    public static void main(String[] args) {
        C_StampedLockExample example = new C_StampedLockExample();
        example.move(3, 4);

        var writerThread = new Thread(() -> example.move(5, 6));

        var readerThread = new Thread(() -> {
            double distance = example.distanceFromOrigin();
            System.out.println("Distance from origin: " + distance);
        });

        writerThread.start();
        readerThread.start();
    }

    private void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    private double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
