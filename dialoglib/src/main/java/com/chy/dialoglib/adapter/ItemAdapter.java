package com.chy.dialoglib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chy.dialoglib.R;
import com.chy.dialoglib.listener.OnDialogItemClickListener;
import com.chy.dialoglib.listener.OnDialogItemLongClickListener;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private List<String> datas;
    private Context context;
    private int menusTextColor;
    private float menusTextFontSize;
    private OnDialogItemClickListener itemClickListener;
    private OnDialogItemLongClickListener itemLongClickListener;

    public ItemAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public ItemAdapter(List<String> datas, Context context, int menusTextColor) {
        this.datas = datas;
        this.context = context;
        this.menusTextColor = menusTextColor;
    }

    public ItemAdapter(List<String> datas, Context context, float menusTextFontSize) {
        this.datas = datas;
        this.context = context;
        this.menusTextFontSize = menusTextFontSize;
    }

    public ItemAdapter(List<String> datas, Context context, int menusTextColor, float menusTextFontSize) {
        this.datas = datas;
        this.context = context;
        this.menusTextColor = menusTextColor;
        this.menusTextFontSize = menusTextFontSize;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_string, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (menusTextColor != 0) {
            myViewHolder.item.setTextColor(menusTextColor);
        }
        if (menusTextFontSize != 0f) {
            myViewHolder.item.setTextSize(menusTextFontSize);
        }
        myViewHolder.item.setText(datas.get(i));
        myViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onDialogItemClick(v, i, null);
                }
            }
        });
        myViewHolder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onDialogItemLongClick(v, i, null);
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
     * @param itemClickListener 点击事件
     */
    public void setOnDialogItemClickListener(OnDialogItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 设置长按监听器
     *
     * @param itemLongClickListener 长按事件
     */
    public void setOnDialogItemLongClickListener(OnDialogItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }

}

