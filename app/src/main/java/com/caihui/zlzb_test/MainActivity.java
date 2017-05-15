package com.caihui.zlzb_test;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.caihui.zlzb_test.bean.LoginReq;
import com.caihui.zlzb_test.bean.LoginRes;
import com.caihui.zlzb_test.bean.Res;
import com.caihui.zlzb_test.fragment.ShowJsonFragment;
import com.caihui.zlzb_test.net.NetWork;
import com.caihui.zlzb_test.tool.JsonUtil;
import com.caihui.zlzb_test.widget.MultiState2View;
import com.jiechic.library.android.widget.MultiStateView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private ShowJsonFragment mShowJsonFragment;

    private MultiState2View msv;

    @Override
    protected void onInitView() {
        msv = findView(R.id.activity_main_msv);
    }

    @Override
    protected void onInitData() {

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
                msv.setState(MultiStateView.ContentState.CONTENT);
                Res<LoginRes> resRes = response.body();
                if (resRes != null) {
                    if (response.isSuccessful()) {
                        if (resRes.getCode() == 200 || resRes.getCode() == 1) {
                            toast("登录成功");
                            showJsonInFragment(resRes);
                        } else {
                            toast(resRes.getMessage());
                        }
                    } else {
                        toast(resRes.getMessage());
                    }
                } else {
                    toast("服务器数据未返回");
                }
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
     * 将实体 以Json的形式展示在Fragment中
     */
    public void showJsonInFragment(Object showObj) {
        if (showObj == null)
            return;
        if (mShowJsonFragment == null)
            mShowJsonFragment = ShowJsonFragment.instance(JsonUtil.getPrettyPrintingStr(showObj));
        if (mShowJsonFragment.isAdded()) {
            mShowJsonFragment.show(JsonUtil.getPrettyPrintingStr(showObj));
        } else {
            mShowJsonFragment.show(getSupportFragmentManager(), "");
        }
    }

    public void json(View view) {
    }


}
