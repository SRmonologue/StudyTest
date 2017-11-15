package com.example.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.DpUtil;

/**
 * 描述：
 * Created by 9527 on 2017/10/18.
 */

public class CircleBarView extends View {

    private Paint mProcessPaint;
    private float mSweepAngle;
    private float mSweeProcesspAngle;
    private CircleBarAnim mAnim;
    private Paint mBgPaint;

    private float processNum;
    private float maxNum;
    private int mDefaultSize;
    private float mBarWidth;
    private RectF mRectF;
    private int mProcessColor;
    private int mBgColor;
    private float mStartAngle;
    private TextView textView;
    private OnAnimationListener onAnimationListener;


    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleBarView);
        mProcessColor = array.getColor(R.styleable.CircleBarView_process_color, Color.GREEN);
        mBgColor = array.getColor(R.styleable.CircleBarView_bg_color, Color.GRAY);
        mStartAngle = array.getFloat(R.styleable.CircleBarView_start_angle, 0);
        mSweepAngle = array.getFloat(R.styleable.CircleBarView_sweep_angle, 360);
        mBarWidth = array.getDimension(R.styleable.CircleBarView_bar_width, DpUtil.dp2px(context, 10));
        array.recycle();

        mDefaultSize = DpUtil.dp2px(context, 100);

        mRectF = new RectF();


        mProcessPaint = new Paint();
        mProcessPaint.setStyle(Paint.Style.STROKE);
        mProcessPaint.setColor(mProcessColor);
        mProcessPaint.setAntiAlias(true);
        mProcessPaint.setStrokeWidth(mBarWidth);

        mBgPaint = new Paint();
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(mBarWidth);

        processNum = 0;
        maxNum = 100;

        mAnim = new CircleBarAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRectF, mStartAngle, mSweepAngle, false, mBgPaint);//画圆形背景
        canvas.drawArc(mRectF, mStartAngle, mSweeProcesspAngle, false, mProcessPaint);//动画圆形进度条
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measeureSize(mDefaultSize, heightMeasureSpec);
        int width = measeureSize(mDefaultSize, widthMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);

        if (min >= mBarWidth * 2) {
            mRectF.set(mBarWidth / 2, mBarWidth / 2, min - mBarWidth / 2, min - mBarWidth / 2);
        }
    }


    private int measeureSize(int defaultSize, int meaureSpec) {
        int result = defaultSize;

        int specMode = View.MeasureSpec.getMode(meaureSpec);
        int specSize = MeasureSpec.getSize(meaureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }

        return result;
    }

    private class CircleBarAnim extends Animation {
        public CircleBarAnim() {

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mSweeProcesspAngle = interpolatedTime * mSweepAngle * processNum / maxNum;
            if (onAnimationListener != null) {
                if (textView != null) {
                    textView.setText(onAnimationListener.howtoChangeText(interpolatedTime, processNum, maxNum));
                }

                onAnimationListener.howtoChangeProcessColor(mProcessPaint, interpolatedTime, processNum, maxNum);
            }

            postInvalidate();
        }
    }

    public void setProcessNum(float processNum, int time) {
        mAnim.setDuration(time);
        this.startAnimation(mAnim);
        this.processNum = processNum;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public interface OnAnimationListener{
        String howtoChangeText(float interpolatedTime, float processNum, float maxNum);

        void howtoChangeProcessColor(Paint paint, float interpolatedTime, float processNum, float maxNum);
    }

    public void setOnAnimationListener(OnAnimationListener onAnimationListener) {
        this.onAnimationListener = onAnimationListener;
    }
}
