package com.caihui.zlzb_test.tool;

import android.widget.Toast;

import com.caihui.zlzb_test.BaseApp;
import com.caihui.zlzb_test.BuildConfig;

/**
 * @auther by yushilei.
 * @time 2017/5/17-14:04
 * @desc
 */

public class ToastUtil {
    public static void toast(String msg) {
        Toast.makeText(BaseApp.context(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastDebug(String msg) {
        if (BuildConfig.DEBUG)
            Toast.makeText(BaseApp.context(), msg, Toast.LENGTH_SHORT).show();
    }
}
