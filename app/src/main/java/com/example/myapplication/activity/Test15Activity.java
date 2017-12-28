package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SlidingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：抽屉
 * Created by 9527 on 2017/12/28.
 */

public class Test15Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private View bottomSheet;
    private BottomSheetBehavior behavior;
    private SlidingAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test15);


        for (int i = 0; i < 7; i++) {
            mList.add(i + "");
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SlidingAdapter(R.layout.item_demo, mList);
        View view = getLayoutInflater().inflate(R.layout.item_head, null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(view);
        view.findViewById(R.id.head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = behavior.getState();
                if (state == 4) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (state == 3) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
//                String state = "null";
//                switch (newState) {
//                    case 1:
//                        state = "STATE_DRAGGING";
//                        break;
//                    case 2:
//                        state = "STATE_SETTLING";
//                        break;
//                    case 3:
//                        state = "STATE_EXPANDED";
//                        break;
//                    case 4:
//                        state = "STATE_COLLAPSED";
//                        break;
//                    case 5:
//                        state = "STATE_HIDDEN";
//                        break;
//                }
//                Log.d("MainActivity", "newState:" + state);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                //                Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
//            }
//        });



//        ImageView fab = (ImageView) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });


    }

}