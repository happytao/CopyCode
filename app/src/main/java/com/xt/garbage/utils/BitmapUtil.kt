package com.xt.garbage.utils

import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import java.io.*
import java.lang.UnsupportedOperationException
import kotlin.math.roundToInt

/**
 *@author:DIY
 *@date: 2021/5/23
 */
class BitmapUtil {
    constructor() {
        throw UnsupportedOperationException("you can't instantiate me...")
    }

    companion object {
        fun getScaledBitmap(context: Context,imageUri: Uri,maxWidth:Float,maxHeight:Float,bitmapConfig:Bitmap.Config) : Bitmap? {
            var filePath:String = FileUtil.getRealPathFromURI(context,imageUri)
            var scaledBitmap:Bitmap? = null
            var options:BitmapFactory.Options = BitmapFactory.Options()
            //通过设定@options.inJustDecodeBounds这个变量为true，实际Bitmap的像素将不会加载到内存中，只会加载该图片的高度宽度信息
            options.inJustDecodeBounds = true
            var bmp:Bitmap = BitmapFactory.decodeFile(filePath,options)
            bmp?.let {
                var inputStream:InputStream? = null

                try {
                    inputStream = FileInputStream(filePath)
                    BitmapFactory.decodeStream(inputStream,null,options)
                    inputStream.close()
                } catch (e: FileNotFoundException) {
                    Log.e("TAG",Log.getStackTraceString(e))
                } catch (e:IOException) {
                    Log.e("TAG",Log.getStackTraceString(e))
                }

            }

            var actualHeight:Int = options.outHeight
            var actualWidth = options.outWidth

            if(actualHeight == -1 || actualWidth == -1) {
                try {
                    var exifInterface:ExifInterface = ExifInterface(filePath)
                    actualHeight = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH,ExifInterface.ORIENTATION_NORMAL)
                    actualWidth = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH,ExifInterface.ORIENTATION_NORMAL)
                } catch (e: IOException) {
                    Log.e("TAG",Log.getStackTraceString(e))
                }
            }

            if(actualWidth <= 0 || actualHeight <= 0) {
                var bitmap2:Bitmap = BitmapFactory.decodeFile(filePath)
                if(bitmap2 != null) {
                    actualWidth = bitmap2.width
                    actualHeight = bitmap2.height
                }
                else {
                    return null
                }

            }

            var imgRatio:Float = (actualWidth/actualHeight).toFloat()
            var maxRatio:Float = maxWidth/maxHeight

            //设置宽度和高度保持图片的宽高比
            if(actualHeight > maxHeight || actualWidth > maxWidth) {
                if(imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight
                    actualWidth = (imgRatio * actualWidth).toInt()
                    actualHeight = maxHeight.toInt()
                }
                else if(imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth
                    actualHeight = (imgRatio * actualHeight).toInt()
                    actualWidth = maxWidth.toInt()
                }
                else {
                    actualHeight = maxHeight.toInt()
                    actualWidth = maxWidth.toInt()
                }
            }
            //设置@options.inSampleSize来加载一个等比例缩小的图片
            options.inSampleSize = calculateInSampleSize(options,actualWidth,actualHeight)

            //设置为false来加载真实图片
            options.inJustDecodeBounds = false
            options.inPurgeable = true
            options.inInputShareable = true
            options.inTempStorage = ByteArray(16 * 1024)

            try {
                bmp = BitmapFactory.decodeFile(filePath,options)
                bmp?.let {
                    var inputStream:InputStream? = null
                    inputStream = FileInputStream(filePath)
                    inputStream.use {
                        BitmapFactory.decodeStream(it,null,options)
                    }
                }
            } catch (e: OutOfMemoryError) {
                Log.e("TAG",Log.getStackTraceString(e))
            }
            if(actualHeight <= 0 || actualWidth <= 0) {
                return null
            }

            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth,actualHeight,bitmapConfig)
            } catch (e:OutOfMemoryError) {
                e.printStackTrace()
            }

            var ratioX:Float = (actualWidth / options.outWidth).toFloat()
            var ratioY:Float = (actualHeight / options.outHeight).toFloat()

            var scaleMatrix:Matrix = Matrix()
            scaleMatrix.setScale(ratioX,ratioY, 0F, 0F)

            var canvas: Canvas? = scaledBitmap?.let { Canvas(it) }
            canvas?.setMatrix(scaleMatrix)
            canvas?.drawBitmap(bmp,0F,0F, Paint(Paint.FILTER_BITMAP_FLAG))

            var exif:ExifInterface? = null
            try {
                exif = ExifInterface(filePath)
                var orientation:Int = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,0)
                var matrix:Matrix = Matrix()
                when(orientation) {
                    6 -> {
                        matrix.postRotate(90f)
                    }

                    3 -> {
                        matrix.postRotate(180f)
                    }

                    8 -> {
                        matrix.postRotate(270f)
                    }
                    else -> { }

                }
                scaledBitmap = scaledBitmap?.let { Bitmap.createBitmap(it,0,0,it.width,it.height,matrix,true) }

            } catch (exception:IOException) {
                exception.printStackTrace()
            }

            return scaledBitmap
        }

        private fun calculateInSampleSize(options:BitmapFactory.Options,reqWidth:Int,reqHeight:Int): Int {
            val height:Int = options.outHeight
            val width:Int = options.outWidth
            var inSampleSize:Int = 1

            if(height > reqHeight || width > reqWidth) {
                val heightRatio:Int = (height.toFloat()/reqHeight.toFloat()).roundToInt()
                val widthRatio:Int = (width.toFloat() / reqWidth.toFloat()).roundToInt()
                inSampleSize = if(heightRatio < widthRatio) heightRatio else widthRatio
            }

            val totalPixels:Float = (width * height).toFloat()
            val totalReqPixelsCap:Float = (reqWidth * reqHeight * 2).toFloat()

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize ++

            }

            return  inSampleSize

        }

        fun compressImage(context: Context, imageUri: Uri, maxWidth: Float, maxHeight: Float, compressFormat: Bitmap.CompressFormat,
                          bitmapConfig: Bitmap.Config, quality:Int, parentPath:String, prefix:String, fileName:String):File {
            var filename:String = generateFilePath(context,parentPath,imageUri,compressFormat.name.toLowerCase(),prefix,fileName)
            FileOutputStream(filename).use {
                var newBmp:Bitmap? = BitmapUtil.getScaledBitmap(context,imageUri,maxWidth,maxHeight,bitmapConfig)
                newBmp?.compress(compressFormat,quality,it)
            }
            return File(filename)


        }

        private fun generateFilePath(context: Context,parentPath: String,uri: Uri,extension:String, prefix: String,fileName: String): String {
            var file:File = File(parentPath)
            if(file.exists()) file.mkdir()
            var myPrefix = if (TextUtils.isEmpty(fileName)) ""  else prefix
            var myFileName = if (TextUtils.isEmpty(fileName)) myPrefix + FileUtil.splitFileName(FileUtil.getFileName(context,uri))[0] else fileName
            return file.absolutePath + File.separator + myFileName + "." + extension

        }
    }
}