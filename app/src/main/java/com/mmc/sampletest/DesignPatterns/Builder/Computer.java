package com.mmc.sampletest.DesignPatterns.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体产品
 */
public class Computer {
    private List<String> components;

    public Computer() {
        components = new ArrayList<>();
    }

    public void add(String component) {
        components.add(component);
    }

    public void show(){
        for (String component:components){
            System.out.println(component);
        }
    }
}
