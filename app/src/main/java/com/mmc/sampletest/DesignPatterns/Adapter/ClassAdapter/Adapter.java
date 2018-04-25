package com.mmc.sampletest.DesignPatterns.Adapter.ClassAdapter;

/**
 * 类适配模式
 */
public class Adapter extends Sourse implements Target {
    @Override
    public void use_220V() {
        this.use_110V();
    }
}
