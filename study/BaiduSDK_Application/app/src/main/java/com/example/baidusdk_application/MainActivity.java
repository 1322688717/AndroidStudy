package com.example.baidusdk_application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.LocationClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.baidusdk_application.adapter.FutureWeatherAdapter;
import com.example.baidusdk_application.bean.BiYingImgResponse;
import com.example.baidusdk_application.bean.FutureWeatherResponse;
import com.example.baidusdk_application.bean.LifeIndexResponse;
import com.example.baidusdk_application.bean.TodayResponse;
import com.example.baidusdk_application.contract.WeatherContract;
import com.example.baidusdk_application.utils.Analysis;
import com.example.baidusdk_application.utils.LocationUtil;
import com.example.baidusdk_application.utils.ToastUtils;
import com.example.mvplibrary.mvp.MvpActivity;
import com.example.mvplibrary.utils.StatusBarUtil;
import com.example.mvplibrary.view.WhiteWinds;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends MvpActivity<WeatherContract.WeatherPresenter> implements WeatherContract.IWeatherView,  Analysis.ICityListener, LocationUtil.ILocationListener {

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_top_temperature)
    TextView tvTopTemperature;
    @BindView(R.id.low_temperature)
    TextView lowTemperature;
    @BindView(R.id.rc_futureWeather)
    RecyclerView rcFutureWeather;
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.tv_life_index)
    TextView tvLifeIndex;
    @BindView(R.id.tv_windsDir)
    TextView tvWindsDir;
    @BindView(R.id.tv_windsScale)
    TextView tvWindsScale;
    @BindView(R.id.ww_big)
    WhiteWinds wwBig;
    @BindView(R.id.ww_small)
    WhiteWinds wwSmall;
    @BindView(R.id.img_city)
    ImageView imgCity;
    @BindView(R.id.bg)
    LinearLayout bg;
    @BindView(R.id.smrf)
    SmartRefreshLayout smrf;
    @BindView(R.id.img_background)
    ImageView imgBackground;


    private static final String TAG = "MainActivity";
    private RxPermissions rxPermissions;//??????????????????
