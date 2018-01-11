package com.coderwjq.viewpager_recyclerview;


import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class HomePageManager {
    private static final String TAG = "HomePageManager";

    public static final int HOME_PAGE_MODE_NORMAL = 0;
    public static final int HOME_PAGE_MODE_NEWS = 1;

    private static int mCurrentMode = HOME_PAGE_MODE_NORMAL;
    private List<OnModeChangeListener> mObservers = new ArrayList<>();

    private Fragment mCurrentChannel;

    public Fragment getCurrentChannel() {
        return mCurrentChannel;
    }

    public void setCurrentChannel(Fragment currentChannel) {
        mCurrentChannel = currentChannel;
    }

    private HomePageManager() {
    }

    private static HomePageManager sInstance;

    public static HomePageManager getInstance() {
        if (sInstance == null) {
            synchronized (HomePageManager.class) {
                if (sInstance == null) {
                    sInstance = new HomePageManager();
                }
            }
        }

        return sInstance;
    }

    public void attatchModeChangeListener(OnModeChangeListener listener) {
        if (listener != null) {
            mObservers.add(listener);
        }
    }

    public boolean isNormalMode() {
        return mCurrentMode == HOME_PAGE_MODE_NORMAL;
    }

    public boolean isNewsMode() {
        return mCurrentMode == HOME_PAGE_MODE_NEWS;
    }

    public void setNewsMode() {
        mCurrentMode = HOME_PAGE_MODE_NEWS;

        refreshMode(mCurrentMode);
    }

    public void setNormalMode() {
        mCurrentMode = HOME_PAGE_MODE_NORMAL;

        refreshMode(mCurrentMode);
    }

    private void refreshMode(int currentMode) {
        for (OnModeChangeListener observer : mObservers) {
            observer.refreshMode(currentMode);
        }
    }

    public interface OnModeChangeListener {
        void refreshMode(int currentMode);
    }

    public interface OnRefreshClickListener {
        void refreshNews();
    }
}
