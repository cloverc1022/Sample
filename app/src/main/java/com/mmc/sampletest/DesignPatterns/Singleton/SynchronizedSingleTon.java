package com.mmc.sampletest.DesignPatterns.Singleton;
/**
 * 同步锁
 * 使用同步锁 synchronized锁住 创建单例的方法 ，防止多个线程同时调用，从而避免造成单例被多次创建
 * 优点：
 * 线程安全
 * 缺点：
 * 过多同步开销
* */
public class SynchronizedSingleTon {

    private static SynchronizedSingleTon instance = null;

    private SynchronizedSingleTon() {
    }

    //写法1
    public static synchronized SynchronizedSingleTon getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleTon();
        }
        return instance;
    }

    //写法2
    public static SynchronizedSingleTon newInstance() {
        synchronized (SynchronizedSingleTon.class) {
            if (instance == null) {
                instance = new SynchronizedSingleTon();
            }
        }
        return instance;
    }
}
