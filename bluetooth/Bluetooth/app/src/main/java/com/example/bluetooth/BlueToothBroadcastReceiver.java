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

        switch (intent.getAction()){
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                isOpenBlueTooth(intent);
                break;
            case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                Log.i("TAG", "开始搜索附近蓝牙设备...");
                break;
            case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                Log.i("TAG", "搜索附近蓝牙完成");
                break;
            case BluetoothDevice.ACTION_FOUND:
                    //获取蓝牙设备
                    BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(scanDevice == null || scanDevice.getName() == null) {
                        return;
                    }
                    Log.d("TAG", "搜索到了: name="+scanDevice.getName()+"  address="+scanDevice.getAddress());
            default:
                break;
        }
    }

    /**
     * 监听蓝牙打开关闭的状态
     * @param intent
     */
    private void isOpenBlueTooth(Intent intent) {
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
    }
}
