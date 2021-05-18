package com.xt.garbage.netutils;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.xt.garbage.base.BaseApplication;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.netapi.HttpApi;
import com.xt.garbage.netapi.URLConstant;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class RetrofitFactory {
    public String TAG = "RetrofitFactory";
    public static final String CACHE_NAME = "com.xt.garbage";
    public static String BASE_URL = URLConstant.BASE_URL;
    public static final int DEFAULT_CONNECT_TIMEOUT = 30;
    public static final int DEFAULT_WRITE_TIMEOUT = 30;
    public static final int DEFAULT_READ_TIMEOUT = 30;
    private Retrofit retrofit;
    private HttpApi httpApi;
    //请求失败重连次数
    private int RETRY_COUNT = 0;
    private OkHttpClient.Builder okHttpBuilder;

    private RetrofitFactory() {
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 设置缓存
         */
        File cacheFile = new File(BaseApplication.appContext.getExternalCacheDir(),CACHE_NAME);
        Cache cache = new Cache(cacheFile,1024 * 1024 *50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!NetUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if(!NetUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control","public,max-age=" + maxAge)
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                else {
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control","public, only-if-cached,max-stale" + maxStale)
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };
        okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor);


        /**
         * 设置头信息
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding","gzip")
                        .addHeader("Accept","application/json")
                        .addHeader("Content-Type","application/json; charset = utf-8")
                        .addHeader("token", BaseConstant.TOKEN)
                        .method(originalRequest.method(),originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d(message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        /**
         * 设置超时和重新连接
         */

        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_READ_TIMEOUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT,TimeUnit.SECONDS);
        okHttpBuilder.retryOnConnectionFailure(true);

        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpApi = retrofit.create(HttpApi.class);

    }

    //创建单例
    private static class SingletonHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }


    public static RetrofitFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void changeBaseUrl(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        httpApi = retrofit.create(HttpApi.class);
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }

    /**
     * 设置订阅和所在的线程环境
     */
    /**
     * 订阅方法
     * @param o 被观察者
     * @param s 观察者
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)
                .subscribe(s);
    }
}
