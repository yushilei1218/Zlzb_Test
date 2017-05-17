package com.caihui.zlzb_test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/5/17-16:26
 * @desc
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public SimpleViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T> T findV(int rid) {
        return (T) itemView.findViewById(rid);
    }
}
