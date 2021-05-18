package com.xt.garbage.netapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.base.BaseBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.utils.GsonUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class OnSuccessAndFaultSub extends DisposableObserver<ResponseBody> implements ProgressCancelListener {
    private boolean showProgress = true;
    private OnSuccessAndFaultListener listener;

    private Context context;
    private ProgressDialog progressDialog;

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener listener) {
        this.listener = listener;
    }

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在加载中...");
    }

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener listener, Context context, boolean showProgress) {
        this.showProgress = showProgress;
        this.listener = listener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        this.showProgress = showProgress;
    }

    private void showProgressDialog() {
        if(showProgress && progressDialog != null) {
            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if(showProgress && progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onStart() {
        showProgressDialog();
    }

    @Override
    public void onNext(@NonNull ResponseBody responseBody) {
        try {
            String result = responseBody.string();
            Log.e("body",result);
            BaseBean baseBean = GsonUtils.fromJson(result,BaseBean.class);
            switch (baseBean.getErrorCode()) {
                case 1:
                    ARouter.getInstance().build(RoutePathConstant.APP_LOGIN_MAIN).navigation();
                    break;
                case 0:
                    listener.onSuccess(result);
                    break;
                case 1099:
                    listener.onSuccess(result);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onError(@NonNull Throwable e) {
        try {
            if(e instanceof SocketTimeoutException) {

            }
            else if(e instanceof ConnectException) {
                listener.onFailed("网络连接超时");
            }
            else if(e instanceof SSLHandshakeException) {
                listener.onFailed("安全证书异常");
            }
            else if(e instanceof HttpException) {
                int code = ((HttpException) e).code();
                if(code == 504) {
                    listener.onFailed("网络异常，请检查你的网络状态");
                }
                else if(code == 404) {
                    listener.onFailed("请求地址不存在");
                }
                else {
                    listener.onFailed("请求失败,失败代码为" + Integer.toString(code));
                }
            }
            else if(e instanceof UnknownHostException) {
                listener.onFailed("域名解析失败");
            }
            else {
                listener.onFailed("error" + e.getMessage());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub","error" + e.getMessage());
            dismissProgressDialog();
            progressDialog = null;
        }

    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        progressDialog = null;

    }

    @Override
    public void onCancelProgress() {
        if(!this.isDisposed()) {
            this.dispose();
        }

    }
}
