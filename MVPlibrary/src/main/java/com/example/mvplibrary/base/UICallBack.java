package com.example.mvplibrary.base;


import android.os.Bundle;

/**
 * UI回调接口
 */
public interface UICallBack {

    //初始化savedInstanceState
    void initBeforeView(Bundle savedInstanceState);

    //初始化
    void initData(Bundle savedInstanceState);

    //布局
    int getLayoutId();

}
