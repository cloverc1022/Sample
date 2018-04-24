package com.mmc.sampletest.Singleton;
/**
 * 枚举式
 * 最简洁、易用 的单例实现方式
 * 优点：
 * 线程安全，自有序列化，实现简单简洁
 * 缺点：
 * 创建时机不可控
 * */
public enum EnumSingleTon {
    INSTANCE;

    private EnumSingleTon() {
    }
}
