package com.example.myapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * Created by gz on 2017/7/6.
 */

public class TestAdpater extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdpater(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ExpandableTextView expTv1 = helper.getView(R.id.expand_text_view);
        expTv1.setText(mContext.getString(R.string.long_string) + item);
    }
}
