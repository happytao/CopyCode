package com.xt.garbage.bean.login;

/**
 * @author:DIY
 * @date: 2021/3/23
 */


public class LoginBean {


    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"siteInfoRespDTO":{"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"},"token":"(string)Token","urlPrefix":"(string)图片资源路径前缀","userInfoRespDTO":{"cleanTonnage":"(string)清运载重量","detailAddress":"(string)详细地址","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}","refSiteId":"(int64)站点ID","regionRespDTO":{"areaCode":"(int64)区代码","areaName":"(string)区名称","cityCode":"(int64)市代码","cityName":"(string)市名称","provinceCode":"(int64)省代码","provinceName":"(string)省名称"},"totalScore":"(int32)总积分","usableScore":"(int32)可用积分","userType":"(int32)用户类型:{1.普通用户 2.住户/业主 3.公共机构用户 4.驿站 5.司机}"}}
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
         * siteInfoRespDTO : {"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"}
         * token : (string)Token
         * urlPrefix : (string)图片资源路径前缀
         * userInfoRespDTO : {"cleanTonnage":"(string)清运载重量","detailAddress":"(string)详细地址","finishOrderNum":"(int32)完成订单数：{驿站人员/司机}","head":"(string)头像","id":"(int64)用户ID","licensePlate":"(string)汽车牌照","mobile":"(string)手机号","nickName":"(string)昵称","refRegionId":"(int64)区域ID：{省/市/区}","refSiteId":"(int64)站点ID","regionRespDTO":{"areaCode":"(int64)区代码","areaName":"(string)区名称","cityCode":"(int64)市代码","cityName":"(string)市名称","provinceCode":"(int64)省代码","provinceName":"(string)省名称"},"totalScore":"(int32)总积分","usableScore":"(int32)可用积分","userType":"(int32)用户类型:{1.普通用户 2.住户/业主 3.公共机构用户 4.驿站 5.司机}"}
         */

        private SiteInfoRespDTODTO siteInfoRespDTO;
        private String token;
        private String urlPrefix;
        private UserInfoRespDTODTO userInfoRespDTO;

        public SiteInfoRespDTODTO getSiteInfoRespDTO() {
            return siteInfoRespDTO;
        }

        public void setSiteInfoRespDTO(SiteInfoRespDTODTO siteInfoRespDTO) {
            this.siteInfoRespDTO = siteInfoRespDTO;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        public UserInfoRespDTODTO getUserInfoRespDTO() {
            return userInfoRespDTO;
        }

        public void setUserInfoRespDTO(UserInfoRespDTODTO userInfoRespDTO) {
            this.userInfoRespDTO = userInfoRespDTO;
        }

        public static class SiteInfoRespDTODTO {
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


        public static class UserInfoRespDTODTO {
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
    }
}
