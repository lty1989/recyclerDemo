package com.lty.recyclerdemo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andy on 2017/12/8.
 */
public class ItemTestAdapter extends RecyclerView.Adapter<ItemTestAdapter.ViewHolder> {


    private List<String> data;

    public ItemTestAdapter(List<String> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            //tv = (TextView) itemView.findViewById(android.R.id.text1);
            tv = (TextView) itemView;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        TextView view = new TextView(parent.getContext());
        view.setBackgroundColor(Color.BLACK);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 4;
        layoutParams.rightMargin = 4;
        layoutParams.topMargin = 4;
        layoutParams.bottomMargin = 4;
        view.setLayoutParams(layoutParams);
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
