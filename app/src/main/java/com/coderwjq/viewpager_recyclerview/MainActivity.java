package com.coderwjq.viewpager_recyclerview;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.coderwjq.lib.homepage.fragment.TextNewsFragment;
import com.coderwjq.lib.homepage.fragment.VideoNewsFragment;
import com.coderwjq.lib.homepage.manager.HomePageManager;
import com.coderwjq.lib.homepage.view.HomePageView;

public class MainActivity extends AppCompatActivity implements HomePageManager.OnModeChangeListener {
    private static final String TAG = "MainActivity";

    private LinearLayout mLlMenuBar;
    private Button mBtnBackHome;

    private Button mBtnForwardOrRefresh;
    private HomePageView mHomePageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomePageManager.getInstance().attatchModeChangeListener(this);

        mLlMenuBar = findViewById(R.id.ll_menu_bar);
        mBtnBackHome = findViewById(R.id.btn_back_home);
        mBtnForwardOrRefresh = findViewById(R.id.btn_forward_or_refresh);
        mHomePageView = findViewById(R.id.home_page_view);
        mHomePageView.setController(getSupportFragmentManager());

        Log.i(TAG, "mLlMenuBarHeight: " + mLlMenuBar.getMeasuredHeight());
        HomePageManager.getInstance().setMenuBarHeight(mLlMenuBar.getMeasuredHeight());

        mBtnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePageView.backToNormalState();
            }
        });
        mBtnForwardOrRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomePageManager.getInstance().isNormalMode()) {
                    Toast.makeText(MainActivity.this, "前进", Toast.LENGTH_SHORT).show();
                } else {
                    Fragment currentChannel = HomePageManager.getInstance().getCurrentChannel();

                    if (currentChannel instanceof TextNewsFragment) {
                        ((TextNewsFragment) currentChannel).refreshNews();
                    } else if (currentChannel instanceof VideoNewsFragment) {
                        ((VideoNewsFragment) currentChannel).refreshNews();
                    }
                }
            }
        });

        mBtnBackHome.setFocusableInTouchMode(true);
        mBtnBackHome.requestFocus();
    }

    @Override
    public void refreshMode(int currentMode) {
        if (currentMode == HomePageManager.HOME_PAGE_MODE_NORMAL) {
            mBtnForwardOrRefresh.setText("FORWARD");
            mBtnForwardOrRefresh.setBackgroundColor(Color.parseColor("#ff99cc00"));
        } else {
            mBtnForwardOrRefresh.setText("REFRESH");
            mBtnForwardOrRefresh.setBackgroundColor(Color.parseColor("#ffaa66cc"));
        }
    }

    @Override
    public void onBackPressed() {
        if (HomePageManager.getInstance().isNewsMode()) {
            mHomePageView.backToNormalState();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: called");

        mHomePageView.calcNewsViewHolder();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}