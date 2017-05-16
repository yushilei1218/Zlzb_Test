package com.caihui.zlzb_test.tool;

import android.text.TextUtils;

import com.caihui.zlzb_test.bean.LoginRes;
import com.caihui.zlzb_test.constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther by yushilei.
 * @time 2017/5/16-15:16
 * @desc
 */

public class TokenUtil {
    public static void saveToken(LoginRes res) {
        if (res == null || TextUtils.isEmpty(res.getAccessToken()) || TextUtils.isEmpty(res.getRefreshToken()))
            return;
        SpUtil.save(Constant.SP_AT, res.getAccessToken());
        SpUtil.save(Constant.SP_RT, res.getRefreshToken());
    }

    public static void clearToken() {
        SpUtil.save(Constant.SP_AT, null);
        SpUtil.save(Constant.SP_RT, null);
    }

    public static String getAt() {
        return SpUtil.getValue(Constant.SP_AT, String.class);
    }

    public static String getRt() {
        return SpUtil.getValue(Constant.SP_RT, String.class);
    }

    public static Map<String, String> getTokenMap() {
        String at = getAt();
        String rt = getRt();
        if (TextUtils.isEmpty(at) || TextUtils.isEmpty(rt))
            return null;
        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", at);
        map.put("refresh_token", rt);
        return map;


    }
}
