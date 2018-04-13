package com.lty.recyclerdemo.layout;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by andyliu on 2018/4/11.
 */

public class MyGridLayoutManager extends GridLayoutManager {

    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        int span = getSpanCount();
        int count = getItemCount();
        int fromPos = getPosition(focused);
        switch (direction) {
            case View.FOCUS_UP:
                fromPos = (fromPos - span);
                break;
            case View.FOCUS_DOWN:
                fromPos = (fromPos + span);
                break;
            case View.FOCUS_RIGHT:
                fromPos++;
                break;
            case View.FOCUS_LEFT:
                fromPos--;
                break;
        }

        if (fromPos < 0) {
            return focused;
        } else if (fromPos >= count) {
            return focused;
        } else {
            return findViewByPosition(fromPos);
        }
    }


}
