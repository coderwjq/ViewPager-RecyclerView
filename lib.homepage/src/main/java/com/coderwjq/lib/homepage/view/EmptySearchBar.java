package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.coderwjq.lib.homepage.R;
import com.coderwjq.lib.homepage.listener.IHomePageScrollListener;
import com.coderwjq.lib.homepage.util.UIUtils;

/**
 * @author: wangjiaqi
 * @data: 2018/1/15
 */

public class EmptySearchBar extends FrameLayout implements IHomePageScrollListener {
    private static final String TAG = "EmptySearchBar";

    private Context mContext;
    private TextView mEmptySearchBar;

    public EmptySearchBar(@NonNull Context context) {
        super(context);

        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.empty_head_home_search_bar_layout, this, true);

        int height = UIUtils.dip2px(mContext, 72);
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));

        mEmptySearchBar = findViewById(R.id.empty_search_bar);
    }

    @Override
    public void onScroll(float scrollY) {
        mEmptySearchBar.setText(scrollY + "");
    }
}
