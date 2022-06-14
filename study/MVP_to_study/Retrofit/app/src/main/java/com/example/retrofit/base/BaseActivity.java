package com.example.retrofit.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit.utils.KnifeKit;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements UICallBack{

    private Activity activity;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        this.activity = this;

        BaseApplication.getActivityManager().addActivity(this);
        if(getLayout()>0){
            setContentView(getLayout());
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
