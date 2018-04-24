package com.mmc.sampletest.Singleton;
/**
 * 静态内部类
 * 根据 静态内部类 的特性，同时解决了按需加载、线程安全的问题，同时实现简洁
 * 优点：
 * 线程安全，节约资源，实现简单
* */
public class StaticInnerClassSingleTon {

    private static class InnerClass{
        private static StaticInnerClassSingleTon instance = new StaticInnerClassSingleTon();
    }

    private StaticInnerClassSingleTon() {
    }

    public static StaticInnerClassSingleTon getInstance(){
        return InnerClass.instance;
    }
}
