package com.example.retrofit.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.example.retrofit.utils.ActivityManager;

public class BaseApplication extends Application {
    public static Context  context;
    public static BaseApplication baseApplication;
    public static ActivityManager activityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        baseApplication = this;
        activityManager = new ActivityManager();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static BaseApplication getApplication() {
        return baseApplication;
    }

    public static Context getContext(){
        return context;
    }

    public static ActivityManager getActivityManager(){
        return activityManager;
    }
}
