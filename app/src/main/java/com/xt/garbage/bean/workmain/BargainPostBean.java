package com.xt.garbage.bean.workmain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/8
 */
public class BargainPostBean {
    private List<BargainUpBean> bargainReqList;
    private long refCleanOrderId;

    public List<BargainUpBean> getBargainReqList() {
        return bargainReqList;
    }

    public void setBargainReqList(List<BargainUpBean> bargainReqList) {
        this.bargainReqList = bargainReqList;
    }

    public long getRefCleanOrderId() {
        return refCleanOrderId;
    }

    public void setRefCleanOrderId(long refCleanOrderId) {
        this.refCleanOrderId = refCleanOrderId;
    }
}
