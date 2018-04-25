package com.mmc.sampletest.DesignPatterns.ProxyPattern;
/**
 * 代理类
* */
public class Proxy extends Subject {
    @Override
    void buy() {
        RealSubject realSubject = new RealSubject();
        realSubject.buy();

        wrap();
    }

    public void wrap(){
        System.out.println("wrap");
    }
}
