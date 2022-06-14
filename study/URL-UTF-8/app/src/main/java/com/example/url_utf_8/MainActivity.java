package com.example.url_utf_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {

    String url = "5%E6%9C%8819%E6%97%A5+%E4%B8%8B%E5%8D%886%E7%82%B958%E5%88%86-1654766108";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            String name = URLDecoder.decode(url,"UTF-8");
            Log.e("TAG","======"+name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}