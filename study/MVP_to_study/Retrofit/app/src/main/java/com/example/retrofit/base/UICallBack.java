package com.example.retrofit.base;

import android.os.Bundle;

public interface UICallBack {
    void initBeforeView(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    int getLayout();
}
