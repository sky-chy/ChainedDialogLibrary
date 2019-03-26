package com.chy.dialoglib.dialog;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.LinearLayout;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.widget.MyProgressBar;

public class ProgressDialog extends BaseDialog {
    //控件
    private LinearLayout lay;
    private MyProgressBar pro;
    private int progress;
    private float textSize = 10;
    private int textColor = Color.WHITE;
    private int second = 500;
    private int max = 100;
    private Drawable drawable;

    public static ProgressDialog newInstance() {
        Bundle bundle = new Bundle();
        ProgressDialog dialog = new ProgressDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_progress;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        lay = view.findViewById(R.id.lay);
        pro = view.findViewById(R.id.pro);
        initView();


    }

    private void initView() {
        pro.setTextSize(textSize);
        pro.setTextColor(textColor);
        pro.setMax(max);
        if (drawable != null) {
            pro.setProgressDrawable(drawable);
        }
        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                pro.setProgress(progress);
                handler.postDelayed(this, second);
            }
        };
        handler.postDelayed(r, second);

    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * 设置进度条更新频率
     *
     * @param second 秒
     * @return 对话框本体
     */
    public ProgressDialog setDelayMillis(int second) {
        this.second = second;
        return this;
    }

    /**
     * 设置进度文字大小
     *
     * @param textSize 字号
     * @return 对话框本体
     */
    public ProgressDialog setTextSize(float textSize) {
        this.textSize = textSize;
        return this;

    }

    /**
     * 设置进度文字颜色
     *
     * @param textColor 颜色
     * @return 对话框本体
     */
    public ProgressDialog setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        return this;

    }

    /**
     * 设置进度条前景
     *
     * @param drawable 前景drawable
     * @return 对话框本体
     */
    public ProgressDialog setProgressDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    /**
     * 设置进度条最大值
     *
     * @param max 最大值
     * @return 对话框本体
     */
    public ProgressDialog setMax(int max) {
        this.max = max;
        return this;
    }
}
