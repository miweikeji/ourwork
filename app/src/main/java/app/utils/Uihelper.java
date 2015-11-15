package app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.entity.ImageEntity;


/**
 * Created by TuLiangtan on 2015/9/4.
 */
public class Uihelper {


    private static Toast mToast;

    private static void initToast(Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
    }

    public static void showToast(final Activity activity, final String content) {
        if (activity == null) {
            return;
        }
        initToast(activity);
        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mToast.show();
        }
    }

    public static void showToast(final Activity context, int id) {
        initToast(context);
        mToast.setText(id);
        mToast.show();
    }

    public static void trace(String st) {

        if (Constants.Debug) {
            Log.e("miweikeji_trace", st);
        }

    }

    public static void trace(String tag, String st) {
        if (Constants.Debug) {
            Log.e(tag, st);
        }

    }

    /**
     * dip2px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dipValue * scale + 0.5 * (dipValue >= 0 ? 1 : -1));
    }

    /**
     * px2dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取单张本地图片的宽高
     *
     * @author yangsq
     * @createDate 2014-9-17
     * @param context
     * @param imageEntity
     */
    public static void getImagesWH(Context context, ImageEntity imageEntity) {
        try {
            Uri uri = Uri.parse(imageEntity.getPath());
            getThumbnail(context, uri, imageEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从一个已知的图片Uri中获得图片的bitmap对象的宽高
     *
     */
    public static void getThumbnail(Context context, Uri uri,
                                    ImageEntity imageEntity) throws FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;// 当解码时避免内存分配
        onlyBoundsOptions.inDither = true;// optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        imageEntity.setHeight(onlyBoundsOptions.outHeight);
        if (TextUtils.isEmpty(imageEntity.getPath())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path) && path.indexOf("file") < 0) {
                path = "file://" + path;
            }
            imageEntity.setPath(path);
        }
        imageEntity.setWidth(onlyBoundsOptions.outWidth);
        input.close();
    }

    /**
     * 获得相册中图片的option
     *
     * @return
     */
    public static DisplayImageOptions getCameraPhotoOptions() {

        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.transparent)
                        // .showImageBackGroundColor(backgroundId)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
                        // .imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true).displayer(new
                        // FadeInBitmapDisplayer(1000))
                .cacheOnDisk(true).build();
    }

    /**
     * 将时间戳转换成yyyy-MM-dd
     *
     * @author yangsq
     * @date 2014-7-22
     * @param timestamp
     * @return
     */
    public static String timestampToDateStr(Double timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = sdf.format(new Date((long) (timestamp * 1000L)));
        return date;
    }

    public static String longToDateStr(Double timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        String date = sdf.format(new Date((long) (timestamp * 1000L)));
        return date;
    }

    public static String timestampToDateStr_other(Double timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sdf.format(new Date((long) (timestamp * 1L)));
        return date;
    }

    /** Create a file Uri for saving an image or video */
    public static Uri getOutputMediaFileUri() {
        File file = getOutputMediaFile();
        if (file != null) {
            return Uri.fromFile(file);
        } else
            return null;
    }


    /** Create a File for saving an image or video */
    private static File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Miqian");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",  Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }
}
