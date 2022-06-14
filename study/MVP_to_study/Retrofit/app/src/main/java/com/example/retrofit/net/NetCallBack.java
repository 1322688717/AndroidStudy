package com.example.retrofit.net;

import android.util.Log;

import com.example.retrofit.base.BaseResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetCallBack<T> implements Callback<T> {


    private static String TAG = "zcq_NetCallBack";
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null && response.body() != null &&response.isSuccessful()){
            BaseResponse baseResponse = new Gson().fromJson(new Gson().toJson(response.body()),BaseResponse.class);
            if (baseResponse.getCode() ==404){
                Log.e(TAG,"code=404    data====="+baseResponse.getData().toString());
            }else if (baseResponse.getCode()==500){
                Log.e(TAG,"code=500    data====="+baseResponse.getData().toString());
            }else {
                onSuccess(call,response);
                Log.e(TAG,"网络请求成功    data====="+baseResponse.getData().toString());
            }
        }else {
            onFailed();
        }
    }

    protected abstract void onSuccess(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed();
    }

    protected abstract void onFailed();
}
