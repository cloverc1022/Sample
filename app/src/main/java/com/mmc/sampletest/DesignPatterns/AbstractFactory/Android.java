package com.mmc.sampletest.DesignPatterns.AbstractFactory;

public class Android extends Phone {
    @Override
    public void create() {
        System.out.print("create Android");
    }
}
