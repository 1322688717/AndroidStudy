package com.example.application_4_22;

import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.mvplibrary.net.NetCallBack;
import com.example.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

public class Contract {
    interface IView extends BaseView {
        void showText(Response<Bean> response);
    }
    public static class Presonter extends BasePresenter<Contract.IView>{

        public void getTemperture(String location,String key){
            API apiService = ServiceGenerator.createService(API.class,0);
            apiService.getBean(location,key).enqueue(new NetCallBack<Bean>() {
                @Override
                public void onSuccess(Call<Bean> call, Response<Bean> response) {
                    getView().showText(response);
                }

                @Override
                public void onFailed() {

                }
            });
        }
    }
}
