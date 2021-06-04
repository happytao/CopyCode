package com.xt.garbage.bean.shop;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/6/2
 */
public class HomeShopBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"hotGoodsResp":[{"goodsCashPrice":"(number)现金售价/元","goodsCostPrice":"(number)成本价/元","goodsDesc":"(string)商品描述","goodsLabel":"(string)商品标签","goodsName":"(string)商品名称","goodsRemake":"(string)商品备注","goodsResourceUrl":"(string)商品主图url","goodsScorePrice":"(int32)积分售价","goodsSellType":"(int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}","goodsSubhead":"(string)商品副标题","id":"(int64)商品ID","refCategoryIdOne":"(int32)商品一级类别ID","refCategoryIdTwo":"(int32)商品二级类别ID"}],"strongGoodsResp":[{"goodsCashPrice":"(number)现金售价/元","goodsCostPrice":"(number)成本价/元","goodsDesc":"(string)商品描述","goodsLabel":"(string)商品标签","goodsName":"(string)商品名称","goodsRemake":"(string)商品备注","goodsResourceUrl":"(string)商品主图url","goodsScorePrice":"(int32)积分售价","goodsSellType":"(int32)商品售卖类型：{1.积分 2.现金 3.积分+现金}","goodsSubhead":"(string)商品副标题","id":"(int64)商品ID","refCategoryIdOne":"(int32)商品一级类别ID","refCategoryIdTwo":"(int32)商品二级类别ID"}]}
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
        private List<GoodsRespDTO> hotGoodsResp;
        private List<GoodsRespDTO> strongGoodsResp;

        public List<GoodsRespDTO> getHotGoodsResp() {
            return hotGoodsResp;
        }

        public void setHotGoodsResp(List<GoodsRespDTO> hotGoodsResp) {
            this.hotGoodsResp = hotGoodsResp;
        }

        public List<GoodsRespDTO> getStrongGoodsResp() {
            return strongGoodsResp;
        }

        public void setStrongGoodsResp(List<GoodsRespDTO> strongGoodsResp) {
            this.strongGoodsResp = strongGoodsResp;
        }

        public static class GoodsRespDTO {
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

    }
}
