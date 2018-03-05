package com.mmc.sampletest.RecycleViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2018/03/01.
 *
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private int tagWidth;
    private Paint decorationPaint;
    private Paint leftPaint, rightPaint;

    public MyItemDecoration(Context context) {
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.dividerHeight);
        tagWidth = context.getResources().getDimensionPixelSize(R.dimen.tagWidth);
        decorationPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        decorationPaint.setColor(context.getResources().getColor(R.color.colorAccent));

        leftPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        leftPaint.setColor(context.getResources().getColor(R.color.colorPrimary));

        rightPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        rightPaint.setColor(context.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            View itemView = parent.getChildAt(i);
            int top = itemView.getBottom();
            int bottom = itemView.getBottom() + dividerHeight;
            c.drawRect(left,top, right, bottom,decorationPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            View itemView = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(itemView);
            int top = itemView.getTop();
            int bottom = itemView.getBottom();
            if (pos%2 == 0){
                int left = itemView.getLeft();
                int right = itemView.getLeft() + tagWidth;
                c.drawRect(left,top, right, bottom, leftPaint);
            }else {
                int left = itemView.getRight() - tagWidth;
                int right = itemView.getRight();
                c.drawRect(left,top, right, bottom, rightPaint);
            }
        }
    }
}
