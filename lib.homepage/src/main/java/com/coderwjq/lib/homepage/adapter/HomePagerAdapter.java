package com.coderwjq.lib.homepage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coderwjq.lib.homepage.base.BaseFragment;
import com.coderwjq.lib.homepage.fragment.HomePageFragment;
import com.coderwjq.lib.homepage.fragment.SiteManageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangjiaqi
 * @data: 2018/1/12
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments = new ArrayList<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments.add(new HomePageFragment());
        mFragments.add(new SiteManageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
