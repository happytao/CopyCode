package com.xt.garbage.netSubscribe.garbage;

import com.xt.garbage.bean.workmain.BargainPostBean;
import com.xt.garbage.netutils.RetrofitFactory;
import com.xt.garbage.utils.GsonUtils;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author:DIY
 * @date: 2021/4/1
 */
public class DriverSubscribe {
    public static void getDriverList(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getDriverList();
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void getSubDriverList(List<String> orderList, DisposableObserver<ResponseBody> subscriber) {
        Map<String,List<String>> map = new HashMap<>();
        map.put("orderStatusList",orderList);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getSubDriverList(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);

    }

    public static void subscribeDriver(long id,DisposableObserver<ResponseBody> subscriber) {
        Map<String,Long> map = new HashMap<>();
        map.put("motormanId",id);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().subscribeDriver(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void getWareHouseList(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getWareHouseList();
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void getOrderDetails(long orderId,DisposableObserver<ResponseBody> subscriber) {
        Map<String,Long> map = new HashMap<>();
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getOrderDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }
    public static void cleanOrderConfirm(BargainPostBean bargainPostBean,DisposableObserver<ResponseBody> subscriber) {
        String string = GsonUtils.toJson(bargainPostBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf8"),string);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().cleanOrderConfirm(body);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void getMotorOrderDetails(Long orderId,DisposableObserver<ResponseBody> subscribe) {
        Map<String,Long> map = new HashMap<>();
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getMotorOrderDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void postPhoto(File file, DisposableObserver<ResponseBody> subscribe) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile",file.getName(),requestFile);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().postPhoto(body);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
}
