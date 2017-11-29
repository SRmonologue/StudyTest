package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.service.MyIntentService;


/**
 * 描述：
 * Created by 9527 on 2017/7/13.
 */

public class Test8Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_loading);
        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test8Activity.this, MyIntentService.class);
                intent.putExtra("start", "haha");
                startService(intent);
            }
        });
    }

}
