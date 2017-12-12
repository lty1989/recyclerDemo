package com.lty.recyclerdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by andy on 2017/12/11.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {


    private ItemTouchMoveListener itemTouchMoveListener = null;

    public MyItemTouchHelperCallback(ItemTouchMoveListener itemTouchMoveListener) {
        this.itemTouchMoveListener = itemTouchMoveListener;
    }


    /**
     * 最先被调用的方法 判断是什么动作
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flag = makeMovementFlags(dragFlag, swipeFlags);
        return flag;
    }

    /**
     * 移动时候回调  拖拽
     *
     * @param recyclerView
     * @param src
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder src, RecyclerView.ViewHolder target) {
        if (src.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        return itemTouchMoveListener.OnItemMove(src.getAdapterPosition(), target.getAdapterPosition());
    }

    /**
     * 侧滑时候回调
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        if (itemTouchMoveListener != null) {
            itemTouchMoveListener.OnItemRemove(viewHolder.getAdapterPosition());
        }

    }

    /**
     * 是否打开长按拖拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.RED);
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder.itemView.setAlpha(1 - Math.abs(dX) / viewHolder.itemView.getWidth());
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
