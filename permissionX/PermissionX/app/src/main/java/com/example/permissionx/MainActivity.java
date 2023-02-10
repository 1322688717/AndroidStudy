package com.example.permissionx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

/**
 * @author zhaichaoqun
 * time 2023/2/10
 */

public class MainActivity extends AppCompatActivity {

    private Button btnCamera;

    private Button btnPhoto;

    private Button btnBlueTooth;

    private Button btnWifi;


    private WifiManager mWifiManager;

    /**
     * 获取本地读写权限
     */
    private static final String[] permission = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private static final String[] permissionBluetooth = new String[]{
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOnclick();
    }

    private void initOnclick() {
        btnPhoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PermissionX.init(MainActivity.this)
                       .permissions(permission)
                       .setDialogTintColor(Color.parseColor("#1972e8"), Color.parseColor("#8ab6f5"))
                       .request(new RequestCallback() {
                           @Override
                           public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                               if (allGranted){
                                   openPhoto();
                               }else {
                                   Toast.makeText(MainActivity.this, "未获相册权限", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });

        btnCamera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PermissionX.init(MainActivity.this)
                       .permissions( Manifest.permission.CAMERA)
                       .request(new RequestCallback() {
                           @Override
                           public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                               if (allGranted){
                                   openCamera();
                               }else {
                                   Toast.makeText(MainActivity.this, "未获取相机权限", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });

       btnBlueTooth.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PermissionX.init(MainActivity.this)
                       .permissions(permissionBluetooth)
                       .request(new RequestCallback() {
                           @Override
                           public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                               if (allGranted){
                                   openBlueTooth();
                               }else {
                                   Toast.makeText(MainActivity.this, "未获取蓝牙权限", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });

       btnWifi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PermissionX.init(MainActivity.this)
                       .permissions(Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.ACCESS_WIFI_STATE)
                       .request(new RequestCallback() {
                           @Override
                           public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                               if (allGranted){
                                   openWifi();
                               }else {
                                   Toast.makeText(MainActivity.this, "未获取WiFi权限", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });
    }

    /**
     * 打开WiFi
     */
    private void openWifi() {
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled() && null != mWifiManager) {
            mWifiManager.setWifiEnabled(true);
        }


    }

    /**
     * 打开蓝牙
     */
    private void openBlueTooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(enableBtIntent);

    }

    /**
     * 打开相机
     */
    private void openCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    /**
     * 打开相册
     */
    private void openPhoto() {
        //intent可以应用于广播和发起意图，其中属性有：ComponentName,action,data等
        Intent intent=new Intent();
        intent.setType("image/*");
        //action表示intent的类型，可以是查看、删除、发布或其他情况；我们选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
        //类型的内容给你选择
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
        startActivityForResult(intent, 1);
    }

    private void initView() {
        btnCamera = findViewById(R.id.btn_camera);
        btnPhoto = findViewById(R.id.btn_photo);
        btnBlueTooth = findViewById(R.id.btn_blueTooth);
        btnWifi = findViewById(R.id.btn_wifi);
    }
}