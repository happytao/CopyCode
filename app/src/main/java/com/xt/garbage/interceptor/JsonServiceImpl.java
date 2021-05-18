package com.xt.garbage.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;
import com.xt.garbage.constant.RoutePathConstant;

import java.lang.reflect.Type;

/**
 * @author:DIY
 * @date: 2021/3/29
 */
@Route(path = RoutePathConstant.SERVICE_JSON)
public class JsonServiceImpl implements SerializationService {
    private Gson mGson;
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        checkJson();
        return mGson.fromJson(input,clazz);
    }

    @Override
    public String object2Json(Object instance) {
        checkJson();
        return mGson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        checkJson();
        return mGson.fromJson(input,clazz);
    }

    @Override
    public void init(Context context) {
        mGson = new Gson();

    }

    private void checkJson() {
        if(mGson != null) {
            mGson = new Gson();
        }
    }
}
