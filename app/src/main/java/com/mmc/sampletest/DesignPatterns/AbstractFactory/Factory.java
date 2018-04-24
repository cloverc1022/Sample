package com.mmc.sampletest.DesignPatterns.AbstractFactory;

import com.mmc.sampletest.DesignPatterns.FactoryMethod.Product;

public abstract class Factory {
    public abstract Phone buildA();
    public abstract Computer buildB();
}
