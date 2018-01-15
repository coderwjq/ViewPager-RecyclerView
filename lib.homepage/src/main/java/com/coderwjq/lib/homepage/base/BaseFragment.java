package com.coderwjq.lib.homepage.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: wangjiaqi
 * @data: 2018/1/12
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null, false);

            initView(mRootView);
        }

        return mRootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView);
}
