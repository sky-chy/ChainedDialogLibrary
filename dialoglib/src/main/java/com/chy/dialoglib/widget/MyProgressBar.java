package com.chy.dialoglib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.chy.dialoglib.R;

import static com.chy.dialoglib.util.Utils.sp2px;

public class MyProgressBar extends ProgressBar {
    //画笔
    private Paint paint;
    // 进度文字位置信息
    private Rect mProgressTextRect = new Rect();
    //设置文字大小
    private float textSize = 10;
    private int color = Color.WHITE;

    public MyProgressBar(Context context) {
        this(context, null);
        init();
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    @SuppressLint("NewApi")
    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, android.R.attr.progressBarStyleHorizontal);
        init();
    }

    @SuppressLint("NewApi")
    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //设置progress进度条风格
        setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.layer_list_progressbar));
        //初始化文字画笔
        paint = new TextPaint();
        //防锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //画笔颜色

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(sp2px(getContext(), textSize));
        paint.setColor(color);
        String progressText = "0";
        if (getMax() <= 100) {
            progressText = (float) getProgress() + "%";
        } else if (getMax() <= 1000 && getMax() > 100) {
            progressText = (float) getProgress() + "‰";
        }
        paint.getTextBounds(progressText, 0, progressText.length(), mProgressTextRect);
        // 进度百分比
        float progressRatio = (float) getProgress() / getMax();
        // thumb偏移量
//        float thumbOffset = (mThumbWidth - mProgressTextRect.width()) / 2 - mThumbWidth * progressRatio;
        float thumbX;
//        if ((getWidth() * progressRatio) / mProgressTextRect.width() >= 0.5) {
        thumbX = getWidth() * progressRatio - mProgressTextRect.width();
//        } else {
//            thumbX = getWidth() * progressRatio + mProgressTextRect.width();
//        }

        float thumbY = getHeight() / 2f + mProgressTextRect.height() / 2f;
        canvas.drawText(progressText, thumbX, thumbY, paint);
    }

    /**
     * 设置进度文字大小，默认10sp
     *
     * @param textSize 字号
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    /**
     * 设置进度文字颜色，默认黑色
     *
     * @param color
     */
    public void setTextColor(@ColorInt int color) {
        this.color = color;
    }

}
