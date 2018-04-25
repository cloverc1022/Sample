package com.mmc.sampletest.DesignPatterns.FacadePattern;

/**
 * 门面模式
 */
public class Facade {
    private ComponentA componenta;
    private ComponentB componentb;
    private ComponentC componentc;

    public Facade(ComponentA componenta, ComponentB componentb, ComponentC componentc) {
        this.componenta = componenta;
        this.componentb = componentb;
        this.componentc = componentc;
    }

    public void on() {
        componenta.on();
        componentb.on();
        componentc.on();
    }

    public void off() {
        componenta.off();
        componentb.off();
        componentc.off();
    }
}
