package com.caihui.zlzb_test.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.caihui.zlzb_test.bean.NetData;
import com.caihui.zlzb_test.bean.NetJop;
import com.caihui.zlzb_test.bean.Res;
import com.caihui.zlzb_test.fragment.ShowJsonFragment;
import com.caihui.zlzb_test.tool.JsonUtil;
import com.caihui.zlzb_test.tool.ToastUtil;

/**
 * @auther by yushilei.
 * @time 2017/5/17-15:52
 * @desc
 */

public class RecyPositionAdapter extends SimpleRecyAdapter<NetJop<JobMiniListReq, Res<JobDtoBeanRes>>, RecyPositionAdapter.PVH> implements View.OnClickListener {

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
        NetJop<JobMiniListReq, Res<JobDtoBeanRes>> netJop = getData().get(position);
        holder.reqParamsTv.setText(JsonUtil.getPrettyPrintingStr(netJop.getReq()));
        holder.resParamsTv.setText(JsonUtil.getPrettyPrintingStr(netJop.getRes()));
        holder.pos = position;
        holder.itemView.setOnClickListener(this);
    }

    public void updatePosition(int pos, Res<JobDtoBeanRes> res) {
        getData().get(pos).setRes(res);
        notifyItemChanged(pos);
    }

    @Override
    public void onClick(View v) {
        NetJop<JobMiniListReq, Res<JobDtoBeanRes>> netJop = getData().get(((PVH) v.getTag()).pos);
        if (netJop.getRes() != null) {
            NetData data = new NetData();
            data.setRequestJson(JsonUtil.getPrettyPrintingStr(netJop.getReq()));
            data.setResponseJson(JsonUtil.getPrettyPrintingStr(netJop.getRes()));
            ShowJsonFragment fg = ShowJsonFragment.instance(data);

            fg.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "");
        } else {
            ToastUtil.toast("无响应Json");
        }
    }

    public static final class PVH extends SimpleViewHolder {

        final TextView reqParamsTv;
        final TextView resParamsTv;
        int pos;

        PVH(View itemView) {
            super(itemView);
            reqParamsTv = findV(R.id.position_item_req);
            resParamsTv = findV(R.id.position_item_res);
        }
    }
}
