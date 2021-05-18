package com.xt.garbage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.SharedPreferencesCompat;

import java.util.Map;

/**
 * @author:DIY
 * @date: 2021/3/23
 */
public class SPUtils {
    /**
     * 手机里的文件名
     */
    public static final String FILE_NAME = "share_data";

    /**
     * 保存数据方法
     * @param context  上下文
     * @param key  保存信息关键字，在SpConstant常量类中选取
     * @param obj 需要保存的对象
     */
    public static void put(Context context,String key,Object obj) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(obj instanceof String) {
            editor.putString(key,(String) obj);
        }
        else if(obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float)
        {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Long)
        {
            editor.putLong(key, (Long) obj);
        } else
        {
            editor.putString(key, obj.toString());
        }

        editor.apply();


    }

    /**
     * 从sp中获取数据
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context,String key,Object defaultObject) {
        SharedPreferences sp =context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);

        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;

    }

    /**
     * 移除某个key对应的数据
     * @param context
     * @param key
     */
    public static void remove(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key)
        .apply();
    }

    /**
     * 清除所有数据
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }

    /**
     * 查询某个键值对是否存在
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有键值对
     * @param context
     * @return
     */
    public static Map<String,?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getAll();
    }


}
