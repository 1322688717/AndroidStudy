package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author zhaichaoqun 2023/2/10
 */
public class BlueToothBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,0);
            switch (state){
                case 10:      Log.i("TAG","蓝牙已关闭");
                    break;
                case 11:      Log.i("TAG","蓝牙正在打开");
                    break;
                case 12:      Log.i("TAG","蓝牙已打开");
                    break;
                case 13:      Log.i("TAG","蓝牙正在关闭");
                    break;
                default:  Log.i("TAG","state状态为："+state);
            }
        }else if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.i("TAG","device==="+device);
        }


    }
}
