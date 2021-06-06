package com.xt.garbage.bean.shop

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CommodityDetailsBean(
    var errorCode: Int,
    var errorMsg: String,
    var result: Result,
    var success: Boolean
) {
    @SuppressLint("ParcelCreator")
    @Parcelize
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
    ) : Parcelable {
        data class GoodsDetail(
            var detailKey: String,
            var detailValue: String
        )

        data class Resource(
            var resourceName: String,
            var resourceRemark: String,
            var resourceUrl: String
        )

        data class Spec(
            var attrList: List<Attr>,
            var id: Int,
            var specName: String,
            var specOrder: Int
        ) {
            data class Attr(
                var attrName: String,
                var attrOrder: Int,
                var goodsStore: Int,
                var id: Int,
                var isTrue: Boolean = false
            )
        }
    }
}