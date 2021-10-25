package com.gxa.blockmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gxa.mylibrary.UiBlockMonitor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ObjectPool pool = new ObjectPool() {
        @Override
        protected Object create(Class<?> type) {
            return new TestBean();
        }
    };

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

        findViewById(R.id.btn_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 5; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i1 = 0; i1 < 100; i1++) {
                                TestBean tmp = pool.acquire();
                                tmp.setAge(18);
                                tmp.setName("JackOu");
                                Log.e(TAG, Thread.currentThread() + "->" + tmp.toString());
                                pool.release(tmp);
                            }
                        }
                    }).start();
                }
            }
        });
    }
}