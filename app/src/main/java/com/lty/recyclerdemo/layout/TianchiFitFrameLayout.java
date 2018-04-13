/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lty.recyclerdemo.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class TianchiFitFrameLayout extends FrameLayout {
    private final TianchiFitLayoutHelper mHelper = new TianchiFitLayoutHelper(this);

    public TianchiFitFrameLayout(Context context) {
        super(context);
    }

    public TianchiFitFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TianchiFitFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHelper.adjustChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHelper.restoreOriginalParams();
    }

    /**
     * @deprecated this class is deprecated along with its parent class.
     */
    @Deprecated
    public static class LayoutParams extends FrameLayout.LayoutParams
            implements TianchiFitLayoutHelper.TianchiLayoutParams {
        private TianchiFitLayoutHelper.TianchiLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mPercentLayoutInfo = TianchiFitLayoutHelper.getPercentLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, int gravity) {
            super(width, height, gravity);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(FrameLayout.LayoutParams source) {
            super((MarginLayoutParams) source);
            gravity = source.gravity;
        }

        @RequiresApi(19)
        public LayoutParams(LayoutParams source) {
            // The copy constructor used here is only supported on API 19+.
            this((FrameLayout.LayoutParams) source);
            mPercentLayoutInfo = source.mPercentLayoutInfo;
        }

        @Override
        public TianchiFitLayoutHelper.TianchiLayoutInfo getPercentLayoutInfo() {
            if (mPercentLayoutInfo == null) {
                mPercentLayoutInfo = new TianchiFitLayoutHelper.TianchiLayoutInfo();
            }

            return mPercentLayoutInfo;
        }


        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            TianchiFitLayoutHelper.fetchWidthAndHeight(this, a, widthAttr, heightAttr);
        }
    }
}
