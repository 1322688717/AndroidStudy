package com.example.mvplibrary.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class BaseApplication extends Application {
    private static ActivityManager activityManager;
    private  static BaseApplication application;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        activityManager=new ActivityManager();
        context = getApplicationContext();
        application=this;

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



    public static ActivityManager getActivityManager() {
        return activityManager;
    }

    //内容提供器
    public static Context getContext(){
        return context;
    }

    public static BaseApplication getApplication() {
        return application;
    }
}
