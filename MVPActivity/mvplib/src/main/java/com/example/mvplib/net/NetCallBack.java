package com.example.mvplib.net;


/**
 * 网络请求回调
 * @param <T>
 */
public abstract class NetCallBack<T> implements Callback<T> {//这里实现了retrofit2.Callback

    //访问成功回调
    @Override
    public void onResponse(Call<T> call, Response<T> response) {//数据返回
        if (response != null && response.body() != null && response.isSuccessful()) {
            BaseResponse baseResponse = new Gson().fromJson(new Gson().toJson(response.body()), BaseResponse.class);
            if (baseResponse.getCode() == 404) {//404
                Log.e("Warn",baseResponse.getData().toString());
            }else if(baseResponse.getCode() == 500) {//500
                Log.e("Warn",baseResponse.getData().toString());
            } else {//无异常则返回数据
                onSuccess(call, response);
                Log.e("Warn","其他情况");
            }
        } else {
            onFailed();
        }
    }

    //访问失败回调
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed();
    }

    //数据返回
    public abstract void onSuccess(Call<T> call, Response<T> response);

    //失败异常
    public abstract void onFailed();


}