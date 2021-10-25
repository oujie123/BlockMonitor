package com.gxa.blockmonitor;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 图片加载工具
 * @Author: JackOu
 * @CreateDate: 2021/10/25 20:38
 */
class BitmapLoadTool {

    //======================从本地（SDcard）文件读取=======================

    /**
     * 获取缩放后的本地图片
     *
     * @param filePath 文件路径
     * @param width    宽
     * @param height   高
     * @return
     */
    public static Bitmap readBitmapFromFile(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 获取缩放后的本地图片
     * readBitmapFromFileDescriptor()效率高于readBitmapFromFile()
     *
     * @param filePath 文件路径
     * @param width    宽
     * @param height   高
     * @return
     */
    public static Bitmap readBitmapFromFileDescriptor(String filePath, int width, int height) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
            float srcWidth = options.outWidth;
            float srcHeight = options.outHeight;
            int inSampleSize = 1;
            if (srcHeight > height || srcWidth > width) {
                if (srcWidth > srcHeight) {
                    inSampleSize = Math.round(srcHeight / height);
                } else {
                    inSampleSize = Math.round(srcWidth / width);
                }
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            return BitmapFactory.decodeFileDescriptor(fis.getFD(), null,
                    options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //======================从输入流中读取文件（网络加载）=======================

    /**
     * 获取缩放后的本地图片
     *
     * @param ins    输入流
     * @param width  宽
     * @param height 高
     * @return
     */
    public static Bitmap readBitmapFromInputStream(InputStream ins, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ins, null, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeStream(ins, null, options);
    }

    //======================Resource资源加载=======================

    /**
     * 使用decodeResource()相对比较消耗内存，建议用decodeStream()
     *
     * @param resources
     * @param resourcesId
     * @param width
     * @param height
     * @return
     */
    public static Bitmap readBitmapFromResourceUsingDecodeResource(Resources resources, int resourcesId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resourcesId, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeResource(resources, resourcesId, options);
    }

    public static Bitmap readBitmapFromResourceUsingDecodeStream(Resources resources, int
            resourcesId, int width, int height) {
        InputStream ins = resources.openRawResource(resourcesId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ins, null, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeStream(ins, null, options);
    }

    //======================Assets资源加载方式=======================

    /**
     * 获取缩放后的本地图片
     *
     * @param filePath 文件路径,即文件名称
     * @return
     */
    public static Bitmap readBitmapFromAssetsFile(Context context, String
            filePath) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(filePath);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    //======================从二进制数据读取图片=======================
    public static Bitmap readBitmapFromByteArray(byte[] data, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }
}

