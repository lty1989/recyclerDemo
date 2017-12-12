package com.lty.recyclerdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by andy on 2017/12/8.
 */

public class DragItemAdapter extends RecyclerView.Adapter<DragItemAdapter.ViewHolder> implements ItemTouchMoveListener {


    private List<String> data;
    private StartDragListener startDragListener = null;

    public DragItemAdapter(List<String> data, StartDragListener startDragListener) {
        this.data = data;
        this.startDragListener = startDragListener;
    }

    @Override
    public boolean OnItemMove(int from, int to) {
        Collections.swap(data, from, to);
        notifyItemMoved(from, to);
        return true;
    }

    @Override
    public boolean OnItemRemove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_drag, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.iv.setImageResource(R.mipmap.ic_launcher);
        holder.tv.setText(data.get(position));
        holder.iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (startDragListener != null) {
                        startDragListener.onStartDrag(holder);
                    }
                }

                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
