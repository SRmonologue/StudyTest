package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/6/16.
 */

public class wenkuFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        GridAdapter adapter = new GridAdapter(R.layout.item_grid, mList);
        mRecyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        return view;
    }
}
