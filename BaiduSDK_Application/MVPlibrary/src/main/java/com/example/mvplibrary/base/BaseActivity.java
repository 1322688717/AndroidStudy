package com.example.mvplibrary.base;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvplibrary.kit.KnifeKit;
import com.example.mvplibrary.utils.BaseApplication;

import butterknife.Unbinder;

/**
 * 用于不需要请求网络接口的Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements UICallBack {

    protected Activity context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        this.context = this;
        //添加继承这个BaseActivity的Activity
        BaseApplication.getActivityManager().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        initData(savedInstanceState);


    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}