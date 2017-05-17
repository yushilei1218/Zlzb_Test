package com.caihui.zlzb_test.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.caihui.zlzb_test.BaseActivity;
import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.adapter.RecyPositionAdapter;
import com.caihui.zlzb_test.bean.JobDtoBeanRes;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.caihui.zlzb_test.bean.NetJop;
import com.caihui.zlzb_test.bean.Res;
import com.caihui.zlzb_test.net.RxNetWork;
import com.caihui.zlzb_test.tool.CollectionUtil;
import com.caihui.zlzb_test.tool.JsonUtil;
import com.caihui.zlzb_test.tool.ToolBarInit;
import com.caihui.zlzb_test.xml.TestCases;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 已发布职位列表测试页面
 */
public class PositionListTestActivity extends BaseActivity {

    private ProgressBar mProgressBar;

    private RecyPositionAdapter recyPositionAdapter;

    private View mStartV;
    /**
     * 本次网络请求的数据集合
     */
    private List<JobMiniListReq> mReqs;

    @Override
    protected void onInitView() {

        ToolBarInit.toolBarInit(this, "职位列表网络请求", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mProgressBar = findView(R.id.activity_position_list_test_progress);
        mStartV = findView(R.id.activity_position_list_test_run);
        RecyclerView mRecycler = findView(R.id.activity_position_list_test_recy);

        recyPositionAdapter = new RecyPositionAdapter(this);
        mRecycler.setAdapter(recyPositionAdapter);
        setOnClick(mStartV);
        mStartV.setSelected(true);
    }

    @Override
    protected void onInitData() {
        //初始化时，加载XML中 case数据
        Observable.just("开始")
                .map(new Func1<String, List<JobMiniListReq>>() {
                    @Override
                    public List<JobMiniListReq> call(String s) {
                        return TestCases.fromRaw().getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<JobMiniListReq>>() {

                    @Override
                    public void call(List<JobMiniListReq> jobMiniListReqs) {
                        mReqs = jobMiniListReqs;

                        List<NetJop<JobMiniListReq, Res<JobDtoBeanRes>>> data = new LinkedList<>();
                        for (JobMiniListReq req : jobMiniListReqs) {
                            data.add(new NetJop<JobMiniListReq, Res<JobDtoBeanRes>>(req));
                        }
                        //展示到列表上面
                        recyPositionAdapter.replaceAll(data);
                        mStartV.setSelected(false);
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_position_list_test;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_position_list_test_run:
                loadCaseAndRun();
                break;
        }
    }

    private int pos = 0;

    /**
     * 使用列表中的数据依次进行网络请求
     */
    private void loadCaseAndRun() {
        if (mStartV.isSelected() || CollectionUtil.isEmpty(mReqs)) {
            return;
        }
        mStartV.setSelected(true);
        //将集合请求数据，依次发送 并记录请求结果
        Observable.from(mReqs)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<JobMiniListReq, Observable<Res<JobDtoBeanRes>>>() {
                    @Override
                    public Observable<Res<JobDtoBeanRes>> call(JobMiniListReq jobMiniListReq) {

                        return RxNetWork.api.getJobList(jobMiniListReq.getQueryMap());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Res<JobDtoBeanRes>>() {
                    @Override
                    public void call(Res<JobDtoBeanRes> jobDtoBeanResRes) {
                        //依次获取到网络数据
                        recyPositionAdapter.updatePosition(pos++, jobDtoBeanResRes);
                        Log.i(TAG(), JsonUtil.toStr(jobDtoBeanResRes));
                        mProgressBar.setVisibility(View.GONE);
                        mStartV.setSelected(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        toast("网络异常");
                        mProgressBar.setVisibility(View.GONE);
                        mStartV.setSelected(false);
                    }
                });


    }
}
