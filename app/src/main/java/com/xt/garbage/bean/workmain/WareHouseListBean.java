package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/7
 */
public class WareHouseListBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : [{"cleanWeightNumber":"(number)已清运重量（kg）","garbageCategoryName":"(string)垃圾分类名称","refGarbageCategoryId":"(int32)垃圾分类ID","surplusWeightNumber":"(number)剩余重量（kg）"}]
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private List<ResultDTO> result;
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

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultDTO {
        /**
         * cleanWeightNumber : (number)已清运重量（kg）
         * garbageCategoryName : (string)垃圾分类名称
         * refGarbageCategoryId : (int32)垃圾分类ID
         * surplusWeightNumber : (number)剩余重量（kg）
         */

        private double cleanWeightNumber;
        private String garbageCategoryName;
        private int refGarbageCategoryId;
        private String surplusWeightNumber;

        public double getCleanWeightNumber() {
            return cleanWeightNumber;
        }

        public void setCleanWeightNumber(double cleanWeightNumber) {
            this.cleanWeightNumber = cleanWeightNumber;
        }

        public String getGarbageCategoryName() {
            return garbageCategoryName;
        }

        public void setGarbageCategoryName(String garbageCategoryName) {
            this.garbageCategoryName = garbageCategoryName;
        }

        public int getRefGarbageCategoryId() {
            return refGarbageCategoryId;
        }

        public void setRefGarbageCategoryId(int refGarbageCategoryId) {
            this.refGarbageCategoryId = refGarbageCategoryId;
        }

        public String getSurplusWeightNumber() {
            return surplusWeightNumber;
        }

        public void setSurplusWeightNumber(String surplusWeightNumber) {
            this.surplusWeightNumber = surplusWeightNumber;
        }
    }
}
