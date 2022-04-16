package com.example.okhttp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = OKHttp.get("https://devapi.qweather.com/v7/weather/now?location=116.41,39.92&key=66d721a1d6024ca8b6c257fcab036de7");
                Log.e("TAG","json=========="+json);
            }
        });


    }
}