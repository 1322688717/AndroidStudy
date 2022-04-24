package com.example.a32_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    /**
     * textview常见基础属性
     * textcolor  设置颜色
     * textstyle   设置字体风格  三个可选值 Nomal无效果  bold加粗  litalic斜体
     * textsize   设置字体大小 一般是sp
     * background  设置背景
     * gravity  这只控件中间对齐方向
     * shadowColor 阴影颜色
     * shadowRadius 阴影扩展范围
     * shadowDx  阴影x轴移动
     * shadowDy  阴影y轴移动
     * singleLine 只在一行输入
     * ellipsize 在哪里省略文本    marquee走马灯形式
     * marqueeRepeatLimit 字幕动画动画重复次数
     * focusable 是否可以获得焦点
     * focusableInTouchMode 用于控制试图在触摸模式下是否可以聚焦
     * @param savedInstanceState
     */
    //@Bind(R.id.img_view)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}