//    private List<String> list;//???????????????
//    private List<CityResponse> provinceList;//???????????????
//    private List<CityResponse.CityBean> citylist;//???????????????
//    private List<CityResponse.CityBean.AreaBean> arealist;//???/???????????????


    private List<FutureWeatherResponse.DailyDTO> mList = new ArrayList<>();
    private FutureWeatherAdapter mAdapter;
    LocationClient mLocationClient;



    @Override
    public void initData(Bundle savedInstanceState) {

        StatusBarUtil.transparencyBar(context);
        rxPermissions = new RxPermissions(this);//???????????????????????????????????????????????????
        initrc(); //?????????RecycleView
        permissionVersion();//????????????
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected WeatherContract.WeatherPresenter createPresent() {
        return new WeatherContract.WeatherPresenter();
    }


    private void permissionVersion() {
        if (Build.VERSION.SDK_INT >= 23) {//6.0???6.0??????
            //??????????????????
            permissionsRequest();

        } else {//6.0??????
            //?????????????????????AndroidManifest.xml????????????????????????????????????granted  ??????????????????
            ToastUtils.showLongToast(this, "???????????????Android6.0???????????????????????????????????????");
        }
    }

    @SuppressLint("CheckResult")
    private void permissionsRequest() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {//????????????
                        //???????????????????????????
                        showLoadingDialog();
                        LocationUtil.getInstance().startLocation(context, this);

                    } else {//????????????
                        ToastUtils.showShortToast(this, "???????????????");
                    }
                });
    }

    @Override
    public void Err(String msg) {
        ToastUtils.showLongToast(this,msg);
    }

    /**
     * ?????????????????????
     * @param city  ????????????????????????
     * @param locationid  ?????????????????????
     * @param mLocationClient   ??????LocationClient??????
     */
    @Override
    public void SuccessLocation(String city, String locationid,LocationClient mLocationClient) {
        tvCity.setText(city);
        mPresent.getFutureWeather(context,locationid);
        mPresent.getLifeIndex(context,locationid);
        mPresent.getLifeIndex(context,locationid);
        mPresent.getbiying(context);
        this.mLocationClient = mLocationClient;
    }




    /**
     * ????????????????????????
     * @param response
     */
    @Override
    public void getFutureWeather(retrofit2.Response<FutureWeatherResponse> response) {
        mLocationClient.stop();
        if (response.body().getCode().equals("200")){
            List<FutureWeatherResponse.DailyDTO> data = response.body().getDaily();
            mList.clear();
            tvTopTemperature.setText(response.body().getDaily().get(0).getTempMax()+"???");
            lowTemperature.setText(response.body().getDaily().get(0).getTempMin()+"???");
            mList.addAll(data);
            mAdapter.notifyDataSetChanged();
        }else {
            ToastUtils.showShortToast(context, "????????????????????????");
        }
    }

    /**
     * ??????????????????
     * @param response
     */
    @Override
    public void getTodayWeatherResult(retrofit2.Response<TodayResponse> response) {
        mLocationClient.stop();
        dismissLoadingDialog();
        if (response.body().getNow().getTemp() != null) {
            smrf.finishRefresh();
            tvTemperature.setText(response.body().getNow().getTemp() + "???");
            tvWeather.setText(response.body().getNow().getText());
            tvWindsDir.setText("??????   "+response.body().getNow().getWindDir());
            tvWindsScale.setText("??????   "+response.body().getNow().getWindScale());
            wwBig.startRotate();
            wwSmall.startRotate();

        } else {
            ToastUtils.showLongToast(context, response.body().getCode());
        }
    }

    /**
     * ????????????
     * @param response
     */
    @Override
    public void getLifeIndex(Response<LifeIndexResponse> response) {
        mLocationClient.stop();
        Log.w(TAG,"????????????====="+response.body().getDaily().get(0));
        if (response.body().getDaily().get(0)!=null){
            tvLifeIndex.setText(response.body().getDaily().get(0).getText());
            tvCar.setText(response.body().getDaily().get(1).getText());
        }else {
            ToastUtils.showLongToast(context, response.body().getCode());
        }
    }

    /**
     * ??????????????????
     * @param response
     */
    @Override
    public void getbiying(Response<BiYingImgResponse> response) {
        dismissLoadingDialog();
        if (response.body().getImages() != null){
            String imgurl = "https://cn.bing.com"+response.body().getImages().get(0).getUrl();
            Glide.with(context)
                    .asBitmap()
                    .load(imgurl)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(context.getResources(),resource);
                            //bg.setBackground(drawable);
                            imgBackground.setBackground(drawable);
                        }
                    });
        }else {
            ToastUtils.showLongToast(context,"????????????");
        }
    }


    /**
     * ?????????rc
     */
    private void initrc(){
        mList = new ArrayList<>();
        mAdapter = new FutureWeatherAdapter(R.layout.rc_future_weather,mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rcFutureWeather.setLayoutManager(manager);
        rcFutureWeather.setAdapter(mAdapter);
    }

    /**
     * ????????????????????????
     */
    @OnClick(R.id.img_city)
    public void citySelect(){
        Analysis.getInstance().showCityWindow(MainActivity.this,context,this);
    }



    //????????????
    @Override
    public void onSelected(String name,String location) {
        mPresent.todayWeather(context,location);//????????????
        mPresent.getFutureWeather(context, location);//????????????
        mPresent.getLifeIndex(context, location);//????????????
        tvCity.setText(name);
    }



    @Override
    public void onDestroy() {
        wwSmall.stop();
        wwBig.stop();
        super.onDestroy();
    }

    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        smrf.finishRefresh();
        ToastUtils.showShortToast(context,"????????????");//?????????context????????????????????????????????????this
    }
}