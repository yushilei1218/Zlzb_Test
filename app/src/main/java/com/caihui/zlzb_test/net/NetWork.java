package com.caihui.zlzb_test.net;

import com.caihui.zlzb_test.bean.JobDtoBean;
import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.LoginReq;
import com.caihui.zlzb_test.bean.LoginRes;
import com.caihui.zlzb_test.bean.Res;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


/**
 * @auther by yushilei.
 * @time 2017/5/15-10:30
 * @desc
 */

public class NetWork {
    private static final String BASE_URL = "https://api-qa.zhaopin.com";
    public static Api api;

    private NetWork() {
    }

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .client(ClientHelper.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(Api.class);
    }

    public interface Api {
        @POST("/passport/login")
        Call<Res<LoginRes>> login(@Body LoginReq user);

        @GET("/ihrapi/job/list")
        Call<Res<JobDtoBeanRes>> getJobList(@QueryMap Map<String, Object> map);
    }
}
