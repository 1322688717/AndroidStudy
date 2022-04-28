package com.example.baidusdk_application.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class LocationUtil {

    private static final String TAG = "zcq_LocationUtil";
    //定位器
    public LocationClient mLocationClient;


    private   static LocationUtil instance;
    public static LocationUtil getInstance(){
        if (instance == null){
            instance = new LocationUtil();
        }
        return instance;
    }

    private MyLocationListener myListener = new MyLocationListener();

    public interface ILocationListener {
        void Err(String msg);
        void SuccessLocation(String city,String locationid,LocationClient locationClient);
    }
    ILocationListener mLocationListener;

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

            String locationid = longitude+","+latitude;
            Log.w(TAG,"locationid======="+locationid);
            Log.w(TAG, "mLocationClient ========= " + mLocationClient);
            mLocationListener.SuccessLocation(city,locationid,mLocationClient);
        }
    }


    //开始定位
    public void startLocation(Context context, ILocationListener listener) {
        mLocationListener = listener;

        //声明LocationClient类
        LocationClient.setAgreePrivacy(true);
        try {
            mLocationClient = new LocationClient(context);
        } catch (Exception e) {
            mLocationListener.Err(e.getLocalizedMessage());
            e.printStackTrace();
            return;
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
