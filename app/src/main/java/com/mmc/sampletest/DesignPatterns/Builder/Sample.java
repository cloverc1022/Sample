package com.mmc.sampletest.DesignPatterns.Builder;

public class Sample {

    public static void main(String[] args){
        Director director = new Director();
        Builder builder = new ComputerBuilder();
        director.construct(builder);
        Computer computer = builder.getProduct();
        computer.show();
    }
}
