package com.mmc.sampletest.DesignPatterns.TemplateMethod;

/**
 * 具体模板
 */
public class ProductB extends BaseStep {
    @Override
    void step2() {
        System.out.println("ProductB---step2");
    }

    @Override
    void step3() {
        System.out.println("ProductB---step3");
    }
}
