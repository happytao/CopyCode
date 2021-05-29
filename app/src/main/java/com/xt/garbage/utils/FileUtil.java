package com.xt.garbage.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.audiofx.AcousticEchoCanceler;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import com.luck.picture.lib.PictureSelector;

import java.io.File;

/**
 * @author:DIY
 * @date: 2021/4/6
 */
public class FileUtil {
    static final String FILES_PATH = "CompressHelper";
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * Uri转文件绝对路径
     * 以content://开头的
     * @param uri
     * @param context
     * @return
     */
    public static String getFilePathByUri(Uri uri, Context context) {
        if(ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            String path = null;
            String[] projection = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if(columnIndex > -1) {
                        path = cursor.getString(columnIndex);
                    }

                }
                cursor.close();
            }
            return path;
        }
        return null;
    }

    /**
     * 获取真实路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealPathFromURI(Context context,Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri,null,null,null,null);
        if(cursor == null){
            return uri.getPath();
        }
        else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String realPath = cursor.getString(index);
            cursor.close();
            return realPath;
        }
    }

    public static String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".") ;
        if(i != -1) {
            name = fileName.substring(0,i);
            extension = fileName.substring(i);
        }

        return new String[]{name,extension};
    }

    public static String getFileName(Context context,Uri uri) {
        String result = null;
        if(uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri,null,null,null,null);
            try {
                if(cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
        }

        if(result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if(cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return  result;
    }
}
