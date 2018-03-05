package com.mmc.sampletest.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import static java.security.AccessController.getContext;

/**
 * Created by 上海滩小马哥 on 2018/01/17.
 * basepop
 */

public abstract class BasePopupWindow {
    private static final String TAG = "BasePopupWindow";

    protected PopupWindow mPopupWindow;
    protected View mContentView;
    protected View mAnimatorView;
    protected View mDismissView;
    protected Activity mContext;

    //    protected Animator mAnimator;
    protected Animation mAnimation;
    protected Animation mDismissAnimation;

    private OnDismissListener onDismissListener;

    public BasePopupWindow(Activity mContext) {
        initView(mContext, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * @param x width
     * @param y height
     */
    public BasePopupWindow(Activity mContext, int x, int y) {
        initView(mContext, x, y);
    }

    private void initView(Activity context, int x, int y) {
        this.mContext = context;

        mContentView = initContentView();
        mContentView.setFocusableInTouchMode(true);

        mPopupWindow = new PopupWindow(mContentView, x, y);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);

        mAnimatorView = initAnimatorView();
        mDismissView = initDismissView();
        if (mDismissView != null) {
            mDismissView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            if (mAnimatorView != null) {
                mAnimatorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        mAnimation = setAnimator();
        mDismissAnimation = setDismissAnimator();
    }

    protected View findViewById(int id) {
        if (mContentView != null && id != 0) {
            return mContentView.findViewById(id);
        }
        return null;
    }

    public void showPopupWindow() {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            tryToShowPopup(null);
        }
    }

    public void showPopupWindow(int resId) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            View view = mContext.findViewById(resId);
            tryToShowPopup(view);
        }
    }

    public void showPopupWindow(View view) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            tryToShowPopup(view);
        }
    }

    private void tryToShowPopup(View v) {
        if (mContentView == null) {
            return;
        }
        //传递了view
        if (v != null) {
            mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        } else {
            if (mContext instanceof Activity) {
                mPopupWindow.showAtLocation(((Activity) mContext).findViewById(android.R.id.content),
                        Gravity.CENTER,
                        0,
                        0);
            } else {
                Log.e(TAG, "can not get token from context,make sure that context is instance of activity");
            }
        }
        if (mAnimation != null) {
            mAnimatorView.setAnimation(mAnimation);
            mAnimation.start();
        }
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            if (mAnimatorView != null && mDismissAnimation != null) {
                mPopupWindow.dismiss();
                mDismissView.setAnimation(mDismissAnimation);
                mDismissAnimation.start();
            } else {
                mPopupWindow.dismiss();
            }
        }
    }

    protected abstract View initContentView();

    protected abstract View initAnimatorView();

    protected abstract View initDismissView();

    protected abstract Animation setAnimator();

    protected abstract Animation setDismissAnimator();

    protected float dipToPx(float dip) {
        if (mContext == null) return dip;
        return dip * mContext.getResources().getDisplayMetrics().density + 0.5f;
    }
}
