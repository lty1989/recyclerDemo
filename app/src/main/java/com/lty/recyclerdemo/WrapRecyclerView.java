package com.lty.recyclerdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by andy on 2017/12/10.
 */

public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderviewInfos = new ArrayList<>();
    private ArrayList<View> mFooterviewInfos = new ArrayList<>();
    private Adapter mAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
        mHeaderviewInfos.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderviewInfos, mFooterviewInfos, mAdapter);
            }

        }


    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderviewInfos.size() > 0 || mFooterviewInfos.size() > 0) {
            this.mAdapter = new HeaderViewRecyclerAdapter(mHeaderviewInfos, mFooterviewInfos, adapter);
        } else {
            this.mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }
}
