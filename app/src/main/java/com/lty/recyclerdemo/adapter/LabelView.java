package com.lty.recyclerdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.lty.recyclerdemo.R;

/**
 * Created by andyliu on 2018/4/11.
 */

@SuppressLint("AppCompatCustomView")
public class LabelView extends TextView {

    private final int TEXT_SIZE_NORMAL = 30;
    private final int TEXT_SIZE_FOCUSED = 33;

    public LabelView(Context context) {
        super(context);
        init();
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_NORMAL);
        setBackgroundResource(R.drawable.labelview_bg);
    }

    private boolean isSelected;

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        if (selected) {
            setBackgroundColor(Color.parseColor("#4a90e2"));
        } else {
            setBackgroundResource(R.drawable.labelview_bg);
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_FOCUSED);
            setBackgroundColor(Color.parseColor("#4a90e2"));
        } else {
            if (isSelected) {
                setBackgroundColor(Color.argb(150, 155, 155, 155));
            } else {
                setBackgroundResource(R.drawable.labelview_bg);
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_NORMAL);


        }

    }
}
