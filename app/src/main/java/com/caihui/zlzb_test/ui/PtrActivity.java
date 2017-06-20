package com.caihui.zlzb_test.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.caihui.zlzb_test.BaseActivity;
import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.adapter.StringRecyAdapter;
import com.caihui.zlzb_test.widget.PtrCustomHeader;

import java.util.LinkedList;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PtrActivity extends BaseActivity {
    PtrFrameLayout mPtr;
    RecyclerView mRecycler;

    @Override
    protected void onInitView() {
        mPtr = findView(R.id.activity_ptr);
        mRecycler = findView(R.id.recycler);
    }

    @Override
    protected void onInitData() {
        StringRecyAdapter adapter = new StringRecyAdapter(this);
        final LinkedList<String> tList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            tList.add("item+" + i);
        }
        adapter.addAll(tList);
        mRecycler.setAdapter(adapter);

        PtrCustomHeader header = new PtrCustomHeader(this);
        mPtr.setHeaderView(header);
        mPtr.addPtrUIHandler(header);

        mPtr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                RecyclerView.LayoutManager manager = mRecycler.getLayoutManager();
                if (manager.getChildCount() == 0)
                    return true;
                int position = manager.getPosition(manager.getChildAt(0));
                return position == 0 && manager.getChildAt(0).getTop() >= 0;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtr.refreshComplete();
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ptr;
    }

    @Override
    public void onClick(View v) {

    }
}
