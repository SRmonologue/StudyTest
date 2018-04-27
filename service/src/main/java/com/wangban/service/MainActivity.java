package com.wangban.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //请求1
        Intent intent1 = new Intent("cn.cn");
        Bundle bundle1 = new Bundle();
        bundle1.putString("taskName", "task1");
        intent1.putExtras(bundle1);
        startService(intent1);
        //请求2
        Intent intent2 = new Intent("cn.cn");
        Bundle bundle2 = new Bundle();
        bundle2.putString("taskName", "task2");
        intent2.putExtras(bundle2);
        startService(intent2);

    }
}
