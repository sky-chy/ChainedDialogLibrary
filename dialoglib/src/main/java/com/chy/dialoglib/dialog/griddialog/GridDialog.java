package com.chy.dialoglib.dialog.griddialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chy.dialoglib.R;
import com.chy.dialoglib.adapter.GridPagerAdapter;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.bean.GridDialogBean;
import com.chy.dialoglib.listener.OnDialogGridClickListener;
import com.chy.dialoglib.listener.OnDialogGridLongClickListener;
import com.chy.dialoglib.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GridDialog extends BaseDialog {
    //控件
    private LinearLayout lay;
    private ViewPager menus;
    private LinearLayout indicator;

    //数据源
    private List<GridDialogBean> menuDatas = new ArrayList<>();
    //附着fragment
    private List<GridDialogItemFragment> gridDialogItemFragments = new ArrayList<>();
    //指示器
    private ImageView[] indicators;
    //菜单文字
    private int menusTextColor = 0;
    private float menusTextFontSize = 0f;
    //每页显示菜单数
    private int showGridCount = 8;
    //背景
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int backgroundDrawableResource;
    //PagerAdapter
    private GridPagerAdapter adapter;
    //指示器
    private boolean showIndication = true;
    private int indicatorNotSelect = R.drawable.ic_indicator;
    private int indicatorSelect = R.drawable.ic_indicator_fill;
    //设置监听器
    private OnDialogGridClickListener gridClickListener;
    private OnDialogGridLongClickListener gridLongClickListener;
    //菜单区高度
    private int dialogMenuHeight;

    public static GridDialog newInstance() {
        Bundle bundle = new Bundle();
        GridDialog dialog = new GridDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_grid;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        lay = view.findViewById(R.id.lay);
        menus = view.findViewById(R.id.menus);
        indicator = view.findViewById(R.id.indication);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        initData();
        setPageParams();
        setIndicationParams();
        setBackgroundParams();
        setListenerParams();
    }

    /**
     * 初始化数据
     */
    @SuppressLint("NewApi")
    private void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            dialogMenuHeight = (int) (Math.ceil(showGridCount / 4d) * Utils.dp2px(Objects.requireNonNull(getContext()), 100));
        else
            dialogMenuHeight = (int) (Math.ceil(showGridCount / 4d) * Utils.dp2px(getContext(), 100));

        //计算总页数
        int page = (int) Math.ceil(menuDatas.size() / (double) showGridCount);
        //初始化指示器数量
        indicators = new ImageView[page];
        GridDialogItemFragment gridDialogItemFragment;
        for (int i = 1; i <= page; i++) {
            gridDialogItemFragment = new GridDialogItemFragment();
            if (i * showGridCount - 1 <= menuDatas.size()) {
                gridDialogItemFragment.setMenuDatas(menuDatas.subList((i - 1) * showGridCount, i * showGridCount));
            } else {
                gridDialogItemFragment.setMenuDatas(menuDatas.subList((i - 1) * showGridCount, menuDatas.size()));
            }
            gridDialogItemFragment.setPageTag(i);//设置页数标志
            gridDialogItemFragment.setDialogMenuHeight(dialogMenuHeight);//设置对话框菜单区高度
            gridDialogItemFragment.setMenuTextColor(menusTextColor);//设置菜单字体颜色
            gridDialogItemFragment.setMenuTextFontSize(menusTextFontSize);//设置菜单字体大小
            gridDialogItemFragment.setOnDialogGridClickListener(new OnDialogGridClickListener() {
                @Override
                public void onDialogGridClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                    if (gridClickListener != null) {
                        onDataPosition = (pageTag - 1) * showGridCount + onPagePosition;
                        gridClickListener.onDialogGridClick(view, pageTag, onDataPosition, onPagePosition, GridDialog.this);
                    }
                }
            });
            gridDialogItemFragment.setOnDialogGridLongClickListener(new OnDialogGridLongClickListener() {
                @Override
                public void onDialogGridLongClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                    if (gridLongClickListener != null) {
                        onDataPosition = (pageTag - 1) * showGridCount + onPagePosition;
                        gridLongClickListener.onDialogGridLongClick(view, pageTag, onDataPosition, onPagePosition, GridDialog.this);
                    }
                }
            });
            gridDialogItemFragments.add(gridDialogItemFragment);

            //设置指示器
            indicators[i - 1] = new ImageView(getContext());
            indicators[i - 1].setImageResource(indicatorNotSelect);
            indicators[i - 1].setPadding(5, 5, 5, 5);
            indicator.addView(indicators[i - 1]);

        }
        //选择第一个指示器
        ((ImageView) indicator.getChildAt(0)).setImageResource(indicatorSelect);
    }

    private void setPageParams() {
        ViewGroup.LayoutParams param = menus.getLayoutParams();
        param.height = dialogMenuHeight;
        menus.setLayoutParams(param);

        adapter = new GridPagerAdapter(getContext(), gridDialogItemFragments, getChildFragmentManager());
        menus.setAdapter(adapter);

    }

    private void setIndicationParams() {
        if (showIndication) {
            indicator.setVisibility(View.VISIBLE);
        } else {
            indicator.setVisibility(View.GONE);
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

    private void setListenerParams() {
        menus.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < indicators.length; j++) {
                    if (j == i) {
                        ((ImageView) indicator.getChildAt(i)).setImageResource(indicatorSelect);
                    } else {
                        ((ImageView) indicator.getChildAt(j)).setImageResource(indicatorNotSelect);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 设置数据源
     *
     * @param menuDatas 数据源
     * @return 对话框本体
     */
    public GridDialog setMenuDatas(List<GridDialogBean> menuDatas) {
        this.menuDatas = menuDatas;
        return this;
    }

    /**
     * 设置数据源
     *
     * @param icons 菜单图片
     * @param texts 菜单文字
     * @return 对话框本体
     */
    public GridDialog setMenuDatas(int[] icons, String[] texts) {
        GridDialogBean dialogBean;
        for (int i = 0; i < (icons.length > texts.length ? icons.length : texts.length); i++) {
            dialogBean = new GridDialogBean();
            if (icons[i] != 0)
                dialogBean.icon = icons[i];
            if (!TextUtils.isEmpty(texts[i]))
                dialogBean.text = texts[i];
            menuDatas.add(dialogBean);
        }
        return this;
    }

    /**
     * 设置菜单文字颜色
     *
     * @param menusTextColor 字体颜色
     * @return 对话框本体
     */
    public GridDialog setMenuTextColor(@ColorInt int menusTextColor) {
        this.menusTextColor = menusTextColor;
        return this;
    }

    /**
     * 设置菜单文字大小
     *
     * @param menusTextFontSize 字号大小
     * @return 对话框本体
     */
    public GridDialog setMenuTextFontSize(float menusTextFontSize) {
        this.menusTextFontSize = menusTextFontSize;
        return this;
    }

    /**
     * 是否显示指示器
     *
     * @param showIndication 是否显示指示器
     * @return 对话框本体
     */
    public GridDialog setIndicatorShow(boolean showIndication) {
        this.showIndication = showIndication;
        return this;
    }

    /**
     * 设置未选择状态的指示器
     *
     * @param indicatorNotSelect 未选择状态的指示器
     * @return 对话框本体
     */
    public GridDialog setIndicatorNotSelectStatus(@DrawableRes int indicatorNotSelect) {
        this.indicatorNotSelect = indicatorNotSelect;
        return this;
    }

    /**
     * 设置已选择状态的指示器
     *
     * @param indicatorSelect 已选择状态的指示器
     * @return 对话框本体
     */
    public GridDialog setIndicatorSelectStatus(@DrawableRes int indicatorSelect) {
        this.indicatorSelect = indicatorSelect;
        return this;
    }

    /**
     * 每页显示的菜单数
     *
     * @param showGridCount 每页显示的菜单数
     * @return 对话框本体
     */
    public GridDialog setShowGridCountOnPage(int showGridCount) {
        this.showGridCount = (showGridCount <= 16 ? showGridCount : 16);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor Color值
     * @return 对话框本体
     */
    public GridDialog setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawable Drawable值
     * @return 对话框本体
     */
    public GridDialog setBackground(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param backgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public GridDialog setBackgroundRes(@DrawableRes int backgroundDrawableResource) {
        this.backgroundDrawableResource = backgroundDrawableResource;
        return this;
    }

    /**
     * 设置监听器
     *
     * @param gridClickListener 点击事件
     * @return 对话框本体
     */
    public GridDialog setOnDialogGridClickListener(OnDialogGridClickListener gridClickListener) {
        this.gridClickListener = gridClickListener;
        return this;
    }

    /**
     * 设置监听器
     *
     * @param gridLongClickListener 长按事件
     * @return 对话框本体
     */
    public GridDialog setOnDialogGridLongClickListener(OnDialogGridLongClickListener gridLongClickListener) {
        this.gridLongClickListener = gridLongClickListener;
        return this;
    }
}
