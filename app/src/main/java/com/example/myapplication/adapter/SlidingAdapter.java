package com.example.myapplication.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;

import java.util.List;


/**
 * 描述：
 * Created by 9527 on 2017/12/28.
 */

public class SlidingAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public SlidingAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
