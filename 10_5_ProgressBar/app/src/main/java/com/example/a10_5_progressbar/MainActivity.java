package com.example.a10_5_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar mprogressBar_one;
    private Button mbtn_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        mbtn_one = findViewById(R.id.btn_one);
        mprogressBar_one = findViewById(R.id.pb_one);
        mbtn_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                if (mprogressBar_one.getVisibility() == View.GONE){
                    mprogressBar_one.setVisibility(View.VISIBLE);
                }else {
                    mprogressBar_one.setVisibility(View.GONE);
                }
            break;
        }

    }
}