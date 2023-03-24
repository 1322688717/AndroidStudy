package com.java8888.java9999.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.java8888.java9999.R;
import com.java8888.java9999.utils.CacheUtil;
import com.java8888.java9999.utils.SharePerfenceUtils;
import com.java8888.java9999.view.PrivacyProtocolDialog;

public class SplashActivity extends AppCompatActivity implements PrivacyProtocolDialog.ResponseCallBack {

    private FrameLayout mLayoutMain;
    private FrameLayout mLayoutPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        //  清缓存
        CacheUtil.clearAllCache(this);
        //  判断是否第一次启动
        boolean isFirst = SharePerfenceUtils.getInstance(this).getFirst();
        Boolean switchHasBackground = Boolean.valueOf(getResources().getString(R.string.switchHasBackground));
        if (switchHasBackground) {
            mLayoutMain.setBackgroundResource(R.mipmap.screen);
            mLayoutPrivacy.setVisibility(View.GONE);
        } else {
            mLayoutMain.setBackgroundResource(R.color.white);
            mLayoutPrivacy.setVisibility(View.VISIBLE);
        }
        Boolean showPrivacy = Boolean.valueOf(getResources().getString(R.string.showPrivacy));
        if (isFirst && showPrivacy) {
            new PrivacyProtocolDialog(this, R.style.protocolDialogStyle, this).show();
        } else {
            mLayoutMain.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLayoutMain.setBackgroundResource(R.color.default_bg);
                    mLayoutPrivacy.setVisibility(View.GONE);
                    //  跳转main
                    skipMain();
                }
            }, 1000);
        }
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mLayoutMain = findViewById(R.id.layout_main);
        mLayoutPrivacy = findViewById(R.id.layout_privacy);
    }


    @Override
    public void agree() {
        //  直接跳转回去,之后不再弹出
        SharePerfenceUtils.getInstance(this).setFirst(false);
        mLayoutMain.setBackgroundResource(R.color.default_bg);
        mLayoutPrivacy.setVisibility(View.GONE);
        skipMain();
    }

    @Override
    public void disAgree() {
        //  取消直接跳转回去,之后不再弹出
        finish();
    }

    public void skipMain() {
        //  取消直接跳转回去,之后不再弹出
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}