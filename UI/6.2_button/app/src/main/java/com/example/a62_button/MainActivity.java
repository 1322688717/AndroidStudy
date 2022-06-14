package com.example.a62_button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * drawable 引用的Drawable位图
     * state_foucesd 是否获取焦点
     * state_pressed 控件是否被按下
     * state_enabled 控件是可用
     * state_checked 控件是否被勾选
     * state_chaeckable 控件可否被勾选
     * state_window_foucesd 是否获得窗口焦点
     * state_active 控件是否处于活动状态
     * state_single 控件包含多个子控件时，确定是否显示一个子控件
     * state_first  控件包含多个子控件时,确定第一个子控件是否处于显示状态
     * state_middle 控件包含多个子控件时,确定中间一个子控件是否处于显示状态
     * state_last   控件包含多个子控件时，确定最后一个子控件是否处于显示状态
     * @param savedInstanceState
     */

    private Button mbtn_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initclic();
    }

    /**
     * 点击事件
     * 点击
     * 长按
     * 触摸
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initclic() {
        mbtn_one = findViewById(R.id.mbtn_one);
        mbtn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","onclick");
            }
        });

        mbtn_one.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("TAG","onlongclick");
                return false;
            }
        });

        mbtn_one.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG","onTouch");
                return false;
            }
        });
    }

    /**
     * 先执行父类方法再执行子类方法
     */
    private void init() {
        SonActivity sonActivity = new SonActivity();
        sonActivity.logcat();
    }
}