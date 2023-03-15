package com.novencia.jconcurrency.ch01.singleton;

public class A_EagerInitSingleton {
    static A_EagerInitSingleton instance = new A_EagerInitSingleton();

    private A_EagerInitSingleton() {
    }

    public static A_EagerInitSingleton getInstance() {
        // Ex01 A todo implement your code here
        throw new UnsupportedOperationException();
    }

}
