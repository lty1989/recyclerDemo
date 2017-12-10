package com.lty.recyclerdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by andy on 2017/12/9.
 */

public class DividerGridDecoration extends RecyclerView.ItemDecoration {


    private int mOrientation;
    private Drawable mDivider;
    private int[] attrs = new int[]{android.R.attr.listDivider};


    public DividerGridDecoration(Context context) {
        TypedArray array = context.obtainStyledAttributes(attrs);
        mDivider = array.getDrawable(0);
        array.recycle();
    }

    public void setOrientation(int mOrientation) {
        this.mOrientation = mOrientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int left = view.getLeft() - params.leftMargin;
            int right = view.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            int top = view.getBottom() - params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }


    }

    private void drawVertical(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int left = view.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = view.getTop() - params.topMargin;
            int bottom = view.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }

    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //所有条目都会调用该方法
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        super.getItemOffsets(outRect, view, parent, state);
    }
}
