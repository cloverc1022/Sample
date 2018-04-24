package com.mmc.sampletest.DesignPatterns.AbstractFactory;

public class IPhone extends Phone {
    @Override
    public void create() {
        System.out.print("create IPhone");
    }
}
