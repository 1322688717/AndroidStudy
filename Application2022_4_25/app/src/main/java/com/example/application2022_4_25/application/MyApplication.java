package com.example.application2022_4_25.application;

import android.app.Application;

import com.llw.mvplibrary.network.NetworkApi;

public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
