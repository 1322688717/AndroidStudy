package com.example.mvplibrary.utils;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public  class CityUtil {

    public class ProvinceBean {
        public String name;
        public List<CityBean> citylist;
    }
    public class CityBean {
        public String name;
        public List<AreaBean> arealist;
    }
    public class AreaBean {
        public String name;
    }

    private static CityUtil instance;
    private CityUtil (){}

    public static CityUtil getInstance() {
        if (instance == null) {
            instance = new CityUtil();
        }
        return instance;
    }

    private List<ProvinceBean> provinceList = new ArrayList<>();
    private List<CityBean> cityBeans = new ArrayList<>();
    private List<AreaBean> areaBeans = new ArrayList<>();

    public void init(Activity activity){
        try {
            InputStream inputStream = activity.getResources().getAssets().open("City.txt");//读取数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuffer.append(lines);
                lines = bufferedReader.readLine();
            }
            JSONArray jsonlist = new JSONArray(stringBuffer.toString());
            initProvince(jsonlist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initProvince(JSONArray jsonlist) throws JSONException {
        try {
            for (int i = 0; i < jsonlist.length(); i++) {
                JSONObject provinceJsonObject = jsonlist.getJSONObject(i);
                ProvinceBean pb = new ProvinceBean();

                pb.name = provinceJsonObject.getString("name");
                JSONArray cityjson = provinceJsonObject.getJSONArray ("city");

                List<CityBean> citylist = new ArrayList<>();

                for (int j = 0; j < cityjson.length(); j++) {
                    CityBean cb = new CityBean();
                    JSONObject cityObj = cityjson.getJSONObject(j);

                    cb.name = cityObj.getString("name");
                    JSONArray areajson = cityObj.getJSONArray ("area");

                    List<AreaBean> arearlist = new ArrayList<>();
                    for (int k = 0; k < areajson.length(); k++) {
                        AreaBean ab = new AreaBean();
                        ab.name = areajson.getString(k);
                        arearlist.add(ab);
                    }
                    cb.arealist = arearlist;
                    citylist.add(cb);
                }
                pb.citylist = citylist;
                provinceList.add(pb);
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<ProvinceBean> getAllProvince(){
        return provinceList;
    }

    public List<CityBean> getCities(int provinceIndex){
        //cityBeans = provinceList.get(post);
        Log.w("TAG","provinceList.get(post)========"+provinceList.get(provinceIndex).citylist);
        return provinceList.get(provinceIndex).citylist;
    }

    public List<AreaBean> getAreas(int cityIndex,int provinceIndex){
        Log.w("TAG","cityBeans.get(post).arealist========"+provinceList.get(provinceIndex).citylist.get(cityIndex).arealist);
        return provinceList.get(provinceIndex).citylist.get(cityIndex).arealist;
    }
}
