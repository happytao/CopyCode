package com.xt.garbage.bean.workmain;

/**
 * @author:DIY
 * @date: 2021/4/8
 */
public class BargainUpBean {
    private double bargainCashPrice;
    private double bargainWeightNumber;
    private int refGarbageCategoryId;
    private boolean isBargain;
    private boolean isAll;

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

    public int getRefGarbageCategoryId() {
        return refGarbageCategoryId;
    }

    public void setRefGarbageCategoryId(int refGarbageCategoryId) {
        this.refGarbageCategoryId = refGarbageCategoryId;
    }

    public boolean isBargain() {
        return isBargain;
    }

    public void setBargain(boolean bargain) {
        isBargain = bargain;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }
}
