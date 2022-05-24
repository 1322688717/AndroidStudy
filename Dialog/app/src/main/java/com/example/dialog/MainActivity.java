package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.dialog.databinding.ActivityMainBinding;
import com.liys.dialoglib.LDialog;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LDialog dialog = LDialog.newInstance( MainActivity.this,R.layout.dialog);
                dialog
                        .setWidth(display.getWidth())
                        .setHeight(display.getHeight())

                        .show();
            }
        });


        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textView.setText("123");
            }
        });



    }


}