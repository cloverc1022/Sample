package com.mmc.sampletest.DesignPatterns.AbstractFactory;

import com.mmc.sampletest.DesignPatterns.FactoryMethod.Product;
import com.mmc.sampletest.DesignPatterns.FactoryMethod.ProductA;

public class FactoryA extends Factory {
    @Override
    public Phone buildA() {
        return new IPhone();
    }

    @Override
    public Computer buildB() {
        return new IMac();
    }
}
