package com.xt.garbage.netSubscribe.shop;

import com.google.gson.Gson;
import com.xt.garbage.bean.shop.AddCarBean;
import com.xt.garbage.netutils.RetrofitFactory;
import com.xt.garbage.utils.GsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author:DIY
 * @date: 2021/3/27
 */
public class ShopSubscribe {

    public static void getOrderList(String orderStatus, String receiveStatus, DisposableObserver<ResponseBody> subscribe) {
        Map<String, String> map = new HashMap<>();
        map.put("orderStatus", orderStatus);
        map.put("receiveStatus", receiveStatus);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getOrderList(map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscribe);
    }

    public static void getParentOrderDetails(String id, DisposableObserver<ResponseBody> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("parentOrderId", id);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getParentOrderId(map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getAddress(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getAddress();
        RetrofitFactory.getInstance().toSubscribe(observable, subscribe);

    }

    public static void getCommodityDetails(Long goodsId, DisposableObserver<ResponseBody> subscribe) {
        Map<String, Long> map = new HashMap<>();
        map.put("goodsId", goodsId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getCommodityDetails(map);
        RetrofitFactory.getInstance().toSubscribe(observable, subscribe);
    }

    public static void addCar(int buyNum, AddCarBean.Spec spec, long refGoodsId, DisposableObserver<ResponseBody> subscribe) {
        List<AddCarBean.Spec> specList = new ArrayList();
        specList.add(spec);
        AddCarBean addCarBean = new AddCarBean(buyNum, refGoodsId, specList);
        String carBean = GsonUtils.toJson(addCarBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), carBean);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().addCar(body);
        RetrofitFactory.getInstance().toSubscribe(observable, subscribe);
    }

    public static void createOrder(int buyNum, AddCarBean.Spec spec, long refGoodsId, DisposableObserver<ResponseBody> subscribe) {
        List<AddCarBean.Spec> specList = new ArrayList<>();
        specList.add(spec);
        AddCarBean addCarBean = new AddCarBean(buyNum, refGoodsId, specList);
        String carBean = GsonUtils.toJson(addCarBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), carBean);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().createOrder(body);
        RetrofitFactory.getInstance().toSubscribe(observable, subscribe);
    }
}


