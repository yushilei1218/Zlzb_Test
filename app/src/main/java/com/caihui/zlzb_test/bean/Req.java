package com.caihui.zlzb_test.bean;

import com.caihui.zlzb_test.tool.TokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther by yushilei.
 * @time 2017/5/17-17:40
 * @desc
 */

public class Req {
    public Map<String, Object> getQueryMap() {
        HashMap<String, Object> map = new HashMap<>();
        Map<String, String> tokenMap = TokenUtil.getTokenMap();
        if (tokenMap != null) {
            map.putAll(tokenMap);
        }
        return map;
    }
}
