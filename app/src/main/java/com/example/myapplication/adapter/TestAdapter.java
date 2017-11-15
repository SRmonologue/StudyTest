package com.example.myapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.widget.FullyGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/6/16.
 */

public class TestAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TestAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);


        //列表结构
        List<String> mList = new ArrayList<>();
        mList.add("互动氛围");
        mList.add("冲突解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        mList.add("情绪解决");
        RecyclerView recyclerView = helper.getView(R.id.recycleView);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(manager);
        GridAdapter adapter = new GridAdapter(R.layout.item_grid,mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
