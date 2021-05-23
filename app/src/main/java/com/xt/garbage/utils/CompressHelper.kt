package com.xt.garbage.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File

/**
 *@author:DIY
 *@date: 2021/5/23
 * 处理图片压缩的工具类
 */
class CompressHelper() {

    companion object {
        @JvmStatic
        val instance:CompressHelper by lazy {
            CompressHelper()
        }
    }

    private var context:Context? = null
    //最大宽度720
    private var maxWidth:Float = 720.0f
    //最大高度960
    private var maxHeight:Float = 960.0f
    //压缩后格式为JPEG
    private var compressFormat:Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
    //图片处理方式为ARGB_8888
    private var bitmapConfig:Bitmap.Config = Bitmap.Config.ARGB_8888
    //压缩质量为80
    private var quality:Int = 80
    //存储路径
    private var destinationDirectoryPath:String? = null
    //文件名前缀
    private var fileNamePrefix:String? = null
    //文件名
    private var fileName:String? = null

    constructor(context: Context?) : this() {
        this.context = context
    }

    init {
        context.cacheDir?.let { destinationDirectoryPath = it.path + File.pathSeparator + FileUtil.FILES_PATH }
    }


    fun compressToFile(file:File):File {
        return

    }






}