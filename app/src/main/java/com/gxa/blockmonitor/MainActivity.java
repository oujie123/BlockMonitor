package com.gxa.blockmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gxa.mylibrary.UiBlockMonitor;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UiBlockMonitor.getInstance().stop();
    }
}