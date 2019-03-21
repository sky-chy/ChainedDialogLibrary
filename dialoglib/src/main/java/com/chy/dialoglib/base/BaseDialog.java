package com.chy.dialoglib.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chy.dialoglib.R;

import java.util.Objects;

import static com.chy.dialoglib.base.DialogAnim.ANIM_SCALE;
import static com.chy.dialoglib.util.Utils.dp2px;
import static com.chy.dialoglib.util.Utils.getScreenWidth;

public abstract class BaseDialog extends DialogFragment {
    @LayoutRes
    private int layID;
    private Context context;
    private int mMargin = 40;
    private boolean mShowBottomEnable;
    private int mWidth;
    private int mHeight;
    private boolean mOutCancel = true;
    private int anim = ANIM_SCALE.getDialogType();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialog);
        layID = setLayoutId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layID, container, false);
        operationInterface(view, this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    /**
     * 设置布局文件
     *
     * @return 布局文件的ID
     */
    public abstract int setLayoutId();

    /**
     * 返回获取到的布局界面
     *
     * @param view       布局界面
     * @param baseDialog 对话框窗体
     */
    public abstract void operationInterface(View view, BaseDialog baseDialog);

    /**
     * 初始化窗体
     */
    @SuppressLint("NewApi")
    private void init() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();

            //设置dialog显示位置
            if (mShowBottomEnable) {
                params.gravity = Gravity.BOTTOM;
            }

            //设置dialog宽度
            if (mWidth == 0) {
                params.width = getScreenWidth(Objects.requireNonNull(getContext())) - 2 * dp2px(getContext(), mMargin);
            } else {
                params.width = dp2px(Objects.requireNonNull(getContext()), mWidth);
            }

            //设置dialog高度
            if (mHeight == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = dp2px(getContext(), mHeight);
            }
            //设置动画
            params.windowAnimations = anim;
            window.setAttributes(params);
        }
        setCancelable(mOutCancel);
    }

    /**
     * 是否显示到底部
     *
     * @param showBottom false或true
     * @return 对话框本体
     */
    public BaseDialog setShowOnBottom(boolean showBottom) {
        mShowBottomEnable = showBottom;
        return this;
    }

    /**
     * 设置宽高
     *
     * @param width  窗体宽度
     * @param height 窗体高度
     * @return 对话框本体
     */
    public BaseDialog setDialogSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    /**
     * 设置左右margin
     *
     * @param margin 窗体边距
     * @return 对话框本体
     */
    public BaseDialog setBothSidesMargin(int margin) {
        mMargin = margin;
        return this;
    }

    /**
     * 设置是否点击外部取消
     *
     * @param outCancel false或true
     * @return 对话框本体
     */
    public BaseDialog setOutCancel(boolean outCancel) {
        mOutCancel = outCancel;
        return this;
    }

    /**
     * 显示对话框
     *
     * @param manager FragmentManager
     * @return 对话框本体
     */
    public BaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    /**
     * 设置对话框进出动画
     *
     * @param dialogAnim 进出动画类型
     * @return 对话框本体
     */
    @SuppressLint("NewApi")
    public BaseDialog setDialogAnim(DialogAnim dialogAnim) {
        this.anim = dialogAnim.getDialogType();
        return this;
    }

}
