package com.chy.dialoglib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.chy.dialoglib.dialog.griddialog.GridDialogItemFragment;

import java.util.List;

public class GridPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<GridDialogItemFragment> fragments;
    private FragmentManager fm;

    public GridPagerAdapter(Context context, List<GridDialogItemFragment> fragments, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = fragments.get(position);// 获取要销毁的fragment
        fm.beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }
}
