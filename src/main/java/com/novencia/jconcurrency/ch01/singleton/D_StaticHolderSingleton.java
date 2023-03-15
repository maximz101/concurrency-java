package com.novencia.jconcurrency.ch01.singleton;

public class D_StaticHolderSingleton {
    private D_StaticHolderSingleton() {
    }

    public static D_StaticHolderSingleton getInstance() {
        // Ex01 D todo implement your code here
        throw new UnsupportedOperationException();
    }

    private static class SingletonHolder {
        // Ex01 D todo implement your code here
    }

}
