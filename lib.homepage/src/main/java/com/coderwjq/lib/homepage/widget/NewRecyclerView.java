package com.coderwjq.lib.homepage.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class NewRecyclerView extends RecyclerView {
    private static final String TAG = "NewRecyclerView";

    public NewRecyclerView(Context context) {
        super(context);
    }


    public NewRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        return super.onTouchEvent(e);
    }
}
