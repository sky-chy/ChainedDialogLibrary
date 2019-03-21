package com.chy.dialoglib.dialog;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.listener.OnDialogClickListener;

public class TextDialog extends BaseDialog {
    //控件
    private LinearLayout lay;
    private ImageView typeIcon = null;
    private TextView title = null;
    private TextView content = null;
    private TextView cancel = null;
    private TextView confirm = null;
    private View hDividing = null;
    private View vDividing = null;
    //对话框类型
    private DIALOG_TYPE dialogType = DIALOG_TYPE.NONE;
    private int customIcon;
    //标题
    private String titleText;
    private int titleTextColor;
    private float titleTextSize;
    //内容区
    private String contentText;
    private int contentTextColor;
    private float contentTextSize;
    private int contentGravity;
    //背景
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int backgroundDrawableResource;
    //取消按钮
    private String cancelText;
    private int cancelButtonTextColor;
    private float cancelButtonTextSize;
    private boolean cancelButtonShow = true;
    //确认按钮
    private String confirmButtonText;
    private int confirmButtonTextColor;
    private float confirmButtonTextSize;
    private boolean confirmShow = true;
    //横向分割线
    private boolean hDividingShow = true;
    private int hDividingColor;
    //纵向分割线
    private boolean vDividingShow = true;
    private int vDividingColor;
    //监听器
    private OnDialogClickListener cancelCickListener;
    private OnDialogClickListener confirmClickListener;

    //对话框类型枚举
    public enum DIALOG_TYPE {
        NONE, ERROR, INFORMATION, RIGHT, WARNING
    }

    public static TextDialog newInstance() {
        Bundle bundle = new Bundle();
        TextDialog dialog = new TextDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_text;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        lay = view.findViewById(R.id.lay);
        typeIcon = view.findViewById(R.id.type_icon);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        cancel = view.findViewById(R.id.cancel);
        confirm = view.findViewById(R.id.confirm);
        hDividing = view.findViewById(R.id.h_dividing);
        vDividing = view.findViewById(R.id.v_dividing);
        initView();
    }

    private void initView() {
        setTypeDialogParams();
        setTitleParams();
        setContentParams();
        setBackgroundParams();
        setCancelParams();
        setConfirmParams();
        setHDividingParams();
        setVDividingParams();
        setClickListenerParams();
    }


    private void setTypeDialogParams() {
        typeIcon.setVisibility(View.VISIBLE);
        switch (dialogType) {
            case NONE:
                if (customIcon == 0) {
                    typeIcon.setVisibility(View.GONE);
                } else {
                    typeIcon.setImageResource(customIcon);
                }
                break;
            case ERROR:
                typeIcon.setImageResource(R.mipmap.ic_error);
                break;
            case RIGHT:
                typeIcon.setImageResource(R.mipmap.ic_right);
                break;
            case WARNING:
                typeIcon.setImageResource(R.mipmap.ic_warning);
                break;
            case INFORMATION:
                typeIcon.setImageResource(R.mipmap.ic_infomation);
                break;
        }
    }

