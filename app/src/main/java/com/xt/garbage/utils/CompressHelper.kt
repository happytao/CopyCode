package com.xt.garbage.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewDebug
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




    fun compressToFile(file:File):File? {
        context?.cacheDir?.let { destinationDirectoryPath = it.path + File.pathSeparator + FileUtil.FILES_PATH }
        return  BitmapUtil.compressImage(context!!, Uri.fromFile(file),maxWidth,maxHeight,
                    compressFormat,bitmapConfig,quality, destinationDirectoryPath, fileNamePrefix, fileName)
    }




     class Builder(context: Context) {

        init {
            instance.context = context
        }

        fun setMaxWidth(maxWidth:Float):Builder {
            instance.maxWidth = maxWidth
            return this
        }

        fun setMaxHeight(maxHeight:Float):Builder {
            instance.maxHeight = maxHeight
            return this
        }

        fun setCompressFormat(compressFormat: Bitmap.CompressFormat) : Builder {
            instance.compressFormat = compressFormat
            return this
        }

        fun setBitmapConfig(bitmapConfig: Bitmap.Config) : Builder {
            instance.bitmapConfig = bitmapConfig
            return this
        }

        fun setQuality(quality:Int) : Builder {
            instance.quality = quality
            return this
        }

        fun setDestinationDirectoryPath(destinationDirectoryPath:String) : Builder {
            instance.destinationDirectoryPath = destinationDirectoryPath
            return this
        }

        fun setFileNamePrefix(prefix:String) : Builder {
            instance.fileNamePrefix = prefix
            return this
        }

        fun setFileName(fileName:String) : Builder {
            instance.fileName = fileName
            return this
        }

        fun build() : CompressHelper {
            return instance
        }

    }








}