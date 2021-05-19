package com.xt.garbage.bean.garbage;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/5/19
 */
public class OrderSubscribeDetailsBean  {


    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"contactMobile":"(string)联系电话","contactName":"(string)联系人名称","createTime":"(date-time)订单创建时间","detailAddress":"(string)详细地址","id":"(int64)订单ID","orderCancelExplain":"(string)订单取消说明","orderCancelReason":"(boolean)订单取消理由：{false.价格不妥 true.其他原因}","orderCancelTime":"(date-time)订单取消时间","orderFinishTime":"(date-time)订单完成时间","orderReceiveTime":"(date-time)订单接单时间","orderStatus":"(int32)订单状态：{1.（用户）待接单 2.（驿站）待上门 3.（驿站）已到达 4.已完成 5.（驿站）交易取消 6.（驿站）拒单 7.（订单）超时取消 8.用户取消}","recycleList":[{"garbageCategoryName":"(string)垃圾分类名称","publicCashPrice":"(number)公示价/现金","publicScorePrice":"(int32)公示价/积分","refGarbageCategoryId":"(int32)垃圾分类ID","sellCashPrice":"(number)销售价/现金","sellScorePrice":"(int32)销售价/积分","totalCashPrice":"(number)总价/现金","totalScorePrice":"(int32)总价/积分","weightNumber":"(number)重量/次（kg/次）"}],"refSiteId":"(int64)驿站ID","settleTotalCashPrice":"(number)结算总现金价","settleTotalScorePrice":"(int32)结算总积分价","settleType":"(boolean)结算方式：{false：积分结算 true：现金结算}","siteResp":{"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"},"siteUserInfo":{"cleanTonnage":"(string)清运载重量","detailAddress":"(string)详细地址","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}","refSiteId":"(int64)站点ID","regionRespDTO":{"areaCode":"(int64)区代码","areaName":"(string)区名称","cityCode":"(int64)市代码","cityName":"(string)市名称","provinceCode":"(int64)省代码","provinceName":"(string)省名称"},"totalScore":"(int32)总积分","usableScore":"(int32)可用积分","userType":"(int32)用户类型:{1.普通用户 2.住户/业主 3.公共机构用户 4.驿站 5.司机}"},"visitTime":"(string)上门时间"}
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
         * refSiteId : (int64)驿站ID
         * settleTotalCashPrice : (number)结算总现金价
         * settleTotalScorePrice : (int32)结算总积分价
         * settleType : (boolean)结算方式：{false：积分结算 true：现金结算}
         * siteResp : {"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"}
         * siteUserInfo : {"cleanTonnage":"(string)清运载重量","detailAddress":"(string)详细地址","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}","refSiteId":"(int64)站点ID","regionRespDTO":{"areaCode":"(int64)区代码","areaName":"(string)区名称","cityCode":"(int64)市代码","cityName":"(string)市名称","provinceCode":"(int64)省代码","provinceName":"(string)省名称"},"totalScore":"(int32)总积分","usableScore":"(int32)可用积分","userType":"(int32)用户类型:{1.普通用户 2.住户/业主 3.公共机构用户 4.驿站 5.司机}"}
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
        private long refSiteId;
        private double settleTotalCashPrice;
        private int settleTotalScorePrice;
        private boolean settleType;
        private SiteRespDTO siteResp;
        private SiteUserInfoDTO siteUserInfo;
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

        public SiteRespDTO getSiteResp() {
            return siteResp;
        }

        public void setSiteResp(SiteRespDTO siteResp) {
            this.siteResp = siteResp;
        }

        public SiteUserInfoDTO getSiteUserInfo() {
            return siteUserInfo;
        }

        public void setSiteUserInfo(SiteUserInfoDTO siteUserInfo) {
            this.siteUserInfo = siteUserInfo;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
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


        public static class SiteUserInfoDTO {
            /**
             * cleanTonnage : (string)清运载重量
             * detailAddress : (string)详细地址
             * finishOrderNum : (int32)完成订单数：{驿站人员/司机}
             * head : (string)头像
             * id : (int64)用户ID
             * licensePlate : (string)汽车牌照
             * mobile : (string)手机号
             * nickName : (string)昵称
             * refRegionId : (int64)区域ID：{省/市/区}
             * refSiteId : (int64)站点ID
             * regionRespDTO : {"areaCode":"(int64)区代码","areaName":"(string)区名称","cityCode":"(int64)市代码","cityName":"(string)市名称","provinceCode":"(int64)省代码","provinceName":"(string)省名称"}
             * totalScore : (int32)总积分
             * usableScore : (int32)可用积分
             * userType : (int32)用户类型:{1.普通用户 2.住户/业主 3.公共机构用户 4.驿站 5.司机}
             */

            private String cleanTonnage;
            private String detailAddress;
            private int finishOrderNum;
            private String head;
            private long id;
            private String licensePlate;
            private String mobile;
            private String nickName;
            private long refRegionId;
            private long refSiteId;
            private RegionRespDTODTO regionRespDTO;
            private int totalScore;
            private int usableScore;
            private int userType;

            public String getCleanTonnage() {
                return cleanTonnage;
            }

            public void setCleanTonnage(String cleanTonnage) {
                this.cleanTonnage = cleanTonnage;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
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

            public long getRefSiteId() {
                return refSiteId;
            }

            public void setRefSiteId(long refSiteId) {
                this.refSiteId = refSiteId;
            }

            public RegionRespDTODTO getRegionRespDTO() {
                return regionRespDTO;
            }

            public void setRegionRespDTO(RegionRespDTODTO regionRespDTO) {
                this.regionRespDTO = regionRespDTO;
            }

            public int getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(int totalScore) {
                this.totalScore = totalScore;
            }

            public int getUsableScore() {
                return usableScore;
            }

            public void setUsableScore(int usableScore) {
                this.usableScore = usableScore;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public static class RegionRespDTODTO {
                /**
                 * areaCode : (int64)区代码
                 * areaName : (string)区名称
                 * cityCode : (int64)市代码
                 * cityName : (string)市名称
                 * provinceCode : (int64)省代码
                 * provinceName : (string)省名称
                 */

                private long areaCode;
                private String areaName;
                private long cityCode;
                private String cityName;
                private long provinceCode;
                private String provinceName;

                public long getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(long areaCode) {
                    this.areaCode = areaCode;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public long getCityCode() {
                    return cityCode;
                }

                public void setCityCode(long cityCode) {
                    this.cityCode = cityCode;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public long getProvinceCode() {
                    return provinceCode;
                }

                public void setProvinceCode(long provinceCode) {
                    this.provinceCode = provinceCode;
                }

                public String getProvinceName() {
                    return provinceName;
                }

                public void setProvinceName(String provinceName) {
                    this.provinceName = provinceName;
                }
            }
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
            private double publicCashPrice;
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

            public double getPublicCashPrice() {
                return publicCashPrice;
            }

            public void setPublicCashPrice(double publicCashPrice) {
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
