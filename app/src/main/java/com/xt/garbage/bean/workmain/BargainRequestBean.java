package com.xt.garbage.bean.workmain;

import retrofit2.http.FormUrlEncoded;

/**
 * @author:DIY
 * @date: 2021/4/8
 */
public class BargainRequestBean {
    private double bargainCashPrice;
    private double bargainWeightNumber;
    private boolean isAll;
    private int index;
    private double recyclePrice;

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

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getRecyclePrice() {
        return recyclePrice;
    }

    public void setRecyclePrice(double recyclePrice) {
        this.recyclePrice = recyclePrice;
    }
}
