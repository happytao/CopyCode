package com.xt.garbage.netSubscribe.shop;

import com.xt.garbage.netutils.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * @author:DIY
 * @date: 2021/3/27
 */
public class ShopSubscribe {

    public static void getOrderList(String orderStatus, String receiveStatus, DisposableObserver<ResponseBody> subscribe) {
        Map<String,String> map = new HashMap<>();
        map.put("orderStatus",orderStatus);
        map.put("receiveStatus",receiveStatus);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getOrderList(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void getParentOrderDetails(String id, DisposableObserver<ResponseBody> subscriber) {
        Map<String,String> map = new HashMap<>();
        map.put("parentOrderId",id);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getParentOrderId(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void getAddress(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getAddress();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);

    }
}
