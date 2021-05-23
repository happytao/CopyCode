package com.xt.garbage.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.lang.UnsupportedOperationException

/**
 *@author:DIY
 *@date: 2021/5/23
 */
class BitmapUtil {
    constructor() {
        throw UnsupportedOperationException("you can't instantiate me...")
    }

    companion object {
        fun getScaledBitmap(context: Context,imageUri: Uri,maxWidth:Float,maxHeight:Float,bitmapConfig:Bitmap.Config) : Bitmap {
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
            }
        }
    }
}