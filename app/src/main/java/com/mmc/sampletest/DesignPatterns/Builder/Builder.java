package com.mmc.sampletest.DesignPatterns.Builder;
/**
 * 建造者抽象类
 * */
public abstract class Builder {
    public abstract void buildCPU();
    public abstract void buildMainboard();
    public abstract void buildHD();
    public abstract Computer getProduct();
}