    private void setTitleParams() {
        if (!TextUtils.isEmpty(titleText)) {
            title.setText(titleText);
        }
        if (titleTextColor != 0) {
            title.setTextColor(titleTextColor);
        }
        if (titleTextSize != 0f) {
            title.setTextSize(titleTextSize);
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
        if (contentGravity != 0) {
            content.setGravity(contentGravity);
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

    private void setCancelParams() {
        if (!TextUtils.isEmpty(cancelText)) {
            cancel.setText(cancelText);
        }
        if (cancelButtonTextColor != 0) {
            cancel.setTextColor(cancelButtonTextColor);
        }
        if (cancelButtonTextSize != 0f) {
            cancel.setTextSize(cancelButtonTextSize);
        }
        if (cancelButtonShow) {
            cancel.setVisibility(View.VISIBLE);
            vDividing.setVisibility(View.VISIBLE);
        } else {
            cancel.setVisibility(View.GONE);
            vDividing.setVisibility(View.GONE);
        }
    }

    private void setConfirmParams() {
        if (!TextUtils.isEmpty(confirmButtonText)) {
            confirm.setText(confirmButtonText);
        }
        if (confirmButtonTextColor != 0) {
            confirm.setTextColor(confirmButtonTextColor);
        }
        if (confirmButtonTextSize != 0f) {
            confirm.setTextSize(confirmButtonTextSize);
        }
        if (confirmShow) {
            confirm.setVisibility(View.VISIBLE);
            vDividing.setVisibility(View.VISIBLE);
        } else {
            confirm.setVisibility(View.GONE);
            vDividing.setVisibility(View.GONE);
        }
    }

    private void setHDividingParams() {
        if (hDividingShow) {
            hDividing.setVisibility(View.VISIBLE);
        } else {
            hDividing.setVisibility(View.GONE);
        }
        if (hDividingColor != 0) {
            hDividing.setBackgroundColor(hDividingColor);
        }
    }

    private void setVDividingParams() {
        if (vDividingShow) {
            vDividing.setVisibility(View.VISIBLE);
        } else {
            vDividing.setVisibility(View.GONE);
        }
        if (vDividingColor != 0) {
            vDividing.setBackgroundColor(vDividingColor);
        }
    }

    private void setClickListenerParams() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelCickListener != null) {
                    cancelCickListener.onDialogClick(v, TextDialog.this);
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmClickListener != null) {
                    confirmClickListener.onDialogClick(v, TextDialog.this);
                }
            }
        });
    }

    /**
     * 设置对话框类型
     *
     * @param dialog_type 对话框类型
     * @return 对话框本体
     */
    public TextDialog setDialogType(DIALOG_TYPE dialog_type) {
        dialogType = dialog_type;
        return this;
    }

    /**
     * 设置自定义Icon
     *
     * @param customIcon 自定义Icon
     * @return 对话框本体
     */
    public TextDialog setCustomIcon(@DrawableRes int customIcon) {
        this.customIcon = customIcon;
        return this;
    }

    /**
     * 设置标题文字
     *
     * @param titleText 标题文字
     * @return 对话框本体
     */
    public TextDialog setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param titleTextColor 标题颜色
     * @return 对话框本体
     */
    public TextDialog setTitleTextColor(@ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * 设置标题文字字体大小
     *
     * @param titleTextSize 字体大小
     * @return 对话框本体
     */
    public TextDialog setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    /**
     * 设置内容文字
     *
     * @param contentText String值
     * @return 对话框本体
     */
    public TextDialog setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     * 设置内容颜色
     *
     * @param contentTextColor Color值
     * @return 对话框本体
     */
    public TextDialog setContentTextColor(@ColorInt int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return this;
    }

    /**
     * 设置内容文字字体大小
     *
     * @param contentTextSize float值
     * @return 对话框本体
     */
    public TextDialog setContentTextSize(float contentTextSize) {
        this.contentTextSize = contentTextSize;
        return this;
    }

    /**
     * 设置内容文字对齐方式
     *
     * @param contentGravity Gravity值
     * @return 对话框本体
     */
    public TextDialog setContentTextGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor Color值
     * @return 对话框本体
     */
    public TextDialog setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawable Drawable值
     * @return 对话框本体
     */
    public TextDialog setBackground(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public TextDialog setBackgroundRes(@DrawableRes int backgroundDrawableResource) {
        this.backgroundDrawableResource = backgroundDrawableResource;
        return this;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancelText 取消按钮文字
     * @return 对话框本体
     */
    public TextDialog setCancelButtonText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    /**
     * 设置取消按钮文字颜色
     *
     * @param cancelButtonTextColor 取消按钮文字颜色
     * @return 对话框本体
     */
    public TextDialog setCancelButtonTextColor(@ColorInt int cancelButtonTextColor) {
        this.cancelButtonTextColor = cancelButtonTextColor;
        return this;
    }

    /**
     * 设置取消按钮文字字体大小
     *
     * @param cancelButtonTextSize 取消按钮文字字体大小
     * @return 对话框本体
     */
    public TextDialog setCancelButtonTextSize(float cancelButtonTextSize) {
        this.cancelButtonTextSize = cancelButtonTextSize;
        return this;
    }

    /**
     * 是否显示取消按钮
     *
     * @param cancelButtonShow false或者true
     * @return 对话框本体
     */
    public TextDialog setCancelButtonShow(boolean cancelButtonShow) {
        this.cancelButtonShow = cancelButtonShow;
        return this;
    }

    /**
     * 设置确认按钮文字
     *
     * @param confirmButtonText 确认按钮文字
     * @return 对话框本体
     */
    public TextDialog setConfirmButtonText(String confirmButtonText) {
        this.confirmButtonText = confirmButtonText;
        return this;
    }

    /**
     * 设置确认按钮文字颜色
     *
     * @param confirmButtonTextColor 确认按钮文字颜色
     * @return 对话框本体
     */
    public TextDialog setConfirmButtonTextColor(@ColorInt int confirmButtonTextColor) {
        this.confirmButtonTextColor = confirmButtonTextColor;
        return this;
    }

    /**
     * 设置确认按钮文字字体大小
     *
     * @param confirmButtonTextSize 字体大小
     * @return 对话框本体
     */
    public TextDialog setConfirmButtonTextSize(float confirmButtonTextSize) {
        this.confirmButtonTextSize = confirmButtonTextSize;
        return this;
    }

    /**
     * 是否显示确认按钮
     *
     * @param confirmShow false或者true
     * @return 对话框本体
     */
    public TextDialog setConfirmButtonShow(boolean confirmShow) {
        this.confirmShow = confirmShow;
        return this;
    }

    /**
     * 是否显示横向分割线
     *
     * @param hDividingShow false或者true
     * @return 对话框本体
     */
    public TextDialog setHDividingShow(boolean hDividingShow) {
        this.hDividingShow = hDividingShow;
        return this;
    }

    /**
     * 设置横向分割线颜色
     *
     * @param hDividingColor 横向分割线颜色
     * @return 对话框本体
     */
    public TextDialog setHDividingColor(@ColorInt int hDividingColor) {
        this.hDividingColor = hDividingColor;
        return this;
    }

    /**
     * 是否显示纵向分割线
     *
     * @param vDividingShow false或者true
     * @return 对话框本体
     */
    public TextDialog setVDividingShow(boolean vDividingShow) {
        this.vDividingShow = vDividingShow;
        return this;
    }

    /**
     * 设置纵向分割线颜色
     *
     * @param vDividingColor 纵向分割线颜色
     * @return 对话框本体
     */
    public TextDialog setVDividingColor(@ColorInt int vDividingColor) {
        this.vDividingColor = vDividingColor;
        return this;
    }

    /**
     * 设置取消按钮的监听
     *
     * @param cancelClickListener 按钮的监听
     * @return 对话框本体
     */
    public TextDialog setOnCancelClickListener(OnDialogClickListener cancelClickListener) {
        this.cancelCickListener = cancelClickListener;
        return this;
    }

    /**
     * 设置确认按钮的监听
     *
     * @param confirmClickListener 按钮的监听
     * @return 对话框本体
     */
    public TextDialog setOnConfirmClickListener(OnDialogClickListener confirmClickListener) {
        this.confirmClickListener = confirmClickListener;
        return this;
    }

}
