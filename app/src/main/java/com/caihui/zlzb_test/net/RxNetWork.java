package com.caihui.zlzb_test.net;

/**
 * @auther by yushilei.
 * @time 2017/5/17-14:16
 * @desc
 */

import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.Res;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 利用RxJava 网络封装
 */
public class RxNetWork {
    private static final String BASE_URL = "https://api-qa.zhaopin.com";
    public static Api api;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .client(ClientHelper.getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(Api.class);
    }

    public interface Api {
        @GET("/ihrapi/job/list")
        Observable<Res<JobDtoBeanRes>> getJobList(@QueryMap Map<String, Object> map);
    }
}
