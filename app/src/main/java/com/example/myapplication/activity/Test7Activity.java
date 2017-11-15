package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;


/**
 * 描述：
 * Created by 9527 on 2017/7/13.
 */

public class Test7Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test7);
        initView();
    }

    private void initView() {
        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
        final Button button = (Button) transitionsContainer.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            boolean visible;
            boolean isRotated;

            @Override
            public void onClick(View v) {
//                TransitionSet set = new TransitionSet()
//                        .addTransition(new Scale(0.7f))
//                        .addTransition(new Fade())
//                        .setInterpolator(visible ? new LinearOutSlowInInterpolator() :
//                                new FastOutLinearInInterpolator());

                TransitionManager.beginDelayedTransition(transitionsContainer, new Rotate());
                visible = !visible;
                isRotated = !isRotated;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
                text.setRotation(isRotated ? 135 : 0);
            }

        });

    }
}
