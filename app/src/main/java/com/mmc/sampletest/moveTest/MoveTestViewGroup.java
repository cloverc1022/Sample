package com.mmc.sampletest.moveTest;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2018/03/08.
 */

public class MoveTestViewGroup extends RelativeLayout {
    private ViewDragHelper dragHelper;
    private ImageView imageView;

    public MoveTestViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MoveTestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoveTestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        dragHelper = ViewDragHelper.create(this, callback);

        imageView = new ImageView(context);
        imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        LayoutParams params = new LayoutParams(300, LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        addView(imageView);
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == imageView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (imageView.getLeft() < 500){
                dragHelper.smoothSlideViewTo(imageView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(MoveTestViewGroup.this);
            }else {
                dragHelper.smoothSlideViewTo(imageView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(MoveTestViewGroup.this);
            }
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
