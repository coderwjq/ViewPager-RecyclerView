package com.coderwjq.viewpager_recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
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
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author: wangjiaqi
 * @data: 2018/1/10
 */

public class VideoNewsFragment extends Fragment implements HomePageManager.OnModeChangeListener, HomePageManager.OnRefreshClickListener {
    private static final String TAG = "VideoNewsFragment";

    private NewRecyclerView mRvNews;
    private SwipeRefreshLayout mSwipeToRefresh;
    private TextView mTvRefreshNotice;
    private int mNoticeHeight;
    private boolean isShowNoticeText;

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
        mTvRefreshNotice = rootView.findViewById(R.id.tv_refresh_notice);

        mTvRefreshNotice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mNoticeHeight = mTvRefreshNotice.getHeight();
                mTvRefreshNotice.setTranslationY(-mNoticeHeight);

                mTvRefreshNotice.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mRvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvNews.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        NewsAdapter newsAdapter = new NewsAdapter(getContext(), "视频新闻");
        mRvNews.setAdapter(newsAdapter);

        mSwipeToRefresh = rootView.findViewById(R.id.swipe_to_refresh);
        mSwipeToRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeToRefresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#BBFFFF"));

        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh: 进入下拉刷新请求数据");

                requestNews();
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
        if (mSwipeToRefresh.isRefreshing() || isShowNoticeText) {
            Toast.makeText(getActivity(), "正在刷新...", Toast.LENGTH_SHORT).show();
            return;
        }

        mRvNews.smoothScrollToPosition(0);
        mSwipeToRefresh.setRefreshing(true);

        requestNews();
    }

    private void requestNews() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //模拟网络请求
                try {
                    Thread.sleep(1000);
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

                        // 刷新完成
                        final ObjectAnimator translationY = ObjectAnimator.ofFloat(mTvRefreshNotice, "translationY", -mNoticeHeight, 0);
                        translationY.setDuration(500);
                        translationY.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);

                                isShowNoticeText = true;
                                mSwipeToRefresh.setEnabled(false);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        //模拟网络请求
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ObjectAnimator translationY = ObjectAnimator.ofFloat(mTvRefreshNotice, "translationY", 0, -mNoticeHeight);
                                                translationY.setDuration(500);
                                                translationY.addListener(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                        isShowNoticeText = false;
                                                        mSwipeToRefresh.setEnabled(true);
                                                    }
                                                });
                                                translationY.start();
                                            }
                                        });
                                    }
                                }.start();
                            }
                        });
                        translationY.start();
                    }
                });
            }
        }.start();
    }
}
