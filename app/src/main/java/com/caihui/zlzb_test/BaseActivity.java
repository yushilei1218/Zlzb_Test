package com.caihui.zlzb_test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.caihui.zlzb_test.tool.ToastUtil;

/**
 * @auther by yushilei.
 * @time 2017/5/15-12:47
 * @desc
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    SparseArray<View> mViews = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onInitView();
        onInitData();
    }


    protected abstract void onInitView();

    protected abstract void onInitData();

    @LayoutRes
    protected abstract int getLayoutId();

    protected void setOnClick(View view) {
        view.setOnClickListener(this);
    }

    @SuppressWarnings("unchecked")
    public <T> T findView(@IdRes int rid) {
        View view = mViews.get(rid);
        if (view == null) {
            view = findViewById(rid);
            mViews.put(rid, view);
        }
        return (T) view;
    }

    public String TAG() {
        return this.getClass().getSimpleName();
    }

    public void toast(String msg) {
        ToastUtil.toast(msg);
    }

    public void toastDebug(String msg) {
        ToastUtil.toastDebug(msg);
    }
}
