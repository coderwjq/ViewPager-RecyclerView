package com.coderwjq.viewpager_recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class NewRecyclerView extends RecyclerView {
    private static final String TAG = "NewRecyclerView";
    private ViewGroup parent;
    private float mStartPosX;
    private float mCurPosX;
    private float mStartPosY;
    private float mCurPosY;
    private float mDeltaPosX;
    private float mDeltaPosY;

    public NewRecyclerView(Context context) {
        super(context);
    }

    public void setNestedpParent(ViewGroup parent) {
        this.parent = parent;
    }

    public NewRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        Log.d(TAG, "onTouchEvent() called with: e = [" + e + "]");
//        boolean isHorizontalScroll = false;
//
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mStartPosX = e.getX();
//                mStartPosY = e.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mCurPosX = e.getX();
//                mCurPosY = e.getY();
//
//                mDeltaPosX = mCurPosX - mStartPosX;
//                mDeltaPosY = mCurPosY - mStartPosY;
//
//                if (Math.abs(mDeltaPosX) > Math.abs(mDeltaPosY)) {
//                    // 水平滑动
//                    isHorizontalScroll = true;
//
//                    if (Math.abs(mDeltaPosX) < 25) {
//                        return false;
//                    }
//
//                    if (mDeltaPosX > 0) {
//                        // 向右滑动
//                        Log.i(TAG, "onTouchEvent: 向右滑动");
//                    } else {
//                        // 向左滑动
//                        Log.i(TAG, "onTouchEvent: 向左滑动");
//                    }
//                } else {
//                    // 垂直滑动
//                    isHorizontalScroll = false;
//
//                    if (Math.abs(mDeltaPosY) < 25) {
//                        return false;
//                    }
//
//                    if (mDeltaPosY > 0) {
//                        // 向下滑动
//                        Log.i(TAG, "onTouchEvent: 向下滑动");
//                    } else {
//                        // 向上滑动
//                        Log.i(TAG, "onTouchEvent: 向上滑动");
//                    }
//                }
//
//                mStartPosX = mCurPosX;
//                mStartPosY = mCurPosY;
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
//
//        if (parent != null && HomePageManager.getInstance().isNewsMode() && !isHorizontalScroll) {
//            Log.i(TAG, "onTouchEvent: requestDisallowInterceptTouchEvent");
////            parent.requestDisallowInterceptTouchEvent(true);
//        }

        return super.onTouchEvent(e);
    }
}
