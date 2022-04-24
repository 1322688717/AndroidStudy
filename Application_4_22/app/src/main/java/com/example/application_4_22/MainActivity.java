package com.example.application_4_22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.mvplibrary.mvp.MvpActivity;

import retrofit2.Response;

public class MainActivity extends MvpActivity<Contract.Presonter> implements Contract.IView {


    private TextView textView;
    @Override
    public void initData(Bundle savedInstanceState) {
        textView = findViewById(R.id.tv);
        mPresent.getTemperture("101010100","66d721a1d6024ca8b6c257fcab036de7");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected Contract.Presonter createPresent() {
        return new Contract.Presonter();
    }


    @Override
    public void showText(Response<Bean> response) {
        textView.setText(response.body().getNow().getTemp());
    }
}