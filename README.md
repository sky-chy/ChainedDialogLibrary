# ChainedDialogLibrary
链式对话框库，支持自定义动画
```gradle
implementation 'com.chy.chydialoglib:chydialoglib:1.1.1'
```

### 通用的方法

|方法|作用|参数/用法|  
|:----|:------:|:-|
|setShowOnBottom(boolean showBottom)|设置是否在屏幕底部显示对话框|true在屏幕底部显示<br>false反之|
|setDialogSize(int width, int height)|设置对话框的宽度和高度|均为int型的dp值|
|setBothSidesMargin(int margin)|设置对话框的左右边距|int型的dp值|
|setOutCancel(boolean outCancel)|设置是否点击对话框外部可以隐藏对话框|true点击对话框外部隐藏对话框<br>false反之|
|show(FragmentManager manager)|显示对话框|FragmentManager|
|setDialogAnim(DialogAnim dialogAnim)|设置对话框进出场动画|ANIM_ALPHA,<br>ANIM_SCALE,<br>ANIM_ROTATE,<br>ANIM_LEFT_TO_LEFT,<br>ANIM_LEFT_TO_TOP,<br>ANIM_LEFT_TO_RIGHT,<br>ANIM_LEFT_TO_BOTTOM,<br>ANIM_RIGHT_TO_RIGHT,<br>ANIM_RIGHT_TO_BOTTOM,<br>ANIM_RIGHT_TO_LEFT,<br>ANIM_RIGHT_TO_TOP,<br> ANIM_TOP_TO_TOP,<br>ANIM_TOP_TO_RIGHT,<br>ANIM_TOP_TO_BOTTOM,<br>ANIM_TOP_TO_LEFT,<br>ANIM_BOTTOM_TO_BOTTOM,<br>ANIM_BOTTOM_TO_LEFT,<br>ANIM_BOTTOM_TO_TOP,<br>ANIM_BOTTOM_TO_RIGHT;|
|setCustomizeAnim(@StyleRes int animStyle)|设置自定义动画的方法|R.style.xxx<br>xxx为你定义在styles文件中的“android:windowEnterAnimation”<br>和“android:windowExitAnimation”动画|

## 提示对话框
<img src="/preview/prompt_dialog.png" alt="提示对话框预览图" width="360" height="640">

## 文本对话框
<img src="/preview/text_dialog.png" alt="文本对话框预览图" width="360" height="640">

## 条目对话框
<img src="/preview/item_dialog.png" alt="条目对话框预览图" width="360" height="640">

## 网格对话框
<img src="/preview/grid_dialog.png" alt="网格对话框预览图" width="360" height="640">
