package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.coderwjq.lib.homepage.R;

/**
 * @author: wangjiaqi
 * @data: 2018/1/15
 */

public class EmptySearchBar extends FrameLayout {
    private Context mContext;

    public EmptySearchBar(@NonNull Context context) {
        super(context);

        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.empty_head_home_search_bar_layout, this, true);
    }
}
