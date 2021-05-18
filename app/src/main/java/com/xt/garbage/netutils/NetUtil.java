package com.xt.garbage.netutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.xt.garbage.base.BaseApplication;

import java.net.NetworkInterface;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class NetUtil {

    /**
     * 判断是否有网络连接
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.appContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if(networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;

    }

    /**
     * 判断wifi是否可用
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if(context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.appContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(wifiNetworkInfo != null) {
                return wifiNetworkInfo.isAvailable();
            }

        }
        return false;
    }

    /**
     * 判断wifi是否目前在使用网络
     * @return
     */
    public static boolean isWifiActive() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.appContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if(infos != null) {
                for (NetworkInfo info : infos) {
                    if(info.getTypeName().equals("WIFI") && info.isConnected())
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接类型
     * @return
     */
    public static int getConnectedType() {
        if(BaseApplication.appContext != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.appContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()) {
                return networkInfo.getType();
            }
        }
        return -1;
    }


}
