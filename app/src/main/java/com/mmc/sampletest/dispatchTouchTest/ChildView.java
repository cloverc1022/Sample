package com.mmc.sampletest.dispatchTouchTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import java.util.logging.Logger;

/**
 * Created by 上海滩小马哥 on 2018/04/02.
 */

public class ChildView extends Button {

    public static final String TAG = "ChildView";

    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down-------dispatchTouchEvent");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "up-------dispatchTouchEvent");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "down-------onTouchEvent");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "up-------onTouchEvent");
                return true;
        }
        return super.onTouchEvent(event);
    }

}
