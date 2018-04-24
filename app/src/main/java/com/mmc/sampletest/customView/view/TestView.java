package com.mmc.sampletest.customView.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2017/10/9.
 */

public class TestView extends View {

    private Paint mPaint;

    private ObjectAnimator objectAnimator;

    private Bitmap bitmap;

    private int mWidth = 0;

    private int mHeight = 0;

    private float radius = 100;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

//        mPaint.setColor(Color.argb(255, 255, 128, 103));

        /*
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
//        mPaint.setStyle(Paint.Style.FILL);

//        mPaint.setStrokeWidth(20);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.393F, 0.769F, 0.189F, 0, 0,
                0.349F, 0.686F, 0.168F, 0, 0,
                0.272F, 0.534F, 0.131F, 0, 0,
                0, 0, 0, 1, 0,
        });
        //色彩矩阵颜色过滤器
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        //光照颜色过滤   mul全称是colorMultiply意为色彩倍增，而add全称是colorAdd意为色彩添加
//        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

        //PorterDuffColorFilter
//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.drawCircle(mWidth/2, mHeight/2, radius, mPaint);
        canvas.drawBitmap(bitmap, 0, 100, mPaint);

//        if (objectAnimator == null){
//            objectAnimator = ObjectAnimator.ofFloat(this, "radius", 0f, 100f, 0f);
//            objectAnimator.setDuration(3000);
//            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//            objectAnimator.setInterpolator(new LinearInterpolator());
//            objectAnimator.start();
//        }
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }
}
