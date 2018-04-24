package com.mmc.sampletest.Singleton;

/**
 * 懒汉式
 * 优点：
 * 按需加载，节约资源
 * 缺点：
 * 线程不安全
 */
public class LazySingleTon {

    private static LazySingleTon instance = null;

    private LazySingleTon() {
    }

    public static LazySingleTon getInstance() {
        if (instance == null) {
            instance = new LazySingleTon();
        }
        return instance;
    }
}
