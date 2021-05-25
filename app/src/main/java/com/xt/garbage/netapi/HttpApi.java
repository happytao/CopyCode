package com.xt.garbage.netapi;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public interface HttpApi {
    //用户登陆
    @POST("noauth/app/login")
    Observable<ResponseBody> login(@Body Map<String,String> map);
    //获取用户预定获取清单
    @POST("auth/shop/order/list")
    Observable<ResponseBody> getOrderList(@Body Map<String,String> map);

    @GET("auth/user/info")
    Observable<ResponseBody> getUserInfo();

    //获取消息list
    @GET("auth/user/message/list")
    Observable<ResponseBody> getMessageList();

    @GET("auth/clean/site/order/motorman/list")
    Observable<ResponseBody> getDriverList();

    @GET("auth/clean/site/order/recycle/warehouse/list")
    Observable<ResponseBody> getWareHouseList();

    @GET("auth/address/list")
    Observable<ResponseBody> getAddress();

    //预约上门list
    @POST("auth/garbage/site/order/site/subscribe/list")
    Observable<ResponseBody> getAppointmentList(@Body RequestBody requestBody);

    @POST("auth/clean/site/order/subscribe/list")
    Observable<ResponseBody> getSubDriverList(@Body Map<String, List<String>> map);

    @POST("auth/shop/order/parent/detail")
    Observable<ResponseBody> getParentOrderId(@Body Map<String,Long> map);

    @POST("auth/garbage/site/order/detail")
    Observable<ResponseBody> getOrderSiteDetails(@Body Map<String,Long> map);

    @POST("auth/clean/site/order/subscribe")
    Observable<ResponseBody> subscribeDriver(@Body Map<String,Long> map);

    @POST("auth/clean/site/order/detail")
    Observable<ResponseBody> getOrderDetails(@Body Map<String,Long> map);

    @POST("auth/clean/site/order/confirm/order")
    Observable<ResponseBody> cleanOrderConfirm(@Body RequestBody requestBody);

    //设置消息已读
    @POST("auth/user/message/set/read")
    Observable<ResponseBody> setMessage(@Body Map<String,Long> map);

    //驿站预约上门订单详情
    @POST("auth/garbage/site/order/detail")
    Observable<ResponseBody> orderSiteDetails(@Body Map<String,Long> map);

    //预约上门订单详情
    @POST("auth/garbage/user/order/detail")
    Observable<ResponseBody> orderDetails(@Body Map<String,Long> map);

    //清运司机获取订单详情
    @POST("auth/clean/motorman/order/detail")
    Observable<ResponseBody> getMotorOrderDetails(@Body Map<String,Long> map);

    //上传照片
    @Multipart
    @POST("auth/file/upload")
    Observable<ResponseBody> postPhoto(@Part MultipartBody.Part uploadFile);

    //个人信息修改
    @POST("auth/user/info/update")
    Observable<ResponseBody> userInfoUp(@Body Map<String,String> map);

}
