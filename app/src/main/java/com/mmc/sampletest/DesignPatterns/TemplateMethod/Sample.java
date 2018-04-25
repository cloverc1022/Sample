package com.mmc.sampletest.DesignPatterns.TemplateMethod;

public class Sample {
    public static void main(String[] args){
        ProductA productA = new ProductA();
        productA.start();
        ProductB productB = new ProductB();
        productB.start();
    }
}
