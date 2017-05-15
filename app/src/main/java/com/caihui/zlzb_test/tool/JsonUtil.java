package com.caihui.zlzb_test.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @auther by yushilei.
 * @time 2017/5/15-12:11
 * @desc
 */

public class JsonUtil {

    public static String toStr(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T getObj(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }

    public static String getPrettyPrintingStr(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }
}
