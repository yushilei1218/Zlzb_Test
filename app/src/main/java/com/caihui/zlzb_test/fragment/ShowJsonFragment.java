package com.caihui.zlzb_test.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caihui.zlzb_test.R;

/**
 * Json 文本展示对话框
 */
public class ShowJsonFragment extends BottomSheetDialogFragment {

    private static final String JSON_TEXT = "JSON";

    public ShowJsonFragment() {
        // Required empty public constructor
    }

    public static ShowJsonFragment instance(String json) {
        ShowJsonFragment fg = new ShowJsonFragment();
        Bundle args = new Bundle();
        args.putString(JSON_TEXT, json);
        fg.setArguments(args);
        return fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_json, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        show(getArguments().getString(JSON_TEXT));
    }

    public void show(CharSequence msg) {
        TextView tv = (TextView) getView().findViewById(R.id.fg_show_json_tv);
        tv.setText(msg);
    }
}
