package com.mmc.sampletest.customView.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class SweepGradientView extends View {

    public static final int START = 1;
    public static final int STOP = 0;
    private Paint mPaint;

    private int strokeStartColor;

    private int strokeEndColor;

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    private float strokeWidth = 100;

    private int width;

    private int height;

    private SweepGradient mSweepGradient;

    private ObjectAnimator objectAnimator;

    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SweepGradientView, defStyleAttr, 0);
        strokeStartColor = a.getColor(R.styleable.SweepGradientView_start_color, Color.BLUE);
        strokeEndColor = a.getColor(R.styleable.SweepGradientView_end_color, Color.BLUE);
        strokeWidth = a.getDimension(R.styleable.SweepGradientView_stroke_width, 2);
        a.recycle();

        mPaint = new Paint();
        //防锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        if(mSweepGradient == null){
            mSweepGradient = new SweepGradient(width / 2f, height / 2f, strokeStartColor, strokeEndColor);
        }
        mPaint.setShader(mSweepGradient);
        //画圆环
        canvas.drawCircle(width / 2f, height / 2f, (width - strokeWidth) / 2f, mPaint);
//        canvas.drawCircle(300, 300, strokeWidth, mPaint);

        setMode(START);
    }

    public void setMode(int mode){
        switch (mode){
            case START:
                if (objectAnimator == null){
//                    objectAnimator = ObjectAnimator.ofFloat(this, "strokeWidth", 0, 300, 100);
                    objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
                    objectAnimator.setDuration(2000);
                    objectAnimator.setRepeatMode(ValueAnimator.RESTART);
                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                    objectAnimator.setInterpolator(new LinearInterpolator());
                    objectAnimator.start();
                }else {
                    objectAnimator.resume();
                }
                break;
            case STOP:
                if (objectAnimator != null){
                    objectAnimator.pause();
                }
                break;
        }
    }
}
