package com.lty.recyclerdemo;

/**
 * Created by Administrator on 2017-12-12.
 */

public interface ItemTouchMoveListener {

    boolean OnItemMove(int from, int to);

    boolean OnItemRemove(int position);

}
