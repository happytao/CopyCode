package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/6/2
 */
public class IndexBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"bannerList":[{"imgLinkLocation":"(string)图片链接地址","imgName":"(string)图片名称","imgUrl":"(string)图片路径"}],"goodsList":[{"goodsCashPrice":"(number)现金售价/元","goodsCostPrice":"(number)成本价/元","goodsDesc":"(string)商品描述","goodsLabel":"(string)商品标签","goodsName":"(string)商品名称","goodsRemake":"(string)商品备注","goodsResourceUrl":"(string)商品主图url","goodsScorePrice":"(int32)积分售价","goodsSellType":"(int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}","goodsSubhead":"(string)商品副标题","id":"(int64)商品ID","refCategoryIdOne":"(int32)商品一级类别ID","refCategoryIdTwo":"(int32)商品二级类别ID"}],"newsList":[{"createTime":"(date-time)创建时间","newsContent":"(string)资讯内容","newsResourceUrl":"(string)资讯主图URL","newsTitle":"(string)资讯标题","newsType":"(int32)资讯类型：{1.新闻 2.软文知识点}"}]}
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
        private List<BannerListDTO> bannerList;
        private List<GoodsListDTO> goodsList;
        private List<NewsListDTO> newsList;

        public List<BannerListDTO> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListDTO> bannerList) {
            this.bannerList = bannerList;
        }

        public List<GoodsListDTO> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListDTO> goodsList) {
            this.goodsList = goodsList;
        }

        public List<NewsListDTO> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListDTO> newsList) {
            this.newsList = newsList;
        }

        public static class BannerListDTO {
            /**
             * imgLinkLocation : (string)图片链接地址
             * imgName : (string)图片名称
             * imgUrl : (string)图片路径
             */

            private String imgLinkLocation;
            private String imgName;
            private String imgUrl;

            public String getImgLinkLocation() {
                return imgLinkLocation;
            }

            public void setImgLinkLocation(String imgLinkLocation) {
                this.imgLinkLocation = imgLinkLocation;
            }

            public String getImgName() {
                return imgName;
            }

            public void setImgName(String imgName) {
                this.imgName = imgName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }


        public static class GoodsListDTO {
            /**
             * goodsCashPrice : (number)现金售价/元
             * goodsCostPrice : (number)成本价/元
             * goodsDesc : (string)商品描述
             * goodsLabel : (string)商品标签
             * goodsName : (string)商品名称
             * goodsRemake : (string)商品备注
             * goodsResourceUrl : (string)商品主图url
             * goodsScorePrice : (int32)积分售价
             * goodsSellType : (int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}
             * goodsSubhead : (string)商品副标题
             * id : (int64)商品ID
             * refCategoryIdOne : (int32)商品一级类别ID
             * refCategoryIdTwo : (int32)商品二级类别ID
             */

            private double goodsCashPrice;
            private double goodsCostPrice;
            private String goodsDesc;
            private String goodsLabel;
            private String goodsName;
            private String goodsRemake;
            private String goodsResourceUrl;
            private int goodsScorePrice;
            private int goodsSellType;
            private String goodsSubhead;
            private long id;
            private int refCategoryIdOne;
            private int refCategoryIdTwo;

            public double getGoodsCashPrice() {
                return goodsCashPrice;
            }

            public void setGoodsCashPrice(double goodsCashPrice) {
                this.goodsCashPrice = goodsCashPrice;
            }

            public double getGoodsCostPrice() {
                return goodsCostPrice;
            }

            public void setGoodsCostPrice(double goodsCostPrice) {
                this.goodsCostPrice = goodsCostPrice;
            }

            public String getGoodsDesc() {
                return goodsDesc;
            }

            public void setGoodsDesc(String goodsDesc) {
                this.goodsDesc = goodsDesc;
            }

            public String getGoodsLabel() {
                return goodsLabel;
            }

            public void setGoodsLabel(String goodsLabel) {
                this.goodsLabel = goodsLabel;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsRemake() {
                return goodsRemake;
            }

            public void setGoodsRemake(String goodsRemake) {
                this.goodsRemake = goodsRemake;
            }

            public String getGoodsResourceUrl() {
                return goodsResourceUrl;
            }

            public void setGoodsResourceUrl(String goodsResourceUrl) {
                this.goodsResourceUrl = goodsResourceUrl;
            }

            public int getGoodsScorePrice() {
                return goodsScorePrice;
            }

            public void setGoodsScorePrice(int goodsScorePrice) {
                this.goodsScorePrice = goodsScorePrice;
            }

            public int getGoodsSellType() {
                return goodsSellType;
            }

            public void setGoodsSellType(int goodsSellType) {
                this.goodsSellType = goodsSellType;
            }

            public String getGoodsSubhead() {
                return goodsSubhead;
            }

            public void setGoodsSubhead(String goodsSubhead) {
                this.goodsSubhead = goodsSubhead;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getRefCategoryIdOne() {
                return refCategoryIdOne;
            }

            public void setRefCategoryIdOne(int refCategoryIdOne) {
                this.refCategoryIdOne = refCategoryIdOne;
            }

            public int getRefCategoryIdTwo() {
                return refCategoryIdTwo;
            }

            public void setRefCategoryIdTwo(int refCategoryIdTwo) {
                this.refCategoryIdTwo = refCategoryIdTwo;
            }
        }


        public static class NewsListDTO {
            /**
             * createTime : (date-time)创建时间
             * newsContent : (string)资讯内容
             * newsResourceUrl : (string)资讯主图URL
             * newsTitle : (string)资讯标题
             * newsType : (int32)资讯类型：{1.新闻 2.软文知识点}
             */

            private String createTime;
            private String newsContent;
            private String newsResourceUrl;
            private String newsTitle;
            private int newsType;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getNewsContent() {
                return newsContent;
            }

            public void setNewsContent(String newsContent) {
                this.newsContent = newsContent;
            }

            public String getNewsResourceUrl() {
                return newsResourceUrl;
            }

            public void setNewsResourceUrl(String newsResourceUrl) {
                this.newsResourceUrl = newsResourceUrl;
            }

            public String getNewsTitle() {
                return newsTitle;
            }

            public void setNewsTitle(String newsTitle) {
                this.newsTitle = newsTitle;
            }

            public int getNewsType() {
                return newsType;
            }

            public void setNewsType(int newsType) {
                this.newsType = newsType;
            }
        }
    }
}
