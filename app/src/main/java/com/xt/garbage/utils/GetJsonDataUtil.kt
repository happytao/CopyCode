package com.xt.garbage.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 *@author:DIY
 *@date: 2021/6/11
 */
class GetJsonDataUtil {
    companion object{
        /**获取assets文件夹下的json的数据
         * @param context 上下文
         * @param fileName assets文件夹内文件的名称
         * @return 返回json字符串
         */
        fun getJson(context: Context, fileName: String): String {
            var stringBuilder: StringBuilder = StringBuilder()
            try {
                var assetManager: AssetManager = context.assets
                var bf: BufferedReader = BufferedReader(InputStreamReader(assetManager.open(fileName)))
                var line: String = ""
                while (bf.readLine()?.let { line = it } != null) {
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                Log.e("TAG", Log.getStackTraceString(e))
            }
            return stringBuilder.toString()
        }
    }
}