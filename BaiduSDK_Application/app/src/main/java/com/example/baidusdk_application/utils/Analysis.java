package com.example.baidusdk_application.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.baidusdk_application.R;
import com.example.baidusdk_application.adapter.AreaAdapter;
import com.example.baidusdk_application.adapter.CityAdapter;
import com.example.baidusdk_application.adapter.ProvinceAdapter;
import com.example.mvplibrary.utils.CityUtil;
import com.example.mvplibrary.utils.LiWindow;
import com.example.mvplibrary.utils.RecyclerViewAnimation;

import java.util.List;

public class Analysis {

    ProvinceAdapter provinceAdapter;//省数据适配器
    AreaAdapter areaAdapter;//县/区数据适配器
    String provinceTitle;//标题
    LiWindow liWindow;//自定义弹窗

    private   static Analysis instance;
    private Analysis(){

    }
    public static Analysis getInstance(){
        if (instance == null){
            instance = new Analysis();
        }
        return instance;
    }

    public interface ICityListener {
        void onSelected(String name,String location);
    }

    ICityListener mCityListenr;
    /**
     * 城市弹窗
     */
    public void showCityWindow(Context context, Activity activity,ICityListener cityListener) {

        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_city_list, null);
        ImageView areaBack = view.findViewById(R.id.iv_back_area);
        ImageView cityBack = view.findViewById(R.id.iv_back_city);
        RecyclerView rv = view.findViewById(R.id.rv);
        TextView windowTitle = view.findViewById(R.id.tv_title);
        liWindow.showRightPopupWindow(view);
        initCityData(rv,windowTitle,cityBack,areaBack,context,activity,cityListener);
    }


    /**
     * 显示省份列表
     * @param recyclerView
     * @param windowTitle
     * @param cityBack
     * @param areaBack
     */
    private void initCityData(RecyclerView recyclerView, TextView windowTitle,ImageView cityBack,ImageView areaBack,Context context,Activity activity,ICityListener cityListener) {
        //初始化省数据 读取省数据并显示到列表中
        CityUtil.getInstance().init(activity);
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
                showCityList(provinceAdapter, positionProvince,recyclerView,windowTitle,cityBack,areaBack,provincelist,context,cityListener);
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
    void showCityList(ProvinceAdapter provinceAdapter, int positionProvince,RecyclerView recyclerView, TextView windowTitle,ImageView cityBack,ImageView areaBack,List<CityUtil.ProvinceBean> provincelist,Context context,ICityListener cityListener){
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

                onCityClick(cityAdapter,position,positionProvince,recyclerView,provincelist,context,cityListener);


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
    void onCityClick(CityAdapter cityAdapter, int cityPostiion, int positionProvince,RecyclerView recyclerView,List<CityUtil.ProvinceBean> provincelist,Context context,ICityListener cityListener){

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
                String locationName = provincelist.get(positionProvince).citylist.get(cityPostiion).arealist.get(position).name;

                liWindow.closePopupWindow();

                cityListener.onSelected(locationName, "101010100");

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




}
