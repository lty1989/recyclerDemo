package com.lty.recyclerdemo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by andy on 2017/12/11.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
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
        int swiperFlag = 0;
        int flag = makeMovementFlags(dragFlag, swiperFlag);
        return flag;
    }

    /**
     * 移动时候回调  拖拽
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * 侧滑时候回调
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     * 是否打开长按拖拽
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
