package com.mmc.sampletest.popup.interpolator;

import android.view.animation.LinearInterpolator;

/**
 * Created by 上海滩小马哥 on 2018/01/18.
 */

public class JellyInterpolator extends LinearInterpolator {
    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}