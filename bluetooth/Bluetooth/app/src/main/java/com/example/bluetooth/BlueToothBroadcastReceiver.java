package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaichaoqun 2023/2/10
 */
public class BlueToothBroadcastReceiver extends BroadcastReceiver {

    List<BlueToothBean> blueToothBeans=new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("TAG", "intent.getAction()==" + intent.getAction());

        if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
            switch (state) {
                case 10:
                    Log.i("TAG", "蓝牙已关闭");
                    break;
                case 11:
                    Log.i("TAG", "蓝牙正在打开");
                    break;
                case 12:
                    Log.i("TAG", "蓝牙已打开");
                    break;
                case 13:
                    Log.i("TAG", "蓝牙正在关闭");
                    break;
                default:
                    Log.i("TAG", "state状态为：" + state);
            }
        } else if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
            String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);

            if (name != null) {
                int rssi = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI);

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

//                MyBlueToothDevice myDevice = new MyBlueToothDevice(device, name, rssi);
//
//                myBlueToothDevices.add(myDevice);
                Log.e("=====", "搜索到设备: " + device);
            }
        } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
            Log.i("TAG", "开始搜索附近蓝牙设备...");
        } else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
            Log.i("TAG", "搜索附近蓝牙完成");
        }
    }
}
