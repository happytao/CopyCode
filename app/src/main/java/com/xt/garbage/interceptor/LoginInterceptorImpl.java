package com.xt.garbage.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.utils.SPUtils;

/**
 * @author:DIY
 * @date: 2021/3/29
 */
public class LoginInterceptorImpl implements IInterceptor {
    private Context mContext;
    private static final String TAG = "LoginInterceptor";
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        /**
         * 给需要跳转的页面添加值为Constant.LOGIN_NEEDED的extra参数，用来标记是否需要用户先登录才可以访问该页面
         * 先判断需不需要
         */
        if(postcard.getExtra() == SpConstant.LOGIN_NEEDED) {
            boolean isLogin = (boolean) SPUtils.get(mContext,SpConstant.IS_LOGIN,false);
            Log.i(TAG,"是否已经登陆" + isLogin);
            if(isLogin) {
                callback.onContinue(postcard);
            }
            else {
                ARouter.getInstance().build(RoutePathConstant.APP_LOGIN_MAIN).navigation(mContext);
            }
        }
        else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }
}
