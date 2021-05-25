package com.xt.garbage.bean.user;

/**
 * @author:DIY
 * @date: 2021/5/25
 */

public class UploadBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : (string)
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private String result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
