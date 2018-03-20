package com.mmc.sampletest.moveTest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by 上海滩小马哥 on 2018/03/08.
 */

public class MoveTestView extends TextView {
    int lastX, lastY;
    private Scroller scroller;

    public MoveTestView(Context context) {
        super(context);
        init(context);
    }

    public MoveTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoveTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        scroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                1.layout
//                layout(getLeft() + offsetX, getTop() + offsetY
//                        ,getRight() + offsetX, getBottom() + offsetY);

//                2.
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

//                3.
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);
//                scrollTo(getLeft()+ offsetX, getTop() +offsetY);

//                4.scroller
//                View parentView = ((View) getParent());
                break;
            case MotionEvent.ACTION_UP:
                View parentView = ((View) getParent());
                scroller.startScroll(parentView.getScrollX(), parentView.getScrollY(), -parentView.getScrollX(), -parentView.getScrollY(), 3000);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
