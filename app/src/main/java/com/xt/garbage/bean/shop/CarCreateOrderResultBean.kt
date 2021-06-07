package com.xt.garbage.bean.shop

data class CarCreateOrderResultBean(
    var errorCode: Int,
    var errorMsg: String,
    var result: ResultBean,
    var success: Boolean
){
    data class ResultBean(
            var parentOrderId:String
    )
}