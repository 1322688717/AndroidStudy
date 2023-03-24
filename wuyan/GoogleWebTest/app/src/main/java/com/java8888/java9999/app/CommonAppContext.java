package com.java8888.java9999.app;

import android.app.Application;
import android.content.Context;

import com.tencent.mmkv.MMKV;

public class CommonAppContext extends Application {
    public static CommonAppContext sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        String rootDir = MMKV.initialize(this);
    }
    public static Context getAppContext() {
        return sInstance;
    }
}
