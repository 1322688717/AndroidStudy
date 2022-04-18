package com.example.mvplib.base;


    /**
     * UI回调接口
     */
    public interface UiCallBack {

        //初始化savedInstanceState
        void initBeforeView(Bundle savedInstanceState);

        //初始化
        void initData(Bundle savedInstanceState);

        //布局
        int getLayoutId();


}
