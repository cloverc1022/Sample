package com.mmc.sampletest.DesignPatterns.AbstractFactory;

public class IMac extends Computer {
    @Override
    public void create() {
        System.out.print("create IMac");
    }
}
