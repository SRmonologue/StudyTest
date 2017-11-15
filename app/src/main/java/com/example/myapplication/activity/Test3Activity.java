package com.example.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.adapter.GridAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/6/19.
 */

public class Test3Activity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayoutState state;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler mHandler = new Handler();

    /**
     * title三种状态
     */
    private enum CollapsingToolbarLayoutState {
        EXPANDED,       //展开
        COLLAPSED,      //关闭
        INTERNEDIATE    //中间
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        initView();
        initTitleBar();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      mSwipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

        for (int i = 0; i < 30; i++) {
            mList.add("" + i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        GridAdapter adapter = new GridAdapter(R.layout.item_grid, mList);
        mRecyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    private void initTitleBar() {
        CollapsingToolbarLayout.LayoutParams lp = new CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,dp2px(48) + getStatusBarHeight(this));
        mToolbar.setLayoutParams(lp);
        mToolbar.setTitleMarginTop(getStatusBarHeight(this));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));//设置收缩后Toolbar上字体的颜色
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        mSwipeRefreshLayout.setEnabled(true);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mCollapsingToolbarLayout.setTitle("班级动态");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        mSwipeRefreshLayout.setEnabled(false);
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            mCollapsingToolbarLayout.setTitle("");//设置title不显示
                            mSwipeRefreshLayout.setEnabled(false);
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = -1;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
