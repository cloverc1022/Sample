package com.mmc.sampletest.customView.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2017/10/10.
 */

public class XforModeView extends View {

    private Paint mPaint;
    private Bitmap srcBitmap, dstBitmap;
    private RectF dstRect, srcRect;
    private Xfermode mXfermode;
    private PorterDuff.Mode mPorterDuffMode = PorterDuff.Mode.CLEAR;

    public XforModeView(Context context) {
        super(context);
    }

    public XforModeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XforModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XforMode, defStyleAttr, 0);
        mPorterDuffMode = intToMode(a.getInt(R.styleable.XforMode_Xmode, 0));
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.one);
        dstBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.two);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
//        int saveCount = canvas.saveLayer(srcRect, mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBitmap, null, dstRect, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(mPorterDuffMode));
        canvas.drawBitmap(srcBitmap, null, srcRect, mPaint);

        mPaint.setXfermode(null);
//        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = w <= h? w:h;
        int centerX = w/2;
        int centerY = h/2;
        int q = width/4;

        srcRect = new RectF(centerX- q, centerY-q, centerX+q, centerY +q);
        dstRect = new RectF(centerX- q, centerY-q, centerX+q, centerY +q);
    }

    public PorterDuff.Mode intToMode(int val) {
        switch (val) {
            default:
            case  0: return PorterDuff.Mode.CLEAR;
            case  1: return PorterDuff.Mode.SRC;
            case  2: return PorterDuff.Mode.DST;
            case  3: return PorterDuff.Mode.SRC_OVER;
            case  4: return PorterDuff.Mode.DST_OVER;
            case  5: return PorterDuff.Mode.SRC_IN;
            case  6: return PorterDuff.Mode.DST_IN;
            case  7: return PorterDuff.Mode.SRC_OUT;
            case  8: return PorterDuff.Mode.DST_OUT;
            case  9: return PorterDuff.Mode.SRC_ATOP;
            case 10: return PorterDuff.Mode.DST_ATOP;
            case 11: return PorterDuff.Mode.XOR;
            case 16: return PorterDuff.Mode.DARKEN;
            case 17: return PorterDuff.Mode.LIGHTEN;
            case 13: return PorterDuff.Mode.MULTIPLY;
            case 14: return PorterDuff.Mode.SCREEN;
            case 12: return PorterDuff.Mode.ADD;
            case 15: return PorterDuff.Mode.OVERLAY;
        }
    }
}
