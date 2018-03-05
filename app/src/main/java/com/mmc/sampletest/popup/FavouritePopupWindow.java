package com.mmc.sampletest.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2018/01/22.
 */

public class FavouritePopupWindow extends BasePopupWindow {


    public FavouritePopupWindow(Activity mContext) {
        this(mContext, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public FavouritePopupWindow(Activity mContext, int x, int y) {
        super(mContext, x, y);
    }

    @Override
    protected View initContentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_pop_favourite, null);
    }

    @Override
    protected View initAnimatorView() {
        return findViewById(R.id.content);
    }

    @Override
    protected View initDismissView() {
        return null;
    }

    @Override
    protected Animation setAnimator() {
        TranslateAnimation showAnima = new TranslateAnimation(dipToPx(200f), 0, 0, 0);
        showAnima.setInterpolator(new DecelerateInterpolator());
        showAnima.setDuration(350);
        return showAnima;
    }

    @Override
    protected Animation setDismissAnimator() {
        return null;
    }
}
