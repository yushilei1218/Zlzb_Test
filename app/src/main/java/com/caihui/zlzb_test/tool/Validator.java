package com.caihui.zlzb_test.tool;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther by yushilei.
 * @time 2017/5/15-13:50
 * @desc
 */

public class Validator {
    public static boolean notEmpty(String value) {
        return value != null && !value.equals("");
    }

    public static boolean match(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(value);
        boolean match = matcher.matches();
        Log.e("Validator", value + (match ? "匹配" : "不匹配") + pattern);
        return matcher.matches();
    }
}
