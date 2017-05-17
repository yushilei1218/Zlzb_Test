package com.caihui.zlzb_test.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.bean.NetData;

import java.util.List;
import java.util.Map;

/**
 * Json 文本展示对话框
 */
public class ShowJsonFragment extends DialogFragment {

    private static final String NET_DATA = "NetData";

    public ShowJsonFragment() {
        // Required empty public constructor
    }

    public static ShowJsonFragment instance(NetData data) {
        ShowJsonFragment fg = new ShowJsonFragment();
        Bundle args = new Bundle();
        args.putSerializable(NET_DATA, data);
        fg.setArguments(args);
        return fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_show_json, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        show((NetData) getArguments().getSerializable(NET_DATA));
    }

    @SuppressWarnings("unchecked")
    public void show(NetData data) {
        TextView apiNameTv = (TextView) getView().findViewById(R.id.net_data_name);
        TextView resCodeTv = (TextView) getView().findViewById(R.id.net_data_res_code);
        TextView durationTv = (TextView) getView().findViewById(R.id.net_data_timing);
        TextView urlTv = (TextView) getView().findViewById(R.id.net_data_url);
        TextView protocolTv = (TextView) getView().findViewById(R.id.net_data_protocol);
        TextView responseJsonTv = (TextView) getView().findViewById(R.id.net_data_res_json_tv);
        TextView reqJsonTv = (TextView) getView().findViewById(R.id.net_data_req_json_tv);
        TextView reqHeaderTv = (TextView) getView().findViewById(R.id.net_data_req_header);
        TextView resHeaderTv = (TextView) getView().findViewById(R.id.net_data_res_header);
        TextView methodTv = (TextView) getView().findViewById(R.id.net_data_method);
        methodTv.setText(String.valueOf("方法: " + data.getNetMethod()));
        reqHeaderTv.setText(shapeHeaderStr("请求头: ", data.getRequestHeadersMap()));
        resHeaderTv.setText(shapeHeaderStr("响应头: ", data.getResponseHeadersMap()));
        apiNameTv.setText(String.valueOf("名称: " + data.getName()));
        resCodeTv.setText(String.valueOf("响应码: " + data.getResponseCode()));
        durationTv.setText(String.valueOf("耗时: " + data.getDuration() + "毫秒"));
        urlTv.setText(String.valueOf("Url: " + data.getNetUrl()));
        String isHttpsStr = data.isHttps() ? "是" : "否";
        protocolTv.setText(String.valueOf("协议: " + data.getNetProtocol() + "   Https:" + isHttpsStr));
        String reqStr = TextUtils.isEmpty(data.getRequestJson()) ? "空" : data.getRequestJson();
        reqJsonTv.setText(String.valueOf("请求体 :\n" + reqStr));
        String resStr = TextUtils.isEmpty(data.getResponseJson()) ? "空" : data.getResponseJson();
        responseJsonTv.setText(String.valueOf("响应体 :\n" + resStr));

    }

    private String shapeHeaderStr(String s, Map<String, List<String>> map) {
        if (map == null || map.size() == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(s).append("\n");
        for (String key : map.keySet()) {
            List<String> values = map.get(key);
            sb.append(key).append(" : ");
            for (String value : values) {
                sb.append(value).append(";");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window!=null){
            WindowManager.LayoutParams attributes = window.getAttributes();
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            attributes.width= (int) (dm.widthPixels*0.98f);
            window.setAttributes(attributes);
        }
    }
}
