package com.caihui.zlzb_test.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caihui.zlzb_test.tool.CollectionUtil;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/5/17-15:53
 * @desc
 */

public abstract class SimpleRecyAdapter<T, E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    private List<T> data = new LinkedList<>();
    private WeakReference<Context> weak;

    public void addAll(List<T> tList) {
        if (!CollectionUtil.isEmpty(tList)) {
            this.data.addAll(tList);
            notifyItemRangeInserted(this.data.size(), this.data.size() + tList.size());
        }
    }

    public void replaceAll(List<T> tList) {
        this.data.clear();
        if (!CollectionUtil.isEmpty(tList)) {
            this.data.addAll(tList);
        }
        notifyDataSetChanged();
    }

    public SimpleRecyAdapter(Context context) {
        weak = new WeakReference<Context>(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(weak.get()).inflate(getItemLayoutId(), parent, false);
        E newHolder = getNewHolder(view);
        view.setTag(newHolder);
        return newHolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bind(((E) holder), position);
    }

    public abstract E getNewHolder(View view);

    @LayoutRes
    public abstract int getItemLayoutId();

    public abstract void bind(E holder, int position);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<T> getData() {
        return data;
    }

    public Context getContext() {
        return weak.get();
    }
}
