package com.example.baidusdk_application.contract;


import android.content.Context;

import com.example.baidusdk_application.api.ApiService;
import com.example.baidusdk_application.bean.BiYingImgResponse;
import com.example.baidusdk_application.bean.FutureWeatherResponse;
import com.example.baidusdk_application.bean.LifeIndexResponse;
import com.example.baidusdk_application.bean.TodayResponse;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.mvplibrary.net.NetCallBack;
import com.example.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 天气订阅器
 *
 */
public class WeatherContract {

    /**
     * personer 层
     */
    public static class WeatherPresenter extends BasePresenter<IWeatherView> {
        /**
         * 当日天气
         *
         * @param context
         * @param location 区/县
         */
        public void todayWeather(final Context context, String locationid) {
            //得到构建之后的网络请求服务，这里的地址已经拼接完成，只差一个location了
            ApiService service = ServiceGenerator.createService(ApiService.class,0);
            //设置请求回调  NetCallBack是重写请求回调
            service.getTodayWeather(locationid).enqueue(new NetCallBack<TodayResponse>() {
                //成功回调
                @Override
                public void onSuccess(Call<TodayResponse> call, Response<TodayResponse> response) {
                    if (getView() != null) {//当视图不会空时返回请求数据

                        getView().getTodayWeatherResult(response);
                    }
                }

                //失败回调
                @Override
                public void onFailed() {
                    if (getView() != null) {//当视图不会空时获取错误信息
                        getView().getDataFailed();
                    }
                }
            });
        }

        /**
         * 未来三天天气
         * @param context
         * @param location
         */
        public void getFutureWeather(final Context context, String location) {
            ApiService service = ServiceGenerator.createService(ApiService.class,0);
            service.getFutureWeather(location).enqueue(new NetCallBack<FutureWeatherResponse>() {
                @Override
                public void onSuccess(Call<FutureWeatherResponse> call, Response<FutureWeatherResponse> response) {
                    if (getView() != null) {//当视图不会空时返回请求数据
                        getView().getFutureWeather(response);
                    }
                }

                @Override
                public void onFailed() {
                    getView().getDataFailed();
                }
            });
        }

        /**
         * 获取生活指数
         * @param context
         * @param locaion
         */
        public void getLifeIndex(final Context context,String locaion){
            ApiService service = ServiceGenerator.createService(ApiService.class,0);
            service.getLifeIndex(locaion).enqueue(new NetCallBack<LifeIndexResponse>() {
                @Override
                public void onSuccess(Call<LifeIndexResponse> call, Response<LifeIndexResponse> response) {
                    if (getView()!=null){
                        getView().getLifeIndex(response);
                    }
                }

                @Override
                public void onFailed() {
                    getView().getDataFailed();
                }
            });
        }

        public void getbiying(Context context){
            ApiService service = ServiceGenerator.createService(ApiService.class,1);
            service.biying().enqueue(new Callback<BiYingImgResponse>() {
                @Override
                public void onResponse(Call<BiYingImgResponse> call, Response<BiYingImgResponse> response) {
                    if (getView()!= null){
                        getView().getbiying(response);
                    }
                }

                @Override
                public void onFailure(Call<BiYingImgResponse> call, Throwable t) {

                    if (getView()!= null){
                        getView().getDataFailed();
                }
                }
            });
        }




    }

    /**
     * view 层
     */
    public interface IWeatherView extends BaseView {
        //将数据放入实体
        void getTodayWeatherResult(Response<TodayResponse> response);

        //获取未来天气的方法
        void getFutureWeather(Response<FutureWeatherResponse> response);

        //获取生活指数
        void getLifeIndex(Response<LifeIndexResponse> response);

        void getbiying(Response<BiYingImgResponse> response);

        //错误返回
        void getDataFailed();
    }

}


