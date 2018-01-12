package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.coderwjq.lib.homepage.R;
import com.coderwjq.lib.homepage.adapter.HomePagerAdapter;
import com.coderwjq.lib.homepage.widget.HomeViewPager;

/**
 * @author: wangjiaqi
 * @data: 2018/1/12
 */

public class HomePageView extends FrameLayout {
    private Context mContext;
    private HomeViewPager mViewPager;
    private HomePagerAdapter mPagerAdapter;

    public HomePageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.home_page_view_layout, this, true);
        mViewPager = findViewById(R.id.home_view_pager);
    }
}
