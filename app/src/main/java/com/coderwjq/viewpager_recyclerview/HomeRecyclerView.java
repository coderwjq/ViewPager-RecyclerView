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

    public HomeRecyclerView(Context context) {
        super(context);
    }

    public HomeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (HomePageManager.getInstance().isNewsMode()) {
            return false;
        } else {
            return super.onInterceptTouchEvent(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d(TAG, "onTouchEvent() called with: e = [" + e + "]");

        return super.onTouchEvent(e);
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
