package com.caihui.zlzb_test;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.caihui.zlzb_test.bean.JobDtoBean;
import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.caihui.zlzb_test.bean.LoginReq;
import com.caihui.zlzb_test.bean.LoginRes;
import com.caihui.zlzb_test.bean.NetData;
import com.caihui.zlzb_test.bean.Res;
import com.caihui.zlzb_test.fragment.ShowJsonFragment;
import com.caihui.zlzb_test.net.NetWork;
import com.caihui.zlzb_test.tool.TokenUtil;
import com.caihui.zlzb_test.widget.MultiState2View;
import com.jiechic.library.android.widget.MultiStateView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private MultiState2View msv;
    private TextView rtTv;
    private TextView atTv;
    private TextView userTv;

    @Override
    protected void onInitView() {
        msv = findView(R.id.activity_main_msv);
        atTv = findView(R.id.main_at);
        rtTv = findView(R.id.main_rt);
        userTv = findView(R.id.main_user);
    }

    @Override
    protected void onInitData() {
        updateAtRtView();
    }

    private void updateAtRtView() {
        String at = "access_token: " + TokenUtil.getAt();
        atTv.setText(at);
        String rt = "refresh_token: " + TokenUtil.getRt();
        rtTv.setText(rt);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 登录点击事件
     */
    public void login(View view) {
        if (msv.isLoading())
            return;

        Call<Res<LoginRes>> loginResCall = NetWork.api.login(new LoginReq("szlxy12", "test123456"));
        msv.setState(MultiStateView.ContentState.LOADING);

        loginResCall.enqueue(new Callback<Res<LoginRes>>() {
            @Override
            public void onResponse(@NonNull Call<Res<LoginRes>> call, @NonNull Response<Res<LoginRes>> response) {
                NetData data = new NetData("登录接口", response);

                msv.setState(MultiStateView.ContentState.CONTENT);
                Res<LoginRes> resRes = response.body();
                if (resRes != null) {
                    if (response.isSuccessful()) {
                        if (resRes.getCode() == 200 || resRes.getCode() == 1) {
                            toast("登录成功");
                            TokenUtil.saveToken(resRes.getData());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    updateAtRtView();
                                }
                            }, 100);
                        } else {
                            toast(resRes.getMessage());
                        }
                    } else {
                        toast(resRes.getMessage());
                    }

                } else {
                    toast("服务器数据未返回");
                }
                showJsonInFragment(data);
            }

            @Override
            public void onFailure(@NonNull Call<Res<LoginRes>> call, @NonNull Throwable t) {
                msv.setState(MultiStateView.ContentState.CONTENT);
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 将网络信息实体 展示在Fragment中
     */
    public void showJsonInFragment(NetData data) {
        if (data == null)
            return;
        ShowJsonFragment mShowJsonFragment = ShowJsonFragment.instance(data);
        mShowJsonFragment.show(getSupportFragmentManager(), "");
    }

    //已发布职位接口
    public void position(View view) {
        final JobMiniListReq req = new JobMiniListReq();
        req.setJobStyle(0);
        req.setPageIndex(1);
        req.setPageSize(20);
        req.setStatus("30");

        Call<Res<JobDtoBeanRes>> resCall = NetWork.api.getJobList(req.getQueryMap());
        msv.setState(MultiStateView.ContentState.LOADING);
        resCall.enqueue(new Callback<Res<JobDtoBeanRes>>() {
            @Override
            public void onResponse(@NonNull Call<Res<JobDtoBeanRes>> call, @NonNull Response<Res<JobDtoBeanRes>> response) {
                msv.setState(MultiStateView.ContentState.CONTENT);
                showJsonInFragment(new NetData("职位列表", response));
            }

            @Override
            public void onFailure(@NonNull Call<Res<JobDtoBeanRes>> call, @NonNull Throwable t) {
                t.printStackTrace();
                toast("网络异常");
                msv.setState(MultiStateView.ContentState.CONTENT);
            }
        });
    }
}
