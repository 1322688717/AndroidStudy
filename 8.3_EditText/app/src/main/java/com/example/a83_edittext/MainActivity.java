package com.example.a83_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * hint 输入提示
     * textcolorhint 输入提示文字颜色
     * inputtype 输入类型
     * drawable 在输入框的指定方位添加图片
     * drawablepadding 设置图片与输入内容的间距
     * pading 设置内容与边框的间距
     * background 背景色
     * @param savedInstanceState
     */

    private EditText edt_account;
    private EditText edt_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        edt_account = findViewById(R.id.edt_auccount);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View view) {
        String account = edt_account.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        Log.e("TAG","账号为："+account+"密码为："+password);
    }
}