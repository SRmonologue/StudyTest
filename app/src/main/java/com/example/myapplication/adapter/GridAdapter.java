package com.example.myapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;

import java.util.List;

/**
 * Created by gz on 2017/6/16.
 */

public class GridAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public GridAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv = helper.getView(R.id.tv);
        tv.setText(item);
    }
}
