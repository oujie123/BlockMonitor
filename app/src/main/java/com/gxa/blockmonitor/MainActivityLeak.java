package com.gxa.blockmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivityLeak extends AppCompatActivity {

    TextView mTextView;

    MyHandler mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leak);
        mTextView = findViewById(R.id.tv_handler);
        mMyHandler = new MyHandler(MainActivityLeak.this);
        doInBackground();
    }

    private static class MyHandler extends Handler {
        private WeakReference<MainActivityLeak> mWeakReference;

        public MyHandler(MainActivityLeak activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivityLeak mainActivity = mWeakReference.get();
            switch (msg.what) {
                case 1:
                    if (mainActivity != null) {
                        mainActivity.mTextView.setText(msg.obj + "");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void doInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟耗时操作
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //耗时操作完成，发送消息给UI线程
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = "更新UI";
                mMyHandler.sendMessage(msg);
            }
        }).start();
    }
}