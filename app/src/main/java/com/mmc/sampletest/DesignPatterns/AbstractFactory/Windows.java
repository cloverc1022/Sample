package com.mmc.sampletest.DesignPatterns.AbstractFactory;

public class Windows extends Computer {
    @Override
    public void create() {
        System.out.print("create Windows");
    }
}
