package com.example.mv;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Author:javakam on 2016/4/6 17:22:22
 */
public class YasuoBitmap {
    public YasuoBitmap() {

    }
    public static Bitmap yasuoBitmapFromFile(Resources resources, int view_id, int reqHeight, int reqWidth) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, view_id, options);
        L.i("初始高---" + options.outHeight + "---初始宽---" + options.outWidth + "---初始outMimeType---" + options.outMimeType);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);
        L.i("压缩比例---" + options.inSampleSize);
        // 使用获取到的inSampleSize值再次解析图片 --- 注：要先设置成false，解析后才会返回Bitmap
        options.inJustDecodeBounds = false;
        L.i("压缩后高---" + options.outHeight + "---压缩后宽---" + options.outWidth + "---压缩后outMimeType---" + options.outMimeType);
        return BitmapFactory.decodeResource(resources, view_id, options);
    }
    /**
     * 从本地资源文件获取图片
     *
     * @param resources getResources
     * @param view_id   本地图片的id
     * @param reqHeight 自定义高
     * @param reqWidth  自定义宽
     * @return
     */
    public static Bitmap yasuoBitmapFromResources(Resources resources, int view_id, int reqHeight, int reqWidth) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, view_id, options);
        L.i("初始高---" + options.outHeight + "---初始宽---" + options.outWidth + "---初始outMimeType---" + options.outMimeType);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);
        L.i("压缩比例---" + options.inSampleSize);
        // 使用获取到的inSampleSize值再次解析图片 --- 注：要先设置成false，解析后才会返回Bitmap
        options.inJustDecodeBounds = false;
        L.i("压缩后高---" + options.outHeight + "---压缩后宽---" + options.outWidth + "---压缩后outMimeType---" + options.outMimeType);
        return BitmapFactory.decodeResource(resources, view_id, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
