package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TabLayoutAdapter;
import com.example.myapplication.fragment.BaikeFragment;
import com.example.myapplication.fragment.wenkuFragment;
import com.example.myapplication.fragment.zhilunFragment;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * Created by gz on 2017/6/16.
 */

public class Test2Activity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mToolbar.setTitle("冲突解决");
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(MODE_FIXED);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), mTabLayout, mViewPager);
        tabLayoutAdapter.addFragment("百科", BaikeFragment.class, null);
        tabLayoutAdapter.addFragment("智论", zhilunFragment.class, null);
        tabLayoutAdapter.addFragment("文库", wenkuFragment.class, null);
    }
}
