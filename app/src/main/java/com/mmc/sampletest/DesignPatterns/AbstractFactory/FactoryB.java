package com.mmc.sampletest.DesignPatterns.AbstractFactory;

import com.mmc.sampletest.DesignPatterns.FactoryMethod.Product;
import com.mmc.sampletest.DesignPatterns.FactoryMethod.ProductB;

public class FactoryB extends Factory {
    @Override
    public Phone buildA() {
        return new Android();
    }

    @Override
    public Computer buildB() {
        return new Windows();
    }
}
