package com.xt.garbage.netapi;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public interface OnSuccessAndFaultListener {
    void onSuccess(String result);

    void onFailed(String errorMsg);
}
