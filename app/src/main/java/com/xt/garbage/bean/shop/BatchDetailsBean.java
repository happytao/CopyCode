package com.xt.garbage.bean.shop;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/3
 */
public class BatchDetailsBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"addressResp":{"area":"(string)区","city":"(string)市","detailAddress":"(string)详细地址","id":"(int64)收货地址ID","isUse":"(boolean)是否常用地址","mobile":"(string)手机号","name":"(string)姓名","province":"(string)省","remark":"(string)备注","telephone":"(string)座机"},"childOrderRespList":[{"buyNum":"(int32)购买数量","consumeCash":"(number)消费现金","consumeScore":"(int32)消费积分","goodsSellType":"(int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}","isEvaluate":"(boolean)是否评价","orderSpecList":[{"refAttrName":"(string)属性名称","refSpecAttrId":"(int32)规格属性ID","refSpecId":"(int32)规格ID","refSpecName":"(string)规格名称"}],"refGoodsCashPrice":"(number)商品现金单价","refGoodsId":"(int64)商品ID","refGoodsName":"(string)商品名称","refGoodsResourceUrl":"(string)商品主图资源URL","refGoodsScorePrice":"(int32)商品积分单价"}],"createTime":"(date-time)创建时间","distributionWay":"(boolean)配送方式：{0.送货上门 1.驿站自提}","freightChargeVal":"(number)运费结算值","freightChargeWay":"(boolean)运费结算方式：{false.积分 true.现金}","id":"(string)主订单ID","orderCancelCause":"(string)订单取消原因","orderCancelTime":"(date-time)订单取消时间","orderCancelType":"(int32)订单取消类型：{0.超时取消 1.用户取消 2.驿站取消}","orderExpireTime":"(date-time)订单过期时间","orderPayTime":"(date-time)订单支付时间","orderPayType":"(int32)订单支付类型：{1.微信 2.支付宝}","orderPickTime":"(date-time)订单取货时间","orderRemark":"(string)订单备注","orderStatus":"(int32)订单状态：{1.待确认 2.待支付 3.已支付 4.已取消 5.已完成}","pickResp":{"pickLinkAddress":"(string)取货链接地址（生成二维码）","pickNo":"(string)取货号","pickSiteName":"(string)取货驿站名称"},"receiveStatus":"(int32)收货状态：{1.待取货（驿站自提） 2.配送中（驿站配送） 3.已收货}","siteResp":{"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"},"systemFreightCharge":{"freightChargeValCash":"(number)运费结算值/现金","freightChargeValScore":"(int32)运费结算值/积分"},"totalConsumeCash":"(number)总消费现金","totalConsumeScore":"(int32)总消费积分"}
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private ResultDTO result;
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

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
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
         * addressResp : {"area":"(string)区","city":"(string)市","detailAddress":"(string)详细地址","id":"(int64)收货地址ID","isUse":"(boolean)是否常用地址","mobile":"(string)手机号","name":"(string)姓名","province":"(string)省","remark":"(string)备注","telephone":"(string)座机"}
         * childOrderRespList : [{"buyNum":"(int32)购买数量","consumeCash":"(number)消费现金","consumeScore":"(int32)消费积分","goodsSellType":"(int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}","isEvaluate":"(boolean)是否评价","orderSpecList":[{"refAttrName":"(string)属性名称","refSpecAttrId":"(int32)规格属性ID","refSpecId":"(int32)规格ID","refSpecName":"(string)规格名称"}],"refGoodsCashPrice":"(number)商品现金单价","refGoodsId":"(int64)商品ID","refGoodsName":"(string)商品名称","refGoodsResourceUrl":"(string)商品主图资源URL","refGoodsScorePrice":"(int32)商品积分单价"}]
         * createTime : (date-time)创建时间
         * distributionWay : (boolean)配送方式：{0.送货上门 1.驿站自提}
         * freightChargeVal : (number)运费结算值
         * freightChargeWay : (boolean)运费结算方式：{false.积分 true.现金}
         * id : (string)主订单ID
         * orderCancelCause : (string)订单取消原因
         * orderCancelTime : (date-time)订单取消时间
         * orderCancelType : (int32)订单取消类型：{0.超时取消 1.用户取消 2.驿站取消}
         * orderExpireTime : (date-time)订单过期时间
         * orderPayTime : (date-time)订单支付时间
         * orderPayType : (int32)订单支付类型：{1.微信 2.支付宝}
         * orderPickTime : (date-time)订单取货时间
         * orderRemark : (string)订单备注
         * orderStatus : (int32)订单状态：{1.待确认 2.待支付 3.已支付 4.已取消 5.已完成}
         * pickResp : {"pickLinkAddress":"(string)取货链接地址（生成二维码）","pickNo":"(string)取货号","pickSiteName":"(string)取货驿站名称"}
         * receiveStatus : (int32)收货状态：{1.待取货（驿站自提） 2.配送中（驿站配送） 3.已收货}
         * siteResp : {"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"}
         * systemFreightCharge : {"freightChargeValCash":"(number)运费结算值/现金","freightChargeValScore":"(int32)运费结算值/积分"}
         * totalConsumeCash : (number)总消费现金
         * totalConsumeScore : (int32)总消费积分
         */

        private AddressRespDTO addressResp;
        private List<ChildOrderRespListDTO> childOrderRespList;
        private String createTime;
        private boolean distributionWay;
        private double freightChargeVal;
        private boolean freightChargeWay;
        private String id;
        private String orderCancelCause;
        private String orderCancelTime;
        private int orderCancelType;
        private String orderExpireTime;
        private String orderPayTime;
        private int orderPayType;
        private String orderPickTime;
        private String orderRemark;
        private int orderStatus;
        private PickRespDTO pickResp;
        private int receiveStatus;
        private SiteRespDTO siteResp;
        private SystemFreightChargeDTO systemFreightCharge;
        private double totalConsumeCash;
        private int totalConsumeScore;

        public AddressRespDTO getAddressResp() {
            return addressResp;
        }

        public void setAddressResp(AddressRespDTO addressResp) {
            this.addressResp = addressResp;
        }

        public List<ChildOrderRespListDTO> getChildOrderRespList() {
            return childOrderRespList;
        }

        public void setChildOrderRespList(List<ChildOrderRespListDTO> childOrderRespList) {
            this.childOrderRespList = childOrderRespList;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isDistributionWay() {
            return distributionWay;
        }

        public void setDistributionWay(boolean distributionWay) {
            this.distributionWay = distributionWay;
        }

        public double getFreightChargeVal() {
            return freightChargeVal;
        }

        public void setFreightChargeVal(double freightChargeVal) {
            this.freightChargeVal = freightChargeVal;
        }

        public boolean isFreightChargeWay() {
            return freightChargeWay;
        }

        public void setFreightChargeWay(boolean freightChargeWay) {
            this.freightChargeWay = freightChargeWay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderCancelCause() {
            return orderCancelCause;
        }

        public void setOrderCancelCause(String orderCancelCause) {
            this.orderCancelCause = orderCancelCause;
        }

        public String getOrderCancelTime() {
            return orderCancelTime;
        }

        public void setOrderCancelTime(String orderCancelTime) {
            this.orderCancelTime = orderCancelTime;
        }

        public int getOrderCancelType() {
            return orderCancelType;
        }

        public void setOrderCancelType(int orderCancelType) {
            this.orderCancelType = orderCancelType;
        }

        public String getOrderExpireTime() {
            return orderExpireTime;
        }

        public void setOrderExpireTime(String orderExpireTime) {
            this.orderExpireTime = orderExpireTime;
        }

        public String getOrderPayTime() {
            return orderPayTime;
        }

        public void setOrderPayTime(String orderPayTime) {
            this.orderPayTime = orderPayTime;
        }

        public int getOrderPayType() {
            return orderPayType;
        }

        public void setOrderPayType(int orderPayType) {
            this.orderPayType = orderPayType;
        }

        public String getOrderPickTime() {
            return orderPickTime;
        }

        public void setOrderPickTime(String orderPickTime) {
            this.orderPickTime = orderPickTime;
        }

        public String getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(String orderRemark) {
            this.orderRemark = orderRemark;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public PickRespDTO getPickResp() {
            return pickResp;
        }

        public void setPickResp(PickRespDTO pickResp) {
            this.pickResp = pickResp;
        }

        public int getReceiveStatus() {
            return receiveStatus;
        }

        public void setReceiveStatus(int receiveStatus) {
            this.receiveStatus = receiveStatus;
        }

        public SiteRespDTO getSiteResp() {
            return siteResp;
        }

        public void setSiteResp(SiteRespDTO siteResp) {
            this.siteResp = siteResp;
        }

        public SystemFreightChargeDTO getSystemFreightCharge() {
            return systemFreightCharge;
        }

        public void setSystemFreightCharge(SystemFreightChargeDTO systemFreightCharge) {
            this.systemFreightCharge = systemFreightCharge;
        }

        public double getTotalConsumeCash() {
            return totalConsumeCash;
        }

        public void setTotalConsumeCash(double totalConsumeCash) {
            this.totalConsumeCash = totalConsumeCash;
        }

        public int getTotalConsumeScore() {
            return totalConsumeScore;
        }

        public void setTotalConsumeScore(int totalConsumeScore) {
            this.totalConsumeScore = totalConsumeScore;
        }

        public static class AddressRespDTO {
            /**
             * area : (string)区
             * city : (string)市
             * detailAddress : (string)详细地址
             * id : (int64)收货地址ID
             * isUse : (boolean)是否常用地址
             * mobile : (string)手机号
             * name : (string)姓名
             * province : (string)省
             * remark : (string)备注
             * telephone : (string)座机
             */

            private String area;
            private String city;
            private String detailAddress;
            private long id;
            private boolean isUse;
            private String mobile;
            private String name;
            private String province;
            private String remark;
            private String telephone;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public boolean isUse() {
                return isUse;
            }

            public void setUse(boolean use) {
                isUse = use;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }

        public static class PickRespDTO {
            /**
             * pickLinkAddress : (string)取货链接地址（生成二维码）
             * pickNo : (string)取货号
             * pickSiteName : (string)取货驿站名称
             */

            private String pickLinkAddress;
            private String pickNo;
            private String pickSiteName;

            public String getPickLinkAddress() {
                return pickLinkAddress;
            }

            public void setPickLinkAddress(String pickLinkAddress) {
                this.pickLinkAddress = pickLinkAddress;
            }

            public String getPickNo() {
                return pickNo;
            }

            public void setPickNo(String pickNo) {
                this.pickNo = pickNo;
            }

            public String getPickSiteName() {
                return pickSiteName;
            }

            public void setPickSiteName(String pickSiteName) {
                this.pickSiteName = pickSiteName;
            }
        }


        public static class SiteRespDTO {
            /**
             * id : (int64)站点ID
             * siteContactMobile : (string)驿站联系人电话
             * siteContactName : (string)驿站联系人名称
             * siteDetailLocation : (string)站点详细位置
             * siteName : (string)驿站名称
             * siteResourceUrl : (string)驿站图片资源地址
             * state : (int32)站点状态：{1.正常 2.停用 3.关闭}
             */

            private long id;
            private String siteContactMobile;
            private String siteContactName;
            private String siteDetailLocation;
            private String siteName;
            private String siteResourceUrl;
            private int state;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getSiteContactMobile() {
                return siteContactMobile;
            }

            public void setSiteContactMobile(String siteContactMobile) {
                this.siteContactMobile = siteContactMobile;
            }

            public String getSiteContactName() {
                return siteContactName;
            }

            public void setSiteContactName(String siteContactName) {
                this.siteContactName = siteContactName;
            }

            public String getSiteDetailLocation() {
                return siteDetailLocation;
            }

            public void setSiteDetailLocation(String siteDetailLocation) {
                this.siteDetailLocation = siteDetailLocation;
            }

            public String getSiteName() {
                return siteName;
            }

            public void setSiteName(String siteName) {
                this.siteName = siteName;
            }

            public String getSiteResourceUrl() {
                return siteResourceUrl;
            }

            public void setSiteResourceUrl(String siteResourceUrl) {
                this.siteResourceUrl = siteResourceUrl;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }


        public static class SystemFreightChargeDTO {
            /**
             * freightChargeValCash : (number)运费结算值/现金
             * freightChargeValScore : (int32)运费结算值/积分
             */

            private double freightChargeValCash;
            private int freightChargeValScore;

            public double getFreightChargeValCash() {
                return freightChargeValCash;
            }

            public void setFreightChargeValCash(double freightChargeValCash) {
                this.freightChargeValCash = freightChargeValCash;
            }

            public int getFreightChargeValScore() {
                return freightChargeValScore;
            }

            public void setFreightChargeValScore(int freightChargeValScore) {
                this.freightChargeValScore = freightChargeValScore;
            }
        }


        public static class ChildOrderRespListDTO {
            /**
             * buyNum : (int32)购买数量
             * consumeCash : (number)消费现金
             * consumeScore : (int32)消费积分
             * goodsSellType : (int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}
             * isEvaluate : (boolean)是否评价
             * orderSpecList : [{"refAttrName":"(string)属性名称","refSpecAttrId":"(int32)规格属性ID","refSpecId":"(int32)规格ID","refSpecName":"(string)规格名称"}]
             * refGoodsCashPrice : (number)商品现金单价
             * refGoodsId : (int64)商品ID
             * refGoodsName : (string)商品名称
             * refGoodsResourceUrl : (string)商品主图资源URL
             * refGoodsScorePrice : (int32)商品积分单价
             */

            private int buyNum;
            private double consumeCash;
            private int consumeScore;
            private int goodsSellType;
            private boolean isEvaluate;
            private List<OrderSpecListDTO> orderSpecList;
            private double refGoodsCashPrice;
            private long refGoodsId;
            private String refGoodsName;
            private String refGoodsResourceUrl;
            private int refGoodsScorePrice;

            public int getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(int buyNum) {
                this.buyNum = buyNum;
            }

            public double getConsumeCash() {
                return consumeCash;
            }

            public void setConsumeCash(double consumeCash) {
                this.consumeCash = consumeCash;
            }

            public int getConsumeScore() {
                return consumeScore;
            }

            public void setConsumeScore(int consumeScore) {
                this.consumeScore = consumeScore;
            }

            public int getGoodsSellType() {
                return goodsSellType;
            }

            public void setGoodsSellType(int goodsSellType) {
                this.goodsSellType = goodsSellType;
            }

            public boolean isEvaluate() {
                return isEvaluate;
            }

            public void setEvaluate(boolean evaluate) {
                isEvaluate = evaluate;
            }

            public List<OrderSpecListDTO> getOrderSpecList() {
                return orderSpecList;
            }

            public void setOrderSpecList(List<OrderSpecListDTO> orderSpecList) {
                this.orderSpecList = orderSpecList;
            }

            public double getRefGoodsCashPrice() {
                return refGoodsCashPrice;
            }

            public void setRefGoodsCashPrice(double refGoodsCashPrice) {
                this.refGoodsCashPrice = refGoodsCashPrice;
            }

            public long getRefGoodsId() {
                return refGoodsId;
            }

            public void setRefGoodsId(long refGoodsId) {
                this.refGoodsId = refGoodsId;
            }

            public String getRefGoodsName() {
                return refGoodsName;
            }

            public void setRefGoodsName(String refGoodsName) {
                this.refGoodsName = refGoodsName;
            }

            public String getRefGoodsResourceUrl() {
                return refGoodsResourceUrl;
            }

            public void setRefGoodsResourceUrl(String refGoodsResourceUrl) {
                this.refGoodsResourceUrl = refGoodsResourceUrl;
            }

            public int getRefGoodsScorePrice() {
                return refGoodsScorePrice;
            }

            public void setRefGoodsScorePrice(int refGoodsScorePrice) {
                this.refGoodsScorePrice = refGoodsScorePrice;
            }

            public static class OrderSpecListDTO {
                /**
                 * refAttrName : (string)属性名称
                 * refSpecAttrId : (int32)规格属性ID
                 * refSpecId : (int32)规格ID
                 * refSpecName : (string)规格名称
                 */

                private String refAttrName;
                private int refSpecAttrId;
                private int refSpecId;
                private String refSpecName;

                public String getRefAttrName() {
                    return refAttrName;
                }

                public void setRefAttrName(String refAttrName) {
                    this.refAttrName = refAttrName;
                }

                public int getRefSpecAttrId() {
                    return refSpecAttrId;
                }

                public void setRefSpecAttrId(int refSpecAttrId) {
                    this.refSpecAttrId = refSpecAttrId;
                }

                public int getRefSpecId() {
                    return refSpecId;
                }

                public void setRefSpecId(int refSpecId) {
                    this.refSpecId = refSpecId;
                }

                public String getRefSpecName() {
                    return refSpecName;
                }

                public void setRefSpecName(String refSpecName) {
                    this.refSpecName = refSpecName;
                }
            }
        }
    }
}
