package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/3
 */
public class SubDriverOrderListBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : [{"createTime":"(date-time)创建时间","id":"(int64)清运订单ID","motormanRefUserId":"(int64)接单司机人员ID","motormanResp":{"cleanTonnage":"(string)清运载重量","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}"},"orderArriveTime":"(date-time)订单到达时间","orderCancelExplain":"(string)订单取消说明","orderCancelReason":"(int32)订单取消理由：{false.价格不妥 true.其他原因}","orderCancelTime":"(date-time)订单取消时间","orderFinishTime":"(date-time)订单完成时间","orderReceiveTime":"(date-time)订单接单时间","orderStatus":"(int32)订单状态：{1.（驿站）待接单 2.（司机）已接单 3.（司机）已到达 4.（驿站）待确认 5.（司机）已完成 6.（驿站）取消预约 7.（司机）拒单 8.（订单）超时取消  9.（驿站）订单取消 10.（司机）订单取消}","recycleList":[{"bargainCashPrice":"(number)重新议价（元/kg）","bargainWeightNumber":"(number)重新称重（kg）","categoryCashPrice":"(number)分类现金价（元/kg）","createTime":"(date-time)创建时间","garbageCategoryName":"(string)垃圾分类名称","id":"(int64)订单回收ID","isAll":"(boolean)是否全部：{false：部分 true：全部}","isBargain":"(boolean)是否重新议价：{false：否 true：是}","recylePrice":"(number)回收价（元/kg）","recyleWeightNumber":"(number)回收重量（kg）","refCleanOrderId":"(int64)清运订单回收ID","refGarbageCategoryId":"(int32)垃圾分类ID"}],"refSiteId":"(int64)驿站ID","settleTotalCashPrice":"(number)结算总现金价","totalWeight":"(number)合计重量/kg"}]
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
         * createTime : (date-time)创建时间
         * id : (int64)清运订单ID
         * motormanRefUserId : (int64)接单司机人员ID
         * motormanResp : {"cleanTonnage":"(string)清运载重量","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}"}
         * orderArriveTime : (date-time)订单到达时间
         * orderCancelExplain : (string)订单取消说明
         * orderCancelReason : (int32)订单取消理由：{false.价格不妥 true.其他原因}
         * orderCancelTime : (date-time)订单取消时间
         * orderFinishTime : (date-time)订单完成时间
         * orderReceiveTime : (date-time)订单接单时间
         * orderStatus : (int32)订单状态：{1.（驿站）待接单 2.（司机）已接单 3.（司机）已到达 4.（驿站）待确认 5.（司机）已完成 6.（驿站）取消预约 7.（司机）拒单 8.（订单）超时取消  9.（驿站）订单取消 10.（司机）订单取消}
         * recycleList : [{"bargainCashPrice":"(number)重新议价（元/kg）","bargainWeightNumber":"(number)重新称重（kg）","categoryCashPrice":"(number)分类现金价（元/kg）","createTime":"(date-time)创建时间","garbageCategoryName":"(string)垃圾分类名称","id":"(int64)订单回收ID","isAll":"(boolean)是否全部：{false：部分 true：全部}","isBargain":"(boolean)是否重新议价：{false：否 true：是}","recylePrice":"(number)回收价（元/kg）","recyleWeightNumber":"(number)回收重量（kg）","refCleanOrderId":"(int64)清运订单回收ID","refGarbageCategoryId":"(int32)垃圾分类ID"}]
         * refSiteId : (int64)驿站ID
         * settleTotalCashPrice : (number)结算总现金价
         * totalWeight : (number)合计重量/kg
         */

        private String createTime;
        private long id;
        private long motormanRefUserId;
        private MotormanRespDTO motormanResp;
        private String orderArriveTime;
        private String orderCancelExplain;
        private int orderCancelReason;
        private String orderCancelTime;
        private String orderFinishTime;
        private String orderReceiveTime;
        private int orderStatus;
        private List<RecycleListDTO> recycleList;
        private long refSiteId;
        private double settleTotalCashPrice;
        private double totalWeight;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getMotormanRefUserId() {
            return motormanRefUserId;
        }

        public void setMotormanRefUserId(long motormanRefUserId) {
            this.motormanRefUserId = motormanRefUserId;
        }

        public MotormanRespDTO getMotormanResp() {
            return motormanResp;
        }

        public void setMotormanResp(MotormanRespDTO motormanResp) {
            this.motormanResp = motormanResp;
        }

        public String getOrderArriveTime() {
            return orderArriveTime;
        }

        public void setOrderArriveTime(String orderArriveTime) {
            this.orderArriveTime = orderArriveTime;
        }

        public String getOrderCancelExplain() {
            return orderCancelExplain;
        }

        public void setOrderCancelExplain(String orderCancelExplain) {
            this.orderCancelExplain = orderCancelExplain;
        }

        public int getOrderCancelReason() {
            return orderCancelReason;
        }

        public void setOrderCancelReason(int orderCancelReason) {
            this.orderCancelReason = orderCancelReason;
        }

        public String getOrderCancelTime() {
            return orderCancelTime;
        }

        public void setOrderCancelTime(String orderCancelTime) {
            this.orderCancelTime = orderCancelTime;
        }

        public String getOrderFinishTime() {
            return orderFinishTime;
        }

        public void setOrderFinishTime(String orderFinishTime) {
            this.orderFinishTime = orderFinishTime;
        }

        public String getOrderReceiveTime() {
            return orderReceiveTime;
        }

        public void setOrderReceiveTime(String orderReceiveTime) {
            this.orderReceiveTime = orderReceiveTime;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<RecycleListDTO> getRecycleList() {
            return recycleList;
        }

        public void setRecycleList(List<RecycleListDTO> recycleList) {
            this.recycleList = recycleList;
        }

        public long getRefSiteId() {
            return refSiteId;
        }

        public void setRefSiteId(long refSiteId) {
            this.refSiteId = refSiteId;
        }

        public double getSettleTotalCashPrice() {
            return settleTotalCashPrice;
        }

        public void setSettleTotalCashPrice(double settleTotalCashPrice) {
            this.settleTotalCashPrice = settleTotalCashPrice;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
        }

        public static class MotormanRespDTO {
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


        public static class RecycleListDTO {
            /**
             * bargainCashPrice : (number)重新议价（元/kg）
             * bargainWeightNumber : (number)重新称重（kg）
             * categoryCashPrice : (number)分类现金价（元/kg）
             * createTime : (date-time)创建时间
             * garbageCategoryName : (string)垃圾分类名称
             * id : (int64)订单回收ID
             * isAll : (boolean)是否全部：{false：部分 true：全部}
             * isBargain : (boolean)是否重新议价：{false：否 true：是}
             * recylePrice : (number)回收价（元/kg）
             * recyleWeightNumber : (number)回收重量（kg）
             * refCleanOrderId : (int64)清运订单回收ID
             * refGarbageCategoryId : (int32)垃圾分类ID
             */

            private double bargainCashPrice;
            private double bargainWeightNumber;
            private double categoryCashPrice;
            private String createTime;
            private String garbageCategoryName;
            private long id;
            private boolean isAll;
            private boolean isBargain;
            private double recylePrice;
            private double recyleWeightNumber;
            private long refCleanOrderId;
            private int refGarbageCategoryId;

            public double getBargainCashPrice() {
                return bargainCashPrice;
            }

            public void setBargainCashPrice(double bargainCashPrice) {
                this.bargainCashPrice = bargainCashPrice;
            }

            public double getBargainWeightNumber() {
                return bargainWeightNumber;
            }

            public void setBargainWeightNumber(double bargainWeightNumber) {
                this.bargainWeightNumber = bargainWeightNumber;
            }

            public double getCategoryCashPrice() {
                return categoryCashPrice;
            }

            public void setCategoryCashPrice(double categoryCashPrice) {
                this.categoryCashPrice = categoryCashPrice;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getGarbageCategoryName() {
                return garbageCategoryName;
            }

            public void setGarbageCategoryName(String garbageCategoryName) {
                this.garbageCategoryName = garbageCategoryName;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isAll() {
                return isAll;
            }

            public void setAll(boolean all) {
                isAll = all;
            }

            public boolean isBargain() {
                return isBargain;
            }

            public void setBargain(boolean bargain) {
                isBargain = bargain;
            }

            public double getRecylePrice() {
                return recylePrice;
            }

            public void setRecylePrice(double recylePrice) {
                this.recylePrice = recylePrice;
            }

            public double getRecyleWeightNumber() {
                return recyleWeightNumber;
            }

            public void setRecyleWeightNumber(double recyleWeightNumber) {
                this.recyleWeightNumber = recyleWeightNumber;
            }

            public long getRefCleanOrderId() {
                return refCleanOrderId;
            }

            public void setRefCleanOrderId(long refCleanOrderId) {
                this.refCleanOrderId = refCleanOrderId;
            }

            public int getRefGarbageCategoryId() {
                return refGarbageCategoryId;
            }

            public void setRefGarbageCategoryId(int refGarbageCategoryId) {
                this.refGarbageCategoryId = refGarbageCategoryId;
            }
        }
    }
}
