package com.chy.dialoglib.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.chy.dialoglib.R;
import com.chy.dialoglib.base.BaseDialog;

public class ProgressDialog extends BaseDialog {
    //控件
    private LinearLayout lay;

    public static ProgressDialog newInstance() {
        Bundle bundle = new Bundle();
        ProgressDialog dialog = new ProgressDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_progress;
    }

    @Override
    public void operationInterface(View view, BaseDialog baseDialog) {
        lay = view.findViewById(R.id.lay);
    }
}
