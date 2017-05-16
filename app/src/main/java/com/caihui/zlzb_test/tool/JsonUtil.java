package com.caihui.zlzb_test.tool;

import android.widget.Toast;

import com.caihui.zlzb_test.BaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @auther by yushilei.
 * @time 2017/5/15-12:11
 * @desc
 */

public class JsonUtil {

    public static String toStr(Object obj) {
        String json = null;
        try {
            json = new Gson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(BaseApp.context(), "Json序列化失败", Toast.LENGTH_SHORT).show();
        }
        return json;
    }

    public static <T> T getObj(String json, Class<T> tClass) {
        T obj = null;
        try {
            obj = new Gson().fromJson(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(BaseApp.context(), "Json反序列化失败", Toast.LENGTH_SHORT).show();
        }
        return obj;
    }

    public static String getPrettyPrintingStr(Object obj) {
        if (obj == null)
            return null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

    public static String getPrettyPrintingStr(String json) {
        if (json == null)
            return null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement parse = new JsonParser().parse(json);
        return gson.toJson(parse);
    }
}
