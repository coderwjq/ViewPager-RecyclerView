package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class NewsViewPager extends ViewPager {
    private static final String TAG = "NewsViewPager";

    public NewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
