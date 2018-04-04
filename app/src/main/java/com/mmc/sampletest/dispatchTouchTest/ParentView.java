package com.mmc.sampletest.dispatchTouchTest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 上海滩小马哥 on 2018/04/02.
 */

public class ParentView extends LinearLayout {
    private static final String TAG = "ParentView";

    public ParentView(Context context) {
        super(context);
    }

    public ParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*
     * 父view
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down-------dispatchTouchEvent");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "up-------dispatchTouchEvent");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down-------onInterceptTouchEvent");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "up-------onInterceptTouchEvent");
                return false;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down-------onTouchEvent");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "up-------onTouchEvent");
                break;
        }
        return false;
    }
}
