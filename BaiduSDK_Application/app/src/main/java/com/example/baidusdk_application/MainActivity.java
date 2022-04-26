package com.example.baidusdk_application;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.baidusdk_application.adapter.AreaAdapter;
import com.example.baidusdk_application.adapter.CityAdapter;
import com.example.baidusdk_application.adapter.FutureWeatherAdapter;
import com.example.baidusdk_application.adapter.ProvinceAdapter;
import com.example.baidusdk_application.bean.BiYingImgResponse;
import com.example.baidusdk_application.bean.CityResponse;
import com.example.baidusdk_application.bean.FutureWeatherResponse;
import com.example.baidusdk_application.bean.LifeIndexResponse;
import com.example.baidusdk_application.bean.TodayResponse;
import com.example.baidusdk_application.contract.WeatherContract;
import com.example.baidusdk_application.utils.Analysis;
import com.example.baidusdk_application.utils.ToastUtils;
import com.example.mvplibrary.mvp.MvpActivity;
import com.example.mvplibrary.utils.CityUtil;
import com.example.mvplibrary.utils.LiWindow;
import com.example.mvplibrary.utils.RecyclerViewAnimation;
import com.example.mvplibrary.utils.StatusBarUtil;
import com.example.mvplibrary.view.WhiteWinds;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Response;

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


    private static final String TAG = "MainActivity";
    private RxPermissions rxPermissions;//权限请求框架
