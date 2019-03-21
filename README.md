# ChainedDialogLibrary
链式对话框库，支持自定义动画

## 动态预览图
<img src="/preview/preview.gif" alt="动态预览图" width="360" height="640">

### 通用的方法

|方法|作用|参数|  
|:-|:------:|:-:|
|setShowOnBottom(boolean showBottom)|设置是否在屏幕底部显示对话框|true或者false|
|setDialogSize(int width, int height)|设置对话框的宽度和高度|均为int型的dp值|
|setBothSidesMargin(int margin)|设置对话框的左右边距|int型的dp值|
|setOutCancel(boolean outCancel)|设置是否点击对话框外部可以隐藏对话框true或者false|
|show(FragmentManager manager)|显示对话框|FragmentManager|
|setDialogAnim(DialogAnim dialogAnim)|设置对话框进出场动画|ANIM_ALPHA 透明度渐变,<br>
    ANIM_SCALE 缩放+透明度渐变,<br>
    ANIM_ROTATE 旋转+缩放+透明度渐变 ,<br>
    ANIM_LEFT_TO_LEFT 左进左出,<br>
    ANIM_LEFT_TO_TOP 左进上出,<br>
    ANIM_LEFT_TO_RIGHT 左进右出,<br>
    ANIM_LEFT_TO_BOTTOM 左进下出,<br>
    ANIM_RIGHT_TO_RIGHT 右进右出,<br>
    ANIM_RIGHT_TO_BOTTOM 右进下出,<br>
    ANIM_RIGHT_TO_LEFT 右进左出,<br>
    ANIM_RIGHT_TO_TOP 右进上出,<br>
    ANIM_TOP_TO_TOP 上进上出,<br>
    ANIM_TOP_TO_RIGHT 上进右出,<br>
    ANIM_TOP_TO_BOTTOM 上进下出,<br>
    ANIM_TOP_TO_LEFT 上进左出,<br>
    ANIM_BOTTOM_TO_BOTTOM 下进下出,<br>
    ANIM_BOTTOM_TO_LEFT 下进左出,<br>
    ANIM_BOTTOM_TO_TOP 下进上出,<br>
    ANIM_BOTTOM_TO_RIGHT 下进右出;|

## 提示对话框
<img src="/preview/prompt_dialog.png" alt="提示对话框预览图" width="360" height="640">

## 文本对话框
<img src="/preview/text_dialog.png" alt="文本对话框预览图" width="360" height="640">

## 条目对话框
<img src="/preview/item_dialog.png" alt="条目对话框预览图" width="360" height="640">

## 网格对话框
<img src="/preview/grid_dialog.png" alt="网格对话框预览图" width="360" height="640">
