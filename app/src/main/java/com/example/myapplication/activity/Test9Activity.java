package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.adapter.DemoAdapter;
import com.example.myapplication.adapter.DemoHeadAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：
 * Created by 9527 on 2017/7/13.
 */

public class Test9Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test9);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 30; i++) {
            mList.add("" + i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DemoAdapter(R.layout.item_demo, mList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        //头布局
        View view = LayoutInflater.from(Test9Activity.this).inflate(R.layout.item_head_view, (ViewGroup) mRecyclerView.getParent(), false);
        RecyclerView head = (RecyclerView) view.findViewById(R.id.recycleViewhead);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        head.setLayoutManager(linearLayoutManager);
        DemoHeadAdapter demoHeadAdapter = new DemoHeadAdapter(R.layout.item_demo, mList);
        head.setAdapter(demoHeadAdapter);

        mAdapter.addHeaderView(view);
    }
}
