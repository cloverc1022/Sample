package com.mmc.sampletest.customView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 上海滩小马哥 on 2017/10/16.
 */

public class BezierView extends View {

    private Paint paint;
    private Path path;

    private int vWidth, vHeight;// 控件宽高
    private float ctrX, ctrY;// 控制点的xy坐标
    private float waveY;// 整个Wave顶部两端点的Y坐标，该坐标与控制点的Y坐标增减幅一致

    private boolean isInc;// 判断控制点是该右移还是左移

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setColor(0xFFA2D6AE);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);

        path = new Path();
//        path.moveTo(100,100);
//        path.lineTo(300, 300);
//        path.quadTo(200, 200, 300, 100);
//        path.cubicTo(200, 200, 300, 0, 400, 100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        vHeight = h;
        vWidth = w;

        Log.d(BezierView.this.getClass().getSimpleName(), "   W:" + w + "     H:" + h);
        Log.d(BezierView.this.getClass().getSimpleName(), "oldW:" + oldw + "  oldH:" + oldh);

        waveY = 1/8F * vHeight;
        ctrY = -1/16F * vHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawPath(path, paint);
        /*
         * 设置Path起点
         * 注意我将Path的起点设置在了控件的外部看不到的区域
         * 如果我们将起点设置在控件左端x=0的位置会使得贝塞尔曲线变得生硬
         * 至于为什么刚才我已经说了
         * 所以我们稍微让起点往“外”走点
         */
        path.moveTo(-1 / 4F * vWidth, waveY);

        /*
         * 以二阶曲线的方式通过控制点连接位于控件右边的终点
         * 终点的位置也是在控件外部
         * 我们只需不断让ctrX的大小变化即可实现“浪”的效果
         */
        path.quadTo(ctrX, ctrY, vWidth + 1 / 4F * vWidth, waveY);

        // 围绕控件闭合曲线
        path.lineTo(vWidth + 1 / 4F * vWidth, vHeight);
        path.lineTo(-1 / 4F * vWidth, vHeight);
        path.close();

        canvas.drawPath(path, paint);

        /*
         * 当控制点的x坐标大于或等于终点x坐标时更改标识值
         */
        if (ctrX >= vWidth + 1 / 4F * vWidth) {
            isInc = false;
        }
        /*
         * 当控制点的x坐标小于或等于起点x坐标时更改标识值
         */
        else if (ctrX <= -1 / 4F * vWidth) {
            isInc = true;
        }

        // 根据标识值判断当前的控制点x坐标是该加还是减
        ctrX = isInc ? ctrX + 20 : ctrX - 20;

        /*
         * 让“水”不断减少
         */
        if (ctrY <= vHeight) {
            ctrY += 2;
            waveY += 2;
        }

        path.reset();

        // 重绘
        invalidate();
    }
}
