package com.example.baidusdk_application;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.baidusdk_application.bean.TodayResponse;
import com.example.baidusdk_application.contract.WeatherContract;
import com.example.baidusdk_application.utils.ToastUtils;
import com.example.mvplibrary.mvp.MvpActivity;
import com.example.mvplibrary.utils.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends MvpActivity<WeatherContract.WeatherPresenter> implements WeatherContract.IWeatherView {

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

    private static final String TAG = "MainActivity";
    private RxPermissions rxPermissions;//权限请求框架
    //定位器
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    @Override
    public void initData(Bundle savedInstanceState) {

        StatusBarUtil.transparencyBar(context);
        rxPermissions = new RxPermissions(this);//实例化这个权限请求框架，否则会报错
        permissionVersion();//权限判断
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected WeatherContract.WeatherPresenter createPresent() {
        return new WeatherContract.WeatherPresenter();
    }

    /**
     * 定位结果返回
     */
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();//161  表示网络定位结果
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            Log.w(TAG, "address ========= " + addr);
            Log.w(TAG, "latitude ========= " + latitude);
            Log.w(TAG, "longitude ========= " + longitude);
            //getTodayWeather(longitude, latitude);
            tvCity.setText(city);
            String locationid = longitude+","+latitude;
            Log.w(TAG,"locationid======="+locationid);
            mPresent.todayWeather(context,locationid);
        }
    }

    @Override
    public void getTodayWeatherResult(retrofit2.Response<TodayResponse> response) {
        mLocationClient.stop();
        Log.w(TAG,"temaerture======"+response.body().getNow().getTemp());
        if (response.body().getNow().getTemp()!=null){
            tvTemperature.setText(response.body().getNow().getTemp());
            tvWeather.setText(response.body().getNow().getText());
        }else {
            ToastUtils.showLongToast(context,response.body().getCode());
        }
    }


    /**
     * 获取本地实时天气
     *
     * @param longitude 经度
     * @param latitude  纬度
     */
    private void getTodayWeather(double longitude, double latitude) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://devapi.qweather.com/v7/weather/now?key=66d721a1d6024ca8b6c257fcab036de7&location=" + longitude + "," + latitude)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtils.showLongToast(MainActivity.this, "网络请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.w(TAG, "code===========" + response.code());
                Log.w(TAG, "body===========" + response.body().string());
            }
        });

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context,"网络异常");//这里的context是框架中封装好的，等同于this
    }

    private void permissionVersion() {
        if (Build.VERSION.SDK_INT >= 23) {//6.0或6.0以上
            //动态权限申请
            permissionsRequest();

        } else {//6.0以下
            //发现只要权限在AndroidManifest.xml中注册过，均会认为该权限granted  提示一下即可
            ToastUtils.showLongToast(this, "你的版本在Android6.0以下，不需要动态申请权限。");
        }
    }

    private void permissionsRequest() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {//申请成功
                        //得到权限后开始定位
                        startLocation();
                    } else {//申请失败
                        ToastUtils.showShortToast(this, "权限未开启");
                    }
                });
    }

    //开始定位
    private void startLocation() {
        //声明LocationClient类
        LocationClient.setAgreePrivacy(true);
        try {
            mLocationClient = new LocationClient(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setIsNeedAddress(true);
        //可选，设置是否需要最新版本的地址信息。默认不需要，即参数为false
        option.setNeedNewVersionRgc(true);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.setLocOption(option);
        //启动定位
        mLocationClient.start();

    }
}