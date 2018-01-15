package com.coderwjq.lib.homepage.manager;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.coderwjq.lib.homepage.common.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class HomePageManager {
    private static final String TAG = "HomePageManager";

    private static int mCurrentMode = Constant.HOME_PAGE_MODE_NORMAL;
    private List<OnModeChangeListener> mObservers = new ArrayList<>();
    private int mMenuBarHeight;

    public int getMenuBarHeight() {
        return mMenuBarHeight;
    }

    public void setMenuBarHeight(int menuBarHeight) {
        mMenuBarHeight = menuBarHeight;
    }

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
        return mCurrentMode == Constant.HOME_PAGE_MODE_NORMAL;
    }

    public boolean isNewsMode() {
        return mCurrentMode == Constant.HOME_PAGE_MODE_NEWS;
    }

    public void setNewsMode() {
        mCurrentMode = Constant.HOME_PAGE_MODE_NEWS;

        refreshMode(mCurrentMode);
    }

    public void setNormalMode() {
        mCurrentMode = Constant.HOME_PAGE_MODE_NORMAL;

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
