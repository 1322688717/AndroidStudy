package com.example.lottie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lottie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        //这个可有可无，如果不涉及本地图片做动画可忽略
//        binding.animationView.setImageAssetsFolder("images");
        //设置动画文件
        binding.animationView.setAnimation("smearloader.json");
        //是否循环执行
        binding.animationView.loop(true);
        //执行动画
        binding.animationView.playAnimation();

    }
}