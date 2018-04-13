package com.lty.recyclerdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lty.recyclerdemo.R;

import java.util.List;

/**
 * Created by andy on 2017/12/8.
 */
public class ItemGridAdapter extends RecyclerView.Adapter<ItemGridAdapter.ViewHolder> {


    private List<String> data;

    public ItemGridAdapter(List<String> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (Button) itemView.findViewById(R.id.btn);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_button, null);
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
