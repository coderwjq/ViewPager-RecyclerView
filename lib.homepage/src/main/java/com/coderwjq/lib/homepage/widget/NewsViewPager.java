package com.coderwjq.lib.homepage.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.coderwjq.lib.homepage.manager.HomePageManager;

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
}