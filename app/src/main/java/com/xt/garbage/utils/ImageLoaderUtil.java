package com.xt.garbage.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xt.garbage.R;

import java.util.concurrent.CopyOnWriteArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author:DIY
 * @date: 2021/3/31
 */
public class ImageLoaderUtil {
    //占位符图片
    public static final int PLACE_HOLDER_SO_WHITE = R.mipmap.ic_launcher;
    //获取图片错误显示
    public static final int ERROR_SO_WHITE = R.mipmap.ic_launcher;

    /**
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(PLACE_HOLDER_SO_WHITE)
                .error(ERROR_SO_WHITE)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 指定图片大小;使用override()方法指定了一个图片的尺寸。
     * Glide现在只会将图片加载成width*height像素的尺寸，而不会管你的ImageView的大小是多少了。
     * 如果你想加载一张图片的原始尺寸的话，可以使用Target.SIZE_ORIGINAL关键字----override(Target.SIZE_ORIGINAL)
     *
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImageSize(Context context, String url, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.white)
                .error(R.drawable.erro)
                .override(width, height)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

//    public static void setGlideIm(Context context,final View view,String url) {
//        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                int w = resource.getMinimumHeight();
//                int h = resource.getMinimumHeight();
//            }
//        });
//    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     */

    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .placeholder(PLACE_HOLDER_SO_WHITE)
                .error(R.drawable.erro)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载任意圆角图片
     * @param context
     * @param url
     * @param imageView
     * @param type
     * @param radius
     */
    public static void loadCustRoundCircleImage(Context context, String url, ImageView imageView, RoundedCornersTransformation.CornerType type,int radius) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(PLACE_HOLDER_SO_WHITE)
                .error(R.drawable.erro)
                .priority(Priority.HIGH)
                .bitmapTransform(new RoundedCornersTransformation(radius, 0 ,type))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
