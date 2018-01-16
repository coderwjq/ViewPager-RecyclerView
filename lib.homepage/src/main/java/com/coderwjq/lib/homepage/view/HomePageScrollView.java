package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author: wangjiaqi
 * @data: 2018/1/16
 */

public class HomePageScrollView extends NestedScrollView {
    private static final String TAG = "HomePageScrollView";

    public HomePageScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: HomePageScrollView");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent: HomePageScrollView");
        return super.onTouchEvent(ev);
    }
}
