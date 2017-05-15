package com.caihui.zlzb_test.net;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.caihui.zlzb_test.constant.Constant;
import com.caihui.zlzb_test.tool.AppUtil;
import com.caihui.zlzb_test.tool.SpUtil;

import java.io.IOException;
import java.net.URL;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @auther by yushilei.
 * @time 2017/5/15-10:35
 * @desc
 */

public class ClientHelper {
    private ClientHelper() {
    }

    private static OkHttpClient client;

    /**
     * 获取设置拦截器的OkHttpClient
     * <p>
     * 为每次请求，增加固定的Header
     */
    public static synchronized OkHttpClient getClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //增加请求拦截
            builder.addInterceptor(new RequestInterceptor());
            //通过Builder构建OkHttpClient
            client = builder.build();
        }
        return client;
    }

    private static class RequestInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();

            URL url = request.url().url();

            String bindUrl = bindUrl(url);

            Request.Builder builder = request.newBuilder().url(bindUrl);

            builder.addHeader("deviceid", AppUtil.getDeviceID()).addHeader("fromsystem", "23");

            String at = SpUtil.getValue(Constant.SP_AT, String.class);
            String rt = SpUtil.getValue(Constant.SP_RT, String.class);

            if (!TextUtils.isEmpty(at) && !TextUtils.isEmpty(rt)) {
                builder.addHeader("at", at).addHeader("rt", rt);
            }

            return chain.proceed(builder.build());
        }

        private String bindUrl(URL url) {
            String r = System.currentTimeMillis() + "";
            String key = AppUtil.getMD5Key(url.getPath(), r);
            String urlStr = url.toString();
            return urlStr + "?r=" + r + "&key=" + key;
        }
    }
}
