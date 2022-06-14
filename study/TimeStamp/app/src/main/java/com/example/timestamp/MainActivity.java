package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 方式1
         * 注:默认使用的是北京时区,无论是UTC时间戳还是北京时间戳,格式化后都是北京时间。
         */
        Long unixtimestamp = 1641537092L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date(unixtimestamp * 1000));
        System.out.println(format1);//2022-01-07 14:31:32

    }
}