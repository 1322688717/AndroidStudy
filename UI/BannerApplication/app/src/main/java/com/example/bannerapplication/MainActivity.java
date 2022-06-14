package com.example.bannerapplication;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useBanner();
    }


    public static ArrayList<Integer> getTestData() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.banner_real_time_transcription);
        list.add(R.color.black);
        list.add(R.color.teal_200);;
        return list;
    }

    private void useBanner() {

        banner = findViewById(R.id.banner);
        banner.setAdapter(new BannerImageAdapter<Integer>(getTestData()) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }

        })
                .addBannerLifecycleObserver(this) //添加生命周期
                .setIndicator(new RectangleIndicator(MainActivity.this)) //添加指示器
                .setBannerGalleryEffect(20, 20, 20) //添加抽屉模式
                .setIndicatorSelectedColor(Color.RED) //指示器选中的颜色
                .setIndicatorSpace(10)  //设置指示器间隔
                .setIndicatorHeight(10);  //设置指示器高度
    }
}