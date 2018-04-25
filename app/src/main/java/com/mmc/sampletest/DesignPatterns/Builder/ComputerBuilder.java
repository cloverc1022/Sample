package com.mmc.sampletest.DesignPatterns.Builder;
/**
 * 具体建造者
 * */
public class ComputerBuilder extends Builder {

    private Computer computer = new Computer();

    @Override
    public void buildCPU() {
        computer.add("CPU");
    }

    @Override
    public void buildMainboard() {
        computer.add("Mainboard");
    }

    @Override
    public void buildHD() {
        computer.add("HD");
    }

    @Override
    public Computer getProduct() {
        return computer;
    }
}
