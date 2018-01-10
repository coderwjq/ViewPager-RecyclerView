package com.coderwjq.viewpager_recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class HomeRecyclerView extends RecyclerView {
    private static final String TAG = "HomeRecyclerView";

    private float mStartPosX;
    private float mCurPosX;
    private float mStartPosY;
    private float mCurPosY;
    private float mDeltaPosX;
    private float mDeltaPosY;

    public HomeRecyclerView(Context context) {
        super(context);
    }

    public HomeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.d(TAG, "onTouchEvent() called with: e = [" + e + "]");
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

                if (Math.abs(mDeltaPosX) > Math.abs(mDeltaPosY)) {
                    // 水平滑动
                    isHorizontalScroll = true;

                    if (mDeltaPosX > 0) {
                        // 向右滑动
                        Log.i(TAG, "onTouchEvent: 向右滑动");
                    } else {
                        // 向左滑动
                        Log.i(TAG, "onTouchEvent: 向左滑动");
                    }
                } else {
                    // 垂直滑动
                    isHorizontalScroll = false;

                    if (mDeltaPosY > 0) {
                        // 向下滑动
                        Log.i(TAG, "onTouchEvent: 向下滑动");
                    } else {
                        // 向上滑动
                        Log.i(TAG, "onTouchEvent: 向上滑动");
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

        if (HomePageManager.getInstance().isNewsMode()) {
            // 新闻模式，不进行拦截
            return false;
        } else if (HomePageManager.getInstance().isNormalMode() && isHorizontalScroll) {
            // 普通模式下水平滑动，拦截
            return true;
        } else {
            return super.onInterceptTouchEvent(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d(TAG, "onTouchEvent() called with: e = [" + e + "]");

        if (HomePageManager.getInstance().isNewsMode()) {
            // 新闻模式，不做处理
            return false;
        } else {
            return super.onTouchEvent(e);
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (!canScrollVertically(1)) {
            Log.i(TAG, "onScrolled: 设置当前为新闻模式");
            HomePageManager.getInstance().setNewsMode();
        }
    }
}
