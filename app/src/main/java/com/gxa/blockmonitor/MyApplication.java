package com.gxa.blockmonitor;

import android.app.Application;

import com.gxa.mylibrary.AppBlockMonitor;
import com.gxa.mylibrary.UiBlockMonitor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppBlockMonitor appMonitor = AppBlockMonitor.getInstance();
        appMonitor.setBlockThresholdMillis(3000); // 可以不填，默认3000ms
        appMonitor.install();

        UiBlockMonitor.getInstance().start();
    }
}
