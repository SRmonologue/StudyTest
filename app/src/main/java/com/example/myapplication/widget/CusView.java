package com.example.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/7/11.
 */

public class CusView extends View {


    private Paint mPaint;
    private Paint mCenterPaint;
    private float mCenterX;
    private float mCenterY;
    private int mAlpha = 255;//透明度
    private float mDiffWidth = 0;//扩散宽度
    private float mInRadius;
    private float mInnerCircleRadus = 25;//中心圆半径
    private Boolean startSweep = true;
    private List<Float> mProgrssL;
    private Paint mPaintSector;
    private int mOffSetArgs;


    public CusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {
        mProgrssL = new ArrayList<>();
        mProgrssL.add(0f);

        //扩散园画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(4);

        mPaintSector = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSector.setDither(true);
        mPaintSector.setStyle(Paint.Style.FILL);
        mPaintSector.setColor(getResources().getColor(R.color.colorPrimary));

        //中心画笔
        mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterPaint.setDither(true);
        mCenterPaint.setStyle(Paint.Style.FILL);
        mCenterPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取中心圆坐标
        mCenterX = w / 2;
        mCenterY = h / 2;
        mInRadius = Math.min(mCenterX, mCenterY);
    }


    private float getValue2Progress(float start, float end, @FloatRange(from = 0.0f, to = 1.0f) float progress) {
        return start + (end - start) * progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mProgrssL.size(); i++) {
            mAlpha = (int) getValue2Progress(255, 0, mProgrssL.get(i));
            mDiffWidth = getValue2Progress(mInnerCircleRadus, mInRadius, mProgrssL.get(i));
            mPaint.setAlpha(mAlpha);
            //画扩散圆
            canvas.drawCircle(mCenterX, mCenterY, mDiffWidth, mPaint);

            if (mProgrssL.get(i) < 1.0f) {
                mProgrssL.set(i, mProgrssL.get(i) + 0.008f);
            }
        }

        if (mDiffWidth > mInRadius / 3) {
            mProgrssL.add(0f);
        }

        if (mProgrssL.size() > 6) {
            mProgrssL.remove(0);
        }



        //画中心圆

        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY, Color.TRANSPARENT, getResources().getColor(R.color.colorPrimary));
        mPaintSector.setShader(sweepGradient);

        canvas.save();
        canvas.rotate(mOffSetArgs, mCenterX, mCenterY);
        canvas.drawCircle(mCenterX, mCenterY, mInRadius, mPaintSector);
        if (mOffSetArgs < 360) {
            mOffSetArgs += 1;
        } else if (mOffSetArgs == 360) {
            mOffSetArgs = 0;
        }

        canvas.restore();

        //画中心圆
        canvas.drawCircle(mCenterX, mCenterY, mInnerCircleRadus, mCenterPaint);
        if (startSweep) {
            postInvalidate();
        }
    }

    @Override
    public void postInvalidate() {
        if (hasWindowFocus()) {
            super.postInvalidate();
        }
    }
}

