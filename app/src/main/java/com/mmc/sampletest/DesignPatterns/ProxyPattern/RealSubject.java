package com.mmc.sampletest.DesignPatterns.ProxyPattern;
/**
 * 真实对象类
* */
public class RealSubject extends Subject {
    @Override
    void buy() {
        System.out.println("buy iphone");
    }
}
