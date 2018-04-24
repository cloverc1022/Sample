package com.mmc.sampletest.customView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 上海滩小马哥 on 2017/10/10.
 */

public class OneFourTestView extends View {

    private Paint mPaint;

    //边框的宽度
    private float circleStrokeWidth = 5;

    //圆心的X坐标
    private float centerX;

    //圆心的Y坐标
    private float centerY;

    //线条的颜色
    private int circleRadiusColor = 0xFFD8D9D9;

    //45°正弦值
    private float mSin45 = (float) Math.sin(45 * Math.PI / 180);

    private float outerCircleRadius, innerCircleRadius;

    private AREA mArea;

    public OneFourTestView(Context context) {
        super(context);
        inint();
    }

    public OneFourTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inint();
    }

    public OneFourTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = getWidth();
        int h = getHeight();

        centerX = w / 2;

        centerY = h / 2;

        outerCircleRadius = centerX - circleStrokeWidth;

        innerCircleRadius = centerX / 3;

    }

    private void inint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
    }

    //定义接口
    public interface ChangeStateListener {
        void onChangeState(AREA area);
    }

    ChangeStateListener mChangeStateListener = null;

    public void setChangeStateListener(ChangeStateListener changeStateListener) {
        mChangeStateListener = changeStateListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        drawCircle(canvas);
        //画直线
        drawLine(canvas);
        //画背景选择器
        drawOnclikColor(canvas, mArea);
    }


    //画圆
    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, outerCircleRadius, mPaint);

        mPaint.setColor(circleRadiusColor);
        mPaint.setStrokeWidth(circleStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, outerCircleRadius, mPaint);

        mPaint.setColor(circleRadiusColor);
        mPaint.setStrokeWidth(circleStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);


    }

    //画直线
    private void drawLine(Canvas canvas) {
        mPaint.setColor(circleRadiusColor);
        mPaint.setStrokeWidth(circleStrokeWidth);

        canvas.drawLine(centerX - (mSin45 * outerCircleRadius), centerY - (mSin45 * outerCircleRadius),
                centerX - (mSin45 * innerCircleRadius), centerY - (mSin45 * innerCircleRadius), mPaint);
        canvas.drawLine(centerX - (mSin45 * outerCircleRadius), centerY + (mSin45 * outerCircleRadius),
                centerX - (mSin45 * innerCircleRadius), centerY + (mSin45 * innerCircleRadius), mPaint);
        canvas.drawLine(centerX + (mSin45 * outerCircleRadius), centerY + (mSin45 * outerCircleRadius),
                centerX + (mSin45 * innerCircleRadius), centerY + (mSin45 * innerCircleRadius), mPaint);
        canvas.drawLine(centerX + (mSin45 * outerCircleRadius), centerY - (mSin45 * outerCircleRadius),
                centerX + (mSin45 * innerCircleRadius), centerY - (mSin45 * innerCircleRadius), mPaint);
    }

    //按下时的X，Y坐标
    float mDownX, mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                mArea = judgeArea(mDownX, mDownY);
                invalidate();
                mChangeStateListener.onChangeState(mArea);
                break;
            case MotionEvent.ACTION_UP:
                mArea = null;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }


    //判断区域
    public AREA judgeArea(float x, float y) {
        //判断是是否在大圆内
        if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= outerCircleRadius * outerCircleRadius) {
            //判断是否在小圆内
            if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) > innerCircleRadius * innerCircleRadius) {
                x = x - centerX;
                y = y - centerY;
                float tan = y / x;
                if ((tan > Math.tan(45 * Math.PI / 180) && tan < Integer.MAX_VALUE && x < 0 && y < 0) ||
                        (tan > Integer.MIN_VALUE && tan < -Math.tan(45 * Math.PI / 180) && x > 0 && y < 0)){
                    return AREA.NUMBER_ONE;
                }else if ((tan > 0 && tan < Math.tan(45 * Math.PI / 180) && x > 0 && y > 0) ||
                        (tan > -Math.tan(45 * Math.PI / 180) && tan < 0 && x > 0 && y < 0)){
                    return AREA.NUMBER_TWO;
                }else if ((tan < -Math.tan(45 * Math.PI / 180) && tan > Integer.MIN_VALUE && x < 0 && y > 0) ||
                        (tan > Math.tan(45 * Math.PI / 180) && tan < Integer.MAX_VALUE && x > 0 && y > 0)){
                    return AREA.NUMBER_THREE;
                }else if ((tan > 0 && tan < Math.tan(45 * Math.PI / 180) && x < 0 && y < 0) ||
                        (tan < 0 && tan > -Math.tan(45 * Math.PI / 180) && x < 0 && y > 0)){
                    return AREA.NUMBER_FOUR;
                }
            } else {
                return AREA.CENTER;
            }
        } else {
            return null;
        }
        return null;
    }


    /**
     * 点击的时候绘制深色的扇形
     *
     * @param canvas
     * @param area
     */
    private void drawOnclikColor(Canvas canvas, AREA area) {
        if (area == null) {
            return;
        }
        //设置点击之后的颜色
        mPaint.setColor(circleRadiusColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(outerCircleRadius - innerCircleRadius);

        switch (area) {
            case NUMBER_ONE:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth, centerY - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth), 225, 90, false, mPaint);
                break;
            case NUMBER_TWO:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth, centerY - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth), 315, 90, false, mPaint);
                break;
            case NUMBER_THREE:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth, centerY - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth), 45, 90, false, mPaint);
                break;
            case NUMBER_FOUR:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth, centerY - (outerCircleRadius - innerCircleRadius) - circleStrokeWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + circleStrokeWidth), 135, 90, false, mPaint);
                break;
            case CENTER:
                mPaint.setStrokeWidth(0);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);
                break;
            default:
                break;
        }

    }

    /**
     * 关于不同区域的枚举，逆时针方向
     */
    public enum AREA {
        //第一区域
        NUMBER_ONE,
        //第二区域
        NUMBER_TWO,
        //第三区域
        NUMBER_THREE,
        //第四区域
        NUMBER_FOUR,
        //第五区域
        NUMBER_FIVE,
        //第六区域
        NUMBER_SIX,
        //第七区域
        NUMBER_SEVEN,
        //第八区域
        NUMBER_EIGHT,
        //中心区域
        CENTER,
    }
}
