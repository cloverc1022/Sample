package com.mmc.sampletest.DesignPatterns.FactoryMethod;

public class FactoryB extends Factory {
    @Override
    public Product build() {
        return new ProductB();
    }
}
