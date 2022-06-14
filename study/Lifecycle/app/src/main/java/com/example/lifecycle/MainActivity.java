package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lifecycle.databinding.ActivityMainBinding;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    private MyObserver myObserver;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        myObserver = new MyObserver();
        getLifecycle().addObserver(myObserver);

        binding.btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyActivity.class);
                startActivity(intent);
            }
        });
    }
}