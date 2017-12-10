package com.lty.recyclerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andy on 2017/12/8.
 */

public class ItemDecAdapter extends RecyclerView.Adapter<ItemDecAdapter.ViewHolder> {


    private List<String> data;

    public ItemDecAdapter(List<String> data) {
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
