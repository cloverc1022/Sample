package com.mmc.sampletest.RecycleViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mmc.sampletest.R;

/**
 * Created by 上海滩小马哥 on 2018/03/05.
 * 首字母分类
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private DecorationCallback callback;
    private Paint bgPaint, textPaint;
    private Paint.FontMetrics fontMetrics;
    private int topGap;

    public SectionDecoration(Context context, DecorationCallback callback) {
        this.callback = callback;

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        bgPaint.setColor(context.getResources().getColor(R.color.colorAccent));

        fontMetrics = new Paint.FontMetrics();
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint.setColor(context.getResources().getColor(R.color.white));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextSize(80);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.getFontMetrics(fontMetrics);

        topGap = context.getResources().getDimensionPixelOffset(R.dimen.topGap);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        long groupId = callback.getGroupId(pos);
        if (groupId < 0) return;
        if (isFrist(pos)) {
            outRect.top = topGap;
        } else {
            outRect.top = 0;
        }
    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount - 1; i++) {
//            View itemView = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(itemView);
//            long groupId = callback.getGroupId(position);
//            if (groupId < 0) return;
//            String text = callback.getFristLetter(position);
//            int top = itemView.getTop() - topGap;
//            int bottom = itemView.getTop();
//            if (isFrist(position)) {
//                c.drawRect(left, top, right, bottom, bgPaint);
//                c.drawText(text, left, bottom, textPaint);
//            }
//        }
//    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String textLine = callback.getFristLetter(position).toUpperCase();

            if (i == 0){
                int top = parent.getPaddingTop();
                if (isLast(position)){
                    int suggestTop = view.getBottom() - topGap;
                    if (suggestTop< top){
                        top = suggestTop;
                    }
                }
                int bottom = top + topGap;
                c.drawRect(left, top, right, bottom, bgPaint);
                c.drawText(textLine, left, bottom, textPaint);
            }else {
                if (isFrist(position)){
                    int top = view.getTop() - topGap;
                    int bottom = view.getTop();
                    c.drawRect(left, top, right, bottom, bgPaint);
                    c.drawText(textLine, left, bottom, textPaint);
                }
            }
        }

    }

public interface DecorationCallback {
    String getFristLetter(int position);

    long getGroupId(int position);

}

    private boolean isFrist(int position) {
        if (position == 0) {
            return true;
        } else {
            long preFirstLetter = callback.getGroupId(position - 1);
            long firstLetter = callback.getGroupId(position);
            return preFirstLetter != firstLetter;
        }
    }

    private boolean isLast(int position) {
        long lastLetter = callback.getGroupId(position + 1);
        long firstLetter = callback.getGroupId(position);
        return lastLetter != firstLetter;
    }
}
