package com.chy.dialoglib.base;

import com.chy.dialoglib.R;

public enum DialogAnim {

    ANIM_ALPHA(R.style.anim_alpha),
    ANIM_SCALE(R.style.anim_scale),
    ANIM_ROTATE(R.style.anim_rotate),

    ANIM_LEFT_TO_LEFT(R.style.anim_left_to_left),
    ANIM_LEFT_TO_TOP(R.style.anim_left_to_top),
    ANIM_LEFT_TO_RIGHT(R.style.anim_left_to_right),
    ANIM_LEFT_TO_BOTTOM(R.style.anim_left_to_bottom),

    ANIM_RIGHT_TO_RIGHT(R.style.anim_right_to_right),
    ANIM_RIGHT_TO_BOTTOM(R.style.anim_right_to_bottom),
    ANIM_RIGHT_TO_LEFT(R.style.anim_right_to_left),
    ANIM_RIGHT_TO_TOP(R.style.anim_right_to_top),

    ANIM_TOP_TO_TOP(R.style.anim_top_to_top),
    ANIM_TOP_TO_RIGHT(R.style.anim_top_to_right),
    ANIM_TOP_TO_BOTTOM(R.style.anim_top_to_bottom),
    ANIM_TOP_TO_LEFT(R.style.anim_top_to_left),

    ANIM_BOTTOM_TO_BOTTOM(R.style.anim_bottom_to_bottom),
    ANIM_BOTTOM_TO_LEFT(R.style.anim_bottom_to_left),
    ANIM_BOTTOM_TO_TOP(R.style.anim_bottom_to_top),
    ANIM_BOTTOM_TO_RIGHT(R.style.anim_bottom_to_right);

    private int anim;

    DialogAnim(int anim) {
        this.anim = anim;
    }

    public int getDialogType() {
        return anim;
    }
}
