package com.mmc.sampletest.DesignPatterns.Adapter.ObjectAdapter;

import com.mmc.sampletest.DesignPatterns.Adapter.ClassAdapter.Target;

public class Adapter implements Target {

    private Sourse sourse;

    public Adapter(Sourse sourse) {
        this.sourse = sourse;
    }

    @Override
    public void use_220V() {
        this.sourse.use_110V();
    }
}
