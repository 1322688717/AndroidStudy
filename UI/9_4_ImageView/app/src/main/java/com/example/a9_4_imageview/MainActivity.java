package com.example.a9_4_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * src 设置图片资源
     * saleType 设置图片缩放类型
     * maxheighy 最大高度
     * maxwidth 最大宽度
     * adjustviewbounds 调整view界限
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}