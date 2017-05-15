package com.caihui.zlzb_test.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @auther by yushilei.
 * @time 2017/5/15-16:47
 * @desc
 */

public class MultiState2View extends com.jiechic.library.android.widget.MultiStateView {

    public MultiState2View(Context context) {
        super(context);
    }

    public MultiState2View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isLoading() {
        return getState().equals(ContentState.LOADING);
    }
}
