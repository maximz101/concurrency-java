package com.novencia.jconcurrency.ch01.singleton;

public class C_LazyDoubleCheckedSingleton {
    static C_LazyDoubleCheckedSingleton instance;

    private C_LazyDoubleCheckedSingleton() {
    }

    public static C_LazyDoubleCheckedSingleton getInstance() {
        // Ex01 C todo implement your code here
        throw new UnsupportedOperationException();
    }
}
