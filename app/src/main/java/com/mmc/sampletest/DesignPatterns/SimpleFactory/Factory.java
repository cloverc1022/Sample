package com.mmc.sampletest.DesignPatterns.SimpleFactory;
/**
 * 工厂类
* */
public class Factory {

    public static Product createProduct(String type) {
        switch (type) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }
}
