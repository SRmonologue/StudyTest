package com.example.myapplication.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * 描述：window
 * Created by 9527 on 2018/4/25.
 */

public class Test16Activity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private Button mBtn;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test16);
        mBtn = new Button(this);
        mBtn.setText("button");
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT
        );
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 400;

        mWindowManage = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManage.addView(mBtn, mLayoutParams);

        mBtn.setOnTouchListener(this);
        mBtn.setOnClickListener(this);
        initAnima();
    }

    private void initAnima() {
        RotateAnimation animation = new RotateAnimation(0, 360);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(2000);
        findViewById(R.id.click).startAnimation(animation);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x = x - mBtn.getWidth() / 2;
                mLayoutParams.y = y - mBtn.getHeight() / 2;
                mWindowManage.updateViewLayout(mBtn, mLayoutParams);
                break;
            case MotionEvent.ACTION_UP:
                mLayoutParams.x = 0;
                mLayoutParams.y = 400;
                mWindowManage.updateViewLayout(mBtn, mLayoutParams);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "click me", Toast.LENGTH_SHORT).show();
    }
}
