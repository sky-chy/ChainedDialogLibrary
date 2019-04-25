package com.chy.dialoglib.dialog;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.LinearLayout;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;

/**
 * @author : chenhongye
 */
public class CustomViewDialog extends BaseDialog {
    private View view;
    private LinearLayout lay;
    //背景
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int backgroundDrawableResource;

    //是否启用默认的背景图片
    private boolean isEnabled = false;

    public static CustomViewDialog newInstance() {
        Bundle bundle = new Bundle();
        CustomViewDialog dialog = new CustomViewDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_view;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        lay = view.findViewById(R.id.lay);
        initView();
    }

    private void initView() {
        lay.removeAllViews();
        if (view != null)
            lay.addView(view);
        else
            isEnabled = true;
        setBackgroundParams();
    }

    private void setBackgroundParams() {
        if (backgroundColor != 0) {
            lay.setBackgroundColor(backgroundColor);
        } else if (backgroundDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                lay.setBackground(backgroundDrawable);
            } else {
                throw new RuntimeException("本机SDK版本低于16或Android版本低于4.2");
            }
        } else if (backgroundDrawableResource != 0) {
            lay.setBackgroundResource(backgroundDrawableResource);
        } else if (!isEnabled) {
            lay.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置视图
     *
     * @param view 需要展示的视图
     * @return 当前类
     */
    public CustomViewDialog addView(View view) {
        this.view = view;
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor Color值
     * @return 对话框本体
     */
    public CustomViewDialog setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawable Drawable值
     * @return 对话框本体
     */
    public CustomViewDialog setBackground(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public CustomViewDialog setBackgroundRes(@DrawableRes int backgroundDrawableResource) {
        this.backgroundDrawableResource = backgroundDrawableResource;
        return this;
    }

    /**
     * 是否启用默认的背景图片
     *
     * @param isEnabled true 启用| false 不启用 | 默认false
     * @return 当前类
     */
    public CustomViewDialog enabledDefaultBackground(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }
}
