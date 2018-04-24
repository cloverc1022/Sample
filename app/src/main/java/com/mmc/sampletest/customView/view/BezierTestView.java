package com.mmc.sampletest.customView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 上海滩小马哥 on 2017/10/24.
 */

public class BezierTestView extends View{
    private Paint paint;
    private Path path;

    private int width;
    private int heigth;
    private float targetY;
    private float ctrlX, ctrlY;
    private boolean isRight;

    public BezierTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setColor(0x55EC6941);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;

        targetY = heigth;
        ctrlY = heigth - 1/8f*heigth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.moveTo(-1/4f*width, targetY);
        path.quadTo(ctrlX,ctrlY,width+ 1/4f*width, targetY);
        path.lineTo(width+ 1/4f*width, heigth);
        path.lineTo(-1/4f*width, heigth);
        path.close();
        canvas.drawPath(path, paint);

        if (ctrlX<= -1/4f*width){
            isRight = true;
        }else if (ctrlX>= width+ 1/4f*width){
            isRight = false;
        }
        ctrlX = isRight?ctrlX + 20: ctrlX - 20;
        if (targetY > 1/8f* heigth){
            targetY -= 2;
            ctrlY -= 2;
        }
        path.reset();
        invalidate();
    }
}
