package com.xt.garbage.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log

/**
 *@author:DIY
 *@date: 2021/5/29
 */
class PackageUtils {
    companion object {
        /**
         * 获取版本名称
         */
        @JvmStatic
        fun getVersionName(context: Context): String? {
            var pm:PackageManager = context.packageManager
            try {
                var packageInfo: PackageInfo = pm.getPackageInfo(context.packageName,0)
                return packageInfo.versionName
            }
            catch (exception:PackageManager.NameNotFoundException) {
                Log.e("TAG",Log.getStackTraceString(exception))
            }

            return null
        }

        /**
         * 获取App的版本号
         */
        @JvmStatic
        fun getVersionCode(context: Context) :Int {
            var pm:PackageManager = context.packageManager
            try {
                var packageInfo:PackageInfo = pm.getPackageInfo(context.packageName,0)
                return packageInfo.versionCode
            }
            catch (exception:PackageManager.NameNotFoundException) {
                Log.e("TAG",Log.getStackTraceString(exception))
            }
            return 0
        }

        /**
         * 获取App的名称
         */
        fun getAppName(context: Context) : String? {
            var pm:PackageManager = context.packageManager
            try {
                var packageInfo: PackageInfo = pm.getPackageInfo(context.packageName,0)
                var applicationInfo:ApplicationInfo = packageInfo.applicationInfo
                return context.resources.getString(applicationInfo.labelRes)
            }
            catch (exception:PackageManager.NameNotFoundException) {
                Log.e("TAG",Log.getStackTraceString(exception))
            }

            return null
        }


    }
}