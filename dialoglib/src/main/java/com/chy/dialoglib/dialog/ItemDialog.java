package com.chy.dialoglib.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chy.dialoglib.R;
import com.chy.dialoglib.adapter.ItemAdapter;
import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.listener.OnDialogItemClickListener;
import com.chy.dialoglib.listener.OnDialogItemLongClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemDialog extends BaseDialog {
    //控件
    private RecyclerView menus;
    private TextView cancel;
    //数据源
    private List<String> itemDatas = new ArrayList<>();
    //适配器
    private ItemAdapter adapter;
    //菜单文字
    private int menusTextColor = 0;
    private float menusTextFontSize = 0f;
    //菜单背景
    private int menuBackgroundColor;
    private Drawable menuBackgroundDrawable;
    private int menuBackgroundDrawableResource;
    //取消按钮
    private String cancelButtonText;
    private int cancelButtonTextColor;
    private float cancelButtonTextFontSize;
    //取消按钮背景
    private int cancelButtonBackgroundColor;
    private Drawable cancelButtonBackgroundDrawable;
    private int cancelButtonBackgroundDrawableResource;
    //监听器
    private OnDialogItemClickListener itemClickListener;
    private OnDialogItemLongClickListener itemLongClickListener;

    public static ItemDialog newInstance() {
        Bundle bundle = new Bundle();
        ItemDialog dialog = new ItemDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_item;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        menus = view.findViewById(R.id.menus);
        cancel = view.findViewById(R.id.cancel);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setItemParams();
        setCancelButtonParams();
        setListenerParams();
        setMenuBackgroundParams();
        setCancelButtonBackgroundParams();

    }

    @SuppressLint("NewApi")
    private void setItemParams() {
        if (menusTextColor == 0 && menusTextFontSize == 0f) {
            adapter = new ItemAdapter(itemDatas, getContext());
        } else if (menusTextFontSize == 0f && menusTextColor != 0) {
            adapter = new ItemAdapter(itemDatas, getContext(), menusTextColor);
        } else if (menusTextColor == 0 && menusTextFontSize != 0f) {
            adapter = new ItemAdapter(itemDatas, getContext(), menusTextFontSize);
        } else if (menusTextColor != 0 && menusTextFontSize != 0f) {
            adapter = new ItemAdapter(itemDatas, getContext(), menusTextColor, menusTextFontSize);
        }
        menus.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            menus.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        else
            menus.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    private void setCancelButtonParams() {
        if (!TextUtils.isEmpty(cancelButtonText)) {
            cancel.setText(cancelButtonText);
        }
        if (cancelButtonTextColor != 0) {
            cancel.setTextColor(cancelButtonTextColor);
        }
        if (cancelButtonTextFontSize != 0f) {
            cancel.setTextSize(cancelButtonTextFontSize);
        }
    }

    private void setListenerParams() {
        adapter.setOnDialogItemClickListener(new OnDialogItemClickListener() {
            @Override
            public void onDialogItemClick(View view, int position, BaseDialog dialog) {
                if (itemClickListener != null) {
                    itemClickListener.onDialogItemClick(view, position, ItemDialog.this);
                }
            }
        });
        adapter.setOnDialogItemLongClickListener(new OnDialogItemLongClickListener() {
            @Override
            public void onDialogItemLongClick(View view, int position, BaseDialog dialog) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onDialogItemLongClick(view, position, ItemDialog.this);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onDialogItemClick(v, -1, ItemDialog.this);
                }
            }
        });
        cancel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onDialogItemLongClick(v, -1, ItemDialog.this);
                    return true;
                }
                return false;
            }
        });
    }

    private void setMenuBackgroundParams() {
        if (menuBackgroundColor != 0) {
            menus.setBackgroundColor(menuBackgroundColor);
        } else if (menuBackgroundDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                menus.setBackground(menuBackgroundDrawable);
            } else {
                throw new RuntimeException("本机SDK版本低于16或Android版本低于4.2");
            }
        } else if (menuBackgroundDrawableResource != 0) {
            menus.setBackgroundResource(menuBackgroundDrawableResource);
        }
    }

    private void setCancelButtonBackgroundParams() {
        if (cancelButtonBackgroundColor != 0) {
            cancel.setBackgroundColor(cancelButtonBackgroundColor);
        }
        if (cancelButtonBackgroundDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                cancel.setBackground(cancelButtonBackgroundDrawable);
            } else {
                throw new RuntimeException("本机SDK版本低于16或Android版本低于4.2");
            }
        }
        if (cancelButtonBackgroundDrawableResource != 0) {
            cancel.setBackgroundResource(cancelButtonBackgroundDrawableResource);
        }
    }

    /**
     * 设置数据源
     *
     * @param stringDatas 字符数组数据源
     * @return 对话框本体
     */
    public ItemDialog setMenuDatas(String[] stringDatas) {
        if (stringDatas.length <= 10) {
            itemDatas = Arrays.asList(stringDatas);
        } else {
            itemDatas = Arrays.asList(stringDatas).subList(0, 10);
        }
        return this;
    }

    /**
     * 设置数据源
     *
     * @param listDatas List数据源
     * @return 对话框本体
     */
    public ItemDialog setMenuDatas(ArrayList<String> listDatas) {
        if (listDatas.size() <= 10) {
            itemDatas = listDatas;
        } else {
            itemDatas = listDatas.subList(0, 10);
        }
        return this;
    }

    /**
     * 设置菜单文字颜色
     *
     * @param menusTextColor 文字颜色
     * @return 对话框本体
     */
    public ItemDialog setMenuTextColor(@ColorInt int menusTextColor) {
        this.menusTextColor = menusTextColor;
        return this;
    }

    /**
     * 设置菜单文字大小
     *
     * @param menusTextFontSize 文字大小
     * @return 对话框本体
     */
    public ItemDialog setMenuTextFontSize(float menusTextFontSize) {
        this.menusTextFontSize = menusTextFontSize;
        return this;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancelButtonText 取消按钮文字
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
        return this;
    }

    /**
     * 设置取消按钮字体颜色
     *
     * @param cancelButtonTextColor 字体颜色
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonTextColor(@ColorInt int cancelButtonTextColor) {
        this.cancelButtonTextColor = cancelButtonTextColor;
        return this;
    }

    /**
     * 设置取消按钮字体大小
     *
     * @param cancelButtonTextFontSize 字体大小
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonTextFontSize(float cancelButtonTextFontSize) {
        this.cancelButtonTextFontSize = cancelButtonTextFontSize;
        return this;
    }

    /**
     * 设置菜单区背景颜色
     *
     * @param menuBackgroundColor Color值
     * @return 对话框本体
     */
    public ItemDialog setMenuBackgroundColor(@ColorInt int menuBackgroundColor) {
        this.menuBackgroundColor = menuBackgroundColor;
        return this;
    }

    /**
     * 设置菜单区背景图片
     *
     * @param menuBackgroundDrawable Drawable值
     * @return 对话框本体
     */
    public ItemDialog setMenuBackground(Drawable menuBackgroundDrawable) {
        this.menuBackgroundDrawable = menuBackgroundDrawable;
        return this;
    }

    /**
     * 设置菜单区背景图片
     *
     * @param menuBackgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public ItemDialog setMenuBackgroundRes(@DrawableRes int menuBackgroundDrawableResource) {
        this.menuBackgroundDrawableResource = menuBackgroundDrawableResource;
        return this;
    }

    /**
     * 设置取消按钮背景背景颜色
     *
     * @param cancelButtonBackgroundColor Color值
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonBackgroundColor(@ColorInt int cancelButtonBackgroundColor) {
        this.cancelButtonBackgroundColor = cancelButtonBackgroundColor;
        return this;
    }

    /**
     * 设置取消按钮背景背景图片
     *
     * @param cancelButtonBackgroundDrawable Drawable值
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonBackground(Drawable cancelButtonBackgroundDrawable) {
        this.cancelButtonBackgroundDrawable = cancelButtonBackgroundDrawable;
        return this;
    }

    /**
     * 设置取消按钮背景背景图片
     *
     * @param cancelButtonBackgroundDrawableResource drawable ID值
     * @return 对话框本体
     */
    public ItemDialog setCancelButtonBackgroundRes(@DrawableRes int cancelButtonBackgroundDrawableResource) {
        this.cancelButtonBackgroundDrawableResource = cancelButtonBackgroundDrawableResource;
        return this;
    }

    /**
     * 设置MenuItem的监听器
     *
     * @param itemClickListener 点击事件
     * @return 对话框本体
     */
    public ItemDialog setOnMenuItemClickListener(OnDialogItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }

    /**
     * 设置MenuItem的监听器
     *
     * @param itemLongClickListener 长按事件
     * @return 对话框本体
     */
    public ItemDialog setOnMenuItemLongClickListener(OnDialogItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
        return this;
    }
}
