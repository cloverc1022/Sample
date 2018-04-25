package com.mmc.sampletest.DesignPatterns.Adapter.ClassAdapter;

public class Adapter extends Sourse implements Target {
    @Override
    public void use_220V() {
        this.use_110V();
    }
}
