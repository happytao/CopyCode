package com.xt.garbage.bean.shop

data class AddCarBean(
    var buyNum: String,
    var refGoodsId: String,
    var specReqDTO: Spec
) {
    data class Spec(
        var refAttrName: String,
        var refSpecAttrId: String,
        var refSpecId: String,
        var refSpecName: String
    )
}