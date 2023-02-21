package com.example.md5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhaichaoqun
 * time 2023/2/13
 */
public class MainActivity extends AppCompatActivity {

    private Boolean isFirst;

    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
    }

    private void initTitle() {
        SharedPreferences sp = getSharedPreferences("SpQing", Context.MODE_PRIVATE);
        isFirst = sp.getBoolean("isFirst",true);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        if (isFirst){
            sp.edit().putBoolean("isFirst",false).apply();
            sp.edit().putString("time",date).apply();
            time = "1";
            Log.e("TAG",""+time);
        }else {
            String value = sp.getString("time", date);
            try {
                Date star = sdf.parse(value);
                Date endDay=sdf.parse(date);
                differentDays(star,endDay);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void differentDays(Date star,Date endDay) {
        //            Date star = dft.parse("2023-02-03");
//            Date endDay=dft.parse("2023-03-25");
        Long starTime=star.getTime();
        Long endTime=endDay.getTime();
        Long num=endTime-starTime;
        System.out.println("相差天数为："+num/24/60/60/1000);
        Log.i("TAG","相差天数为=="+num/24/60/60/1000);
        time = num.toString();
        Log.e("TAG",""+time+1);
    }
}