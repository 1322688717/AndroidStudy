package com.example.utilcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;

public class NewSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_splash);

        ActivityUtils.startHomeActivity();
        Activity activity = ActivityUtils.getTopActivity();
        LogUtils.eTag("123","333");
        LogUtils.log(1,"1","123");
        LogUtils.e("123");
    }
}