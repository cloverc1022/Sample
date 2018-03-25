package com.mmc.sampletest.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by 上海滩小马哥 on 2018/03/23.
 */

public class ClockView extends View {

    private Paint circlePaint;
    private Paint pointPaint;
    private Paint linePaint;
    private Paint textPaint;
    private int radius, centerRadius, centerX, centerY, hourLength, minLength;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(Color.WHITE);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(5);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(30);
        textPaint.setStrokeWidth(5);
        textPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        radius = getMeasuredWidth() / 3;
        centerRadius = getMeasuredWidth() / 50;
        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredWidth() / 2;
        minLength = radius * 2 / 3;
        hourLength = minLength * 2 / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFF29B76);
        //画圆
        canvas.drawCircle(centerX, centerY, radius, circlePaint);
        //画中心圆点
        canvas.drawCircle(centerX, centerY, centerRadius, pointPaint);
        //画刻度
        for (int i = 0; i < 24; i++) {
            int offset = i < 6 ? 15 : 8;
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                canvas.drawLine((float) centerX, (float) (centerY - radius), (float) centerX, (float) (centerY - radius + 50), linePaint);
            } else {
                canvas.drawLine((float) centerX, (float) (centerY - radius), (float) centerX, (float) (centerY - radius + 25), linePaint);
            }
            if (i % 2 == 0) {
                float textWidth = textPaint.measureText(String.valueOf((24 - i) / 2));
                float startX = centerX - textWidth / 2;
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float dy = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;

                canvas.save();
                if (i == 0 || i == 6 || i == 12 || i == 18) {
                    canvas.rotate((15 * i), centerX, centerY - radius + 60 + dy);
                    canvas.drawText(String.valueOf((24 - i) / 2), startX, centerY - radius + 60 + 2 * dy, textPaint);
                } else {
                    canvas.rotate((15 * i), centerX, centerY - radius + 35 + dy);
                    canvas.drawText(String.valueOf((24 - i) / 2), startX, centerY - radius + 35 + 2 * dy, textPaint);
                }
                canvas.restore();
            }
            canvas.rotate(-15, centerX, centerY);
        }
        //画时针分针
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        drawScale(hour, minute, canvas);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 1000);
    }

    private void drawScale(int hour, int min, Canvas canvas) {
        int hourDegree = 360 * hour / 12;
        int minDegree = 360 * min / 60;
        canvas.save();
        canvas.rotate(hourDegree, centerX, centerY);
        canvas.drawLine((float) centerX, (float) (centerY), (float) centerX, (float) (centerY - hourLength), linePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(minDegree, centerX, centerY);
        canvas.drawLine((float) centerX, (float) (centerY), (float) centerX, (float) (centerY - minLength), linePaint);
        canvas.restore();
    }
}
