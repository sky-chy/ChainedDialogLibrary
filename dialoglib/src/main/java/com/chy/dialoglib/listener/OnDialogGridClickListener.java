package com.chy.dialoglib.listener;

import android.view.View;

import com.chy.dialoglib.base.BaseDialog;

public interface OnDialogGridClickListener {
    void onDialogGridClick(View view, int pageTag, int onDataPosition,int onPagePosition, BaseDialog dialog);
}
