package com.mmc.sampletest.DesignPatterns.TemplateMethod;
/**
 * 抽象模板结构
* */
public abstract class BaseStep {
    final void start() {
        step1();
        step2();
        step3();
    }

    private void step1() {
        System.out.println("step1");
    }

    abstract void step2();

    abstract void step3();
}
