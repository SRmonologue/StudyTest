package com.example.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

/**
 * 下标标记的日历控件
 * Created by huanghaibin on 2017/11/15.
 */

public class IndexCalendarCardView extends MonthView {
    private Paint mSchemeBasicPaint = new Paint();
    private int mPadding;
    private int mH, mW;
    private int mRadius;

    public IndexCalendarCardView(Context context) {
        super(context);
        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mPadding = dipToPx(getContext(), 4);
        mH = dipToPx(getContext(), 2);
        mW = dipToPx(getContext(), 8);
    }

    @Override
    protected void onPreviewHook() {
        mCurMonthTextPaint.setTextSize(dipToPx(getContext(), 16));
        mOtherMonthTextPaint.setTextSize(dipToPx(getContext(), 16));
        mSchemeTextPaint.setTextSize(dipToPx(getContext(), 16));
        mCurDayTextPaint.setTextSize(dipToPx(getContext(), 16));
        mCurMonthLunarTextPaint.setTextSize(dipToPx(getContext(), 12));
        mOtherMonthLunarTextPaint.setTextSize(dipToPx(getContext(), 12));
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
    }

    @Override
    protected void onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        mSelectedPaint.setStyle(Paint.Style.FILL);
        mSelectedPaint.setColor(Color.parseColor("#108cd4"));
//                canvas.drawRect(x + mPadding,
//                        y + mPadding,
//                        x + mItemWidth - mPadding,
//                        y + mItemHeight - mPadding, mSelectedPaint);

        canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight / 2 -mPadding , 50, mSelectedPaint);
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;
        if (hasScheme) {
            mCurMonthLunarTextPaint.setColor(mCurMonthLunarTextColor);
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);
            mSchemeBasicPaint.setColor(calendar.getSchemeColor());
            // canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mCurMonthLunarTextPaint);
            //            canvas.drawRect(x + mItemWidth / 2 - mW / 2,
            //                    y + mItemHeight - mH * 2 - mPadding,
            //                    x + mItemWidth / 2 + mW / 2,
            //                    y + mItemHeight - mH - mPadding, mSchemeBasicPaint);
            canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight - 3 * mH / 2 - mPadding, 10, mSchemeBasicPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
            // canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mCurMonthLunarTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
