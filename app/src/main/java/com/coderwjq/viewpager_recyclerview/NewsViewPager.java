package com.coderwjq.viewpager_recyclerview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class NewsViewPager extends ViewPager {
    private static final String TAG = "NewsViewPager";

    public NewsViewPager(Context context) {
        super(context);
    }

    public NewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent() called with: ev = [" + ev + "]");

        return super.onTouchEvent(ev);
    }
}
