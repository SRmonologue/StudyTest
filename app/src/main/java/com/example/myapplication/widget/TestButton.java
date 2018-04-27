package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 描述：
 * Created by 9527 on 2018/4/25.
 */

public class TestButton extends android.support.v7.widget.AppCompatTextView {

    private int mScaledTouchSlop;
    private int mLastX;
    private int mLastY;

    public TestButton(Context context) {
        this(context, null);
    }

    public TestButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.w("gz", "mScaledTouchSlop: " + mScaledTouchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                int destX = x - mLastX;
                int destY = y - mLastY;
                this.setTranslationX(this.getTranslationX() + destX);
                this.setTranslationY(this.getTranslationY() + destY);
                //                int translationX = (int) (ViewHelper.getTranslationX(this) + destX);
                //                int translationY = (int) (ViewHelper.getTranslationY(this) + destY);
                //                ViewHelper.setTranslationX(this, translationX);
                //                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

}
