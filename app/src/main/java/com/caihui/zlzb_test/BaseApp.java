package com.caihui.zlzb_test;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * @auther by yushilei.
 * @time 2017/5/15-10:49
 * @desc
 */

public class BaseApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        res = getResources();
    }

    public static Context context() {
        return context;
    }

    public static Resources getRes() {
        return res;
    }
}
