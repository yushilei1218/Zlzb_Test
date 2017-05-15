package com.caihui.zlzb_test.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.caihui.zlzb_test.BaseApp;

/**
 * @auther by yushilei.
 * @time 2017/5/15-10:47
 * @desc
 */

public class SpUtil {
    private static final String SP_NAME = "zlzp";

    private static SharedPreferences getSp() {
        return BaseApp.context().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static void save(String name, Object value) {
        SharedPreferences.Editor edit = getSp().edit();
        String simpleName = value.getClass().getSimpleName();

        switch (simpleName) {
            case "Integer":
                edit.putInt(name, (Integer) value);
                break;
            case "Boolean":
                edit.putBoolean(name, (Boolean) value);
                break;
            case "String":
                edit.putString(name, (String) value);
                break;
            case "Float":
                edit.putFloat(name, (Float) value);
                break;
            case "Long":
                edit.putLong(name, (Long) value);
                break;
        }
        edit.apply();

    }

    public static <T> T getValue(String name, Class<T> clas) {
        SharedPreferences sp = getSp();
        Object value = null;
        switch (clas.getSimpleName()) {
            case "Integer":
                value = sp.getInt(name, -1);
                break;
            case "Boolean":
                value = sp.getBoolean(name, false);
                break;
            case "String":
                value = sp.getString(name, null);
                break;
            case "Float":
                value = sp.getFloat(name, -1f);
                break;
            case "Long":
                value = sp.getLong(name, -1L);
                break;
        }
        return (T) value;
    }
}
