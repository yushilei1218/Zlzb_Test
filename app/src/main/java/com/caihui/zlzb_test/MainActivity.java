package com.caihui.zlzb_test;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.caihui.zlzb_test.bean.LoginReq;
import com.caihui.zlzb_test.bean.LoginRes;
import com.caihui.zlzb_test.bean.NetData;
import com.caihui.zlzb_test.bean.Res;
import com.caihui.zlzb_test.fragment.ShowJsonFragment;
import com.caihui.zlzb_test.net.NetWork;
import com.caihui.zlzb_test.tool.JsonUtil;
import com.caihui.zlzb_test.tool.TokenUtil;
import com.caihui.zlzb_test.tool.ToolBarInit;
import com.caihui.zlzb_test.ui.PositionListTestActivity;
import com.caihui.zlzb_test.ui.PtrActivity;
import com.caihui.zlzb_test.widget.MultiState2View;
import com.caihui.zlzb_test.xml.TestCases;
import com.caihui.zlzb_test.xml.XmlUtil;
import com.jiechic.library.android.widget.MultiStateView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private MultiState2View msv;
    private TextView rtTv;
    private TextView atTv;

    @Override
    protected void onInitView() {
        ToolBarInit.toolBarInit(this, "首页 -登录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        msv = findView(R.id.activity_main_msv);
        atTv = findView(R.id.main_at);
        rtTv = findView(R.id.main_rt);

        setOnClick((View) findView(R.id.main_login));
        setOnClick((View) findView(R.id.main_position));
        setOnClick((View) findView(R.id.main_jump_position));
        setOnClick((View) findView(R.id.main_jump_ptr));
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
     * 将网络信息实体 展示在Fragment中
     */
    public void showJsonInFragment(NetData data) {
        if (data == null)
            return;
        ShowJsonFragment mShowJsonFragment = ShowJsonFragment.instance(data);
        mShowJsonFragment.show(getSupportFragmentManager(), "");
    }

    /**
     * 登录点击网络请求
     */
    public void login() {
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
     * 已发布职位网络请求
     */
    public void position() {
        TestCases testCases = XmlUtil.fromXml(getResources().openRawResource(R.raw.test_cases), TestCases.class);

        JobMiniListReq req = testCases.getData().get(0);
        String s = JsonUtil.toStr(req);
        Log.i(TAG(), s);
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

    @Override
    public void onClick(View v) {
        if (msv.isLoading())
            return;
        switch (v.getId()) {
            case R.id.main_login://处理点击 登录网络接口 事件
                login();
                break;
            case R.id.main_position://处理已发布职位 网络
                position();
                break;
            case R.id.main_jump_position://
                startActivity(new Intent(this, PositionListTestActivity.class));
                break;
            case R.id.main_jump_ptr:
                startActivity(new Intent(this, PtrActivity.class));
                break;
        }
    }
}
