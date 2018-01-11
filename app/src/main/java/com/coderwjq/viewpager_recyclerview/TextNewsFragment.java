package com.coderwjq.viewpager_recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class TextNewsFragment extends Fragment implements HomePageManager.OnModeChangeListener, HomePageManager.OnRefreshClickListener {
    private static final String TAG = "TextNewsFragment";

    private NewRecyclerView mRvNews;
    private SwipeRefreshLayout mSwipeToRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomePageManager.getInstance().attatchModeChangeListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, null);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView) {
        mRvNews = rootView.findViewById(R.id.rv_news);

        mRvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvNews.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        NewsAdapter newsAdapter = new NewsAdapter(getContext(), "文字新闻");
        mRvNews.setAdapter(newsAdapter);

        mSwipeToRefresh = rootView.findViewById(R.id.swipe_to_refresh);
        mSwipeToRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeToRefresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#BBFFFF"));
        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh: 进入下拉刷新请求数据");

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //模拟网络请求
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //在UI线程中更新UI
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mSwipeToRefresh.isRefreshing()) {
                                    mSwipeToRefresh.setRefreshing(false);
                                }
                            }
                        });
                    }
                }.start();
            }
        });

    }

    @Override
    public void refreshMode(int currentMode) {
        if (mRvNews == null) {
            return;
        }

        if (currentMode == HomePageManager.HOME_PAGE_MODE_NORMAL) {
            mRvNews.scrollToPosition(0);
        }
    }

    @Override
    public void refreshNews() {
        if (mSwipeToRefresh.isRefreshing()) {
            return;
        }

        mSwipeToRefresh.setRefreshing(true);

        new Thread() {
            @Override
            public void run() {
                super.run();
                //模拟网络请求
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在UI线程中更新UI
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mSwipeToRefresh.isRefreshing()) {
                            mSwipeToRefresh.setRefreshing(false);
                        }
                    }
                });
            }
        }.start();
    }
}
