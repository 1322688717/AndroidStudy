package com.example.application2022_4_25.contract;

import android.annotation.SuppressLint;

import com.example.application2022_4_25.api.APIService;
import com.example.application2022_4_25.bean.NowWeaherResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.network.NetworkApi;
import com.llw.mvplibrary.network.observer.BaseObserver;

public class MainContract {

    public static class MainPresenter extends BasePresenter<IMainView>{

        /**
         * 获取本日天气
         * @param key
         * @param location
         */
        @SuppressLint("CheckResult")
        public void getNowWeather(String key,String location){
            APIService service = NetworkApi.createService(APIService.class);
            service.getNowWeather(key, location).compose(NetworkApi.applySchedulers(new BaseObserver<NowWeaherResponse>() {
                @Override
                public void onSuccess(NowWeaherResponse response) {
                    if (getView()!=null){
                        getView().showTem(response);
                    }
                }

                @Override
                public void onFailure(Throwable e) {

                }
            }));
        }
    }

    public interface IMainView extends BaseView {
        void showTem(NowWeaherResponse response);
    }
}


