package com.example.mvplibrary.base;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvplibrary.R;
import com.example.mvplibrary.kit.KnifeKit;
import com.example.mvplibrary.utils.BaseApplication;

import butterknife.Unbinder;

/**
 * 用于不需要请求网络接口的Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements UICallBack {

    protected Activity context;
    private Unbinder unbinder;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        this.context = this;
        //添加继承这个BaseActivity的Activity
        Log.w("TAG","this=========="+this);
        BaseApplication.getActivityManager().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            //unbinder = KnifeKit.bind(this);
        }
        initData(savedInstanceState);


    }

    //弹窗出现
    public void showLoadingDialog(){
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }
    //弹窗消失
    public void dismissLoadingDialog(){
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}