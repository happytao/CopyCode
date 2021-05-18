package com.xt.garbage.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.ObjectConstructor;

/**
 * @author:DIY
 * @date: 2021/3/23
 * Gson库工具类
 */
public class GsonUtils {
    public static Gson gson = new Gson();

    /**
     * 解析异常抛回null
     * @param result 需要解析的json字符串
     * @param clazz 对应的javabean字节码
     * @return 返回对应的javabean对象
     */
    public static <T> T fromJson(String result,Class<T> clazz) {
        try {
            if(gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(result,clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj) {
        if(gson == null) {
            gson = new Gson();
        }
        return gson.toJson(obj);
    }


}
