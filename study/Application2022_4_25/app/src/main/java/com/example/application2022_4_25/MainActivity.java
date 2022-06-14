package com.example.application2022_4_25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.application2022_4_25.bean.NowWeaherResponse;
import com.example.application2022_4_25.contract.MainContract;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.mvp.MvpActivity;

public class MainActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView {

    private String key = "66d721a1d6024ca8b6c257fcab036de7";
    private String location = "101010100";

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getNowWeather(key,location);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    @Override
    public void showTem(NowWeaherResponse response) {
        Log.w("TAG",""+response.getNow().getTemp());
    }
}