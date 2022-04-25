package com.example.mvplibrary.utils;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public  class CityUtil {


    private static CityUtil instance;
    private CityUtil (){}

    public static CityUtil getInstance() {
        if (instance == null) {
            instance = new CityUtil();
        }
        return instance;
    }

    private  JSONArray cityJsonData;

    public void init(Activity activity){

        if(cityJsonData != null ) return;
        try {
            InputStream inputStream = activity.getResources().getAssets().open("City.txt");//读取数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuffer.append(lines);
                lines = bufferedReader.readLine();
            }
            cityJsonData = new JSONArray(stringBuffer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public JSONArray getData(){
        return cityJsonData;
    }
}
