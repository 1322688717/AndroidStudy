package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;

/**
 * @author zhaichaoqun
 * 关于动态广播的基本使用方法
 * 运行程序后打开关闭屏幕  会受到广播日志
 */

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myBroadcastReceiver;
    MyBroadcastReceiver2 myBroadcastReceiver2;
    TextView tv_onclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册屏幕打开广播
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(myBroadcastReceiver,intentFilter);

        //注册屏幕关闭广播
        myBroadcastReceiver2 = new MyBroadcastReceiver2();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(myBroadcastReceiver2,intentFilter2);
    }


    /**
     * 注销广播
     * 防止内存泄漏
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(myBroadcastReceiver2);
        myBroadcastReceiver = null;
        tv_onclick = null;
        Log.i("zcq","onDestroy");
    }
}