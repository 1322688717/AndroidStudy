package com.example.mvpactivity;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.example.mvplib.BaseApplication;
import com.example.mvplib.utils.ActivityManager;

public class WeatherApplication extends BaseApplication {

    /**
     * 应用实例
     */
    public static WeatherApplication weatherApplication;
    private static Context context;
    private static ActivityManager activityManager;

    private static Activity sActivity;

    public static Context getMyContext() {
        return weatherApplication == null ? null : weatherApplication.getApplicationContext();
    }

    private Handler myHandler;

    public Handler getMyHandler() {
        return myHandler;
    }

    public void setMyHandler(Handler handler) {
        myHandler = handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        activityManager = new ActivityManager();
        context = getApplicationContext();
        weatherApplication = this;


        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                sActivity = activity;

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }


    public static ActivityManager getActivityManager() {
        return activityManager;
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.darker_gray, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
