package com.caihui.zlzb_test.tool;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

/**
 * @auther by yushilei.
 * @time 2017/5/15-13:09
 * @desc
 */

public class FJsonUtil {
    public static String toStr(Object obj) {
        return JSON.toJSONString(obj);
    }
}
