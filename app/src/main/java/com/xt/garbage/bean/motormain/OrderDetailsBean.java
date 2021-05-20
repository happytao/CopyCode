package com.xt.garbage.bean.motormain;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/5/20
 */
@lombok.NoArgsConstructor
@lombok.Data
public class OrderDetailsBean {

    /**
     * errorCode : (int32)
     * errorMsg : (string)
     * result : {"createTime":"(date-time)创建时间","id":"(int64)清运订单ID","motormanRefUserId":"(int64)接单司机人员ID","orderArriveTime":"(date-time)订单到达时间","orderCancelExplain":"(string)订单取消说明","orderCancelReason":"(int32)订单取消理由：{false.价格不妥 true.其他原因}","orderCancelTime":"(date-time)订单取消时间","orderFinishTime":"(date-time)订单完成时间","orderReceiveTime":"(date-time)订单接单时间","orderStatus":"(int32)订单状态：{1.（驿站）待接单 2.（司机）已接单 3.（司机）已到达 4.（驿站）待确认 5.（司机）已完成 6.（驿站）取消预约 7.（司机）拒单 8.（订单）超时取消  9.（驿站）订单取消 10.（司机）订单取消}","recycleList":[{"bargainCashPrice":"(number)重新议价（元/kg）","bargainWeightNumber":"(number)重新称重（kg）","categoryCashPrice":"(number)分类现金价（元/kg）","createTime":"(date-time)创建时间","garbageCategoryName":"(string)垃圾分类名称","id":"(int64)订单回收ID","isAll":"(boolean)是否全部：{false：部分 true：全部}","isBargain":"(boolean)是否重新议价：{false：否 true：是}","recylePrice":"(number)回收价（元/kg）","recyleWeightNumber":"(number)回收重量（kg）","refCleanOrderId":"(int64)清运订单回收ID","refGarbageCategoryId":"(int32)垃圾分类ID"}],"refSiteId":"(int64)驿站ID","settleTotalCashPrice":"(number)结算总现金价","siteResp":{"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"},"totalWeight":"(number)合计重量/kg"}
     * success : (boolean)
     */

    private int errorCode;
    private String errorMsg;
    private ResultDTO result;
    private boolean success;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ResultDTO {
        /**
         * createTime : (date-time)创建时间
         * id : (int64)清运订单ID
         * motormanRefUserId : (int64)接单司机人员ID
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
         * siteResp : {"id":"(int64)站点ID","siteContactMobile":"(string)驿站联系人电话","siteContactName":"(string)驿站联系人名称","siteDetailLocation":"(string)站点详细位置","siteName":"(string)驿站名称","siteResourceUrl":"(string)驿站图片资源地址","state":"(int32)站点状态：{1.正常 2.停用 3.关闭}"}
         * totalWeight : (number)合计重量/kg
         */

        private String createTime;
        private long id;
        private long motormanRefUserId;
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
        private SiteRespDTO siteResp;
        private double totalWeight;

        @lombok.NoArgsConstructor
        @lombok.Data
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

            private double id;
            private String siteContactMobile;
            private String siteContactName;
            private String siteDetailLocation;
            private String siteName;
            private String siteResourceUrl;
            private int state;
        }

        @lombok.NoArgsConstructor
        @lombok.Data
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
        }
    }
}
