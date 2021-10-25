package com.gxa.blockmonitor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: Bitmap | Drawable | InputStream | Byte[ ] 之间进行转换
 * @Author: JackOu
 * @CreateDate: 2021/10/25 20:52
 */
class BitmapTransforTool {

    /**
     * drawable 的获取方式： Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
     * Drawable转化成Bitmap
     *
     * @param drawable drawable source
     * @return bitmap source
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ?
                        Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap转换成Drawable
     *
     * @param resources
     * @param bm
     * @return
     */
    public static Drawable bitmapToDrawable(Resources resources, Bitmap bm) {
        Drawable drawable = new BitmapDrawable(resources, bm);
        return drawable;
    }

    /**
     * Bitmap转换成byte[]
     *
     * @param bm
     * @return
     */
    public byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * byte[]转换成Bitmap
     */
    public Bitmap bytes2Bitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * InputStream转Bitmap
     */
    public Bitmap InputStream2Bitmap(Context context, int resId) {
        if (context == null || resId == 0) return null;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is);
    }

    /**
     * InputStream转byte[]
     */
    public byte[] bytes2InputStream(Context context, int resId) {
        if (context == null || resId == 0) return null;
        InputStream is = context.getResources().openRawResource(resId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024*2];
        int len = 0;
        try {
            while ((len = is.read(b, 0, b.length)) != -1)
            {
                baos.write(b, 0, len);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
