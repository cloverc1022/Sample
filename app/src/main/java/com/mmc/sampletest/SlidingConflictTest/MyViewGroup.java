package com.mmc.sampletest.SlidingConflictTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 上海滩小马哥 on 2018/04/03.
 * 简易可横向滑动的view，类似viewpager
 */

public class MyViewGroup extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker velocityTracker;
    private int touchLastX;
    private int interceptLastX, interceptLastY;
    private int childWidth;
    private int childIndex;
    private int childCount;

    public MyViewGroup(Context context) {
        super(context);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if (scroller == null){
            scroller = new Scroller(getContext());
            velocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;

        childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (childCount == 0){
            setMeasuredDimension(0, 0);
        }else if (widthMode== MeasureSpec.AT_MOST && heightMode== MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth()* childCount;
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measureWidth, measureHeight);
        }else if (widthMode== MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth()* childCount;
            setMeasuredDimension(measureWidth, heightSize);
        }else if (heightMode== MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSize, measureHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftLength = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility()!= View.GONE){
                childWidth = childView.getMeasuredWidth();
                childView.layout(leftLength, 0, leftLength+ childWidth, childView.getMeasuredHeight());
                leftLength +=  childWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int currentX = (int)ev.getX();
        int currentY = (int)ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                if (!scroller.isFinished()){
                    isIntercept = true;
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int moveLengthX = currentX- interceptLastX;
                int moveLengthY = currentY- interceptLastY;
                if (Math.abs(moveLengthX) > Math.abs(moveLengthY)){
                    isIntercept = true;
                }else {
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
        }
        interceptLastX = currentX;
        interceptLastY = currentY;
        touchLastX = currentX;
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        int currentX = (int) event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                scrollBy(touchLastX - currentX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                velocityTracker.computeCurrentVelocity(1000);
                float xVelocity = velocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 1000){
                    childIndex = xVelocity>0?childIndex-1:childIndex+1;
                }else {
                    childIndex = (scrollX + childWidth/2)/childWidth;
                }
                childIndex = Math.max(0, Math.min(childIndex, childCount));
                int dx = childIndex*childWidth - scrollX;
                if (childIndex == childCount){
                    dx = (childCount - 1)*childWidth - scrollX;
                }
                smoothScrollBy(dx);
                velocityTracker.clear();
                break;
        }
        touchLastX = currentX;
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollBy(int x){
        scroller.startScroll(getScrollX(), 0, x, 0, 1000);
        invalidate();
    }
}
