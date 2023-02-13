package com.example.bluetooth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
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

    private Button btn_search_blueTooth;

    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothManager mBluetoothManager;

    private BlueToothBroadcastReceiver mBlueToothBroadcastReceiver;

    private BluetoothLeScanner mBluetoothLeScanner;

    private static final String[] permissionBluetooth = new String[]{
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.BLUETOOTH_SCAN
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initBroadReceiver();
        initView();
        onclick();
    }

    private void onclick() {
        btn_search_blueTooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBluetoothAdapter == null) {
                    Log.i("TAG","没有蓝牙设备");
                }else {
                    getPermissionX();
                }
            }
        });
    }

    private void initBroadReceiver() {
        mBlueToothBroadcastReceiver = new BlueToothBroadcastReceiver();
        IntentFilter deviceIntentFilter = new IntentFilter();

        deviceIntentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        deviceIntentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//
        deviceIntentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);

        deviceIntentFilter.addAction(BluetoothDevice.ACTION_FOUND);

        deviceIntentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

        registerReceiver(mBlueToothBroadcastReceiver, deviceIntentFilter);
    }

    private void initData() {
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        String name = mBluetoothAdapter.getName();
        String address = mBluetoothAdapter.getAddress();
        Log.i("TAG","蓝牙名称："+name+"  蓝牙地址："+address);
        ScanCallback mScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                String device = result.getDevice().toString();
                Log.i("TAG","蓝牙连接设备："+device);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
            }
        };

    }

    /**
     * 获取蓝牙权限
     */
    private void getPermissionX() {
        PermissionX.init(MainActivity.this)
                .permissions(permissionBluetooth)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted){
                            for (int i = 0; i < grantedList.size(); i++){
                                Log.i("TAG","获取权限:"+grantedList.get(i));
                            }
                            searchBlueTooth();
                        }else {
                            for (int i = 0; i < deniedList.size(); i++){
                                Log.i("TAG","未获取权限:"+deniedList.get(i));
                            }
                        }
                    }
                });
    }

    @SuppressLint("MissingPermission")
    private void searchBlueTooth() {
        if (mBluetoothAdapter.isEnabled()){
            Log.i("TAG","开始搜索");
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            mBluetoothAdapter.startDiscovery();
            mBluetoothLeScanner.startScan(new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    Log.i("TAG","result="+result.getDevice().getName());
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                }
            });

        }else {
            Log.i("TAG","蓝牙未打开");
            Toast.makeText(MainActivity.this, "蓝牙未打开", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        btn_search_blueTooth = findViewById(R.id.btn_search_blueTooth);

    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetoothAdapter.cancelDiscovery();
        mBluetoothAdapter = null;
        mBluetoothManager = null;
    }

}