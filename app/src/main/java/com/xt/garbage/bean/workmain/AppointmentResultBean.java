package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/29
 */
public class AppointmentResultBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : [{"contactMobile":"(string)联系电话","contactName":"(string)联系人名称","createTime":"(date-time)订单创建时间","detailAddress":"(string)详细地址","id":"(int64)订单ID","orderCancelExplain":"(string)订单取消说明","orderCancelReason":"(boolean)订单取消理由：{false.价格不妥 true.其他原因}","orderCancelTime":"(date-time)订单取消时间","orderFinishTime":"(date-time)订单完成时间","orderReceiveTime":"(date-time)订单接单时间","orderStatus":"(int32)订单状态：{1.（用户）待接单 2.（驿站）待上门 3.（驿站）已到达 4.已完成 5.（驿站）交易取消 6.（驿站）拒单 7.（订单）超时取消 8.用户取消}","recycleList":[{"garbageCategoryName":"(string)垃圾分类名称","publicCashPrice":"(number)公示价/现金","publicScorePrice":"(int32)公示价/积分","refGarbageCategoryId":"(int32)垃圾分类ID","sellCashPrice":"(number)销售价/现金","sellScorePrice":"(int32)销售价/积分","totalCashPrice":"(number)总价/现金","totalScorePrice":"(int32)总价/积分","weightNumber":"(number)重量/次（kg/次）"}],"settleTotalCashPrice":"(number)结算总现金价","settleTotalScorePrice":"(int32)结算总积分价","settleType":"(boolean)结算方式：{false：积分结算 true：现金结算}","visitTime":"(string)上门时间"}]
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
         * contactMobile : (string)联系电话
         * contactName : (string)联系人名称
         * createTime : (date-time)订单创建时间
         * detailAddress : (string)详细地址
         * id : (int64)订单ID
         * orderCancelExplain : (string)订单取消说明
         * orderCancelReason : (boolean)订单取消理由：{false.价格不妥 true.其他原因}
         * orderCancelTime : (date-time)订单取消时间
         * orderFinishTime : (date-time)订单完成时间
         * orderReceiveTime : (date-time)订单接单时间
         * orderStatus : (int32)订单状态：{1.（用户）待接单 2.（驿站）待上门 3.（驿站）已到达 4.已完成 5.（驿站）交易取消 6.（驿站）拒单 7.（订单）超时取消 8.用户取消}
         * recycleList : [{"garbageCategoryName":"(string)垃圾分类名称","publicCashPrice":"(number)公示价/现金","publicScorePrice":"(int32)公示价/积分","refGarbageCategoryId":"(int32)垃圾分类ID","sellCashPrice":"(number)销售价/现金","sellScorePrice":"(int32)销售价/积分","totalCashPrice":"(number)总价/现金","totalScorePrice":"(int32)总价/积分","weightNumber":"(number)重量/次（kg/次）"}]
         * settleTotalCashPrice : (number)结算总现金价
         * settleTotalScorePrice : (int32)结算总积分价
         * settleType : (boolean)结算方式：{false：积分结算 true：现金结算}
         * visitTime : (string)上门时间
         */

        private String contactMobile;
        private String contactName;
        private String createTime;
        private String detailAddress;
        private long id;
        private String orderCancelExplain;
        private boolean orderCancelReason;
        private String orderCancelTime;
        private String orderFinishTime;
        private String orderReceiveTime;
        private int orderStatus;
        private List<RecycleListDTO> recycleList;
        private double settleTotalCashPrice;
        private int settleTotalScorePrice;
        private boolean settleType;
        private String visitTime;

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrderCancelExplain() {
            return orderCancelExplain;
        }

        public void setOrderCancelExplain(String orderCancelExplain) {
            this.orderCancelExplain = orderCancelExplain;
        }

        public boolean isOrderCancelReason() {
            return orderCancelReason;
        }

        public void setOrderCancelReason(boolean orderCancelReason) {
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

        public double getSettleTotalCashPrice() {
            return settleTotalCashPrice;
        }

        public void setSettleTotalCashPrice(double settleTotalCashPrice) {
            this.settleTotalCashPrice = settleTotalCashPrice;
        }

        public int getSettleTotalScorePrice() {
            return settleTotalScorePrice;
        }

        public void setSettleTotalScorePrice(int settleTotalScorePrice) {
            this.settleTotalScorePrice = settleTotalScorePrice;
        }

        public boolean isSettleType() {
            return settleType;
        }

        public void setSettleType(boolean settleType) {
            this.settleType = settleType;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public static class RecycleListDTO {
            /**
             * garbageCategoryName : (string)垃圾分类名称
             * publicCashPrice : (number)公示价/现金
             * publicScorePrice : (int32)公示价/积分
             * refGarbageCategoryId : (int32)垃圾分类ID
             * sellCashPrice : (number)销售价/现金
             * sellScorePrice : (int32)销售价/积分
             * totalCashPrice : (number)总价/现金
             * totalScorePrice : (int32)总价/积分
             * weightNumber : (number)重量/次（kg/次）
             */

            private String garbageCategoryName;
            private String publicCashPrice;
            private int publicScorePrice;
            private int refGarbageCategoryId;
            private double sellCashPrice;
            private int sellScorePrice;
            private double totalCashPrice;
            private int totalScorePrice;
            private double weightNumber;

            public String getGarbageCategoryName() {
                return garbageCategoryName;
            }

            public void setGarbageCategoryName(String garbageCategoryName) {
                this.garbageCategoryName = garbageCategoryName;
            }

            public String getPublicCashPrice() {
                return publicCashPrice;
            }

            public void setPublicCashPrice(String publicCashPrice) {
                this.publicCashPrice = publicCashPrice;
            }

            public int getPublicScorePrice() {
                return publicScorePrice;
            }

            public void setPublicScorePrice(int publicScorePrice) {
                this.publicScorePrice = publicScorePrice;
            }

            public int getRefGarbageCategoryId() {
                return refGarbageCategoryId;
            }

            public void setRefGarbageCategoryId(int refGarbageCategoryId) {
                this.refGarbageCategoryId = refGarbageCategoryId;
            }

            public double getSellCashPrice() {
                return sellCashPrice;
            }

            public void setSellCashPrice(double sellCashPrice) {
                this.sellCashPrice = sellCashPrice;
            }

            public int getSellScorePrice() {
                return sellScorePrice;
            }

            public void setSellScorePrice(int sellScorePrice) {
                this.sellScorePrice = sellScorePrice;
            }

            public double getTotalCashPrice() {
                return totalCashPrice;
            }

            public void setTotalCashPrice(double totalCashPrice) {
                this.totalCashPrice = totalCashPrice;
            }

            public int getTotalScorePrice() {
                return totalScorePrice;
            }

            public void setTotalScorePrice(int totalScorePrice) {
                this.totalScorePrice = totalScorePrice;
            }

            public double getWeightNumber() {
                return weightNumber;
            }

            public void setWeightNumber(double weightNumber) {
                this.weightNumber = weightNumber;
            }
        }
    }
}
