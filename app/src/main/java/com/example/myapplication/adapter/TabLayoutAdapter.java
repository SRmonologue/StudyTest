package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 9527 on 2016/12/22.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private Context mContext;
    List<Class<?>> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    List<Bundle> bundles = new ArrayList<>();
    private FragmentManager fm;

    public TabLayoutAdapter(FragmentManager fm, TabLayout mTab, ViewPager mVp) {
        super(fm);
        mContext = mVp.getContext();
        this.fm = fm;
        mVp.setAdapter(this);
        mTab.setupWithViewPager(mVp);
    }


    public void addFragment(String title, Class<?> f, Bundle bundle) {
        titles.add(title);
        fragments.add(f);
        bundles.add(bundle);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Class<?> clazz = fragments.get(position);
        return Fragment.instantiate(mContext, clazz.getName(), bundles.get(position));
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = getItem(position);
        fm.beginTransaction().hide(fragment).commit();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
