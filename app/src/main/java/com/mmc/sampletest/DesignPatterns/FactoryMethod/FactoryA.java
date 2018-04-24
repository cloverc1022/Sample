package com.mmc.sampletest.DesignPatterns.FactoryMethod;

public class FactoryA extends Factory {
    @Override
    public Product build() {
        return new ProductA();
    }
}
