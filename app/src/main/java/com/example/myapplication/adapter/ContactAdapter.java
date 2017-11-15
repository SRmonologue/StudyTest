package com.example.myapplication.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.widget.ContactBean;
import com.example.myapplication.R;

import java.util.List;

/**
 * Created by gz on 2017/6/22.
 */

public class ContactAdapter extends BaseQuickAdapter<ContactBean,BaseViewHolder> {

    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();

    public ContactAdapter(@LayoutRes int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactBean item) {
        helper.setText(R.id.tv_name, item.getName());
        TextDrawable drawable = mDrawableBuilder.build(String.valueOf(item.getName().charAt(0)), mColorGenerator.getColor(item.getName()));
        ImageView imageView = helper.getView(R.id.iv_img);
        imageView.setImageDrawable(drawable);
    }
}
