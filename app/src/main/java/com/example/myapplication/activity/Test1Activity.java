package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/6/16.
 */

public class Test1Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private String[] Tag = {"学习环境", "版务常规", "师幼互动", "家园共育", "幼儿评估"};
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTabLayout = (TabLayout) findViewById(R.id.anchor_tagContainer);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mTabLayout.setOnTabSelectedListener(this);
        initTab();
        initRecycleView();
    }

    private void initTab() {
        // 添加页内导航标签
        for (String item : Tag) {
            mTabLayout.addTab(mTabLayout.newTab().setText(item));
            mList.add(item);
        }
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        TestAdapter adapter = new TestAdapter(R.layout.item_test, mList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    mTabLayout.setScrollPosition(position, 0, true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        mRecyclerView.scrollToPosition(position);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
