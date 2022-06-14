package com.example.retrofit.mvp;

import android.os.Bundle;

import com.example.retrofit.base.BaseActivity;
import com.example.retrofit.base.BasePresenter;
import com.example.retrofit.base.BaseView;

public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresent;

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        mPresent = createPresent();
        mPresent.attach((BaseView) this);
    }

    protected abstract P createPresent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.detach((BaseView) this);
    }
}
