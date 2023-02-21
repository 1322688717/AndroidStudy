package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author zhaichaoqun
 * time 2023/2/21
 * 线程的几种用法
 */
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThread1();
        mThread2();
        myThred3();
        mHandle1();
        mHandle2();
    }

    private void mHandle2() {
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                LogUtils.i("匿名内部类,来使用handle  mHandle2");
            }
        };
        Message message = new Message();
        message.what = 1;
        message.obj = "用handle实现更新ui";
        handler.sendMessage(message);
    }

    private void mHandle1() {
        mHandle handle = new mHandle();
        Message message = new Message();
        message.what = 1;
        message.obj = "用handle实现更新ui";
        handle.sendMessage(message);
    }


    /**
     * 简易方便
     * 不可复用
     */
    private void mThread1() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                LogUtils.i("匿名类,来启动线程");
            }
        }.start();
    }


    private void mThread2() {
     MyThread2 myThread2 = new MyThread2();
     Thread thread = new Thread(myThread2);
     thread.start();
    }


    private void myThred3() {
        MyThread mt=new MyThread();
        mt.start();
    }




    /**
     * 优点可复用
     */
    class MyThread extends Thread{
        @Override
        public void run(){
            LogUtils.i("继承Thread类,来启动线程");
        }
    }

    class MyThread2 implements Runnable{

        @Override
        public void run() {
            LogUtils.i("实现runnable接口,来启动线程");
        }
    }

    class  mHandle extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
                LogUtils.i("用handle发送消息,来启动线程  mHandle1");

        }
    }
}