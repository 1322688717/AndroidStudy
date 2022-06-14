package com.example.timerhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


@SuppressLint("HandlerLeak")
public class MainActivity extends AppCompatActivity {
    Chronometer ch;//计时器
    Button begin;//开始计时按钮
    Button end;//结束计时按钮
    Button goon;//继续计时按钮
    Button reset;//重置计时按钮
    long recordingTime;//记录总时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ch=(Chronometer)findViewById(R.id.timer);
        begin=(Button)findViewById(R.id.begin);
        end=(Button)findViewById(R.id.end);
        goon=(Button)findViewById(R.id.goon);
        reset=(Button)findViewById(R.id.reset);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.setBase(SystemClock.elapsedRealtime()-recordingTime);//SystemClock.elapsedRealtime()获取的是系统开机到现在的时间，不能被修改
                ch.start();//开始计时
                begin.setEnabled(false);
                end.setEnabled(true);
                goon.setEnabled(false);
                reset.setEnabled(true);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.stop();//停止计时
                recordingTime=SystemClock.elapsedRealtime()-ch.getBase();//保存当前停止的时间
                begin.setEnabled(false);
                goon.setEnabled(true);
                end.setEnabled(false);
                reset.setEnabled(true);
            }
        });
        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.start();
                begin.setEnabled(false);
                end.setEnabled(true);
                goon.setEnabled(false);
                reset.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordingTime=0;//将当前时间置为0
                ch.start();
                ch.setBase(SystemClock.elapsedRealtime());
                begin.setEnabled(false);
                goon.setEnabled(false);
                end.setEnabled(true);
                reset.setEnabled(false);
            }
        });
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {//为Chronomter绑定事件监听器
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-ch.getBase()>3600*1000)//若计时超过了3600s=1 h即停止计时
                {
                    ch.stop();
                    begin.setEnabled(true);
                    end.setEnabled(false);
                    goon.setEnabled(false);
                }
            }
        });
    }

}