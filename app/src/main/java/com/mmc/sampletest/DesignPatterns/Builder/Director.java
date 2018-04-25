package com.mmc.sampletest.DesignPatterns.Builder;
/**
 * 指挥者
* */
public class Director {

    public void construct(Builder builder){
        builder.buildCPU();
        builder.buildMainboard();
        builder.buildHD();
    }
}
