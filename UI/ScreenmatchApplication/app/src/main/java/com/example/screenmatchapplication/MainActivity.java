package com.example.screenmatchapplication;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tv_language;
    Button btn_switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //initData();
    }
    public void initView(){
        tv_language = findViewById(R.id.tv_language);
        btn_switch = findViewById(R.id.btn_switch);

        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Resources resources = getResources();
                Configuration configuration = resources.getConfiguration();
                configuration.locale = Locale.ENGLISH;
                DisplayMetrics dm = resources.getDisplayMetrics();
                resources.updateConfiguration(configuration, dm);
                tv_language.setText(R.string.language);
            }
        });


    }

}