package com.lty.recyclerdemo.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andy on 2017/12/8.
 */
public class ItemButtonAdapter extends RecyclerView.Adapter<ItemButtonAdapter.ViewHolder> {


    private int selected = 0;
    private List<String> data;

    public ItemButtonAdapter(List<String> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LabelView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            //tv = (TextView) itemView.findViewById(android.R.id.text1);
            tv = (LabelView) itemView;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        LabelView view = new LabelView(parent.getContext());
        // view.setBackgroundColor(Color.RED);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 4;
        layoutParams.rightMargin = 4;
        layoutParams.topMargin = 4;
        layoutParams.bottomMargin = 4;
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position));
        if (selected == position) {
            holder.tv.setSelected(true);
        } else {
            holder.tv.setSelected(false);
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != position) {
                    notifyItemChanged(selected);
                    selected = position;
                    notifyItemChanged(selected);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
