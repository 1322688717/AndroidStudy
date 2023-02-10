package com.example.bluetooth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_open_blueTooth;

    private Button btn_close_blueTooth;

    private Button btn_search_blueTooth;

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothManager bluetoothManager;

    private BlueToothBroadcastReceiver blueToothBroadcastReceiver;

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
        initData();
        initBroadReceiver();

    }

    private void initBroadReceiver() {
        blueToothBroadcastReceiver = new BlueToothBroadcastReceiver();
        //监听蓝牙是否打开
        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        //开始扫描
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        //结束扫描
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        //找到的设备
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        //状态改变
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(blueToothBroadcastReceiver,intentFilter);
    }

    private void initData() {
        btn_search_blueTooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                bluetoothAdapter = bluetoothManager.getAdapter();
                if (bluetoothAdapter == null) {
                    Log.i("TAG","蓝牙已关闭");
                }else {
                    if (!bluetoothAdapter.isEnabled()) {
                        PermissionX.init(MainActivity.this)
                                .permissions(permissionBluetooth)
                                .request(new RequestCallback() {
                                    @Override
                                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                        if (allGranted){
                                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                            enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(enableBtIntent);
                                        }else {
                                            Toast.makeText(MainActivity.this, "未获取蓝牙权限", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else {
                        Toast.makeText(MainActivity.this, "正在搜索设备", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });
    }

    private void initView() {
        btn_close_blueTooth = findViewById(R.id.btn_close_blueTooth);
        btn_open_blueTooth = findViewById(R.id.btn_open_blueTooth);
        btn_search_blueTooth = findViewById(R.id.btn_search_blueTooth);

    }
}