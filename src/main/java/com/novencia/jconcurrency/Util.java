package com.novencia.jconcurrency;

public final class Util {
    private Util() {
    }

    public static void handleInterruptedException(InterruptedException e) {
        System.out.println(Thread.currentThread().getName() + " : interrupted." + e.getMessage());
        Thread.currentThread().interrupt();
    }

}
