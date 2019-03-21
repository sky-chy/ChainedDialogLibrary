package com.chy.dialoglib.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chy.dialoglib.R;
import com.chy.dialoglib.bean.GridDialogBean;
import com.chy.dialoglib.listener.OnDialogGridClickListener;
import com.chy.dialoglib.listener.OnDialogGridLongClickListener;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {
    private List<GridDialogBean> datas;
    private Context context;
    private int menusTextColor;
    private float menusTextFontSize;
    private OnDialogGridClickListener gridClickListener;
    private OnDialogGridLongClickListener gridLongClickListener;

    public GridAdapter(List<GridDialogBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public GridAdapter(List<GridDialogBean> datas, Context context, int menusTextColor) {
        this.datas = datas;
        this.context = context;
        this.menusTextColor = menusTextColor;
    }

    public GridAdapter(List<GridDialogBean> datas, Context context, float menusTextFontSize) {
        this.datas = datas;
        this.context = context;
        this.menusTextFontSize = menusTextFontSize;
    }

    public GridAdapter(List<GridDialogBean> datas, Context context, int menusTextColor, float menusTextFontSize) {
        this.datas = datas;
        this.context = context;
        this.menusTextColor = menusTextColor;
        this.menusTextFontSize = menusTextFontSize;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        myViewHolder.icon.setImageResource(datas.get(i).icon);
        myViewHolder.text.setText(datas.get(i).text);
        if (menusTextColor != 0) {
            myViewHolder.text.setTextColor(menusTextColor);
        }
        if (menusTextFontSize != 0f) {
            myViewHolder.text.setTextSize(menusTextFontSize);
        }
        myViewHolder.grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gridClickListener != null) {
                    gridClickListener.onDialogGridClick(v, 0, 0, i, null);
                }
            }
        });
        myViewHolder.grid.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (gridLongClickListener != null) {
                    gridLongClickListener.onDialogGridLongClick(v, 0, 0, i, null);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 设置监听器
     *
     * @param gridClickListener 点击事件
     */
    public void setOnDialogGridClickListener(OnDialogGridClickListener gridClickListener) {
        this.gridClickListener = gridClickListener;
    }

    /**
     * 设置长按监听器
     *
     * @param gridLongClickListener 长按事件
     */
    public void setOnDialogGridLongClickListener(OnDialogGridLongClickListener gridLongClickListener) {
        this.gridLongClickListener = gridLongClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        LinearLayout grid;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            text = itemView.findViewById(R.id.text);
            grid = itemView.findViewById(R.id.grid);
        }
    }
}
