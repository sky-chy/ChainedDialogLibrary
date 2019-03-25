package com.chy.dialoglib.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Utils {
    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * dp转px
     *
     * @param context  上下文
     * @param dipValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * sp转px
     *
     * @param context 上下文
     * @param sp      sp值
     * @return px值
     */
    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }
}
