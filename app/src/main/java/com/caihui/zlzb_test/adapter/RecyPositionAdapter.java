package com.caihui.zlzb_test.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.caihui.zlzb_test.tool.JsonUtil;

/**
 * @auther by yushilei.
 * @time 2017/5/17-15:52
 * @desc
 */

public class RecyPositionAdapter extends SimpleRecyAdapter<JobMiniListReq, RecyPositionAdapter.PVH> {

    public RecyPositionAdapter(Context context) {
        super(context);
    }

    @Override
    public PVH getNewHolder(View view) {
        return new PVH(view);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_position;
    }

    @Override
    public void bind(PVH holder, int position) {
        JobMiniListReq req = getData().get(position);
        holder.reqParamsTv.setText(JsonUtil.getPrettyPrintingStr(req));
    }

    public static final class PVH extends SimpleViewHolder {

        final TextView reqParamsTv;

        public PVH(View itemView) {
            super(itemView);
            reqParamsTv = findV(R.id.position_item_params);
        }
    }
}
