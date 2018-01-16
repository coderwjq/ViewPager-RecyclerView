package com.coderwjq.lib.homepage.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.coderwjq.lib.homepage.R;
import com.coderwjq.lib.homepage.adapter.HomePagerAdapter;
import com.coderwjq.lib.homepage.fragment.HomePageFragment;
import com.coderwjq.lib.homepage.manager.HomePageManager;

/**
 * @author: wangjiaqi
 * @data: 2018/1/12
 */

public class HomePageView extends FrameLayout {
    private Context mContext;
    private HomeViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;

    public HomePageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.home_page_view_layout, this, true);
        mViewPager = findViewById(R.id.home_view_pager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        HomePageManager.getInstance().setLastMode();
                        break;
                    case 1:
                        HomePageManager.getInstance().setSiteMode();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setController(FragmentManager fragmentManager) {
        mHomePagerAdapter = new HomePagerAdapter(fragmentManager);
        mViewPager.setAdapter(mHomePagerAdapter);
    }

    public void backToNormalState() {
        if (HomePageManager.getInstance().isSiteMode()) {
            // 网址模式，返回普通模式
            mViewPager.setCurrentItem(0);
        } else {
            Fragment fragment = mHomePagerAdapter.getItem(0);

            if (fragment instanceof HomePageFragment) {
                ((HomePageFragment) fragment).backToNormalState();
            }
        }
    }

    public void calcNewsViewHolder() {
        Fragment fragment = mHomePagerAdapter.getItem(0);

        if (fragment instanceof HomePageFragment) {
            ((HomePageFragment) fragment).calcNewsViewHolder();
        }
    }
}
