package com.example.lifecycle;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


public class MyObserver implements LifecycleObserver {

    private static final String TAG = "tag";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE(){
        Log.e(TAG, "host invoke ON_CREATE, thread: " + Thread.currentThread().getName());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START(){
        Log.e(TAG, "host invoke ON_START, thread: " + Thread.currentThread().getName());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME(){
        Log.e(TAG, "host invoke ON_RESUME, thread: " + Thread.currentThread().getName());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP(){
        Log.e(TAG, "host invoke ON_STOP, thread: " + Thread.currentThread().getName());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE(){
        Log.e(TAG, "host invoke ON_PAUSE, thread: " + Thread.currentThread().getName());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY(){
        Log.e(TAG, "host invoke ON_DESTROY, thread: " + Thread.currentThread().getName());
    }



}
