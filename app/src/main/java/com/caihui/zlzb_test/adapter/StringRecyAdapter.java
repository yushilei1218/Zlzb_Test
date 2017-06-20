package com.caihui.zlzb_test.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.widget.PtrClockView;

/**
 * @auther by yushilei.
 * @time 2017/6/20-11:45
 * @desc
 */

public class StringRecyAdapter extends SimpleRecyAdapter<String, StringRecyAdapter.VH> implements View.OnClickListener {
    public StringRecyAdapter(Context context) {
        super(context);
    }

    @Override
    public VH getNewHolder(View view) {
        return new VH(view);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_ptr;
    }

    @Override
    public void bind(VH holder, int position) {
        holder.tv.setText(getData().get(position));
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public static final class VH extends SimpleViewHolder {
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = findV(R.id.item_tv);

        }
    }
}
