package com.chy.dialoglib.dialog;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.listener.OnDialogClickListener;

public class PromptDialog extends BaseDialog {
    //控件
    private TextView btn = null;
    private TextView content = null;
    private LinearLayout lay = null;
    private View dividing = null;
    //按钮
    private String btnText;
    private int btnTextColor;
    private float btnTextSize;
    //内容
    private String contentText;
    private int contentTextColor;
    private float contentTextSize;
    //背景
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int backgroundDrawableResource;
    //分割线
    private int dividingColor;
    private boolean dividingShow = true;
    //监听器
    private OnDialogClickListener listener = null;

    public static PromptDialog newInstance() {
        Bundle bundle = new Bundle();
        PromptDialog dialog = new PromptDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_prompt;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        btn = view.findViewById(R.id.btn);
        content = view.findViewById(R.id.content);
        lay = view.findViewById(R.id.lay);
        dividing = view.findViewById(R.id.dividing);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        setButtonParams();
        setContentParams();
        setBackgroundParams();
        setDividingParams();
        setListenerParams();

    }

    private void setButtonParams() {
        if (!TextUtils.isEmpty(btnText)) {
            btn.setText(btnText);
        }
        if (btnTextColor != 0) {
            btn.setTextColor(btnTextColor);
        }
        if (btnTextSize != 0f) {
            btn.setTextSize(btnTextSize);
        }
    }

    private void setContentParams() {
        if (!TextUtils.isEmpty(contentText)) {
            content.setText(contentText);
        }
        if (contentTextColor != 0) {
            content.setTextColor(contentTextColor);
        }
        if (contentTextSize != 0f) {
            content.setTextSize(contentTextSize);
        }
    }

    private void setBackgroundParams() {
        if (backgroundColor != 0) {
            lay.setBackgroundColor(backgroundColor);
        }
        if (backgroundDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                lay.setBackground(backgroundDrawable);
            } else {
                throw new RuntimeException("本机SDK版本低于16或Android版本低于4.2");
            }
        }
        if (backgroundDrawableResource != 0) {
            lay.setBackgroundResource(backgroundDrawableResource);
        }
    }

    private void setDividingParams() {
        if (dividingColor != 0) {
            dividing.setBackgroundColor(dividingColor);
        }
        if (dividingShow) {
            dividing.setVisibility(View.VISIBLE);
        } else {
            dividing.setVisibility(View.GONE);
        }
    }

    private void setListenerParams() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDialogClick(v, PromptDialog.this);
                }
            }
        });
    }


    /**
     * 设置按钮文字
     *
     * @param btnText 按钮文字
     * @return 对话框本体
     */
    public PromptDialog setButtonText(String btnText) {
        this.btnText = btnText;
        return this;
    }

    /**
     * 设置按钮文字颜色
     *
     * @param btnTextColor Color值
     * @return 对话框本体
     */
    public PromptDialog setButtonTextColor(@ColorInt int btnTextColor) {
        this.btnTextColor = btnTextColor;
        return this;
    }

    /**
     * 设置内容文字
     *
     * @param contentText 内容文字
     * @return 对话框本体
     */
    public PromptDialog setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     * 设置内容区的文字颜色
     *
     * @param contentTextColor Color值
     * @return 对话框本体
     */
    public PromptDialog setContentTextColor(@ColorInt int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return this;
    }

    /**
     * 设置按钮文字字体大小
     *
     * @param btnTextSize float型字号
     * @return 对话框本体
     */
    public PromptDialog setButtonTextFontSize(float btnTextSize) {
        this.btnTextSize = btnTextSize;
        return this;
    }

    /**
     * 设置内容文字字体大小
     *
     * @param contentTextSize float型字号
     * @return 对话框本体
     */
    public PromptDialog setContentTextFontSize(float contentTextSize) {
        this.contentTextSize = contentTextSize;
        return this;
    }

    /**
     * 设置按钮监听器
     *
     * @param listener 监听器
     * @return 对话框本体
     */
    public PromptDialog setOnDialogClickListener(OnDialogClickListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor Color值
     * @return 对话框本体
     */
    public PromptDialog setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawable Drawable值
     * @return 对话框本体
     */
    public PromptDialog setBackground(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public PromptDialog setBackgroundRes(@DrawableRes int backgroundDrawableResource) {
        this.backgroundDrawableResource = backgroundDrawableResource;
        return this;
    }

    /**
     * 设置分割线颜色
     *
     * @param dividingColor Color值
     * @return 对话框本体
     */
    public PromptDialog setDividingColor(@ColorInt int dividingColor) {
        this.dividingColor = dividingColor;
        return this;
    }

    /**
     * 是否显示分割线
     *
     * @param dividingShow 是否显示分割线
     * @return 对话框本体
     */
    public PromptDialog setDividingShow(boolean dividingShow) {
        this.dividingShow = dividingShow;
        return this;
    }
}
