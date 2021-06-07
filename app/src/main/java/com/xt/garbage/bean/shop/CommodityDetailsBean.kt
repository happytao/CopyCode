package com.xt.garbage.bean.shop

import android.annotation.SuppressLint
import android.os.Parcelable
import java.io.Serializable

data class CommodityDetailsBean(
    var errorCode: Int,
    var errorMsg: String,
    var result: Result,
    var success: Boolean
): Serializable {

    data class Result(
        var categoryNameOne: String,
        var categoryNameTwo: String,
        var goodsCashPrice: Double,
        var goodsCostPrice: Double,
        var goodsDesc: String,
        var goodsDetailList: List<GoodsDetail>,
        var goodsHtmlText: String,
        var goodsLabel: String,
        var goodsName: String,
        var goodsRemake: String,
        var goodsResourceUrl: String,
        var goodsScorePrice: Int,
        var goodsSellType: Int,
        var goodsSubhead: String,
        var id: Long,
        var refCategoryIdOne: Int,
        var refCategoryIdTwo: Int,
        var resourceList: List<Resource>,
        var specList: List<Spec>
    ):Serializable {

        data class GoodsDetail(
            var detailKey: String,
            var detailValue: String
        ):Serializable


        data class Resource(
            var resourceName: String,
            var resourceRemark: String,
            var resourceUrl: String
        ):Serializable

        data class Spec(
            var attrList: List<Attr>,
            var id: Int,
            var specName: String,
            var specOrder: Int
        ):Serializable {

            data class Attr(
                var attrName: String,
                var attrOrder: Int,
                var goodsStore: Int,
                var id: Int,
                var isTrue: Boolean = false,
                var goodsScorePrice : Long,
                var goodsCashPrice : Double
            ):Serializable
        }
    }
}