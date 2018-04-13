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
import android.support.annotation.NonNull;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lty.recyclerdemo.R;


public class TianchiFitLayoutHelper {

    private final ViewGroup mMainViewGroup;

    private static final int STAND_WIDTH = 1920;
    private static final int STAND_HEIGHT = 1080;

    private static final int CURRENT_WIDTH = 1920;
    private static final int CURRENT_HEIGHT = 1080;

    public TianchiFitLayoutHelper(@NonNull ViewGroup host) {
        if (host == null) {
            throw new IllegalArgumentException("host must be non-null");
        }
        mMainViewGroup = host;
    }


    public static void fetchWidthAndHeight(ViewGroup.LayoutParams params, TypedArray array,
                                           int widthAttr, int heightAttr) {
        params.width = array.getLayoutDimension(widthAttr, 0);
        params.height = array.getLayoutDimension(heightAttr, 0);
    }


    public void adjustChildren(int widthMeasureSpec, int heightMeasureSpec) {


        int widthHint = View.MeasureSpec.getSize(widthMeasureSpec) - mMainViewGroup.getPaddingLeft()
                - mMainViewGroup.getPaddingRight();
        int heightHint = View.MeasureSpec.getSize(heightMeasureSpec) - mMainViewGroup.getPaddingTop()
                - mMainViewGroup.getPaddingBottom();
        for (int i = 0, N = mMainViewGroup.getChildCount(); i < N; i++) {
            View view = mMainViewGroup.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof TianchiLayoutParams) {
                TianchiLayoutInfo info =
                        ((TianchiLayoutParams) params).getPercentLayoutInfo();
                if (info != null) {
                    if (params instanceof ViewGroup.MarginLayoutParams) {
                        info.fillMarginLayoutParams(view, (ViewGroup.MarginLayoutParams) params,
                                widthHint, heightHint);
                    } else {
                        info.fillLayoutParams(params, widthHint, heightHint);
                    }
                }
            }
        }
    }


    public static TianchiLayoutInfo getPercentLayoutInfo(Context context,
                                                         AttributeSet attrs) {
        TianchiLayoutInfo info = null;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TianchiLayout_Layout);
        int value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_widthDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mWidthDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_heightDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mHeightDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mLeftMarginDistance = value;
            info.mTopMarginDistance = value;
            info.mRightMarginDistance = value;
            info.mBottomMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginLeftDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mLeftMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginTopDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mTopMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginRightDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mRightMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginBottomDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mBottomMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginStartDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mStartMarginDistance = value;
        }
        value = array.getInteger(R.styleable.TianchiLayout_Layout_layout_marginEndDistance, -1);
        if (value != -1) {
            info = info != null ? info : new TianchiLayoutInfo();
            info.mEndMarginDistance = value;
        }

        array.recycle();
        return info;
    }


    public void restoreOriginalParams() {
        for (int i = 0, len = mMainViewGroup.getChildCount(); i < len; i++) {
            View view = mMainViewGroup.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof TianchiLayoutParams) {
                TianchiLayoutInfo info =
                        ((TianchiLayoutParams) params).getPercentLayoutInfo();
                if (info != null) {
                    if (params instanceof ViewGroup.MarginLayoutParams) {
                        info.restoreMarginLayoutParams((ViewGroup.MarginLayoutParams) params);
                    } else {
                        info.restoreLayoutParams(params);
                    }
                }
            }
        }
    }


    public boolean handleMeasuredStateTooSmall() {
        boolean needsSecondMeasure = false;
        for (int i = 0, len = mMainViewGroup.getChildCount(); i < len; i++) {
            View view = mMainViewGroup.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof TianchiLayoutParams) {
                TianchiLayoutInfo info =
                        ((TianchiLayoutParams) params).getPercentLayoutInfo();
                if (info != null) {
                    if (shouldHandleMeasuredWidthTooSmall(view, info)) {
                        needsSecondMeasure = true;
                        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    }
                    if (shouldHandleMeasuredHeightTooSmall(view, info)) {
                        needsSecondMeasure = true;
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    }
                }
            }
        }
        return needsSecondMeasure;
    }

    private static boolean shouldHandleMeasuredWidthTooSmall(View view, TianchiLayoutInfo info) {
        int state = view.getMeasuredWidthAndState() & View.MEASURED_STATE_MASK;
        return state == View.MEASURED_STATE_TOO_SMALL && info.mWidthDistance >= 0
                && info.mPreservedParams.width == ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    private static boolean shouldHandleMeasuredHeightTooSmall(View view, TianchiLayoutInfo info) {
        int state = view.getMeasuredHeightAndState() & View.MEASURED_STATE_MASK;
        return state == View.MEASURED_STATE_TOO_SMALL && info.mHeightDistance >= 0
                && info.mPreservedParams.height == ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    static class TianchiMarginLayoutParams extends ViewGroup.MarginLayoutParams {
        private boolean mIsHeightComputedFromAspectRatio;
        private boolean mIsWidthComputedFromAspectRatio;

        public TianchiMarginLayoutParams(int width, int height) {
            super(width, height);
        }
    }


    public static class TianchiLayoutInfo {

        public int mWidthDistance;


        public int mHeightDistance;


        public int mLeftMarginDistance;


        public int mTopMarginDistance;


        public int mRightMarginDistance;


        public int mBottomMarginDistance;


        public int mStartMarginDistance;


        public int mEndMarginDistance;


        final TianchiMarginLayoutParams mPreservedParams;

        public TianchiLayoutInfo() {
            mWidthDistance = -1;
            mHeightDistance = -1;
            mLeftMarginDistance = -1;
            mTopMarginDistance = -1;
            mRightMarginDistance = -1;
            mBottomMarginDistance = -1;
            mStartMarginDistance = -1;
            mEndMarginDistance = -1;
            mPreservedParams = new TianchiMarginLayoutParams(0, 0);
        }


        public void fillLayoutParams(ViewGroup.LayoutParams params, int widthHint,
                                     int heightHint) {
            mPreservedParams.width = params.width;
            mPreservedParams.height = params.height;

            if (mWidthDistance >= 0) {
                int width = Math.round(CURRENT_WIDTH * mWidthDistance / STAND_WIDTH);
                if (width > widthHint) {
                    width = widthHint;
                }
                params.width = width;
            }

            if (mHeightDistance >= 0) {
                int height = Math.round(CURRENT_HEIGHT * mHeightDistance / STAND_HEIGHT);
                if (height > heightHint) {
                    height = heightHint;
                }
                params.height = height;
            }
        }


        public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams params,
                                           int widthHint, int heightHint) {
            fillMarginLayoutParams(null, params, widthHint, heightHint);
        }


        public void fillMarginLayoutParams(View view, ViewGroup.MarginLayoutParams params,
                                           int widthHint, int heightHint) {
            fillLayoutParams(params, widthHint, heightHint);

            mPreservedParams.leftMargin = params.leftMargin;
            mPreservedParams.topMargin = params.topMargin;
            mPreservedParams.rightMargin = params.rightMargin;
            mPreservedParams.bottomMargin = params.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(mPreservedParams,
                    MarginLayoutParamsCompat.getMarginStart(params));
            MarginLayoutParamsCompat.setMarginEnd(mPreservedParams,
                    MarginLayoutParamsCompat.getMarginEnd(params));

            if (mLeftMarginDistance >= 0) {
                params.leftMargin = Math.round(CURRENT_WIDTH * mLeftMarginDistance / STAND_WIDTH);
            }
            if (mTopMarginDistance >= 0) {
                params.topMargin = Math.round(CURRENT_HEIGHT * mTopMarginDistance / STAND_HEIGHT);
            }
            if (mRightMarginDistance >= 0) {
                params.rightMargin = Math.round(CURRENT_WIDTH * mRightMarginDistance / STAND_WIDTH);
            }
            if (mBottomMarginDistance >= 0) {
                params.bottomMargin = Math.round(CURRENT_HEIGHT * mBottomMarginDistance / STAND_HEIGHT);
            }
            boolean shouldResolveLayoutDirection = false;
            if (mStartMarginDistance >= 0) {
                MarginLayoutParamsCompat.setMarginStart(params,
                        Math.round(CURRENT_WIDTH * mStartMarginDistance / STAND_WIDTH));
                shouldResolveLayoutDirection = true;
            }
            if (mEndMarginDistance >= 0) {
                MarginLayoutParamsCompat.setMarginEnd(params,
                        Math.round(CURRENT_WIDTH * mEndMarginDistance / STAND_WIDTH));
                shouldResolveLayoutDirection = true;
            }
            if (shouldResolveLayoutDirection && (view != null)) {
                MarginLayoutParamsCompat.resolveLayoutDirection(params,
                        ViewCompat.getLayoutDirection(view));
            }

        }


        public void restoreMarginLayoutParams(ViewGroup.MarginLayoutParams params) {
            restoreLayoutParams(params);
            params.leftMargin = mPreservedParams.leftMargin;
            params.topMargin = mPreservedParams.topMargin;
            params.rightMargin = mPreservedParams.rightMargin;
            params.bottomMargin = mPreservedParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(params,
                    MarginLayoutParamsCompat.getMarginStart(mPreservedParams));
            MarginLayoutParamsCompat.setMarginEnd(params,
                    MarginLayoutParamsCompat.getMarginEnd(mPreservedParams));
        }


        public void restoreLayoutParams(ViewGroup.LayoutParams params) {
            if (!mPreservedParams.mIsWidthComputedFromAspectRatio) {
                params.width = mPreservedParams.width;
            }
            if (!mPreservedParams.mIsHeightComputedFromAspectRatio) {
                params.height = mPreservedParams.height;
            }
            mPreservedParams.mIsWidthComputedFromAspectRatio = false;
            mPreservedParams.mIsHeightComputedFromAspectRatio = false;
        }
    }


    public interface TianchiLayoutParams {
        TianchiLayoutInfo getPercentLayoutInfo();
    }
}
