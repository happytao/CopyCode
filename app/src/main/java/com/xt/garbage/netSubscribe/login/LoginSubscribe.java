package com.xt.garbage.netSubscribe.login;

import com.xt.garbage.netutils.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import retrofit2.Retrofit;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class LoginSubscribe {

    public static void login(String deviceId, String loginType, String mobile, String password, String smsCode, String sourceType,
                             DisposableObserver<ResponseBody> subscribe) {
        Map<String,String> map = new HashMap<>();
        map.put("deviceId",deviceId);
        map.put("loginType",loginType);
        map.put("mobile",mobile);
        map.put("password",password);
        map.put("smsCode",smsCode);
        map.put("sourceType",sourceType);
//        RetrofitTest.login(map);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().login(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);

    }

    public static void sendSms(String mobile,String smsType,DisposableObserver<ResponseBody> subscribe) {
        Map<String,String> map = new HashMap<>();
        map.put("smsType",smsType);
        map.put("mobile",mobile);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().sendSms(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }

    public static void upPassword(String password,String smsCode,DisposableObserver<ResponseBody> subscribe) {
        Map<String,String> map = new HashMap<>();
        map.put("smsCode",smsCode);
        map.put("password",password);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().upPassword(map);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
    
}
