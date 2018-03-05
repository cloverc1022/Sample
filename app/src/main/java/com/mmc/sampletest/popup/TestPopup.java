package com.mmc.sampletest.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.mmc.sampletest.R;
import com.mmc.sampletest.popup.interpolator.JellyInterpolator;

/**
 * Created by 上海滩小马哥 on 2018/01/18.
 * 测试用
 */

public class TestPopup extends BasePopupWindow {

    public TestPopup(Activity mContext) {
        this(mContext, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public TestPopup(Activity mContext, int x, int y) {
        super(mContext, x, y);
    }

    @Override
    protected View initContentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_pop_test, null);
    }

    @Override
    protected View initAnimatorView() {
        return findViewById(R.id.content);
    }

    @Override
    protected View initDismissView() {
        return findViewById(R.id.dismiss);
    }

    @Override
    protected Animation setAnimator() {
        Animation scaleAnimation =
                new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setInterpolator(new JellyInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    @Override
    protected Animation setDismissAnimator() {
        return null;
    }
}
