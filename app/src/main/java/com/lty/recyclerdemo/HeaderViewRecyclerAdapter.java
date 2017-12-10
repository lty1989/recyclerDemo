package com.lty.recyclerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2017/12/10.
 */

public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {


    private ArrayList<View> mHeaderviewInfos = null;
    private ArrayList<View> mFooterviewInfos = null;
    private RecyclerView.Adapter mAdapter;

    public HeaderViewRecyclerAdapter(ArrayList<View> mHeaderviewInfos, ArrayList<View> mFooterviewInfos, RecyclerView.Adapter mAdapter) {

        if (mHeaderviewInfos == null) {
            this.mHeaderviewInfos = new ArrayList<>();
        } else {
            this.mHeaderviewInfos = mHeaderviewInfos;
        }

        if (mFooterviewInfos == null) {
            this.mFooterviewInfos = new ArrayList<>();
        } else {
            this.mFooterviewInfos = mHeaderviewInfos;
        }
        this.mAdapter = mAdapter;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == RecyclerView.INVALID_TYPE) {
            return new HeadersViewHolder(mHeaderviewInfos.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new HeadersViewHolder(mHeaderviewInfos.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }

        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return RecyclerView.INVALID_TYPE;
        }

        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.getItemViewType(position);
            }
        }

        return RecyclerView.INVALID_TYPE - 1;
    }

    @Override
    public int getItemCount() {
        if (mAdapter == null) {
            return getFootersCount() + getHeadersCount();
        } else {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        }
    }

    private int getHeadersCount() {
        return mHeaderviewInfos.size();
    }

    private int getFootersCount() {
        return mFooterviewInfos.size();
    }


    private static class HeadersViewHolder extends RecyclerView.ViewHolder {

        public HeadersViewHolder(View itemView) {
            super(itemView);
        }
    }


}
