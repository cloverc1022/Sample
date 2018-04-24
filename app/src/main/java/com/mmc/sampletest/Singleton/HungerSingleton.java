package com.mmc.sampletest.Singleton;

/**
 * 饿汉式
 * 优点：
 * 线程安全，初始化速度快，占用内存小
 * 缺点：
 * 创建时机不可控
 */
public class HungerSingleton {

    private static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return instance;
    }
}
