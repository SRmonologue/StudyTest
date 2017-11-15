package com.example.myapplication.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.LinearGradientUtil;
import com.example.myapplication.widget.CircleBarView;

import java.text.DecimalFormat;

/**
 * 描述：
 * Created by 9527 on 2017/9/19.
 */

public class Test11Activity extends AppCompatActivity {

    private CircleBarView mCircleBarView;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        mCircleBarView = (CircleBarView) findViewById(R.id.circle_View);
        mTv = (TextView) findViewById(R.id.tv);
        mCircleBarView.setProcessNum(100,5000);
        mCircleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howtoChangeText(float interpolatedTime, float processNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * processNum / maxNum * 100) + "%";
                return s;

            }

            @Override
            public void howtoChangeProcessColor(Paint paint, float interpolatedTime, float processNum, float maxNum) {
                LinearGradientUtil linearGradientUtil = new LinearGradientUtil(Color.BLACK, Color.RED);
                paint.setColor(linearGradientUtil.getColor(interpolatedTime));
            }
        });
        mCircleBarView.setTextView(mTv);
    }
}
