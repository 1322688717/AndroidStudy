package com.example.myscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myscrollview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ScrollViewListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.myscrollview.setScrollViewListener(this);

    }


    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if ((oldy - y) < -50) {
            binding.banner.setVisibility(View.VISIBLE);
        }
        if ((oldy - y) > 50) {
            binding.banner.setVisibility(View.GONE);
        }
    }
}