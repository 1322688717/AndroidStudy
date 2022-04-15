package com.example.baidusdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BaiduSDKService extends Service {
    public BaiduSDKService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}