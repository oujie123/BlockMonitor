package com.gxa.blockmonitor;

import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: dump堆内存
 * @Author: JackOu
 * @CreateDate: 2021/10/26 12:04
 */
class DumpHeapTool {

    private static final String TAG = DumpHeapTool.class.getSimpleName();

    public static boolean createDumpFile(Context context) {
        Log.i(TAG, "start to dump heap....");
        String LOG_PATH = "/dump.gc/";
        boolean ret = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ssss");
        String createTime = sdf.format(new Date(System.currentTimeMillis()));
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + LOG_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            String hprofPath = file.getAbsolutePath();
            if (!hprofPath.endsWith("/")) {
                hprofPath += "/";
            }

            hprofPath += createTime + ".hprof";
            try {
                Debug.dumpHprofData(hprofPath);
                ret = true;
                Log.d(TAG, "createDumpFile: done!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ret = false;
            Log.d(TAG, "NO SDCARD");
        }
        return ret;
    }
}
