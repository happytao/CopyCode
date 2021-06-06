package com.xt.garbage.bean.garbage
import com.google.gson.annotations.SerializedName


/**
 *@author:DIY
 *@date: 2021/6/5
 */
data class GarbageTypeBean(
    var errorCode: Int,
    var errorMsg: String,
    var result: List<Result>,
    var success: Boolean
) {
    data class Result(
        var categoryCashPrice: Double,
        var categoryDesc: String,
        var categoryList: List<CategoryList>,
        var categoryName: String,
        var categoryResourceUrl: String,
        var categoryScorePrice: Int,
        var categoryType: Int,
        var id: Int,
        var superId: Int,
        var isTrue:Boolean = false
    ) {
        data class CategoryList(
            var categoryCashPrice: Double,
            var categoryDesc: String,
            var categoryList: List<Category>,
            var categoryName: String,
            var categoryResourceUrl: String,
            var categoryScorePrice: Int,
            var categoryType: Int,
            var id: Int,
            var superId: Int
        ) {
            data class Category(
                var categoryCashPrice: Double,
                var categoryDesc: String,
                var categoryName: String,
                var categoryResourceUrl: String,
                var categoryScorePrice: Int,
                var categoryType: Int,
                var id: Int,
                var superId: Int
            )
        }
    }
}