package com.xt.garbage.utils

import android.content.Context

/**
 *@author:DIY
 *@date: 2021/6/4
 */
class DimensionUtils {
    companion object{
        @JvmStatic
        fun dip2px(context: Context,dpValue:Float) : Int {
            val scale:Float = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }
}