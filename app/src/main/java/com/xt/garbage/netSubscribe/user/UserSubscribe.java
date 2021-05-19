package com.xt.garbage.netSubscribe.user;

import com.xt.garbage.netutils.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;

/**
 * @author:DIY
 * @date: 2021/3/28
 */
public class UserSubscribe {
    public static void getUserInfo(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getUserInfo();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void getMessageList(DisposableObserver<ResponseBody> subscribe) {
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().getMessageList();
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void setMessage(Long messageId, DisposableObserver<ResponseBody> subscribe) {
        Map<String,Long> map = new HashMap<>();
        map.put("messageId",messageId);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().setMessage(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
}
