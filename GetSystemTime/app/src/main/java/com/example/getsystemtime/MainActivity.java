package com.example.getsystemtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btn_my;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_my = findViewById(R.id.button);
        tv_time = findViewById(R.id.textView);

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String time = format.format(curDate);
                tv_time.setText(time);
            }
        });
    }
}