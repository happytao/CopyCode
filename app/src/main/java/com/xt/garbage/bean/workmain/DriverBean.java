package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/31
 */

public class DriverBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : [{"cleanTonnage":"(string)清运载重量","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}"}]
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
         * cleanTonnage : (string)清运载重量
         * finishOrderNum : (int32)完成订单数：{驿站人员/司机}
         * head : (string)头像
         * id : (int64)用户ID
         * licensePlate : (string)汽车牌照
         * mobile : (string)手机号
         * nickName : (string)昵称
         * refRegionId : (int64)区域ID：{省/市/区}
         */

        private String cleanTonnage;
        private int finishOrderNum;
        private String head;
        private long id;
        private String licensePlate;
        private String mobile;
        private String nickName;
        private long refRegionId;

        public String getCleanTonnage() {
            return cleanTonnage;
        }

        public void setCleanTonnage(String cleanTonnage) {
            this.cleanTonnage = cleanTonnage;
        }

        public int getFinishOrderNum() {
            return finishOrderNum;
        }

        public void setFinishOrderNum(int finishOrderNum) {
            this.finishOrderNum = finishOrderNum;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public long getRefRegionId() {
            return refRegionId;
        }

        public void setRefRegionId(long refRegionId) {
            this.refRegionId = refRegionId;
        }
    }
}
