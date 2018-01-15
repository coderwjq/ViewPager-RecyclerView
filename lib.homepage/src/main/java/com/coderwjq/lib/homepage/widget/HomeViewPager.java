package com.coderwjq.lib.homepage.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.coderwjq.lib.homepage.manager.HomePageManager;

/**
 * @author: wangjiaqi
 * @data: 2018/1/12
 */

public class HomeViewPager extends ViewPager {
    private static final String TAG = "HomeViewPager";

    private float mStartPosX;
    private float mCurPosX;
    private float mStartPosY;
    private float mCurPosY;
    private float mDeltaPosX;
    private float mDeltaPosY;

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
    }

    /**
     * 当在普通模式下，左右滑动屏幕，进行主页切换
     *
     * @param e
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean isHorizontalScroll = false;

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartPosX = e.getX();
                mStartPosY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = e.getX();
                mCurPosY = e.getY();

                mDeltaPosX = mCurPosX - mStartPosX;
                mDeltaPosY = mCurPosY - mStartPosY;

                if (Math.abs(mDeltaPosX) - Math.abs(mDeltaPosY) > ViewConfiguration.getTouchSlop()) {
                    // 水平滑动
                    isHorizontalScroll = true;

                    if (mDeltaPosX > 0) {
                        // 向右滑动
                    } else {
                        // 向左滑动
                    }
                } else {
                    // 垂直滑动
                    isHorizontalScroll = false;

                    if (mDeltaPosY > 0) {
                        // 向下滑动
                    } else {
                        // 向上滑动
                    }
                }

                mStartPosX = mCurPosX;
                mStartPosY = mCurPosY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        if (HomePageManager.getInstance().isNormalMode() && isHorizontalScroll) {
            // 首页模式下，水平滑动，拦截，由自己处理
            return true;
        } else {
            return super.onInterceptTouchEvent(e);
        }

    }
}
