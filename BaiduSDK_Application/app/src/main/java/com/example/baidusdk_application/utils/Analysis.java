package com.example.baidusdk_application.utils;

import com.example.baidusdk_application.bean.CityResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Analysis {



    public static List<CityResponse> analysisJson(JSONArray jsonArray){
        List<CityResponse> provinceList = null;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject provinceJsonObject = jsonArray.getJSONObject(i);
                String provinceName = provinceJsonObject.getString("name");
                CityResponse response = new CityResponse();
                response.setName(provinceName);
                provinceList.add(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return provinceList;
    }
}
