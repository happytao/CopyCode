package com.xt.garbage.utils

import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
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

            options.inSampleSize = calculateInSampleSize(options,actualWidth,actualHeight)

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
                if(orientation)

            }
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
    }
}