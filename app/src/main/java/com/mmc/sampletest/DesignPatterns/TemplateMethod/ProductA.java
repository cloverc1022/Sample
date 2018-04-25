package com.mmc.sampletest.DesignPatterns.TemplateMethod;
/**
 * 具体模板
* */
public class ProductA extends BaseStep {
    @Override
    void step2() {
        System.out.println("ProductA---step2");
    }

    @Override
    void step3() {
        System.out.println("ProductA---step3");
    }
}
