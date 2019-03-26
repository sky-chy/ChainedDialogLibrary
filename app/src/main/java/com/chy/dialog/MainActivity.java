package com.chy.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.chy.dialoglib.base.BaseDialog;
import com.chy.dialoglib.base.DialogAnim;
import com.chy.dialoglib.bean.GridDialogBean;
import com.chy.dialoglib.dialog.ItemDialog;
import com.chy.dialoglib.dialog.LoadingDialog;
import com.chy.dialoglib.dialog.ProgressDialog;
import com.chy.dialoglib.dialog.PromptDialog;
import com.chy.dialoglib.dialog.TextDialog;
import com.chy.dialoglib.dialog.griddialog.GridDialog;
import com.chy.dialoglib.listener.OnDialogClickListener;
import com.chy.dialoglib.listener.OnDialogGridClickListener;
import com.chy.dialoglib.listener.OnDialogGridLongClickListener;
import com.chy.dialoglib.listener.OnDialogItemClickListener;
import com.chy.dialoglib.listener.OnDialogItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            //提示对话框
            case R.id.prompt_dialog:
                PromptDialog.newInstance()
                        .setButtonText("好的")
                        .setButtonTextColor(Color.BLUE)
                        .setContentText("测试通知")
                        .setOnDialogClickListener(new OnDialogClickListener() {
                            @Override
                            public void onDialogClick(View view, BaseDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .show(getSupportFragmentManager());
                break;
            //文本框
            case R.id.text_dialog:
                TextDialog.newInstance()
                        .setDialogType(TextDialog.DIALOG_TYPE.INFORMATION)
                        .setTitleText("通知信息")
                        .setContentText("Bootstrap 是一套用于 HTML、CSS 和 JS 开发的开源工具集。利用我们提供的 Sass 变量和大量 mixin、响应式栅格系统、可扩展的预制组件、基于 jQuery 的强大的插件系统，能够快速为你的想法开发出原型或者构建整个 app 。")
                        .setContentTextGravity(Gravity.LEFT)
                        .setOnCancelClickListener(new OnDialogClickListener() {
                            @Override
                            public void onDialogClick(View view, BaseDialog dialog) {
                                toast("点击了取消按钮");
                                dialog.dismiss();
                            }
                        })
                        .setOnConfirmClickListener(new OnDialogClickListener() {
                            @Override
                            public void onDialogClick(View view, BaseDialog dialog) {
                                toast("点击了确定按钮");
                                dialog.dismiss();
                            }
                        })
                        .show(getSupportFragmentManager());
                break;
            //条目对话框
            case R.id.item_dialog:
                ItemDialog.newInstance()
                        .setMenuDatas(new String[]{"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8", "菜单9", "菜单10"})
                        .setOnMenuItemClickListener(new OnDialogItemClickListener() {
                            @Override
                            public void onDialogItemClick(View view, int position, BaseDialog dialog) {
                                switch (position) {
                                    case -1:
                                        toast("点击了取消按钮");
                                        break;
                                    default:
                                        toast("点击了菜单" + (position + 1));
                                        break;
                                }
                                dialog.dismiss();

                            }
                        })
                        .setOnMenuItemLongClickListener(new OnDialogItemLongClickListener() {
                            @Override
                            public void onDialogItemLongClick(View view, int position, BaseDialog dialog) {
                                switch (position) {
                                    case -1:
                                        toast("长按了取消按钮");
                                        break;
                                    default:
                                        toast("长按了菜单" + (position + 1));
                                        break;
                                }
                            }
                        })
                        .setShowOnBottom(true)
                        .setDialogAnim(DialogAnim.ANIM_BOTTOM_TO_BOTTOM)
                        .setBothSidesMargin(10)
                        .show(getSupportFragmentManager());
                break;
            //网格对话框;
            case R.id.grid_dialog:
                final List<GridDialogBean> beans = new ArrayList<>();
                GridDialogBean bean;
                for (int i = 0; i < 100; i++) {
                    bean = new GridDialogBean();
                    bean.text = "测试菜单" + i;
                    bean.icon = R.mipmap.ic_launcher;
                    beans.add(bean);
                }
                GridDialog.newInstance()
                        .setMenuDatas(beans)
                        .setOnDialogGridClickListener(new OnDialogGridClickListener() {
                            @Override
                            public void onDialogGridClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                                dialog.dismiss();
                                toast("点击了第" + pageTag + "页第" + (onPagePosition + 1) + "个菜单：" + beans.get(onDataPosition).text);
                            }
                        })
                        .setOnDialogGridLongClickListener(new OnDialogGridLongClickListener() {
                            @Override
                            public void onDialogGridLongClick(View view, int pageTag, int onDataPosition, int onPagePosition, BaseDialog dialog) {
                                toast("长按了第" + pageTag + "页第" + (onPagePosition + 1) + "个菜单：" + beans.get(onDataPosition).text);
                            }
                        })
                        .setBothSidesMargin(0)
                        .setShowOnBottom(true)
                        .show(getSupportFragmentManager());
                break;
            case R.id.progress_dialog:
                final ProgressDialog dialog = ProgressDialog.newInstance()
                        .setDelayMillis(300)
                        .setMax(100);
                dialog.show(getSupportFragmentManager());
                final int[] pro_num = {0};
                final Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        pro_num[0]=pro_num[0]+10;
                        dialog.setProgress(pro_num[0]);
                        if (pro_num[0] != 100)
                            handler.postDelayed(this, 500);
                    }
                };
                handler.postDelayed(r, 500);

                break;
            case R.id.loading_dialog:
                LoadingDialog.newInstance()
                        .show(getSupportFragmentManager());
                break;
        }

    }

    private void toast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }
}