package com.novencia.jconcurrency.ch01.singleton;

public enum E_EnumSingleton {
    INSTANCE;

    public static E_EnumSingleton getInstance(){
        // Ex01 E todo implement your code here
        throw new UnsupportedOperationException();
    }
}