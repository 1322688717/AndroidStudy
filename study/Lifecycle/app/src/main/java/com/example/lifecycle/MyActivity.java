package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifecycle.databinding.ActivityMyBinding;

public class MyActivity extends AppCompatActivity {

    private MyObserver myObserver;
    private ActivityMyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myObserver = new MyObserver();
        getLifecycle().addObserver(myObserver);
    }
}