package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.coderwjq.lib.homepage.manager.HomePageManager;

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

    public HomeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

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
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (!canScrollVertically(1)) {
            Log.i(TAG, "onScrolled: 设置当前为新闻模式");
            HomePageManager.getInstance().setNewsMode();
        } else {
            if (!HomePageManager.getInstance().isNormalMode()) {
                Log.i(TAG, "onScrolled: 设置当前为普通模式");
                HomePageManager.getInstance().setNormalMode();
            }
        }
    }

    private float mStartTouchY;
    private float mDeltaTouchY;
    private float mTotalDeltaY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartTouchY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mDeltaTouchY = e.getY() - mStartTouchY;
                mStartTouchY = e.getY();

                if (!canScrollVertically(-1)) {
                    mTotalDeltaY += mDeltaTouchY;
                } else {
                    if (mTotalDeltaY > 0) {
                        mTotalDeltaY += mDeltaTouchY;
                    } else {
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTotalDeltaY = 0;
                break;
            default:
                break;
        }

        Log.i(TAG, "onTouchEvent...mTotalDeltaY: " + mTotalDeltaY);

        return super.onTouchEvent(e);
    }
}
