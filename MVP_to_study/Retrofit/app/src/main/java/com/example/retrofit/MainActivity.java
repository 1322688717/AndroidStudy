package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retrofit.base.BaseActivity;
import com.example.retrofit.base.BasePresenter;
import com.example.retrofit.base.UICallBack;
import com.example.retrofit.contract.AppContract;
import com.example.retrofit.mvp.MVPActivity;

public class MainActivity extends MVPActivity<AppContract.AppPersonter> implements AppContract.IAppView {


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected AppContract.AppPersonter createPresent() {
        return new AppContract.AppPersonter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


}