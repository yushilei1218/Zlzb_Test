package com.caihui.zlzb_test.tool;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther by yushilei.
 * @time 2017/5/15-13:22
 * @desc
 */

public class AppUtil {
    public static String deviceID;

    /**
     * 获取用户设备唯一标识(imei).
     *
     * @return
     */
    public static String getDeviceID() {
        deviceID = "70931BDD-CBC3-4D3F-B862-2E9C9F0820C0";

        return deviceID;
    }

    public static Map<String, String> getMap(String url) {
        HashMap<String, String> map = new HashMap<>();
        String r = System.currentTimeMillis() + "";

        String key = getMD5Key(url, r);
        map.put("r", r);
        map.put("key", key);
        return map;
    }

    static final String[] APPKEY = new String[]{
            "tet!DSSGE433",
            "23HFHDfdsfs",
            "sfssdf#$DS03",
            "KGHGsa44@*(",
            "34Snbd3SSFL)(",
            "fh409gdM%MS",
            "879ELK(Biop",
            "4axbu0T)QI29",
            "ri^f906kpIOEG",
            "Pq#O19453iQYN"
    };

    public static String getMD5Key(String path, String r) {
        //验证是否是13位数字
        if (!Validator.match(r, "^\\d{13}$")) {
            return "";
        }

        String key = APPKEY[Integer.valueOf(r.substring(7, 8))];

        String params = r + path + AppUtil.getDeviceID() + key;

        Log.i("AppUtil", "[" + params + "]");

        String md5Key = md5(params);
        Log.i("AppUtil", "[" + md5Key + "]");

        md5Key = md5Key.substring(0, 15) + md5Key.substring(20);
        //小写
//        md5Key = md5Key.toLowerCase();
        return md5Key;
    }

    public static String md5(String var0) {
        if (var0 == null) {
            return null;
        } else {
            try {
                byte[] var1 = var0.getBytes();
                MessageDigest var2 = MessageDigest.getInstance("MD5");
                var2.reset();
                var2.update(var1);
                byte[] var3 = var2.digest();
                StringBuffer var4 = new StringBuffer();

                for (int var5 = 0; var5 < var3.length; ++var5) {
                    var4.append(String.format("%02X", new Object[]{Byte.valueOf(var3[var5])}));
                }

                return var4.toString();
            } catch (Exception var6) {
                return var0.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
            }
        }
    }
}
