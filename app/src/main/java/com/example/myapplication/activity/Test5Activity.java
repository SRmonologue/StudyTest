package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TestAdpater;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/7/6.
 */

public class Test5Activity extends AppCompatActivity {

    private ExpandableTextView mTv;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);
        for (int i = 0; i < 50; i++) {
            mList.add("" + i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        TestAdpater adpater = new TestAdpater(R.layout.item_test_1, mList);
        mRecyclerView.setAdapter(adpater);
    }
}
