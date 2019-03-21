package com.chy.dialoglib.dialog.griddialog;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chy.dialoglib.R;
import com.chy.dialoglib.adapter.GridAdapter;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.bean.GridDialogBean;
import com.chy.dialoglib.listener.OnDialogGridClickListener;
import com.chy.dialoglib.listener.OnDialogGridLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class GridDialogItemFragment extends Fragment {
    private List<GridDialogBean> menus = new ArrayList<>();
    private GridAdapter adapter;
    private RecyclerView menu;
    //菜单文字
    private int menusTextColor = 0;
    private float menusTextFontSize = 0f;
    //每页显示菜单数
    private int dialogMenuHeight;
    //页数标志
    private int pageTag;
    //设置监听器
    private OnDialogGridClickListener gridClickListener;
    private OnDialogGridLongClickListener gridLongClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        menu = view.findViewById(R.id.menus);
        initView();
        return view;
    }

    private void initView() {
        initMenusHeightParams();
        initAdatperParams();
    }

    private void initMenusHeightParams() {
        ViewGroup.LayoutParams param = menu.getLayoutParams();
        param.height = dialogMenuHeight;
        menu.setLayoutParams(param);

    }

    private void initAdatperParams() {
        if (menusTextColor == 0 && menusTextFontSize == 0f) {
            adapter = new GridAdapter(menus, getContext());
        } else if (menusTextFontSize == 0f && menusTextColor != 0) {
            adapter = new GridAdapter(menus, getContext(), menusTextColor);
        } else if (menusTextColor == 0 && menusTextFontSize != 0f) {
            adapter = new GridAdapter(menus, getContext(), menusTextFontSize);
        } else if (menusTextColor != 0 && menusTextFontSize != 0f) {
            adapter = new GridAdapter(menus, getContext(), menusTextColor, menusTextFontSize);
        }
        menu.setAdapter(adapter);
        adapter.setOnDialogGridClickListener(new OnDialogGridClickListener() {
            @Override
            public void onDialogGridClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                if (gridClickListener != null) {
                    gridClickListener.onDialogGridClick(view, GridDialogItemFragment.this.pageTag, onDataPosition, onPagePosition, null);
                }
            }
        });
        adapter.setOnDialogGridLongClickListener(new OnDialogGridLongClickListener() {
            @Override
            public void onDialogGridLongClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                if (gridLongClickListener != null) {
                    gridLongClickListener.onDialogGridLongClick(view, GridDialogItemFragment.this.pageTag, onDataPosition, onPagePosition, null);
                }
            }
        });
    }

    /**
     * 设置数据源
     *
     * @param menus 数据源
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setMenuDatas(List<GridDialogBean> menus) {
        this.menus = menus;
        return this;
    }

    /**
     * 设置菜单文字颜色
     *
     * @param menusTextColor 文字颜色
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setMenuTextColor(@ColorInt int menusTextColor) {
        this.menusTextColor = menusTextColor;
        return this;
    }

    /**
     * 设置菜单文字大小
     *
     * @param menusTextFontSize 文字大小
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setMenuTextFontSize(float menusTextFontSize) {
        this.menusTextFontSize = menusTextFontSize;
        return this;
    }

    /**
     * 设置页数标志
     *
     * @param pageTag 设置页数标志
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setPageTag(int pageTag) {
        this.pageTag = pageTag;
        return this;
    }

    /**
     * 设置对话框高度
     *
     * @param dialogMenuHeight 对话框高度
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setDialogMenuHeight(int dialogMenuHeight) {
        this.dialogMenuHeight = dialogMenuHeight;
        return this;
    }

    /**
     * 设置监听器
     *
     * @param gridClickListener 点击事件
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setOnDialogGridClickListener(OnDialogGridClickListener gridClickListener) {
        this.gridClickListener = gridClickListener;
        return this;
    }

    /**
     * 设置监听器
     *
     * @param gridLongClickListener 长按事件
     * @return GridDialogItemFragment本体
     */
    public GridDialogItemFragment setOnDialogGridLongClickListener(OnDialogGridLongClickListener gridLongClickListener) {
        this.gridLongClickListener = gridLongClickListener;
        return this;
    }
}
