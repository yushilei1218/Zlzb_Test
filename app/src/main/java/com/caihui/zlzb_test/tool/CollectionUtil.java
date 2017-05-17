package com.caihui.zlzb_test.tool;

import java.util.Collection;
import java.util.Map;

/**
 * @auther by yushilei.
 * @time 2017/5/17-16:12
 * @desc
 */

public class CollectionUtil {
    public static boolean isEmpty(Collection data) {
        return data == null || data.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }
}
