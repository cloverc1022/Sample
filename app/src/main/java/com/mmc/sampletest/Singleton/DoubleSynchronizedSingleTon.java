package com.mmc.sampletest.Singleton;
/**
 * 双重校验锁
 * 在同步锁的基础上，添加1层 if判断：若单例已创建，则不需再执行加锁操作就可获取实例，从而提高性能
 * 优点：
 * 线程安全，节省资源
 * 缺点：
 * 实现较复杂
* */
public class DoubleSynchronizedSingleTon {

    private static DoubleSynchronizedSingleTon instance = null;

    private DoubleSynchronizedSingleTon() {
    }

    public static DoubleSynchronizedSingleTon newInstance() {
        if (instance == null){
            synchronized (DoubleSynchronizedSingleTon.class) {
                if (instance == null) {
                    instance = new DoubleSynchronizedSingleTon();
                }
            }
        }
        return instance;
    }
}
