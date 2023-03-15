package com.novencia.jconcurrency.misc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author max
 * java -XX:+PrintFlagsFinal -version | grep 'ThreadStackSize'
 */
public class A_HowManyThreads {
    public static void main(String[] args) throws InterruptedException {
        var atomicInteger = new AtomicInteger();
        while (true) {
            atomicInteger.getAndIncrement();
            new Thread(LockSupport::park).start();
            System.out.println(atomicInteger.get());
            //Thread.sleep(100);
        }
    }
}
