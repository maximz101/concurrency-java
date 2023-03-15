package com.novencia.jconcurrency.part1.threadlocal;

import java.util.Scanner;

public class B_InheritThreadLocalExample {
    public static void main(String[] args) {
        // Uncomment the following line to see the difference

        //var threadLocalVar = new InheritableThreadLocal<Integer>();
        var threadLocalVar = new DoublingThreadLocal();
        // var threadLocalVar = new ThreadLocal<Integer>();
        var t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocalVar.get());
            threadLocalVar.set(20);
            System.out.println(Thread.currentThread().getName() + " : " + threadLocalVar.get());
            var c = new Thread(() -> System.out.println(Thread.currentThread().getName() + " : " + threadLocalVar.get()));
            c.start();
        });

        t.start();

        new Scanner(System.in).nextLine();
    }

    private static class DoublingThreadLocal extends InheritableThreadLocal<Integer> {
        @Override
        protected Integer childValue(Integer parentValue) {
            return parentValue * 2;
        }
    }

}
