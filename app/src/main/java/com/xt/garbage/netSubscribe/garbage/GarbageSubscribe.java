package com.xt.garbage.netSubscribe.garbage;

import com.xt.garbage.bean.workmain.GetAppointmentOrderStatusBean;
import com.xt.garbage.netutils.RetrofitFactory;
import com.xt.garbage.utils.GsonUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author:DIY
 * @date: 2021/3/29
 */
public class GarbageSubscribe {
    public static void getAppointmentList(GetAppointmentOrderStatusBean getBean, DisposableObserver<ResponseBody> subscribe) {
        String s = GsonUtils.toJson(getBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),s);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getAppointmentList(requestBody);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void getOrderSiteDetails(long orderId, DisposableObserver<ResponseBody> subscriber) {
        Map<String,Long> map = new HashMap<>();
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getOrderSiteDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    public static void orderSiteDetails(Long orderId,DisposableObserver<ResponseBody> subscribe) {
        Map<String,Long> map = new HashMap<>();
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().orderSiteDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void orderDetails(Long orderId,DisposableObserver<ResponseBody> subscribe) {
        Map<String,Long> map = new HashMap<>();
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().orderDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void userInfoUp(String nickName,String head,String detailAddress,DisposableObserver<ResponseBody> subscribe) {
        Map<String,String> map = new HashMap<>();
        map.put("nickName",nickName);
        map.put("head",head);
        map.put("detailAddress",detailAddress);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().userInfoUp(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void getIndex(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getIndex();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void doingOrder(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().doingOrder();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void getManyList(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getManyList();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
}
