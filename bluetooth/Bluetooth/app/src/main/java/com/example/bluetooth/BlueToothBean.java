package com.example.bluetooth;



/**
 * @author zhaichaoqun
 * time 2023/2/10
 */
public class BlueToothBean {
    private String BlueToothName;
    private String BlueToothAddress;

    public BlueToothBean(String blueToothName, String blueToothAddress) {
        BlueToothName = blueToothName;
        BlueToothAddress = blueToothAddress;
    }

    public String getBlueToothName() {
        return BlueToothName;
    }

    public void setBlueToothName(String blueToothName) {
        BlueToothName = blueToothName;
    }

    public String getBlueToothAddress() {
        return BlueToothAddress;
    }

    public void setBlueToothAddress(String blueToothAddress) {
        BlueToothAddress = blueToothAddress;
    }
}