//    private List<String> list;//字符串列表
//    private List<CityResponse> provinceList;//省列表数据
//    private List<CityResponse.CityBean> citylist;//市列表数据
//    private List<CityResponse.CityBean.AreaBean> arealist;//区/县列表数据
    ProvinceAdapter provinceAdapter;//省数据适配器
    AreaAdapter areaAdapter;//县/区数据适配器
    String provinceTitle;//标题
    LiWindow liWindow;//自定义弹窗
    //定位器
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private List<FutureWeatherResponse.DailyDTO> mList = new ArrayList<>();
    private FutureWeatherAdapter mAdapter;


    @Override
    public void initData(Bundle savedInstanceState) {

        StatusBarUtil.transparencyBar(context);
        rxPermissions = new RxPermissions(this);//实例化这个权限请求框架，否则会报错
        initrc(); //初始化RecycleView
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
            showLoadingDialog();
            mPresent.todayWeather(context,locationid); //获取本日天气
            mPresent.getFutureWeather(context,locationid); //获取未来天气
            mPresent.getLifeIndex(context,locationid); //获取生活指数
            mPresent.getbiying(context); //获取每日一图
            smrf.setOnRefreshListener(refreshLayout ->
            {
                mPresent.todayWeather(context,locationid); //获取本日天气
                mPresent.getFutureWeather(context,locationid); //获取未来天气
                mPresent.getLifeIndex(context,locationid); //获取生活指数
                mPresent.getbiying(context); //获取每日一图
            });
        }
    }

    /**
     * 获取未来三天天气
     * @param response
     */
    @Override
    public void getFutureWeather(retrofit2.Response<FutureWeatherResponse> response) {
        mLocationClient.stop();
        if (response.body().getCode().equals("200")){
            List<FutureWeatherResponse.DailyDTO> data = response.body().getDaily();
            mList.clear();
            tvTopTemperature.setText(response.body().getDaily().get(0).getTempMax()+"℃");
            lowTemperature.setText(response.body().getDaily().get(0).getTempMin()+"℃");
            mList.addAll(data);
            mAdapter.notifyDataSetChanged();
        }else {
            ToastUtils.showShortToast(context, "天气预报数据为空");
        }
    }

    /**
     * 获取实时天气
     * @param response
     */
    @Override
    public void getTodayWeatherResult(retrofit2.Response<TodayResponse> response) {
        mLocationClient.stop();
        dismissLoadingDialog();
        if (response.body().getNow().getTemp() != null) {
            smrf.finishRefresh();
            tvTemperature.setText(response.body().getNow().getTemp() + "℃");
            tvWeather.setText(response.body().getNow().getText());
            tvWindsDir.setText("风向   "+response.body().getNow().getWindDir());
            tvWindsScale.setText("风力   "+response.body().getNow().getWindScale());
            wwBig.startRotate();
            wwSmall.startRotate();

        } else {
            ToastUtils.showLongToast(context, response.body().getCode());
        }
    }

    /**
     * 生活指数
     * @param response
     */
    @Override
    public void getLifeIndex(Response<LifeIndexResponse> response) {
        mLocationClient.stop();
        Log.w(TAG,"生活指数====="+response.body().getDaily().get(0));
        if (response.body().getDaily().get(0)!=null){
            tvLifeIndex.setText(response.body().getDaily().get(0).getText());
            tvCar.setText(response.body().getDaily().get(1).getText());
        }else {
            ToastUtils.showLongToast(context, response.body().getCode());
        }
    }

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
                            bg.setBackground(drawable);
                        }
                    });
        }else {
            ToastUtils.showLongToast(context,"数据为空");
        }
    }

    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        smrf.finishRefresh();
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

    /**
     * 初始化rc
     */
    private void initrc(){
        mList = new ArrayList<>();
        mAdapter = new FutureWeatherAdapter(R.layout.rc_future_weather,mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rcFutureWeather.setLayoutManager(manager);
        rcFutureWeather.setAdapter(mAdapter);
    }

    @OnClick(R.id.img_city)
    public void citySelect(){
        showCityWindow();
    }
    /**
     * 城市弹窗
     */
    private void showCityWindow() {

        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_city_list, null);
        ImageView areaBack = view.findViewById(R.id.iv_back_area);
        ImageView cityBack = view.findViewById(R.id.iv_back_city);
        RecyclerView rv = view.findViewById(R.id.rv);
        TextView windowTitle = view.findViewById(R.id.tv_title);
        liWindow.showRightPopupWindow(view);
        initCityData(rv,windowTitle,cityBack,areaBack);
    }


    /**
     * 显示省份列表
     * @param recyclerView
     * @param windowTitle
     * @param cityBack
     * @param areaBack
     */
    private void initCityData(RecyclerView recyclerView, TextView windowTitle,ImageView cityBack,ImageView areaBack) {
        //初始化省数据 读取省数据并显示到列表中
        CityUtil.getInstance().init(context);
        List<CityUtil.ProvinceBean> provincelist = CityUtil.getInstance().getAllProvince();
        //定义省份显示适配器
        provinceAdapter = new ProvinceAdapter(R.layout.item_city_list, provincelist);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(provinceAdapter);
        provinceAdapter.notifyDataSetChanged();
        RecyclerViewAnimation.runLayoutAnimationRight(recyclerView);//动画展示

        provinceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener(){
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int positionProvince) {
                windowTitle.setText(provincelist.get(positionProvince).name);
                showCityList(provinceAdapter, positionProvince,recyclerView,windowTitle,cityBack,areaBack,provincelist);
            }
        });
    }

    /**
     * 显示城市列表
     * @param provinceAdapter
     * @param positionProvince
     * @param recyclerView
     * @param windowTitle
     * @param cityBack
     * @param areaBack
     * @param provincelist
     */
    void showCityList(ProvinceAdapter provinceAdapter, int positionProvince,RecyclerView recyclerView, TextView windowTitle,ImageView cityBack,ImageView areaBack,List<CityUtil.ProvinceBean> provincelist){
        //返回上一级数据
        setBack2Province(provinceAdapter,cityBack,recyclerView,windowTitle);

        //定义城市列表
        List<CityUtil.CityBean> citylist = CityUtil.getInstance().getCities(positionProvince);

        CityAdapter cityAdapter = new CityAdapter(R.layout.item_city_list, citylist);
        LinearLayoutManager manager1 = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();
        RecyclerViewAnimation.runLayoutAnimationRight(recyclerView);

        cityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                String cityTitle = provincelist.get(positionProvince).name;
                String provinceTitle = provincelist.get(positionProvince).citylist.get(position).name;
                windowTitle.setText(provinceTitle);
                setBack2City(cityAdapter,areaBack,recyclerView,windowTitle,cityTitle);

                onCityClick(cityAdapter,position,positionProvince,recyclerView,provincelist);


            }
        });
    }

    /**
     * 显示区列表
     * @param cityAdapter
     * @param cityPostiion
     * @param positionProvince
     * @param recyclerView
     * @param provincelist
     */
    void onCityClick(CityAdapter cityAdapter, int cityPostiion, int positionProvince,RecyclerView recyclerView,List<CityUtil.ProvinceBean> provincelist){
        List<CityUtil.AreaBean> arealist = CityUtil.getInstance().getAreas(cityPostiion,positionProvince);
        Log.w("TAG","arealist======="+arealist);
        AreaAdapter areaAdapter = new AreaAdapter(R.layout.item_city_list, arealist);
        LinearLayoutManager manager2 = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(manager2);
        recyclerView.setAdapter(areaAdapter);
        areaAdapter.notifyDataSetChanged();
        RecyclerViewAnimation.runLayoutAnimationRight(recyclerView);

        areaAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                                        Log.w(TAG,"位置===="+arealist.get(position).getName());
                mPresent.todayWeather(context,"101010100");//今日天气
                mPresent.getFutureWeather(context, "101010100");//天气预报
                mPresent.getLifeIndex(context, "101010100");//生活指数
                tvCity.setText(arealist.get(position).name);
                liWindow.closePopupWindow();

            }
        });
    }


    /**
     * 返回城市
     * @param cityAdapter
     * @param areaBack
     * @param recyclerView
     * @param windowTitle
     * @param provinceTitle
     */
    void setBack2City(CityAdapter cityAdapter,ImageView areaBack,RecyclerView recyclerView,TextView windowTitle,String provinceTitle){
        //返回上一级数据
        areaBack.setVisibility(View.VISIBLE);
        areaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(cityAdapter);
                cityAdapter.notifyDataSetChanged();
                areaBack.setVisibility(View.GONE);
                windowTitle.setText(provinceTitle);
                //arealist.clear();
            }
        });

    }

    /**
     * 返回省份
     * @param provinceAdapter
     * @param cityBack
     * @param recyclerView
     * @param windowTitle
     */
    void setBack2Province(ProvinceAdapter provinceAdapter,ImageView cityBack,RecyclerView recyclerView,TextView windowTitle){
        cityBack.setVisibility(View.VISIBLE);
        cityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(provinceAdapter);
                provinceAdapter.notifyDataSetChanged();
                cityBack.setVisibility(View.GONE);
                windowTitle.setText("中国");
            }
        });
    }




    @Override
    public void onDestroy() {
        wwSmall.stop();
        wwBig.stop();
        super.onDestroy();
    }
}