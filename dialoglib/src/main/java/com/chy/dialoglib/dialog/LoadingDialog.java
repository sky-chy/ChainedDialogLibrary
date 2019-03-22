package com.chy.dialoglib.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.LinearLayout;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;

import pl.droidsonroids.gif.GifImageView;

public class LoadingDialog extends BaseDialog {
    //等待对话框类型
    public enum LoadingType {
        CYLINDER, DISC, STRIPE, COLUMNAR
    }

    //控件
    private GifImageView gif;
    private LinearLayout lay;
    //自定义等待对话框
    private int loadingImageID;
    private int backgroundColor;
    private int backgroundRes;
    private Drawable backgroundDrawable;
    //等待对话框类型
    private LoadingType type = LoadingType.CYLINDER;

    public static LoadingDialog newInstance() {
        Bundle bundle = new Bundle();
        LoadingDialog dialog = new LoadingDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        gif = view.findViewById(R.id.gif);
        lay = view.findViewById(R.id.lay);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        setPresetTypeParams();
        setCustomizeLoadingDialogParams();
    }

    @SuppressLint("NewApi")
    private void setCustomizeLoadingDialogParams() {
        if (loadingImageID != 0 && backgroundColor != 0) {
            lay.setBackgroundColor(backgroundColor);
            gif.setImageResource(loadingImageID);
        }
        if (loadingImageID != 0 && backgroundDrawable != null) {
            lay.setBackground(backgroundDrawable);
            gif.setImageResource(loadingImageID);
        }
        if (loadingImageID != 0 && backgroundRes != 0) {
            lay.setBackgroundResource(backgroundRes);
            gif.setImageResource(loadingImageID);
        }
    }

    private void setPresetTypeParams() {
        switch (type) {
            case CYLINDER:
                gif.setImageResource(R.mipmap.ic_loading1);
                break;
            case DISC:
                gif.setImageResource(R.mipmap.ic_loading2);
                break;
            case STRIPE:
                gif.setImageResource(R.mipmap.ic_loading3);
                break;
            case COLUMNAR:
                gif.setImageResource(R.mipmap.ic_loading4);
                break;
        }
    }

    /**
     * 设置等待对话框类型
     *
     * @param type 类型
     * @return 本体
     */
    public LoadingDialog setLoadingType(LoadingType type) {
        this.type = type;
        return this;
    }

    /**
     * 设置自定义背景和图案的等待对话框
     *
     * @param loadingImageID      自定义图案
     * @param backgroundColor 自定义背景颜色
     * @return 本体
     */
    public LoadingDialog setCustomizeLoadingColor(@DrawableRes int loadingImageID, @ColorInt int backgroundColor) {
        this.loadingImageID = loadingImageID;
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置自定义背景和图案的等待对话框
     *
     * @param loadingImageID    自定义图案
     * @param backgroundRes 自定义背景Drawable
     * @return 本体
     */
    public LoadingDialog setCustomizeLoadingResource(@DrawableRes int loadingImageID, @DrawableRes int backgroundRes) {
        this.loadingImageID = loadingImageID;
        this.backgroundRes = backgroundRes;
        return this;
    }

    /**
     * 设置自定义背景和图案的等待对话框
     *
     * @param loadingImageID         自定义图案
     * @param backgroundDrawable 自定义背景图片
     * @return 本体
     */
    public LoadingDialog setCustomizeLoadingDrawable(@DrawableRes int loadingImageID, Drawable backgroundDrawable) {
        this.loadingImageID = loadingImageID;
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

}
