package com.caihui.zlzb_test.tool;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.caihui.zlzb_test.R;

/**
 * @auther by yushilei.
 * @time 2017/5/17-11:14
 * @desc
 */

public class ToolBarInit {
    public static void toolBarInit(AppCompatActivity activity, String title, View.OnClickListener listener) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.tool_bar);
        activity.setSupportActionBar(toolbar);
        activity.setTitle(title);
        toolbar.setSubtitle("测试");
        toolbar.setNavigationIcon(R.mipmap.arrow_back);
        toolbar.setNavigationOnClickListener(listener);
    }
}
