package com.mmc.sampletest.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 上海滩小马哥 on 2017/10/11.
 */

public class CircleView extends View {
    private static final float STROKE_WIDTH = 1F / 256F, // 描边宽度占比
            SPACE = 1F / 64F,// 大圆小圆线段两端间隔占比
            LINE_LENGTH = 3F / 32F, // 线段长度占比
            CRICLE_LARGER_RADIU = 3F / 32F,// 大圆半径
            CRICLE_SMALL_RADIU = 5F / 64F,// 小圆半径
            ARC_RADIU = 1F / 8F,// 弧半径
            ARC_TEXT_RADIU = 5F / 32F;// 弧围绕文字半径

    private Paint strokePaint, textPaint, arcPaint;// 描边画笔

    private int size;// 控件边长

    private float strokeWidth;// 描边宽度
    private float ccX, ccY;// 中心圆圆心坐标
    private float largeCricleRadiu;// 大圆半径
    private float smallCricleRadiu;// 小圆半径
    private float lineLength;// 线段长度
    private float space;// 大圆小圆线段两端间隔
    private float textOffsetY;// 文本的Y轴偏移值

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);

        /*
         * 初始化文字画笔
         */
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 强制长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 获取控件边长
        size = w;

        // 参数计算
        calculation();
    }

    /*
     * 参数计算
     */
    private void calculation() {
        // 计算描边宽度
        strokeWidth = STROKE_WIDTH * size;

        // 计算大圆半径
        largeCricleRadiu = size * CRICLE_LARGER_RADIU;
        // 计算小圆半径
        smallCricleRadiu = size * CRICLE_SMALL_RADIU;

        // 计算线段长度
        lineLength = size * LINE_LENGTH;

        // 计算大圆小圆线段两端间隔
        space = size * SPACE;

        // 计算中心圆圆心坐标
        ccX = size / 2;
        ccY = size / 2 + size * CRICLE_LARGER_RADIU;

        // 设置参数
        setPara();
    }

    /**
     * 设置参数
     */
    private void setPara() {
        // 设置描边宽度
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景
        canvas.drawColor(0xFFF29B76);

        // 绘制中心圆
        canvas.drawCircle(ccX, ccY, largeCricleRadiu, strokePaint);
        canvas.drawText("machao", ccX, ccY - textOffsetY, textPaint);

        drawLeftTop(canvas);
        drawRightTop(canvas);
        drawLeft(canvas);
        drawRight(canvas);
        drawButtom(canvas);
    }

    private void drawLeftTop(Canvas canvas){
        // 锁定画布
        canvas.save();

        //平移
        canvas.translate(ccX, ccY);
        canvas.rotate(-30);

        //画：线圈线圈
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, strokePaint);
        canvas.drawText("machao1", 0, -lineLength * 3 - textOffsetY, textPaint);
        canvas.drawLine(0, -largeCricleRadiu * 4, 0, -lineLength * 5, strokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCricleRadiu, strokePaint);
        canvas.drawText("machao2", 0, -lineLength * 6 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    private void drawRightTop(Canvas canvas){
        // 锁定画布
        canvas.save();

        //平移
        canvas.translate(ccX, ccY);
        canvas.rotate(30);

        //画：线圈线圈
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, strokePaint);
        canvas.drawText("machao3", 0, -lineLength * 3 - textOffsetY, textPaint);

        drawTopRightArc(canvas, -lineLength * 3);
        // 释放画布
        canvas.restore();
    }

    private void drawLeft(Canvas canvas){
        // 锁定画布
        canvas.save();

        //平移
        canvas.translate(ccX, ccY);
        canvas.rotate(-100);

        //画：线圈线圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, strokePaint);
        canvas.drawText("machao4", 0, -lineLength * 2 - smallCricleRadiu - space * 2 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    private void drawRight(Canvas canvas){
        // 锁定画布
        canvas.save();

        //平移
        canvas.translate(ccX, ccY);
        canvas.rotate(100);

        //画：线圈线圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, strokePaint);
        canvas.drawText("machao5", 0, -lineLength * 2 - smallCricleRadiu - space * 2 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    private void drawButtom(Canvas canvas){
        // 锁定画布
        canvas.save();

        //平移
        canvas.translate(ccX, ccY);
        canvas.rotate(180);

        //画：线圈线圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, strokePaint);
        canvas.drawText("machao6", 0, -lineLength * 2 - smallCricleRadiu - space * 2 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右上角画弧形
     *
     * @param canvas
     * @param cricleY
     */
    private void drawTopRightArc(Canvas canvas, float cricleY) {
        canvas.save();

        canvas.translate(0, cricleY);
        canvas.rotate(-30);

        float arcRadiu = size * ARC_RADIU;

        RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(oval, -22.5F, -135, true, arcPaint);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5F, -135, false, arcPaint);

        canvas.restore();
    }
}
