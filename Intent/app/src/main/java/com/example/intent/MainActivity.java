package com.example.intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

/**
 * @author zhaichaoqun
 * time 2023/2/13
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     Button mButtonUri;

     Button mButtonPhone;

     Button mButtonInformation;

     Button mButtonCallPhone;

     Button mButtonSendInformation;

     static final String[] PERMISSION = new String[]{
            Manifest.permission.CALL_PHONE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mButtonInformation = findViewById(R.id.btn_information);
        mButtonUri = findViewById(R.id.btn_uri);
        mButtonPhone = findViewById(R.id.btn_phone);
        mButtonCallPhone = findViewById(R.id.btn_callPhone);
        mButtonSendInformation = findViewById(R.id.btn_sendInformation);
        mButtonPhone.setOnClickListener(this);
        mButtonUri.setOnClickListener(this);
        mButtonInformation.setOnClickListener(this);
        mButtonCallPhone.setOnClickListener(this);
        mButtonSendInformation.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_uri:
                intentUri();
                break;
            case R.id.btn_phone:
                intentPhone();
                break;
            case R.id.btn_information:
                intentInformation();
                break;
            case R.id.btn_callPhone:
                intentCallPhone();
                break;
            case R.id.btn_sendInformation:
                intentSendInformation();
                break;
            default:
                break;
        }
    }

    private void intentCallPhone() {
        PermissionX.init(this)
                .permissions(PERMISSION)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted){
                            Log.i("TAG","grantedList=="+grantedList);
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:10086"));
                            startActivity(intent);
                        }else {
                            Log.i("TAG","deniedList=="+deniedList);
                        }
                    }
                });

    }

    private void intentSendInformation() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:17683817853"));
        intent.putExtra("sms_body", "具体短信内容");
        startActivity(intent);
    }

    private void intentInformation() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("sms_body", "具体短信内容");
        startActivity(intent);
    }

    private void intentPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void intentUri() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri data = Uri.parse("http://www.baidu.com");
        intent.setData(data);
        startActivity(intent);
    }
}