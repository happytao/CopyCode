package com.xt.garbage.bean.login;

/**
 * @author:DIY
 * @date: 2021/5/28
 */
public class SmsBean {
    /**
     * errorCode : 72
     * errorMsg : 我是默认字符串
     * result : {"smsUuId":585}
     * success : true
     */

    private int errorCode;
    private String errorMsg;
    private ResultBean result;
    private boolean success;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultBean {
        /**
         * smsUuId : 585
         */

        private int smsUuId;

        public int getSmsUuId() {
            return smsUuId;
        }

        public void setSmsUuId(int smsUuId) {
            this.smsUuId = smsUuId;
        }
    }
}
